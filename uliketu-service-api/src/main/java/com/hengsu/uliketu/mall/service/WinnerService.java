
package com.hengsu.uliketu.mall.service;

import com.hengsu.uliketu.mall.model.WinnerModel;
import java.util.Date;

public interface WinnerService{
	
	public int create(WinnerModel winnerModel);
	
	public int createSelective(WinnerModel winnerModel);
	
	public WinnerModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(WinnerModel winnerModel);
	
	public int updateByPrimaryKeySelective(WinnerModel winnerModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public int selectCount(WinnerModel winnerModel);
	
}