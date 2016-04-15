package com.hengsu.uliketu.core.repository;

import com.hengsu.uliketu.core.entity.Category;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("category") Category category);

    int insertSelective(@Param("category") Category category);

    Category selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("category") Category category);

    int updateByPrimaryKey(@Param("category") Category category);

    int selectCount(@Param("category") Category category);

    List<Category> selectPage(@Param("category") Category category, @Param("pageable") Pageable pageable);
}