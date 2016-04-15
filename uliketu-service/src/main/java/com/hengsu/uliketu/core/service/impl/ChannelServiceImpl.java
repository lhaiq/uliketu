package com.hengsu.uliketu.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.core.entity.Channel;
import com.hengsu.uliketu.core.repository.ChannelRepository;
import com.hengsu.uliketu.core.model.ChannelModel;
import com.hengsu.uliketu.core.service.ChannelService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private ChannelRepository channelRepo;

	@Transactional
	@Override
	public int create(ChannelModel channelModel) {
		return channelRepo.insert(beanMapper.map(channelModel, Channel.class));
	}

	@Transactional
	@Override
	public int createSelective(ChannelModel channelModel) {
		return channelRepo.insertSelective(beanMapper.map(channelModel, Channel.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return channelRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public ChannelModel findByPrimaryKey(Long id) {
		Channel channel = channelRepo.selectByPrimaryKey(id);
		return beanMapper.map(channel, ChannelModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectCount(ChannelModel channelModel) {
		return channelRepo.selectCount(beanMapper.map(channelModel, Channel.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(ChannelModel channelModel) {
		return channelRepo.updateByPrimaryKey(beanMapper.map(channelModel, Channel.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(ChannelModel channelModel) {
		return channelRepo.updateByPrimaryKeySelective(beanMapper.map(channelModel, Channel.class));
	}

}
