
package com.hengsu.uliketu.mall.service;

import com.hengsu.uliketu.mall.model.ShoppingLogModel;
import java.util.Date;

public interface ShoppingLogService{
	
	public int create(ShoppingLogModel shoppingLogModel);
	
	public int createSelective(ShoppingLogModel shoppingLogModel);
	
	public ShoppingLogModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(ShoppingLogModel shoppingLogModel);
	
	public int updateByPrimaryKeySelective(ShoppingLogModel shoppingLogModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(ShoppingLogModel shoppingLogModel);
	
}