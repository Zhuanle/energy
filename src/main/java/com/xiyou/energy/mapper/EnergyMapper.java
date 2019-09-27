package com.xiyou.energy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xiyou.energy.pojo.Energy;
import com.xiyou.energy.pojo.EnergyExample;

@Mapper
public interface EnergyMapper {
    int countByExample(EnergyExample example);

    int deleteByExample(EnergyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Energy record);

    int insertSelective(Energy record);

    List<Energy> selectByExample(EnergyExample example);

    Energy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Energy record, @Param("example") EnergyExample example);

    int updateByExample(@Param("record") Energy record, @Param("example") EnergyExample example);

    int updateByPrimaryKeySelective(Energy record);

    int updateByPrimaryKey(Energy record);
}