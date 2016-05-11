
package com.hengsu.uliketu.nav.service;

import com.hengsu.uliketu.nav.model.LinkClickCountModel;
import com.hengsu.uliketu.nav.model.LinkClickModel;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LinkClickService {

    public int create(LinkClickModel linkClickModel);

    public int createSelective(LinkClickModel linkClickModel);

    public LinkClickModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(LinkClickModel linkClickModel);

    public int updateByPrimaryKeySelective(LinkClickModel linkClickModel);

    public int deleteByPrimaryKey(Long id);

    public int selectCount(LinkClickModel linkClickModel);

    public List<LinkClickCountModel> selectByTime(
            Date startTime,
            Date endTime);

    public void updateStatusByTime(Date startTime,
                                   Date endTime);

}