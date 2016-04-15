
package com.hengsu.uliketu.core.service;

import com.hengsu.uliketu.core.model.WebsiteModel;

public interface WebsiteService{
	
	public int create(WebsiteModel websiteModel);
	
	public int createSelective(WebsiteModel websiteModel);
	
	public WebsiteModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(WebsiteModel websiteModel);
	
	public int updateByPrimaryKeySelective(WebsiteModel websiteModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(WebsiteModel websiteModel);
	
}