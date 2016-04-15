package com.hengsu.uliketu.core.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hengsu.uliketu.core.entity.Website")
public class WebsiteModel{
	
	private Long id;
	private String name;
	private String keywords;
	private String icp;
	private String version;
	private String description;
	private String statisticCode;
	private String domain;
	private Integer status;
	private Integer type;
		
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
		
	public void setKeywords(String keywords){
		this.keywords = keywords;
	}
	
	public String getKeywords(){
		return this.keywords;
	}
		
	public void setIcp(String icp){
		this.icp = icp;
	}
	
	public String getIcp(){
		return this.icp;
	}
		
	public void setVersion(String version){
		this.version = version;
	}
	
	public String getVersion(){
		return this.version;
	}
		
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return this.description;
	}
		
	public void setStatisticCode(String statisticCode){
		this.statisticCode = statisticCode;
	}
	
	public String getStatisticCode(){
		return this.statisticCode;
	}
		
	public void setDomain(String domain){
		this.domain = domain;
	}
	
	public String getDomain(){
		return this.domain;
	}
		
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return this.status;
	}
		
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return this.type;
	}
		
		
}