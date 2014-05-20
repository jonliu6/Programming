package org.freecode.demo;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.freecode.demo.entity.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
//import org.freecode.demo.entity.Transaction;

public class HibernateDemo {
	public static void main(String[] args)
	{
//		Configuration cfg = new Configuration().addClass(org.freecode.demo.entity.Account.class);
//		Configuration cfg = new Configuration();
//		cfg.configure("/conf/hibernate.cfg.xml");
//		cfg.configure(); // if /conf is not in the classpath, use the line above to specify
//		ServiceRegistryBuilder srb = new ServiceRegistryBuilder();
//		srb.applySettings( cfg.getProperties());
//		ServiceRegistry sr = srb.buildServiceRegistry();
//		SessionFactory sf = cfg.buildSessionFactory( sr );
		
		Session s = getDBSession();	
		Transaction tx = s.beginTransaction();
//		tx.begin();
		Account newAccount = createAccount();
		s.save( newAccount );
//		s.flush();
		System.out.println( "Account inserted." );
		
//		s.createSQLQuery( "SELECT * FROM t_account" );
		List users = s.createQuery( "FROM Account" ).list();
		printOut( users);
		
		// create Transactions
		org.freecode.demo.entity.Transaction trans1 = createTransaction(newAccount.getId(), 100.00, "W", new Date() );
		s.save( trans1 );
		org.freecode.demo.entity.Transaction trans2 = createTransaction(newAccount.getId(), 50.00, "D", new Date() );
		s.save( trans2 );
		
		// Display Transactions
		List trans = s.createSQLQuery( "SELECT * FROM t_transaction WHERE user_id = " + newAccount.getId() ).list();
		printOut( trans );
		
		// update
		newAccount.setBalance( newAccount.getBalance() * 2 );
		s.update( newAccount );
		users = s.createQuery( "FROM Account" ).list();
		printOut( users);
//		s.flush();
		System.out.println( "Balance updated." );
		
		// update 2 - by stored procedure
		System.out.println("Current balance: " + newAccount.getBalance() + "\nWithdrawing 200...");
		List usrLst = s.getNamedQuery("updateBalance").setParameter("v_userid", newAccount.getId())
		                                .setParameter("v_transactiontype", "W").setParameter("d_amount", 200).list();
		newAccount = (Account) usrLst.get(0);
		if ( newAccount != null )
		{
			System.out.println("Withdrawn, remaining " + newAccount.getBalance());
		}
		
		// delete
//		Account foundAcc = (Account) s.createQuery( "FROM Account WHERE id = " + newAccount.getId() ).uniqueResult();
//		s.delete( foundAcc );
//		int ret = s.createQuery( "DELETE Account WHERE id = " + newAccount.getId() ).executeUpdate();
//		s.flush();
		users = s.createQuery( "FROM Account" ).list();
		printOut(users);
//		System.out.println( "Account deleted. [" + ret + "]" );
		
		tx.commit();
		
		if ( s != null )
		{
			s.close();
		}
		
	}
	
	public static Session getDBSession()
	{
		Configuration cfg = new Configuration();
		cfg.configure("/conf/hibernate.cfg.xml");
//		cfg.configure(); // if /conf is not in the classpath, use the line above to specify
		ServiceRegistryBuilder srb = new ServiceRegistryBuilder();
		srb.applySettings( cfg.getProperties());
		ServiceRegistry sr = srb.buildServiceRegistry();
		SessionFactory sf = cfg.buildSessionFactory( sr );
		
		Session s = sf.openSession();
		
		return s;
	}
	
	private static Account createAccount( )
	{
		Account acc = new Account();
		// Session s = getDBSession();
		int aNum = (int) Math.round( Math.random() * 10000 );
		
		acc.setId(aNum);
		acc.setUserName( "Alpha Dog" );
		acc.setBalance( 1000.00 );
		acc.setCreatedAt( new Date() );
//		s.save( acc );
		
//		if ( s != null )
//		{
//			s.close();
//			s = null;
//		}
		return acc;
	}
	
	private static org.freecode.demo.entity.Transaction createTransaction( int userId, double amount, String type, Date aTime )
	{
		int aNum = (int) Math.round( Math.random() * 1000000 );
		org.freecode.demo.entity.Transaction aTrans = new org.freecode.demo.entity.Transaction(aNum, userId, amount, type, aTime );
		
		return aTrans;
	}
	
	private static void printOut( List objs )
	{
		for (Iterator it = objs.iterator(); it.hasNext(); )
		{
			System.out.println( it.next() );
		}
	}

}
