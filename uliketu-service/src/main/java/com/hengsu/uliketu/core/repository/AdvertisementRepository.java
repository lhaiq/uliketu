package com.hengsu.uliketu.core.repository;

import com.hengsu.uliketu.core.entity.Advertisement;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(@Param("advertisement") Advertisement advertisement);

    int insertSelective(@Param("advertisement") Advertisement advertisement);

    Advertisement selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(@Param("advertisement") Advertisement advertisement);

    int updateByPrimaryKey(@Param("advertisement") Advertisement advertisement);

    long selectCount(@Param("advertisement") Advertisement advertisement);

    List<Advertisement> selectPage(@Param("advertisement") Advertisement advertisement, @Param("pageable") Pageable pageable);
}