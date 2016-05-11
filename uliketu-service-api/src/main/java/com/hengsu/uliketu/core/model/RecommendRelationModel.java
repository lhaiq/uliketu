package com.hengsu.uliketu.core.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.uliketu.core.entity.RecommendRelation")
public class RecommendRelationModel{

	public static final Integer RECOMMEDN_STATUS_USED=1;

	private Long id;
	private Long userId;
	private Long recommendId;
	private Date createTime;
	private Long num;
	private Integer status;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return this.userId;
	}
		
	public void setRecommendId(Long recommendId){
		this.recommendId = recommendId;
	}
	
	public Long getRecommendId(){
		return this.recommendId;
	}
		
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return this.createTime;
	}
		
	public void setNum(Long num){
		this.num = num;
	}
	
	public Long getNum(){
		return this.num;
	}
		
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return this.status;
	}
		
		
}