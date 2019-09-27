package com.xiyou.energy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiyou.energy.mapper.EnergyMapper;
import com.xiyou.energy.mapper.FeedMapper;
import com.xiyou.energy.mapper.JianchaMapper;
import com.xiyou.energy.pojo.Energy;
import com.xiyou.energy.pojo.EnergyExample;
import com.xiyou.energy.pojo.Feed;
import com.xiyou.energy.pojo.JianchaExample;
import com.xiyou.energy.service.EnergyService;
import com.xiyou.energy.util.Result;

@Service
public class EnergyServiceImpl implements EnergyService {

	@Autowired
	private EnergyMapper energyMapper;
	@Autowired
	private FeedMapper feedMapper;
	@Autowired
	private JianchaMapper jianchaMapper;

	@Override
	public List<Energy> listEnergy() {
		// TODO Auto-generated method stub
		EnergyExample example = new EnergyExample();
		// id升序排列
		example.setOrderByClause("id desc");
		List<Energy> list = energyMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<Energy> listEnergyByCid(int cid) {
		// TODO Auto-generated method stub
		EnergyExample example = new EnergyExample();
		example.createCriteria().andCidEqualTo(cid);
		List<Energy> energy = energyMapper.selectByExample(example);
		return energy;
	}

	@Override
	public Result addEnergy(Energy energy) {
		// TODO Auto-generated method stub
		EnergyExample example = new EnergyExample();
		//先判断此能源是否存在
		example.createCriteria().andNameEqualTo(energy.getName());
		List<Energy> energy1 =  energyMapper.selectByExample(example);
		if(energy1.size() > 0 ){
			return Result.fail("能源已存在");
		}
		
		energy.setUpdatetime(new Date());
		energy.setUpdatetime(new Date());
		energyMapper.insertSelective(energy);
		
		//根据能源名获得能源id
		 
		 
		 Feed feed = new Feed();
			feed.setEid(energy1.get(0).getId());
			feed.setCount(energy1.get(0).getCount());
			feed.setCreatetime(new Date());
			//0   购买     1   消耗
			int type=1;
			if(energy.getCount() > 0){
				type=0;
			}
			feed.setType(type);
			feed.setCreatetime(new Date());
			feedMapper.insertSelective(feed);
			return Result.success();
	}

	@Override
	public Energy getEnergyById(int id) {
		// TODO Auto-generated method stub
		Energy energy = energyMapper.selectByPrimaryKey(id);
		return energy;
	}

	@Override
	public void updateEnergy(Energy energy) {
		// TODO Auto-generated method stub
		Feed feed = new Feed();
		feed.setEid(energy.getId());
		feed.setCount(energy.getCount());
		feed.setCreatetime(new Date());
		//0   购买     1   消耗
		int type=1;
		if(energy.getCount() > 0){
			type=0;
		}
		feed.setType(type);
		feed.setCreatetime(new Date());
		feedMapper.insertSelective(feed);
		Energy energy1 = energyMapper.selectByPrimaryKey(energy.getId());
		int count = energy.getCount()+energy1.getCount();
		energy.setCount(count);
		energy.setUpdatetime(new Date());
		energyMapper.updateByPrimaryKeySelective(energy);
		
	}

	@Override
	public void deleteEnergy(int id) {
		energyMapper.deleteByPrimaryKey(id);
	}

}
