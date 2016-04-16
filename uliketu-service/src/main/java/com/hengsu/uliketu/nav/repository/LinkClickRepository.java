package com.hengsu.uliketu.nav.repository;

import com.hengsu.uliketu.nav.entity.LinkClick;
import java.util.List;
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

    int selectCount(@Param("linkclick") LinkClick linkclick);

    List<LinkClick> selectPage(@Param("linkclick") LinkClick linkclick, @Param("pageable") Pageable pageable);
}