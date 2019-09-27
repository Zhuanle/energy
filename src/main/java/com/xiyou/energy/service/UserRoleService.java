package com.xiyou.energy.service;

import com.xiyou.energy.pojo.User;
import com.xiyou.energy.util.UserRoleVo;

public interface UserRoleService {

	public void setRoles(User user, int[] roleIds);

	public void deleteByUser(int userId);

	public void deleteByRole(int roleId);
	
	public void addUserRole(int uid);
	
	public void updateUserRole(int uid,int rid);

}