package com.steel.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.steel.pojo.User;
import com.steel.util.HibernateUtil;

public class HibernateTest {

//	@Test
	public void testHibernate(){
		Session session = HibernateUtil.getSessoin();
		String hql = "from User";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		for(User user:list){
			System.out.println(user.getName());
		}
	}
	
	@Test
	public void testSave(){
		Session session = HibernateUtil.getSessoin();
		Transaction tx = session.beginTransaction();
		User user = new User();
		user.setName("houdm");
		user.setAge(20);
		user.setPhone("5434243");
		user.setSex(true);
		user.setAddress("wuhu");
		session.save(user);
		tx.commit();
	}
}
