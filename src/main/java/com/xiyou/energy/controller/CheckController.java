package com.xiyou.energy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiyou.energy.pojo.Jiancha;
import com.xiyou.energy.service.CheckService;
import com.xiyou.energy.util.Result;

@RestController
@RequestMapping("/check")
public class CheckController {

	@Autowired
	private CheckService checkServie;
	
	@RequestMapping(value="/listCheck",method=RequestMethod.GET)
	public Result listCheck(@RequestParam(value="start",defaultValue="0")int start,@RequestParam(value = "size", defaultValue = "5") int size)throws Exception{

		PageHelper.startPage(start, size,"id desc");
		List<Jiancha> list = checkServie.listCheck();
		PageInfo<Jiancha> page = new PageInfo<>(list);
		return Result.success(page);
	}
	
	@RequestMapping("/addCheck")
	public Result addCheck(@RequestBody Jiancha jiancha){
//		System.out.println(jiancha.getPercent());
		checkServie.addCheck(jiancha);
		return Result.success();
	}
}
