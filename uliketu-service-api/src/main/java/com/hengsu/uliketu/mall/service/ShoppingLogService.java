
package com.hengsu.uliketu.mall.service;

import com.hengsu.uliketu.mall.model.ShoppingLogModel;
import com.hengsu.uliketu.mall.model.ShoppingModel;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ShoppingLogService{
	
	public int create(ShoppingLogModel shoppingLogModel);
	
	public int createSelective(ShoppingLogModel shoppingLogModel);
	
	public ShoppingLogModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(ShoppingLogModel shoppingLogModel);
	
	public int updateByPrimaryKeySelective(ShoppingLogModel shoppingLogModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public long selectCount(ShoppingLogModel shoppingLogModel);

	public void lottery(ShoppingModel shoppingModel);

	public List<ShoppingLogModel> selectPage(ShoppingLogModel shoppingLogModel,Pageable pageable);
	
}