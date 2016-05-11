package com.hengsu.uliketu.mall.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.uliketu.mall.model.GoodsModel")
public class GoodsVO{
	
	private Long id;
	private Long categoryId;
	private String name;
	private String image;
	private Long num;
	private Integer expenseBy;
	private String keywords;
	private Integer type;
	private Integer periods;
	private Integer excutePeriods;
	private Date addTime;
	private Integer sort;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setCategoryId(Long categoryId){
		this.categoryId = categoryId;
	}
	
	public Long getCategoryId(){
		return this.categoryId;
	}
		
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
		
	public void setImage(String image){
		this.image = image;
	}
	
	public String getImage(){
		return this.image;
	}
		
	public void setNum(Long num){
		this.num = num;
	}
	
	public Long getNum(){
		return this.num;
	}
		
	public void setExpenseBy(Integer expenseBy){
		this.expenseBy = expenseBy;
	}
	
	public Integer getExpenseBy(){
		return this.expenseBy;
	}
		
	public void setKeywords(String keywords){
		this.keywords = keywords;
	}
	
	public String getKeywords(){
		return this.keywords;
	}
		
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return this.type;
	}
		
	public void setPeriods(Integer periods){
		this.periods = periods;
	}
	
	public Integer getPeriods(){
		return this.periods;
	}
		
	public void setExcutePeriods(Integer excutePeriods){
		this.excutePeriods = excutePeriods;
	}
	
	public Integer getExcutePeriods(){
		return this.excutePeriods;
	}

		
	public void setAddTime(Date addTime){
		this.addTime = addTime;
	}
	
	public Date getAddTime(){
		return this.addTime;
	}
		
	public void setSort(Integer sort){
		this.sort = sort;
	}
	
	public Integer getSort(){
		return this.sort;
	}
		
		
}