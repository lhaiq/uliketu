package com.hengsu.uliketu.core.repository;

import com.hengsu.uliketu.core.entity.Cash;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CashRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("cash") Cash cash);

    int insertSelective(@Param("cash") Cash cash);

    Cash selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("cash") Cash cash);

    int updateByPrimaryKey(@Param("cash") Cash cash);

    long selectCount(@Param("cash") Cash cash);

    List<Cash> selectPage(@Param("cash") Cash cash, @Param("pageable") Pageable pageable);
}