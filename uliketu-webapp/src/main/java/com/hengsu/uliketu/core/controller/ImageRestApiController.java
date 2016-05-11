package com.hengsu.uliketu.core.controller;

import com.hengsu.uliketu.core.annotation.IgnoreAuth;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.uliketu.core.service.ImageService;
import com.hengsu.uliketu.core.model.ImageModel;
import com.hengsu.uliketu.core.vo.ImageVO;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@RestApiController
@RequestMapping("/uliketu")
public class ImageRestApiController {

    private final Logger logger = LoggerFactory.getLogger(ImageRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private ImageService imageService;

    @IgnoreAuth
    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public void getImageById(@PathVariable Long id,
                             HttpServletResponse response) {
        ImageModel imageModel = imageService.findById(id);
        response.setContentType(imageModel.getContentType());
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            IOUtils.copy(imageModel.getContent(), outputStream);

        } catch (IOException e) {
            logger.error("get image error", e);
        } finally {
            if (null != outputStream) {
                IOUtils.closeQuietly(outputStream);
            }
        }


    }

    @IgnoreAuth
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<List<Long>>> createImage(@RequestParam("file") CommonsMultipartFile[] files
    ) {

        List<Long> imageIds = new ArrayList<>();
        for (CommonsMultipartFile file : files) {
            ImageModel imageModel = new ImageModel();
            imageModel.setContentType(file.getContentType());
            imageModel.setFilename(file.getOriginalFilename());
            imageModel.setLength(file.getSize());
            try {
                imageModel.setContent(file.getInputStream());
            } catch (IOException e) {
                logger.error("upload error" + file.getName());
                continue;
            }
            Long imageId = imageService.uploadImage(imageModel);
            imageIds.add(imageId);

        }

        ResponseEnvelope<List<Long>> responseEnv = new ResponseEnvelope<>(imageIds, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }
}
