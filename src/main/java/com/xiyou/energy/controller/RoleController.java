package com.xiyou.energy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiyou.energy.pojo.Role;
import com.xiyou.energy.pojo.UserRole;
import com.xiyou.energy.service.RolePermissionService;
import com.xiyou.energy.service.RoleService;
import com.xiyou.energy.service.UserRoleService;
import com.xiyou.energy.util.Result;


@RestController
@RequestMapping("/user")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RolePermissionService rolePermissionService;
	
	//获得所有角色
	@RequestMapping("/roleList")
	public Result roleList(){
		List<Role> roleList = roleService.list();
		return Result.success(roleList);
	}
	
	//对用户授权
	@RequestMapping("/rolePermission")
	public Result rolePermission(@RequestBody UserRole userRole){
		userRoleService.updateUserRole(userRole.getUid(),userRole.getRid());
		return Result.success();
	}
	
	//添加角色
	@RequestMapping("/addRole")
	public Result addRole(@RequestBody Role role){
		roleService.add(role);
		return Result.success();
	}
	
	//删除角色
	@RequestMapping("/deleteRole")
	public Result deleteRole(int id){
		//删除用户角色表中的数据
		userRoleService.deleteByRole(id);
		//删除角色权限表中的数据
		rolePermissionService.deleteByRole(id);
		roleService.delete(id);
		return Result.success();
	}
}
