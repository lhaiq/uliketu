package com.hengsu.uliketu.core.repository;

import com.hengsu.uliketu.core.entity.Website;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("website") Website website);

    int insertSelective(@Param("website") Website website);

    Website selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("website") Website website);

    int updateByPrimaryKey(@Param("website") Website website);

    long selectCount(@Param("website") Website website);

    List<Website> selectPage(@Param("website") Website website, @Param("pageable") Pageable pageable);
}