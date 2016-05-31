package com.hengsu.uliketu.core.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@MapClass("com.hengsu.uliketu.core.model.UserModel")
public class CertifieUserVO {

	private Long id;

	@NotEmpty
	private String realName;

	@NotNull
	private Integer gender;

	@NotNull
	private Integer age;

	@NotEmpty
	private String address;

	@NotNull
	private Integer accountType;

	@NotEmpty
	private String accountName;

	@NotEmpty
	private String accountId;

	@NotEmpty
	private String idnumber;

	@NotEmpty
	private String idphoto;
	private String avatar;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getIdphoto() {
		return idphoto;
	}

	public void setIdphoto(String idphoto) {
		this.idphoto = idphoto;
	}
}