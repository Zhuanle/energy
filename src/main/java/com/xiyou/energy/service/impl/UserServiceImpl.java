
package com.xiyou.energy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiyou.energy.mapper.UserMapper;
import com.xiyou.energy.mapper.UserRoleMapper;
import com.xiyou.energy.pojo.User;
import com.xiyou.energy.pojo.UserExample;
import com.xiyou.energy.pojo.UserRole;
import com.xiyou.energy.pojo.UserRoleExample;
import com.xiyou.energy.service.UserRoleService;
import com.xiyou.energy.service.UserService;
import com.xiyou.energy.util.Result;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	UserRoleService userRoleService;
	@Autowired
	UserRoleMapper userRoleMapper;

	@Override
	public String getPassword(String name) {
		User user = getByName(name);
		if (null == user)
			return null;
		return user.getPassword();
	}

	@Override
	public User getByName(String name) {
		UserExample example = new UserExample();
		example.createCriteria().andNameEqualTo(name);
		List<User> users = userMapper.selectByExample(example);
		if (users.isEmpty())
			return null;
		return users.get(0);
	}

	@Override
	public Result add(User u) {
		u.setCratetime(new Date());
		//判断用户名是否存在
		UserExample example = new UserExample();
		example.createCriteria().andNameEqualTo(u.getName());
		List<User> user = userMapper.selectByExample(example);
		System.out.println(user.size());
		if(user.size() > 0){
			return Result.fail("用户名已存在");
		}
		else{
			userMapper.insertSelective(u);
			List<User> userList = userMapper.selectByExample(example);
			userRoleService.addUserRole(userList.get(0).getId());
			return Result.success();
		}
		
	}

	@Override
	public void delete(int id) {
		userRoleService.deleteByUser(id);
		userMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void update(User u) {
		userMapper.updateByPrimaryKeySelective(u);
	}

	@Override
	public User get(int id) {
		User user =  userMapper.selectByPrimaryKey(id);
		user.setPassword(null);
		user.setSalt(null);
		return user;
	}

	@Override
	public List<User> list() {
		UserExample example = new UserExample();
		example.setOrderByClause("id desc");
		return userMapper.selectByExample(example);

	}

	@Override
	public void editService(User user) {
		userMapper.updateByPrimaryKey(user);
	}

	@Override
	public void editUser(User user, int rid) {
		// TODO Auto-generated method stub
		userMapper.updateByPrimaryKeySelective(user);
		UserRole userRole = new UserRole();
		userRole.setUid(user.getId());
		userRole.setRid(rid);
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUidEqualTo(user.getId());
		userRoleMapper.updateByExampleSelective(userRole, example);
	}

}