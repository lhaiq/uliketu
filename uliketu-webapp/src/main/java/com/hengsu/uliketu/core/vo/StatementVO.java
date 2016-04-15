package com.hengsu.uliketu.core.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.uliketu.core.model.StatementModel")
public class StatementVO{
	
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