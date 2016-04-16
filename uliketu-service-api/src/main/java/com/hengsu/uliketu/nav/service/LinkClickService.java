
package com.hengsu.uliketu.nav.service;

import com.hengsu.uliketu.nav.model.LinkClickModel;
import java.util.Date;

public interface LinkClickService{
	
	public int create(LinkClickModel linkClickModel);
	
	public int createSelective(LinkClickModel linkClickModel);
	
	public LinkClickModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(LinkClickModel linkClickModel);
	
	public int updateByPrimaryKeySelective(LinkClickModel linkClickModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(LinkClickModel linkClickModel);
	
}