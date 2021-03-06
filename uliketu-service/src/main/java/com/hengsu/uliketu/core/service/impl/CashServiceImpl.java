package com.hengsu.uliketu.core.service.impl;

import com.hengsu.uliketu.core.Consts;
import com.hengsu.uliketu.core.model.StatementModel;
import com.hengsu.uliketu.core.model.UserModel;
import com.hengsu.uliketu.core.service.ConfService;
import com.hengsu.uliketu.core.service.MessageService;
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

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CashServiceImpl implements CashService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CashRepository cashRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private ConfService confService;

    @Autowired
    private MessageService messageService;

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
    public CashModel addCash(CashModel cashModel) {

        //每月25日后不能提取
        if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) > 25) {
            throwBusinessException(DATE_CANNOT_CASH);
        }

        //最低提现标准
        if (cashModel.getBalance() < confService.findLong(Consts.VIRTUAL_COIN_EXCHANGE_MINIMUM_NUM)) {
            throwBusinessException(BALANCE_NOT_TO_MINIMUM);
        }

        //check该用户余额是否足够
        UserModel userModel = userService.findByPrimaryKey(cashModel.getUserid());
        if (userModel.getBalance() < cashModel.getBalance()) {
            throwBusinessException(BALANCE_NOT_ENOUGH);
        }

        //锁定用户的提现余额
        userService.addBlockBalance(userModel.getId(), cashModel.getBalance());
        userService.addBalance(userModel.getId(), cashModel.getBalance() * (-1), StatementModel.CASH, "提现锁定");

        //配置文件
        double poundage = confService.findDouble(Consts.VIRTUAL_COIN_EXCHANGE_POUNDAGE);
        double rate = confService.findDouble(Consts.VIRTUAL_COIN_EXCHNAGE_RATE_WITH_RENMIN);

        //计算提现金额 取两位数字
        double money = cashModel.getBalance() / rate - (1 - poundage / 100);
        BigDecimal b = new BigDecimal(money);
        money = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        cashModel.setPoundage(poundage);
        cashModel.setRate(rate);
        cashModel.setMoney(money);

        //创建提现申请
        cashModel.setApplyTime(new Date());
        createSelective(cashModel);
        return cashModel;
    }

    @Transactional
    @Override
    public void agreeCash(Long id) {
        CashModel cashModel = findByPrimaryKey(id);

        //减少用户锁定余额
        userService.addBlockBalance(cashModel.getUserid(), cashModel.getBalance() * (-1));

        //更新状态
        CashModel param = new CashModel();
        param.setId(id);
        param.setFinishTime(new Date());
        param.setStatus(CashModel.STATUS_SUCCESS);
        updateByPrimaryKeySelective(param);

        //通知用户
        messageService.addMessage(Consts.AGREE_CASH, cashModel.getUserid());

    }

    @Transactional
    @Override
    public void refuseCash(Long id) {

        CashModel cashModel = findByPrimaryKey(id);

        //减少用户锁定余额
        userService.addBlockBalance(cashModel.getUserid(), cashModel.getBalance() * (-1));

        //将虚拟币返回用户账户
        userService.addBalance(cashModel.getUserid(), cashModel.getBalance(), StatementModel.CASH, "提现失败");

        //更新状态
        CashModel param = new CashModel();
        param.setId(id);
        param.setFinishTime(new Date());
        param.setStatus(CashModel.STATUS_FAILURE);
        updateByPrimaryKeySelective(param);

        //通知用户
        messageService.addMessage(Consts.REFUSH_CASH, cashModel.getUserid());
    }

    @Override
    public List<CashModel> selectPage(CashModel cashModel, Pageable pageable) {
        List<Cash> cashs = cashRepo.selectPage(beanMapper.map(cashModel, Cash.class), pageable);
        return beanMapper.mapAsList(cashs, CashModel.class);
    }

    @Override
    public Map selectGroupByStatus(Date startTime, Date endTime) {
        return cashRepo.selectGroupByStatus(startTime, endTime);
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
