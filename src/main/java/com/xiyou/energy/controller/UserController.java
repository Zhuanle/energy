package com.xiyou.energy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiyou.energy.pojo.Role;
import com.xiyou.energy.pojo.User;
import com.xiyou.energy.service.RoleService;
import com.xiyou.energy.service.UserRoleService;
import com.xiyou.energy.service.UserService;
import com.xiyou.energy.util.Result;
import com.xiyou.energy.util.UserRole;
/**
 * 
 * @author zhanghuanle
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	//获得所有用户列表
	@RequestMapping("/listUser")
	public Result listUser(){
		List<User> us = userService.list();
		UserRole ur = new UserRole();
		for(User u:us){
			u.setPassword(null);
			u.setSalt(null);
			ur.setUser(u);
			ur.setRole(roleService.listRoles(u));
		}
		return Result.success(ur);
	}
	
	//删除用户
	@RequestMapping("deleteUser")
	public Result deleteUser(int id){
		userService.delete(id);
		return Result.success();
	}
}
