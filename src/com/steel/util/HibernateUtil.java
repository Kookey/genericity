package com.steel.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static String CONFIG_FILE_LOCATION = "hibernate.cfg.xml";
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private  static Configuration configuration = new Configuration();
	private static SessionFactory sessionFactory;
	private static String configFile = CONFIG_FILE_LOCATION;
	
	static {
		configuration.configure(configFile);
		sessionFactory = configuration.buildSessionFactory();
	}
	
	public synchronized static Session getSessoin() {
        Session session = threadLocal.get();
        if (session == null) {
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }
        return session;
    }
	public static void closeSession(){
		Session session = threadLocal.get();
		threadLocal.set(null);
		if(session != null){
			session.close();
		}
	}
	//	测试是否连接成功
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessoin();
		System.out.println(session.hashCode());
	}
}
