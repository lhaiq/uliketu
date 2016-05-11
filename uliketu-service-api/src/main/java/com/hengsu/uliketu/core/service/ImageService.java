
package com.hengsu.uliketu.core.service;

import com.hengsu.uliketu.core.model.ImageModel;
import java.util.Date;

public interface ImageService{
	
	public int create(ImageModel imageModel);
	
	public int createSelective(ImageModel imageModel);
	
	public ImageModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(ImageModel imageModel);
	
	public int updateByPrimaryKeySelective(ImageModel imageModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public long selectCount(ImageModel imageModel);

	public long uploadImage(ImageModel imageModel);

	public ImageModel findById(Long id);
	
}