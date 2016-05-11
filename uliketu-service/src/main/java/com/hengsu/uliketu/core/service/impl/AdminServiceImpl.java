package com.hengsu.uliketu.core.service.impl;

import com.hengsu.uliketu.core.ErrorCode;
import com.hengsu.uliketu.core.RandomUtil;
import com.hengsu.uliketu.core.model.AuthModel;
import com.hengsu.uliketu.core.model.UserModel;
import com.hengsu.uliketu.core.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.core.entity.Admin;
import com.hengsu.uliketu.core.repository.AdminRepository;
import com.hengsu.uliketu.core.model.AdminModel;
import com.hengsu.uliketu.core.service.AdminService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private AdminRepository adminRepo;

    @Transactional
    @Override
    public int create(AdminModel adminModel) {
        return adminRepo.insert(beanMapper.map(adminModel, Admin.class));
    }

    @Transactional
    @Override
    public int createSelective(AdminModel adminModel) {
        return adminRepo.insertSelective(beanMapper.map(adminModel, Admin.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return adminRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public AdminModel findByPrimaryKey(Long id) {
        Admin admin = adminRepo.selectByPrimaryKey(id);
        return beanMapper.map(admin, AdminModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public long selectCount(AdminModel adminModel) {
        return adminRepo.selectCount(beanMapper.map(adminModel, Admin.class));
    }

    @Override
    public List<AdminModel> selectPage(AdminModel adminModel, Pageable pageable) {
        List<Admin> admins = adminRepo.selectPage(beanMapper.map(adminModel, Admin.class), pageable);
        return beanMapper.mapAsList(admins, AdminModel.class);
    }

    @Transactional
    @Override
    public void addAdmin(AdminModel adminModel) {

        //判断用户名是否存在
        AdminModel param = new AdminModel();
        param.setName(adminModel.getName());
        if (CollectionUtils.isNotEmpty(selectPage(param, new PageRequest(0, Integer.MAX_VALUE)))) {
            ErrorCode.throwBusinessException(ErrorCode.ADMIN_NAME_EXISTED);
        }

        adminModel.setRole(AuthModel.ROLE_ADMIN);
        adminModel.setPassword(DigestUtils.md5Hex(adminModel.getPassword()));
        createSelective(adminModel);
    }

    @Override
    public AdminModel adminLogin(AdminModel adminModel) {
        adminModel.setPassword(DigestUtils.md5Hex(adminModel.getPassword()));
        List<AdminModel> adminModels = selectPage(adminModel, new PageRequest(0, Integer.MAX_VALUE));
        if (CollectionUtils.isEmpty(adminModels)) {
            ErrorCode.throwBusinessException(ErrorCode.ADMIN_LOGIN_ERROR);
        }

        adminModel = adminModels.get(0);
        String authCode = RandomUtil.generateAuthToken();
        adminModel.setAuthCode(authCode);
        return adminModels.get(0);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(AdminModel adminModel) {
        return adminRepo.updateByPrimaryKey(beanMapper.map(adminModel, Admin.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(AdminModel adminModel) {
        return adminRepo.updateByPrimaryKeySelective(beanMapper.map(adminModel, Admin.class));
    }

}
