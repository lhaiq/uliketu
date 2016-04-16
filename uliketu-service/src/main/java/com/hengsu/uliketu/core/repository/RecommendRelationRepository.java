package com.hengsu.uliketu.core.repository;

import com.hengsu.uliketu.core.entity.RecommendRelation;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendRelationRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("recommendrelation") RecommendRelation recommendrelation);

    int insertSelective(@Param("recommendrelation") RecommendRelation recommendrelation);

    RecommendRelation selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("recommendrelation") RecommendRelation recommendrelation);

    int updateByPrimaryKey(@Param("recommendrelation") RecommendRelation recommendrelation);

    long selectCount(@Param("recommendrelation") RecommendRelation recommendrelation);

    List<RecommendRelation> selectPage(@Param("recommendrelation") RecommendRelation recommendrelation, @Param("pageable") Pageable pageable);
}