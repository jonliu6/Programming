package org.freecode.demo;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

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
    		cfg.configure("/conf/hibernate.cfg.xml"); // specify this if hibernate.cfg.xml is not in classpath
    		// cfg.addAnnotatedClass(Account.class); // instead of XML configuration, the entity can be added by this method
    		// ServiceRegistryBuilder srb = new ServiceRegistryBuilder(); // deprecated in Hibernate 4.3+
    		sr = new StandardServiceRegistryBuilder().applySettings( cfg.getProperties()).build();
    		// srb.applySettings( cfg.getProperties());
    	    // sr = srb.buildServiceRegistry();
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
