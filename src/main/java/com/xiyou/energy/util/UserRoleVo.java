package com.xiyou.energy.util;

import java.util.List;

import com.xiyou.energy.pojo.Role;
import com.xiyou.energy.pojo.User;
/**
 * 
 * @author zhanghuanle
 * 用户信息展示
 */
public class UserRoleVo {

	private User user;
	private List<Role> role;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Role> getRole() {
		return role;
	}
	public void setRole(List<Role> role) {
		this.role = role;
	}
	
	
}
