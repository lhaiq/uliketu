
package com.hengsu.uliketu.mall.service;

import com.hengsu.uliketu.mall.model.GoodsModel;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface GoodsService{
	
	public int create(GoodsModel goodsModel);
	
	public int createSelective(GoodsModel goodsModel);
	
	public GoodsModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(GoodsModel goodsModel);
	
	public int updateByPrimaryKeySelective(GoodsModel goodsModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public long selectCount(GoodsModel goodsModel);

	public List<GoodsModel> selectPage(GoodsModel goodsModel,Pageable pageable);
	
}