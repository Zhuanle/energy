package com.xiyou.energy.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiyou.energy.mapper.PermissionMapper;
import com.xiyou.energy.mapper.RolePermissionMapper;
import com.xiyou.energy.pojo.Permission;
import com.xiyou.energy.pojo.PermissionExample;
import com.xiyou.energy.pojo.Role;
import com.xiyou.energy.pojo.RolePermission;
import com.xiyou.energy.pojo.RolePermissionExample;
import com.xiyou.energy.service.PermissionService;
import com.xiyou.energy.service.RoleService;
import com.xiyou.energy.service.UserService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	PermissionMapper permissionMapper;
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	RolePermissionMapper rolePermissionMapper;

	@Override
	public Set<String> listPermissions(String userName) {
		Set<String> result = new HashSet<>();
		List<Role> roles = roleService.listRoles(userName);

		List<RolePermission> rolePermissions = new ArrayList<>();

		for (Role role : roles) {
			RolePermissionExample example = new RolePermissionExample();
			example.createCriteria().andRidEqualTo(role.getId());
			List<RolePermission> rps = rolePermissionMapper.selectByExample(example);
			rolePermissions.addAll(rps);
		}

		for (RolePermission rolePermission : rolePermissions) {
			Permission p = permissionMapper.selectByPrimaryKey(rolePermission.getPid());
			result.add(p.getName());
		}

		return result;
	}

	@Override
	public void add(Permission u) {
		permissionMapper.insert(u);
	}

	@Override
	public void delete(Integer id) {
		permissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Permission u) {
		permissionMapper.updateByPrimaryKeySelective(u);
	}

	@Override
	public Permission get(Integer id) {
		return permissionMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Permission> list() {
		PermissionExample example = new PermissionExample();
		example.setOrderByClause("id desc");
		return permissionMapper.selectByExample(example);

	}

	// 通过角色id或的角色所有权限
	@Override
	public List<Permission> list(Role role) {
		List<Permission> result = new ArrayList<>();
		RolePermissionExample example = new RolePermissionExample();
		example.createCriteria().andRidEqualTo(role.getId());
		List<RolePermission> rps = rolePermissionMapper.selectByExample(example);
		for (RolePermission rolePermission : rps) {
			result.add(permissionMapper.selectByPrimaryKey(rolePermission.getPid()));
		}

		return result;
	}

	@Override
	public boolean needInterceptor(String requestURI) {
		List<Permission> ps = list();
		
		for (Permission p : ps) {
			if (requestURI.contains(p.getUrl()))
				System.out.println("111111" + requestURI.contains(p.getUrl()));
				return true;
		}
		return false;
	}

	@Override
	public Set<String> listPermissionURLs(String userName) {
		Set<String> result = new HashSet<>();
		List<Role> roles = roleService.listRoles(userName);

		List<RolePermission> rolePermissions = new ArrayList<>();

		for (Role role : roles) {
			RolePermissionExample example = new RolePermissionExample();
			example.createCriteria().andRidEqualTo(role.getId());
			List<RolePermission> rps = rolePermissionMapper.selectByExample(example);
			rolePermissions.addAll(rps);
		}

		for (RolePermission rolePermission : rolePermissions) {
			Permission p = permissionMapper.selectByPrimaryKey(rolePermission.getPid());
			result.add(p.getUrl());
		}

		return result;
	}

}