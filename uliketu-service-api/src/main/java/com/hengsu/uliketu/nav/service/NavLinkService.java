
package com.hengsu.uliketu.nav.service;

import com.hengsu.uliketu.nav.model.NavLinkModel;
import java.util.Date;

public interface NavLinkService{
	
	public int create(NavLinkModel navLinkModel);
	
	public int createSelective(NavLinkModel navLinkModel);
	
	public NavLinkModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(NavLinkModel navLinkModel);
	
	public int updateByPrimaryKeySelective(NavLinkModel navLinkModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(NavLinkModel navLinkModel);
	
}