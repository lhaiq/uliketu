package com.hengsu.uliketu.core.repository;

import com.hengsu.uliketu.core.entity.Link;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(@Param("link") Link link);

    int insertSelective(@Param("link") Link link);

    Link selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(@Param("link") Link link);

    int updateByPrimaryKey(@Param("link") Link link);

    long selectCount(@Param("link") Link link);

    List<Link> selectPage(@Param("link") Link link, @Param("pageable") Pageable pageable);
}