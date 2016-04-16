package com.hengsu.uliketu.core.repository;

import com.hengsu.uliketu.core.entity.Statement;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(@Param("statement") Statement statement);

    int insertSelective(@Param("statement") Statement statement);

    Statement selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(@Param("statement") Statement statement);

    int updateByPrimaryKey(@Param("statement") Statement statement);

    long selectCount(@Param("statement") Statement statement);

    List<Statement> selectPage(@Param("statement") Statement statement, @Param("pageable") Pageable pageable);
}