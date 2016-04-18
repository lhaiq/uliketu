package com.hengsu.uliketu.mall.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.uliketu.mall.model.WinnerModel")
public class WinnerVO{
	
	private Long id;
	private Long goodsId;
	private Long shoppingId;
	private Long userId;
	private Date createTime;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setGoodsId(Long goodsId){
		this.goodsId = goodsId;
	}
	
	public Long getGoodsId(){
		return this.goodsId;
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
		
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return this.createTime;
	}
		
		
}