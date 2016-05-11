package com.hengsu.uliketu.core.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hengsu.uliketu.core.model.AdminModel")
public class UpdateAdminPassVO {
	
	private Long id;
	private String oldPassword;
	private String newPassword;

	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}
}