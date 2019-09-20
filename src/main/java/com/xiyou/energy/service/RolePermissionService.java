package com.xiyou.energy.service;

import com.xiyou.energy.pojo.Role;

public interface RolePermissionService {
	public void setPermissions(Role role, int[] permissionIds);

	public void deleteByRole(Integer roleId);

	public void deleteByPermission(Integer permissionId);
}