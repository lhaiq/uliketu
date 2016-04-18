package com.hengsu.uliketu.mall.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.uliketu.mall.entity.ShoppingLog")
public class ShoppingLogModel{
	
	private Long id;
	private Long shoppingId;
	private Long userId;
	private Date shoppingTime;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setShoppingId(Long shoppingId){
		this.shoppingId = shoppingId;
	}
	
	public Long getShoppingId(){
		return this.shoppingId;
	}
		
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return this.userId;
	}
		
	public void setShoppingTime(Date shoppingTime){
		this.shoppingTime = shoppingTime;
	}
	
	public Date getShoppingTime(){
		return this.shoppingTime;
	}
		
		
}