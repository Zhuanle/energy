package com.xiyou.energy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiyou.energy.pojo.Feed;
import com.xiyou.energy.service.FeedService;
import com.xiyou.energy.util.Result;

@RestController
@RequestMapping("/energy")
public class FeedController {

	@Autowired
	private FeedService feedService;
	
	@RequestMapping("/getFeedByEid")
	public Result getFeedByEid(int eid){
		List<Feed> feed = feedService.getFeedByEid(eid);
		System.out.println(feed.get(0).getCreatetime());
		return Result.success(feed);
	}
}
