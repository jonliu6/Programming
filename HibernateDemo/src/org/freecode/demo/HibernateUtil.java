package org.freecode.demo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory()
    {
    	Configuration cfg = new Configuration();
    	ServiceRegistry sr;
    	SessionFactory sf;
    	
    	try 
    	{
    		// Create the SessionFactory from hibernate.cfg.xml
    		cfg.configure("/conf/hibernate.cfg.xml");
    		ServiceRegistryBuilder srb = new ServiceRegistryBuilder();
    		srb.applySettings( cfg.getProperties());
    	    sr = srb.buildServiceRegistry();
    		sf = cfg.buildSessionFactory( sr );
    		
        } 
    	catch (Throwable ex)
    	{
    		System.err.println("Initial SessionFactory creation failed." + ex);
    		throw new ExceptionInInitializerError(ex);
    	}
    	
    	return sf;
    }
    
    public static SessionFactory getSessionFactory()
    {
    	return sessionFactory;
    }
}
