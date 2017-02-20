package org.freecode.demo.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateHelper {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try
		{
			Configuration conf = new Configuration();
			conf.configure();
			ServiceRegistry svrReg = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
			
			return conf.buildSessionFactory(svrReg);
		}
		catch (Exception ex) {
			System.err.println("Exception caught in buildSessionFactory(): " + ex.getMessage());
			throw new ExceptionInInitializerError(ex);
		}
		
		
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
