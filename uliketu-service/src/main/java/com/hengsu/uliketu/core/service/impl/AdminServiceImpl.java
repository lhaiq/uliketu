package com.hengsu.uliketu.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.core.entity.Admin;
import com.hengsu.uliketu.core.repository.AdminRepository;
import com.hengsu.uliketu.core.model.AdminModel;
import com.hengsu.uliketu.core.service.AdminService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

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
	public int selectCount(AdminModel adminModel) {
		return adminRepo.selectCount(beanMapper.map(adminModel, Admin.class));
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
