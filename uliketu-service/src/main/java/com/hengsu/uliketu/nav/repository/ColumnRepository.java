package com.hengsu.uliketu.nav.repository;

import com.hengsu.uliketu.nav.entity.Column;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("column") Column column);

    int insertSelective(@Param("column") Column column);

    Column selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("column") Column column);

    int updateByPrimaryKey(@Param("column") Column column);

    long selectCount(@Param("column") Column column);

    List<Column> selectPage(@Param("column") Column column, @Param("pageable") Pageable pageable);
}