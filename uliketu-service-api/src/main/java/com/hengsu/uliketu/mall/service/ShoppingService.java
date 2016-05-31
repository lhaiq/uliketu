
package com.hengsu.uliketu.mall.service;

import com.hengsu.uliketu.mall.model.ShoppingModel;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ShoppingService{
	
	public int create(ShoppingModel shoppingModel);
	
	public int createSelective(ShoppingModel shoppingModel);
	
	public ShoppingModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(ShoppingModel shoppingModel);
	
	public int updateByPrimaryKeySelective(ShoppingModel shoppingModel);
	
	public int deleteByPrimaryKey(Long id);

	public void buyGoods(Long id,Long userId);

	public long selectCount(ShoppingModel shoppingModel);

	public List<ShoppingModel> selectPage(ShoppingModel shoppingModel,Pageable pageable);

	public int reduceRepertory(Long id);
	
}