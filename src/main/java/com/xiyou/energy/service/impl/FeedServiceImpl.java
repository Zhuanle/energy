package com.xiyou.energy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiyou.energy.mapper.FeedMapper;
import com.xiyou.energy.pojo.Feed;
import com.xiyou.energy.pojo.FeedExample;
import com.xiyou.energy.service.FeedService;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	private FeedMapper feedMapper;
	
	@Override
	public List<Feed> getFeedByEid(int eid) {
		// TODO Auto-generated method stub
		FeedExample example = new FeedExample();
		example.createCriteria().andEidEqualTo(eid);
		example.setOrderByClause("createTime desc");
		List<Feed> feed = feedMapper.selectByExample(example);
		return feed;
	}

}
