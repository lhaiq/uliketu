package com.hengsu.uliketu.mall.repository;

import com.hengsu.uliketu.mall.entity.ShoppingLog;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingLogRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("shoppinglog") ShoppingLog shoppinglog);

    int insertSelective(@Param("shoppinglog") ShoppingLog shoppinglog);

    ShoppingLog selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("shoppinglog") ShoppingLog shoppinglog);

    int updateByPrimaryKey(@Param("shoppinglog") ShoppingLog shoppinglog);

    int selectCount(@Param("shoppinglog") ShoppingLog shoppinglog);

    List<ShoppingLog> selectPage(@Param("shoppinglog") ShoppingLog shoppinglog, @Param("pageable") Pageable pageable);
}