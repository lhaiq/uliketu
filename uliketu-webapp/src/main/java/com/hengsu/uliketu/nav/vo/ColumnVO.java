package com.hengsu.uliketu.nav.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hengsu.uliketu.nav.model.ColumnModel")
public class ColumnVO{
	
	private Long id;
	private String name;
	private Integer type;
	private Long parentId;
	private Integer sort;
		
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
		
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return this.type;
	}
		
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}
	
	public Long getParentId(){
		return this.parentId;
	}
		
	public void setSort(Integer sort){
		this.sort = sort;
	}
	
	public Integer getSort(){
		return this.sort;
	}
		
		
}