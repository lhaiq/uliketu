package com.hengsu.uliketu.core.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hengsu.uliketu.core.entity.Channel")
public class ChannelModel{
	
	private Long id;
	private String name;
	private Integer order;
	private Long parent;
		
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
		
	public void setOrder(Integer order){
		this.order = order;
	}
	
	public Integer getOrder(){
		return this.order;
	}
		
	public void setParent(Long parent){
		this.parent = parent;
	}
	
	public Long getParent(){
		return this.parent;
	}
		
		
}