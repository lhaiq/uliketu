package com.hengsu.uliketu.core.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.uliketu.core.entity.Cash")
public class CashModel{

	public static final int STATUS_APPLY=0;
	public static final int STATUS_SUCCESS=1;
	public static final int STATUS_FAILURE=2;

	private Long id;
	private Long userid;
	private Double balance;
	private Double money;
	private Double rate;
	private Double poundage;
	private Date applyTime;
	private Date finishTime;
	private Integer status;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setUserid(Long userid){
		this.userid = userid;
	}
	
	public Long getUserid(){
		return this.userid;
	}
		
	public void setBalance(Double balance){
		this.balance = balance;
	}
	
	public Double getBalance(){
		return this.balance;
	}
		
	public void setMoney(Double money){
		this.money = money;
	}
	
	public Double getMoney(){
		return this.money;
	}
		
	public void setRate(Double rate){
		this.rate = rate;
	}
	
	public Double getRate(){
		return this.rate;
	}
		
	public void setPoundage(Double poundage){
		this.poundage = poundage;
	}
	
	public Double getPoundage(){
		return this.poundage;
	}
		
	public void setApplyTime(Date applyTime){
		this.applyTime = applyTime;
	}
	
	public Date getApplyTime(){
		return this.applyTime;
	}
		
	public void setFinishTime(Date finishTime){
		this.finishTime = finishTime;
	}
	
	public Date getFinishTime(){
		return this.finishTime;
	}
		
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return this.status;
	}
		
		
}