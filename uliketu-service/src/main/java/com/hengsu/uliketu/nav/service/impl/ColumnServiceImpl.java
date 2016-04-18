package com.hengsu.uliketu.nav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.nav.entity.Column;
import com.hengsu.uliketu.nav.repository.ColumnRepository;
import com.hengsu.uliketu.nav.model.ColumnModel;
import com.hengsu.uliketu.nav.service.ColumnService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class ColumnServiceImpl implements ColumnService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private ColumnRepository columnRepo;

	@Transactional
	@Override
	public int create(ColumnModel columnModel) {
		return columnRepo.insert(beanMapper.map(columnModel, Column.class));
	}

	@Transactional
	@Override
	public int createSelective(ColumnModel columnModel) {
		return columnRepo.insertSelective(beanMapper.map(columnModel, Column.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return columnRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public ColumnModel findByPrimaryKey(Long id) {
		Column column = columnRepo.selectByPrimaryKey(id);
		return beanMapper.map(column, ColumnModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(ColumnModel columnModel) {
		return columnRepo.selectCount(beanMapper.map(columnModel, Column.class));
	}

	@Override
	public List<ColumnModel> selectPage(ColumnModel columnModel, Pageable pageable) {
		Column column = beanMapper.map(columnModel,Column.class);
		List<Column> columns = columnRepo.selectPage(column,pageable);
		return beanMapper.mapAsList(columns,ColumnModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(ColumnModel columnModel) {
		return columnRepo.updateByPrimaryKey(beanMapper.map(columnModel, Column.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(ColumnModel columnModel) {
		return columnRepo.updateByPrimaryKeySelective(beanMapper.map(columnModel, Column.class));
	}

}
