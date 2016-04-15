
package com.hengsu.uliketu.core.service;

import com.hengsu.uliketu.core.model.StatementModel;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface StatementService{
	
	public int create(StatementModel statementModel);
	
	public int createSelective(StatementModel statementModel);
	
	public StatementModel findByPrimaryKey(Integer id);
	
	public int updateByPrimaryKey(StatementModel statementModel);
	
	public int updateByPrimaryKeySelective(StatementModel statementModel);
	
	public int deleteByPrimaryKey(Integer id);
	
	public long selectCount(StatementModel statementModel);

	public List<StatementModel> selectPage(StatementModel statementModel,Pageable pageable);
	
}