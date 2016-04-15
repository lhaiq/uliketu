package com.hengsu.uliketu.core.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.uliketu.core.entity.Message")
public class MessageModel{

	//未读
	public static final int unread=0;
	//已读
	public static final int readed=1;

	private Long id;
	private String content;
	private Long userId;
	private Date createTime;
	private Integer isread;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return this.content;
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
		
	public void setIsread(Integer isread){
		this.isread = isread;
	}
	
	public Integer getIsread(){
		return this.isread;
	}
		
		
}