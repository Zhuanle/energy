package com.xiyou.energy.service;

import java.util.List;

import com.xiyou.energy.pojo.User;
import com.xiyou.energy.util.Result;

public interface UserService {
	public String getPassword(String name);

	public User getByName(String name);

	public List<User> list();

	public Result add(User user);

	public void delete(int id);

	public User get(int id);

	public void update(User user);

	public void editService(User user);

}