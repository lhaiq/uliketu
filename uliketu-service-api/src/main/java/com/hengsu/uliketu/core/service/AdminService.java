
package com.hengsu.uliketu.core.service;

import com.hengsu.uliketu.core.model.AdminModel;

public interface AdminService{
	
	public int create(AdminModel adminModel);
	
	public int createSelective(AdminModel adminModel);
	
	public AdminModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(AdminModel adminModel);
	
	public int updateByPrimaryKeySelective(AdminModel adminModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public long selectCount(AdminModel adminModel);
	
}