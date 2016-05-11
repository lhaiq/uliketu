
package com.hengsu.uliketu.core.service;

import com.hengsu.uliketu.core.model.RecommendRelationModel;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface RecommendRelationService{
	
	public int create(RecommendRelationModel recommendRelationModel);
	
	public int createSelective(RecommendRelationModel recommendRelationModel);
	
	public RecommendRelationModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(RecommendRelationModel recommendRelationModel);
	
	public int updateByPrimaryKeySelective(RecommendRelationModel recommendRelationModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public long selectCount(RecommendRelationModel recommendRelationModel);

	public Long recommendMe(Long userId);

	public List<RecommendRelationModel> selectPage(RecommendRelationModel recommendRelationModel,Pageable pageable);
	
}