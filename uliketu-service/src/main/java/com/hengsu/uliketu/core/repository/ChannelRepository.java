package com.hengsu.uliketu.core.repository;

import com.hengsu.uliketu.core.entity.Channel;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("channel") Channel channel);

    int insertSelective(@Param("channel") Channel channel);

    Channel selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("channel") Channel channel);

    int updateByPrimaryKey(@Param("channel") Channel channel);

    int selectCount(@Param("channel") Channel channel);

    List<Channel> selectPage(@Param("channel") Channel channel, @Param("pageable") Pageable pageable);
}