package com.hengsu.uliketu.core.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
import java.util.Map;

@MapClass("com.hengsu.uliketu.core.model.UserModel")
public class UserVO{

	private Long id;
	private String username;
	private String mail;
	private String phone;
	private String avatar;
	private Long balance;
	private Long blockBalance;
	private String idnumber;
	private String idphoto;
	private Integer blackStatus;
	private Integer certifie;
	private Date registerTime;
	private String nickname;
	private String authCode;
	private String randomId;
	private Map<String,String> answers;

	private String realName;

	private Integer gender;

	private Integer age;

	private String address;

	private Integer accountType;

	private String accountName;

	private String accountId;

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Integer getBlackStatus() {
		return blackStatus;
	}

	public void setBlackStatus(Integer blackStatus) {
		this.blackStatus = blackStatus;
	}

	public Long getBlockBalance() {
		return blockBalance;
	}

	public void setBlockBalance(Long blockBalance) {
		this.blockBalance = blockBalance;
	}

	public Integer getCertifie() {
		return certifie;
	}

	public void setCertifie(Integer certifie) {
		this.certifie = certifie;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRandomId() {
		return randomId;
	}

	public void setRandomId(String randomId) {
		this.randomId = randomId;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public void setAnswers(Map<String, String> answers) {
		this.answers = answers;
	}

	public Map<String, String> getAnswers() {
		return answers;
	}
}