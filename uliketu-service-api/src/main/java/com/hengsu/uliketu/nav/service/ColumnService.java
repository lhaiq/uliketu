
package com.hengsu.uliketu.nav.service;

import com.hengsu.uliketu.nav.model.ColumnModel;

public interface ColumnService{
	
	public int create(ColumnModel columnModel);
	
	public int createSelective(ColumnModel columnModel);
	
	public ColumnModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(ColumnModel columnModel);
	
	public int updateByPrimaryKeySelective(ColumnModel columnModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(ColumnModel columnModel);
	
}