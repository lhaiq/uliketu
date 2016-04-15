package com.hengsu.uliketu.core.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hengsu.uliketu.core.model.AdvertisementModel")
public class AdvertisementVO{
	
	private Integer id;
	private String name;
	private String url;
	private String image;
	private Integer sort;
		
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
		
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
		
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return this.url;
	}
		
	public void setImage(String image){
		this.image = image;
	}
	
	public String getImage(){
		return this.image;
	}
		
	public void setSort(Integer sort){
		this.sort = sort;
	}
	
	public Integer getSort(){
		return this.sort;
	}
		
		
}