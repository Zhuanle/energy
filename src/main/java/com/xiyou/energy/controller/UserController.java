package com.xiyou.energy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiyou.energy.pojo.User;
import com.xiyou.energy.service.RoleService;
import com.xiyou.energy.service.UserRoleService;
import com.xiyou.energy.service.UserService;
import com.xiyou.energy.util.EditUserVo;
import com.xiyou.energy.util.Result;
import com.xiyou.energy.util.UserRoleVo;
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
	@PostMapping( value="/listUser")
	public Result listUser()throws Exception{
		List<User> us = userService.list();
		List<UserRoleVo> ls = new ArrayList<>();
		for(User u:us){
			UserRoleVo ur = new UserRoleVo();
			u.setPassword(null);
			u.setSalt(null);
			ur.setUser(u);
			ur.setRole(roleService.listRoles(u));
			ls.add(ur);
		}
//		System.out.println(us.size()+"   "+ls.size());
//		PageInfo<UserRoleVo> page = new PageInfo<>(ls);
		
		return Result.success(ls);
	}
	
	
	
	//删除用户
	@RequestMapping(value="/deleteUser",method=RequestMethod.GET)
	public Result deleteUser(@RequestParam int id) throws Exception{
		System.out.println(id);
		userService.delete(id);
		return Result.success();
	}
	
	//修改用户信息
	@RequestMapping(value="/editUser",method=RequestMethod.POST)
	public Result editUser( @RequestBody EditUserVo editUserVo)throws Exception{
		System.out.println(editUserVo.getName()+"   "+editUserVo.getRid());
		User user = new User();
		user.setId(editUserVo.getId());
		user.setName(editUserVo.getName());
		user.setRealname(editUserVo.getRealname());
		user.setPhone(editUserVo.getPhone());
		userService.editUser(user,editUserVo.getRid());
		return Result.success();
		
		
	}
}
