
package com.hengsu.uliketu.core.service;

import com.hengsu.uliketu.core.model.ConfModel;

public interface ConfService{

	
	public ConfModel findByPrimaryKey(String key);
	
	public int updateByPrimaryKey(ConfModel confModel);
	
	public int updateByPrimaryKeySelective(ConfModel confModel);
	

}