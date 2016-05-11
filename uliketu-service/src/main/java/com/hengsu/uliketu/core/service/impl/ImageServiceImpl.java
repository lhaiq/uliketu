package com.hengsu.uliketu.core.service.impl;

import static com.hengsu.uliketu.core.ErrorCode.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.core.entity.Image;
import com.hengsu.uliketu.core.repository.ImageRepository;
import com.hengsu.uliketu.core.model.ImageModel;
import com.hengsu.uliketu.core.service.ImageService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {


    @Value("${image.directory}")
    public String imageDirectory;

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private ImageRepository imageRepo;

    @Transactional
    @Override
    public int create(ImageModel imageModel) {
        return imageRepo.insert(beanMapper.map(imageModel, Image.class));
    }

    @Transactional
    @Override
    public int createSelective(ImageModel imageModel) {
        Image image = beanMapper.map(imageModel, Image.class);
        int ret = imageRepo.insertSelective(image);
        imageModel.setId(image.getId());
        return ret;
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return imageRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public ImageModel findByPrimaryKey(Long id) {
        Image image = imageRepo.selectByPrimaryKey(id);
        return beanMapper.map(image, ImageModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public long selectCount(ImageModel imageModel) {
        return imageRepo.selectCount(beanMapper.map(imageModel, Image.class));
    }

    @Override
    public long uploadImage(ImageModel imageModel) {
        String path = UUID.randomUUID().toString();
        imageModel.setPath(path);
        try {
            IOUtils.copy(imageModel.getContent(), FileUtils.openOutputStream(new File(imageDirectory + "/" + path)));
        } catch (IOException e) {
            throwBusinessException(UPLOAD_IMAGE_ERROR);
        }
        imageModel.setTime(new Date());
        createSelective(imageModel);
        return imageModel.getId();
    }

    @Transactional
    @Override
    public ImageModel findById(Long id) {
        ImageModel imageModel = findByPrimaryKey(id);
        InputStream content = null;
        try {
            content = FileUtils.openInputStream(new File(imageDirectory + "/" + imageModel.getPath()));
        } catch (IOException e) {
            throwBusinessException(GET_IMAGE_ERROR);
        }
        imageModel.setContent(content);
        return imageModel;
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(ImageModel imageModel) {
        return imageRepo.updateByPrimaryKey(beanMapper.map(imageModel, Image.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(ImageModel imageModel) {
        return imageRepo.updateByPrimaryKeySelective(beanMapper.map(imageModel, Image.class));
    }

}
