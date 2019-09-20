package com.xiyou.energy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiyou.energy.pojo.User;
import com.xiyou.energy.service.UserService;
import com.xiyou.energy.util.Result;

@RestController
public class CustomerController {

	@Autowired
	private UserService userService;

	// 修改信息
	@RequestMapping("/customer")
	public Result editUser(@RequestBody User user) {
		userService.editService(user);
		return Result.success();
	}

	// 根据用户id获得用户信息
	@RequestMapping("/getMe")
	public Result getUserById(int id) {
		User user = userService.get(id);
		return Result.success(user);
	}
}
