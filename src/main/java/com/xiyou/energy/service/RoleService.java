package com.xiyou.energy.service;

import java.util.List;
import java.util.Set;

import com.xiyou.energy.pojo.Role;
import com.xiyou.energy.pojo.User;
import com.xiyou.energy.util.UserRole;

public interface RoleService {
	public Set<String> listRoleNames(String userName);

	public List<Role> listRoles(String userName);

	public List<Role> listRoles(User user);

	public List<Role> list();

	public void add(Role role);

	public void delete(Integer id);

	public Role get(Integer id);

	public void update(Role role);

}