package com.hengsu.uliketu.core.repository;

import com.hengsu.uliketu.core.entity.Message;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("message") Message message);

    int insertSelective(@Param("message") Message message);

    Message selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("message") Message message);

    int updateByPrimaryKey(@Param("message") Message message);

    int selectCount(@Param("message") Message message);

    List<Message> selectPage(@Param("message") Message message, @Param("pageable") Pageable pageable);
}