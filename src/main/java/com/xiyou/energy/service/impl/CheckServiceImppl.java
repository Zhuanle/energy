package com.xiyou.energy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiyou.energy.mapper.EnergyMapper;
import com.xiyou.energy.mapper.JianchaMapper;
import com.xiyou.energy.pojo.EnergyExample;
import com.xiyou.energy.pojo.Jiancha;
import com.xiyou.energy.pojo.JianchaExample;
import com.xiyou.energy.service.CheckService;

@Service
public class CheckServiceImppl implements CheckService {

	@Autowired
	private JianchaMapper jianchaMapper;
	@Autowired
	private EnergyMapper energyMapper;
	
	@Override
	public List<Jiancha> listCheck() {
		// 获得所有检查信息
		JianchaExample example = new JianchaExample();
		example.setOrderByClause("id desc");
		List<Jiancha> list = jianchaMapper.selectByExample(example);
		return list;
	}

	@Override
	public void addCheck(Jiancha jiancha) {
		// TODO Auto-generated method stub
		jiancha.setCreatetime(new Date());
		jianchaMapper.insertSelective(jiancha);
	}

}
