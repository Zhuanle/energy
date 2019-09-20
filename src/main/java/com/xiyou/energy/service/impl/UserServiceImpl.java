
package com.xiyou.energy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiyou.energy.mapper.UserMapper;
import com.xiyou.energy.pojo.User;
import com.xiyou.energy.pojo.UserExample;
import com.xiyou.energy.service.UserRoleService;
import com.xiyou.energy.service.UserService;
import com.xiyou.energy.util.Result;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	UserRoleService userRoleService;

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
			System.out.println(userList.get(0).getId());
			userRoleService.addUserRole(userList.get(0).getId());
			return Result.success();
		}
		
	}

	@Override
	public void delete(int id) {
		userMapper.deleteByPrimaryKey(id);
		userRoleService.deleteByUser(id);
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
		return userMapper.selectList();

	}

	@Override
	public void editService(User user) {
		userMapper.updateByPrimaryKey(user);
	}

}