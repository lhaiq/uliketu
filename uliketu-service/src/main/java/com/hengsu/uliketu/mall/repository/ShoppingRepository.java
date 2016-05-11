package com.hengsu.uliketu.mall.repository;

import com.hengsu.uliketu.mall.entity.Shopping;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("shopping") Shopping shopping);

    int insertSelective(@Param("shopping") Shopping shopping);

    Shopping selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("shopping") Shopping shopping);

    int updateByPrimaryKey(@Param("shopping") Shopping shopping);

    int selectCount(@Param("shopping") Shopping shopping);

    int reduceRepertory(Long id);

    List<Shopping> selectPage(@Param("shopping") Shopping shopping, @Param("pageable") Pageable pageable);
}