package com.xiyou.energy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xiyou.energy.pojo.Feed;
import com.xiyou.energy.pojo.FeedExample;

@Mapper
public interface FeedMapper {
    int countByExample(FeedExample example);

    int deleteByExample(FeedExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Feed record);

    int insertSelective(Feed record);

    List<Feed> selectByExample(FeedExample example);

    Feed selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Feed record, @Param("example") FeedExample example);

    int updateByExample(@Param("record") Feed record, @Param("example") FeedExample example);

    int updateByPrimaryKeySelective(Feed record);

    int updateByPrimaryKey(Feed record);
}