
package com.hengsu.uliketu.mall.service;

import com.hengsu.uliketu.mall.model.ShoppingModel;
import java.util.Date;

public interface ShoppingService{
	
	public int create(ShoppingModel shoppingModel);
	
	public int createSelective(ShoppingModel shoppingModel);
	
	public ShoppingModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(ShoppingModel shoppingModel);
	
	public int updateByPrimaryKeySelective(ShoppingModel shoppingModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(ShoppingModel shoppingModel);
	
}