
package com.hengsu.uliketu.core.service;

import com.hengsu.uliketu.core.model.RecommendRelationModel;
import java.util.Date;

public interface RecommendRelationService{
	
	public int create(RecommendRelationModel recommendRelationModel);
	
	public int createSelective(RecommendRelationModel recommendRelationModel);
	
	public RecommendRelationModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(RecommendRelationModel recommendRelationModel);
	
	public int updateByPrimaryKeySelective(RecommendRelationModel recommendRelationModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(RecommendRelationModel recommendRelationModel);
	
}