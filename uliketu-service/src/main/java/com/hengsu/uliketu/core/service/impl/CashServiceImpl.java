package com.hengsu.uliketu.core.service.impl;

import com.hengsu.uliketu.core.model.UserModel;
import com.hengsu.uliketu.core.service.UserService;

import static com.hengsu.uliketu.core.ErrorCode.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.core.entity.Cash;
import com.hengsu.uliketu.core.repository.CashRepository;
import com.hengsu.uliketu.core.model.CashModel;
import com.hengsu.uliketu.core.service.CashService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CashServiceImpl implements CashService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CashRepository cashRepo;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public int create(CashModel cashModel) {
        return cashRepo.insert(beanMapper.map(cashModel, Cash.class));
    }

    @Transactional
    @Override
    public int createSelective(CashModel cashModel) {
        return cashRepo.insertSelective(beanMapper.map(cashModel, Cash.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return cashRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public CashModel findByPrimaryKey(Long id) {
        Cash cash = cashRepo.selectByPrimaryKey(id);
        return beanMapper.map(cash, CashModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public long selectCount(CashModel cashModel) {
        return cashRepo.selectCount(beanMapper.map(cashModel, Cash.class));
    }

    @Transactional
    @Override
    public void addCash(CashModel cashModel) {

        //每月25日后不能提取
        if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) > 25) {
            throwBusinessException(DATE_CANNOT_CASH);
        }

        //check该用户余额是否足够
        UserModel userModel = userService.findByPrimaryKey(cashModel.getUserid());
        if (userModel.getBalance() < cashModel.getBalance()) {
            throwBusinessException(BALANCE_NOT_ENOUGH);
        }

        //锁定用户的提现余额
        userService.addBlockBalance(cashModel.getBalance());
        userService.addBalance(cashModel.getBalance() * (-1));

        //创建提现申请
        cashModel.setApplyTime(new Date());
        createSelective(cashModel);
    }

    @Transactional
    @Override
    public void agreeCash(Long id) {
        CashModel cashModel = findByPrimaryKey(id);

        //减少用户锁定余额
        userService.addBlockBalance(cashModel.getBalance() * (-1));

        //更新状态
        CashModel param = new CashModel();
        param.setId(id);
        param.setFinishTime(new Date());
        param.setStatus(CashModel.STATUS_SUCCESS);
        updateByPrimaryKeySelective(param);
    }

    @Transactional
    @Override
    public void refuseCash(Long id) {

        CashModel cashModel = findByPrimaryKey(id);

        //减少用户锁定余额
        userService.addBlockBalance(cashModel.getBalance() * (-1));

        //将虚拟币返回用户账户
        userService.addBalance(cashModel.getBalance());

        //更新状态
        CashModel param = new CashModel();
        param.setId(id);
        param.setFinishTime(new Date());
        param.setStatus(CashModel.STATUS_FAILURE);
        updateByPrimaryKeySelective(param);
    }

    @Override
    public List<CashModel> selectPage(CashModel cashModel, Pageable pageable) {
        List<Cash> cashs = cashRepo.selectPage(beanMapper.map(cashModel, Cash.class), pageable);
        return beanMapper.mapAsList(cashs, CashModel.class);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(CashModel cashModel) {
        return cashRepo.updateByPrimaryKey(beanMapper.map(cashModel, Cash.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(CashModel cashModel) {
        return cashRepo.updateByPrimaryKeySelective(beanMapper.map(cashModel, Cash.class));
    }


}
