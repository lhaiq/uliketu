
package com.hengsu.uliketu.core.service;

import com.hengsu.uliketu.core.model.ChannelModel;

public interface ChannelService{
	
	public int create(ChannelModel channelModel);
	
	public int createSelective(ChannelModel channelModel);
	
	public ChannelModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(ChannelModel channelModel);
	
	public int updateByPrimaryKeySelective(ChannelModel channelModel);
	
	public int deleteByPrimaryKey(Long id);
	
	public long selectCount(ChannelModel channelModel);
	
}