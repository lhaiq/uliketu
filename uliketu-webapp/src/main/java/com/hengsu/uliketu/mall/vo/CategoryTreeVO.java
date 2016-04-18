package com.hengsu.uliketu.mall.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

import java.util.List;

@MapClass("com.hengsu.uliketu.mall.model.CategoryModel")
public class CategoryTreeVO {
	
	private Long id;
	private String name;
	private String description;
	private Long parentId;
	private Integer sort;
	private List<CategoryTreeVO> children;
		
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
		
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return this.description;
	}
		
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}
	
	public Long getParentId(){
		return this.parentId;
	}


	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSort() {
		return sort;
	}

	public void setChildren(List<CategoryTreeVO> children) {
		this.children = children;
	}

	public List<CategoryTreeVO> getChildren() {
		return children;
	}
}