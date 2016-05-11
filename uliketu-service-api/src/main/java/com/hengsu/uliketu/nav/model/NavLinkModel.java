package com.hengsu.uliketu.nav.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.uliketu.nav.entity.NavLink")
public class NavLinkModel{

	public static final int LINK_TYPE_CPC=0;
	public static final int LINK_TYPE_CPA=1;
	public static final int LINK_TYPE_CPS=2;
	public static final int LINK_REPEAT_FALSE=0;
	public static final int LINK_REPEAT_TRUE=1;


	private Long id;
	private String name;
	private String url;
	private Integer type;
	private Long num;
	private Integer isRepeat;
	private Long columnId;
	private Integer sort;
	private Date addTime;
		
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
		
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return this.url;
	}
		
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return this.type;
	}
		
	public void setNum(Long num){
		this.num = num;
	}
	
	public Long getNum(){
		return this.num;
	}
		
	public void setIsRepeat(Integer isRepeat){
		this.isRepeat = isRepeat;
	}
	
	public Integer getIsRepeat(){
		return this.isRepeat;
	}
		
	public void setColumnId(Long columnId){
		this.columnId = columnId;
	}
	
	public Long getColumnId(){
		return this.columnId;
	}
		
	public void setSort(Integer sort){
		this.sort = sort;
	}
	
	public Integer getSort(){
		return this.sort;
	}
		
	public void setAddTime(Date addTime){
		this.addTime = addTime;
	}
	
	public Date getAddTime(){
		return this.addTime;
	}
		
		
}