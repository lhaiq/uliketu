package com.hengsu.uliketu.core.repository;

import com.hengsu.uliketu.core.entity.Conf;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfRepository {
    int deleteByPrimaryKey(@Param("confKey") String confKey);

    int insert(@Param("conf") Conf conf);

    int insertSelective(@Param("conf") Conf conf);

    Conf selectByPrimaryKey(@Param("confKey") String confKey);

    int updateByPrimaryKeySelective(@Param("conf") Conf conf);

    int updateByPrimaryKey(@Param("conf") Conf conf);

    int selectCount(@Param("conf") Conf conf);

    List<Conf> selectPage(@Param("conf") Conf conf, @Param("pageable") Pageable pageable);
}