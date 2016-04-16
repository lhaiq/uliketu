package com.hengsu.uliketu.nav.repository;

import com.hengsu.uliketu.nav.entity.NavLink;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NavLinkRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("navlink") NavLink navlink);

    int insertSelective(@Param("navlink") NavLink navlink);

    NavLink selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("navlink") NavLink navlink);

    int updateByPrimaryKey(@Param("navlink") NavLink navlink);

    int selectCount(@Param("navlink") NavLink navlink);

    List<NavLink> selectPage(@Param("navlink") NavLink navlink, @Param("pageable") Pageable pageable);
}