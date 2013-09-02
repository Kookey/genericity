package com.steel.dao;

import java.util.List;

import com.steel.pojo.User;

public interface UserDao {

	public void saveUser(User user);

	public List<User> search(User user);
	
	public User findUserById (int id);
}
