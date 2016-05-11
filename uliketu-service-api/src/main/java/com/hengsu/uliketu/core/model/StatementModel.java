package com.hengsu.uliketu.core.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.uliketu.core.entity.Statement")
public class StatementModel{

	public static final int RECOMMEND=0;
	public static final int RECOMMEND_RATE=1;
	public static final int NAV=2;
	public static final int GOODS=3;
	public static final int CASH=4;

	private Integer id;
	private Long userid;
	private Long num;
	private Date createTime;
	private Integer type;
	private String description;
		
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
		
	public void setUserid(Long userid){
		this.userid = userid;
	}
	
	public Long getUserid(){
		return this.userid;
	}
		
	public void setNum(Long num){
		this.num = num;
	}
	
	public Long getNum(){
		return this.num;
	}
		
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return this.createTime;
	}
		
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return this.type;
	}
		
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return this.description;
	}
		
		
}