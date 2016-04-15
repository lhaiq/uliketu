package com.hengsu.uliketu.core.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hengsu.uliketu.core.entity.Conf")
public class ConfModel{
	
	private String confKey;
	private String confValue;
		
	public void setConfKey(String confKey){
		this.confKey = confKey;
	}
	
	public String getConfKey(){
		return this.confKey;
	}
		
	public void setConfValue(String confValue){
		this.confValue = confValue;
	}
	
	public String getConfValue(){
		return this.confValue;
	}
		
		
}