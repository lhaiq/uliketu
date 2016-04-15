
package com.hengsu.uliketu.core.service;

import com.hengsu.uliketu.core.model.AdvertisementModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdvertisementService{
	
	public int create(AdvertisementModel advertisementModel);
	
	public int createSelective(AdvertisementModel advertisementModel);
	
	public AdvertisementModel findByPrimaryKey(Integer id);
	
	public int updateByPrimaryKey(AdvertisementModel advertisementModel);
	
	public int updateByPrimaryKeySelective(AdvertisementModel advertisementModel);
	
	public int deleteByPrimaryKey(Integer id);
	
	public long selectCount(AdvertisementModel advertisementModel);

	public List<AdvertisementModel> selectPage(AdvertisementModel advertisementModel,Pageable pageable);
	
}