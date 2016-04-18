
package com.hengsu.uliketu.nav.service;

import com.hengsu.uliketu.nav.model.ColumnModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ColumnService{
	
	public int create(ColumnModel columnModel);
	
	public int createSelective(ColumnModel columnModel);
	
	public ColumnModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(ColumnModel columnModel);
	
	public int updateByPrimaryKeySelective(ColumnModel columnModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public long selectCount(ColumnModel columnModel);

	public List<ColumnModel> selectPage(ColumnModel columnModel,Pageable pageable);
	
}