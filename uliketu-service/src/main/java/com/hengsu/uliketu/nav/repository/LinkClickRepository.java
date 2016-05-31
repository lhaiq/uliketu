package com.hengsu.uliketu.nav.repository;

import com.hengsu.uliketu.nav.entity.LinkClick;

import java.util.Date;
import java.util.List;

import com.hengsu.uliketu.nav.entity.LinkClickCount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkClickRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("linkclick") LinkClick linkclick);

    int insertSelective(@Param("linkclick") LinkClick linkclick);

    LinkClick selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("linkclick") LinkClick linkclick);

    int updateByPrimaryKey(@Param("linkclick") LinkClick linkclick);

    long selectCount(@Param("linkclick") LinkClick linkclick);

    List<LinkClick> selectPage(@Param("linkclick") LinkClick linkclick, @Param("pageable") Pageable pageable);

    List<LinkClickCount> selectByTime(
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

    void updateStatusByTime(@Param("startTime") Date startTime,
                            @Param("endTime") Date endTime);


}