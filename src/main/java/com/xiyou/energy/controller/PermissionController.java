package com.xiyou.energy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiyou.energy.pojo.Permission;
import com.xiyou.energy.service.PermissionService;
import com.xiyou.energy.service.RolePermissionService;
import com.xiyou.energy.util.Result;

@RestController
@RequestMapping("/user")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RolePermissionService rolePermissionService;
	
	//获得所有权限列表
	@RequestMapping("/listPermission")
	public Result listPermission(){
		List<Permission> listPermission = permissionService.list();
		return Result.success(listPermission);
	}
	
	//根据id获得权限信息
	@RequestMapping("/getPermissionById")
	public Result getPermissionById(int id){
		Permission permission = permissionService.get(id);
		return Result.success(permission);
	}
	
	//编辑权限列表
	@RequestMapping("/editPermission")
	public Result editPermission(@RequestBody Permission permission){
		permissionService.update(permission);
		return Result.success();
	}
	
	//删除权限
	@RequestMapping("/deletePermission")
	public Result deletePermission(int id){
		rolePermissionService.deleteByPermission(id);
		permissionService.delete(id);
		return Result.success();
	}
	
	//添加权限
	@RequestMapping("/addPermission")
	public Result addPermission(@RequestBody Permission permission){
		permissionService.add(permission);
		return Result.success();
	}
}
