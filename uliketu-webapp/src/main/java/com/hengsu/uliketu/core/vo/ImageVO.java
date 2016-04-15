package com.hengsu.uliketu.core.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.uliketu.core.model.ImageModel")
public class ImageVO{
	
	private Long id;
	private String name;
	private Date uploadTime;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
		
	public void setUploadTime(Date uploadTime){
		this.uploadTime = uploadTime;
	}
	
	public Date getUploadTime(){
		return this.uploadTime;
	}
		
		
}