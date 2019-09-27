package com.xiyou.energy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xiyou.energy.pojo.Jiancha;
import com.xiyou.energy.pojo.JianchaExample;

@Mapper
public interface JianchaMapper {
    int countByExample(JianchaExample example);

    int deleteByExample(JianchaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Jiancha record);

    int insertSelective(Jiancha record);

    List<Jiancha> selectByExample(JianchaExample example);

    Jiancha selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Jiancha record, @Param("example") JianchaExample example);

    int updateByExample(@Param("record") Jiancha record, @Param("example") JianchaExample example);

    int updateByPrimaryKeySelective(Jiancha record);

    int updateByPrimaryKey(Jiancha record);
}