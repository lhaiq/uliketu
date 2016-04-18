package com.hengsu.uliketu.mall.repository;

import com.hengsu.uliketu.mall.entity.Winner;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WinnerRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("winner") Winner winner);

    int insertSelective(@Param("winner") Winner winner);

    Winner selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("winner") Winner winner);

    int updateByPrimaryKey(@Param("winner") Winner winner);

    int selectCount(@Param("winner") Winner winner);

    List<Winner> selectPage(@Param("winner") Winner winner, @Param("pageable") Pageable pageable);
}