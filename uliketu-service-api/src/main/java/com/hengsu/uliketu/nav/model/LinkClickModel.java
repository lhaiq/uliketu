package com.hengsu.uliketu.nav.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.uliketu.nav.entity.LinkClick")
public class LinkClickModel{
	
	private Long id;
	private Long userId;
	private Long linkId;
	private Date clickTime;
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
		
	public void setLinkId(Long linkId){
		this.linkId = linkId;
	}
	
	public Long getLinkId(){
		return this.linkId;
	}
		
	public void setClickTime(Date clickTime){
		this.clickTime = clickTime;
	}
	
	public Date getClickTime(){
		return this.clickTime;
	}
		
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return this.status;
	}
		
		
}