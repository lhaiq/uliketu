package com.hengsu.uliketu.nav.service.impl;

import com.hengsu.uliketu.core.model.StatementModel;
import com.hengsu.uliketu.core.service.RecommendRelationService;
import com.hengsu.uliketu.core.service.UserService;
import com.hengsu.uliketu.nav.entity.LinkClickCount;
import com.hengsu.uliketu.nav.model.LinkClickCountModel;
import com.hengsu.uliketu.nav.model.NavLinkModel;
import com.hengsu.uliketu.nav.service.NavLinkService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.uliketu.nav.entity.LinkClick;
import com.hengsu.uliketu.nav.repository.LinkClickRepository;
import com.hengsu.uliketu.nav.model.LinkClickModel;
import com.hengsu.uliketu.nav.service.LinkClickService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.*;

@Service
public class LinkClickServiceImpl implements LinkClickService {

    private final Logger logger = LoggerFactory.getLogger(LinkClickServiceImpl.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private LinkClickRepository linkClickRepo;

    @Autowired
    private NavLinkService navLinkService;

    @Autowired
    private RecommendRelationService recommendRelationService;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public int create(LinkClickModel linkClickModel) {
        return linkClickRepo.insert(beanMapper.map(linkClickModel, LinkClick.class));
    }

    @Transactional
    @Override
    public int createSelective(LinkClickModel linkClickModel) {
        return linkClickRepo.insertSelective(beanMapper.map(linkClickModel, LinkClick.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return linkClickRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public LinkClickModel findByPrimaryKey(Long id) {
        LinkClick linkClick = linkClickRepo.selectByPrimaryKey(id);
        return beanMapper.map(linkClick, LinkClickModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(LinkClickModel linkClickModel) {
        return linkClickRepo.selectCount(beanMapper.map(linkClickModel, LinkClick.class));
    }

    @Override
    public List<LinkClickCountModel> selectByTime(Date startTime, Date endTime) {
        List<LinkClickCount> linkClickCounts = linkClickRepo.selectByTime(startTime, endTime);
        return beanMapper.mapAsList(linkClickCounts, LinkClickCountModel.class);
    }

    @Transactional
    @Override
    public void updateStatusByTime(Date startTime, Date endTime) {
        linkClickRepo.updateStatusByTime(startTime, endTime);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(LinkClickModel linkClickModel) {
        return linkClickRepo.updateByPrimaryKey(beanMapper.map(linkClickModel, LinkClick.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(LinkClickModel linkClickModel) {
        return linkClickRepo.updateByPrimaryKeySelective(beanMapper.map(linkClickModel, LinkClick.class));
    }

    @Transactional
    public void calculateLinkClick() {

        Date endTime = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
        Date startTime = DateUtils.addDays(endTime, -1);

        logger.info("start calculate link click log, date - {}",endTime.toLocaleString());
        //用户的收入
        Map<Long, Long> userInComing = new HashMap<>();

        //查出前一天的点击记录
        List<LinkClickCountModel> linkClickCountModels = selectByTime(startTime, endTime);
        if (CollectionUtils.isEmpty(linkClickCountModels)) {
            return;
        }

        //计算每个人的收入
        for (LinkClickCountModel linkClickCountModel : linkClickCountModels) {
            NavLinkModel navLinkModel = navLinkService.findByPrimaryKey(linkClickCountModel.getId());
            Long incoming = 0L;
            if (NavLinkModel.LINK_TYPE_CPC == navLinkModel.getType()) {
                if (NavLinkModel.LINK_REPEAT_TRUE == navLinkModel.getIsRepeat()) {
                    incoming = navLinkModel.getNum() * linkClickCountModel.getCount();
                } else {
                    incoming = navLinkModel.getNum();
                }

                Long userId = linkClickCountModel.getUserId();
                if (userInComing.containsKey(userId)) {
                    userInComing.put(userId, userInComing.get(userId) + incoming);
                } else {
                    userInComing.put(userId, incoming);
                }
            }
        }

        //保存每个人的收入,并推荐提成
        for (Map.Entry<Long, Long> entry : userInComing.entrySet()) {
            Long userId = entry.getKey();
            Long count = entry.getKey();
            userService.addBalance(userId, count, StatementModel.NAV, "导航收入");
            Long recommendId = recommendRelationService.recommendMe(userId);

            //推荐人提成
            if (recommendId != null) {
                userService.addBalance(recommendId, count / 5, StatementModel.RECOMMEND_RATE, "用户" + userId + "导航收入提成");
            }
        }

        //更新状态
        updateStatusByTime(startTime, endTime);

    }

}
