
package com.hengsu.uliketu.core.service;

import com.hengsu.uliketu.core.model.LinkModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LinkService{
	
	public int create(LinkModel linkModel);
	
	public int createSelective(LinkModel linkModel);
	
	public LinkModel findByPrimaryKey(Integer id);
	
	public int updateByPrimaryKey(LinkModel linkModel);
	
	public int updateByPrimaryKeySelective(LinkModel linkModel);
	
	public int deleteByPrimaryKey(Integer id);
	
	public long selectCount(LinkModel linkModel);

	public List<LinkModel> selectPage(LinkModel linkModel,Pageable pageable);
	
}