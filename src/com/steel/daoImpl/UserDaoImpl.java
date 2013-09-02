package com.steel.daoImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.steel.dao.UserDao;
import com.steel.hibernate3.GenericHibernateDao;
import com.steel.pojo.User;

public class UserDaoImpl extends GenericHibernateDao<User,Integer> implements UserDao {


	@Override
	public List<User> search(User user) {
		return this.findByExample(user);
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		this.save(user);
	}

	@Override
	public User findUserById(int id) {
		return this.get(id);
	}

	@Override
	public List<User> findAllUser() {
		return this.loadAll();
	}

	@Override
	public void DeleteUserById(int id) {
		this.deleteById(id);
	}
}
