package com.steel.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.steel.dao.UserDao;
import com.steel.pojo.User;

public class SpringTest {

	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	UserDao dao = (UserDao) ac.getBean("userDao");
	//@Test
	public void testSave(){
		User user = new User();
		user.setName("wangxl");
		user.setAddress("beijing");
		user.setAge(19);
		user.setPhone("545342324");
		user.setSex(true);
//		dao.saveUser(user);
		List<User> search = dao.search(user);
		System.out.println(search.get(0).getName());
	}
	
	//@Test
	public void testFindUserById(){
		User user = dao.findUserById(7);
		System.out.println(user);
	}
	
//	@Test
	public void listAllUser(){
		List<User> list = dao.findAllUser();
		for(User u:list){
			System.out.println(u);
		}
	}
	
	@Test
	public void deleteUserById(){
		dao.DeleteUserById(7);
	}
}