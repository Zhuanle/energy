package com.xiyou.energy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiyou.energy.mapper.UserRoleMapper;
import com.xiyou.energy.pojo.User;
import com.xiyou.energy.pojo.UserRole;
import com.xiyou.energy.pojo.UserRoleExample;
import com.xiyou.energy.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleMapper userRoleMapper;

	@Override
	public void setRoles(User user, int[] roleIds) {
		// 删除当前用户所有的角色
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUidEqualTo(user.getId());
		List<UserRole> urs = userRoleMapper.selectByExample(example);
		for (UserRole userRole : urs)
			userRoleMapper.deleteByPrimaryKey(userRole.getId());

		// 设置新的角色关系
		if (null != roleIds)
			for (int rid : roleIds) {
				UserRole userRole = new UserRole();
				userRole.setRid(rid);
				userRole.setUid(user.getId());
				userRoleMapper.insert(userRole);
			}
	}

	@Override
	public void deleteByUser(int userId) {
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUidEqualTo(userId);
		List<UserRole> urs = userRoleMapper.selectByExample(example);
		for (UserRole userRole : urs) {
			userRoleMapper.deleteByPrimaryKey(userRole.getId());
		}
	}

	@Override
	public void deleteByRole(int roleId) {
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andRidEqualTo(roleId);
		List<UserRole> urs = userRoleMapper.selectByExample(example);
		for (UserRole userRole : urs) {
			userRoleMapper.deleteByPrimaryKey(userRole.getId());
		}
	}

	//添加用户及其角色对应
	@Override
	public void addUserRole(int uid) {
		// TODO Auto-generated method stub
		UserRole ur = new UserRole();
		ur.setUid(uid);
		ur.setRid(1);
		userRoleMapper.insertSelective(ur);
	}

	//修改权限
	@Override
	public void updateUserRole(int uid, int rid) {
		// TODO Auto-generated method stub
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUidEqualTo(uid);
		UserRole ur = new UserRole();
		ur.setRid(rid);
		userRoleMapper.updateByExampleSelective(ur, example);
	}


}