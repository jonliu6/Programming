package org.freecode.demo;

import java.io.Reader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.freecode.demo.entity.Account;
import org.freecode.demo.entity.Transaction;


public class MainTest extends TestCase {
	private SqlSessionFactory sessionFactory;
	private int accountNo = 6271; //6271 in MySQL, 4786 in Oracle
	
	public MainTest( String aName )
	{
		super( aName );
	}
	
	public static void main(String[] args) {
		
	}
	
	private SqlSessionFactory createSqlSessionFactory()
	{
		if ( sessionFactory == null )
		{
			
		try
		{
			Reader rd = Resources.getResourceAsReader("org/mybatis/conf/mybatis-config.xml");
			sessionFactory = new SqlSessionFactoryBuilder()
					.build(rd );
		}
		catch (Exception ex )
		{
			ex.printStackTrace();
		}
		
	    }
		
		return sessionFactory;
	}
	
	private SqlSession createSqlSession()
	{
		SqlSession sqlSession = createSqlSessionFactory().openSession();
		
		return sqlSession;
	}
	
	private void closeSqlSession( SqlSession sqlSession )
	{
		if ( sqlSession != null )
		{
			sqlSession.close();
			sqlSession = null;
		}
	}
	
	public void testFindAccountById()
	{
		// int accountNo = 4786;
		
		SqlSession sqlSession = createSqlSession();
		Account anAccount = (Account) sqlSession.selectOne("org.freecode.demo.mapper.Mapper.findAccountById", accountNo);
		closeSqlSession( sqlSession );
		
		assertNotNull( anAccount );
		System.out.println( anAccount );
	}
	
	public void testFindAllAccounts()
	{
		SqlSession sqlSession = createSqlSession();
		List<Account> accountList = sqlSession.selectList("org.freecode.demo.mapper.Mapper.findAllAccounts");
		closeSqlSession( sqlSession );

		assertNotNull( accountList );
		assertTrue( accountList.size() > 0 );
		
		System.out.println( accountList.size() + " account(s) found." );
		for (Account account : accountList) {
			System.out.println( account );
		}
	}
	
	private int generateRandomId()
	{
		int aNum = (int) Math.round( Math.random() * 10000 );
		
		return aNum;
	}
	
	private int generateRandomId2()
	{
		int aNum = (int) Math.round( Math.random() * 1000000 );
		
		return aNum;
	}
	
	public void testCreateAccount()
	{
		SqlSession sqlSession = createSqlSession();
		Account account = new Account();
		int accountId = generateRandomId();
		account.setId( accountId );
		account.setName( "Charlie Brown" );
		account.setBalance( 999.90 );
		account.setCreatedAt( new Date() );
		sqlSession.insert("org.freecode.demo.mapper.Mapper.createAccount", account);
		sqlSession.commit();
		Account returnedAccount = (Account) sqlSession.selectOne("org.freecode.demo.mapper.Mapper.findAccountById", accountId );
		closeSqlSession( sqlSession );
		assertEquals( account , returnedAccount );
		System.out.println( returnedAccount );
	}
	
	public void testUpdateBalance()
	{
		SqlSession sqlSession = createSqlSession();
		Account account = sqlSession.selectOne("org.freecode.demo.mapper.Mapper.findAccountById", 1151);
		double oldBalance = 0.0d;
		if ( account != null )
		{
			oldBalance = account.getBalance();
		}
		System.out.println( account );
		account.setBalance( oldBalance + 100.50 );
		sqlSession.update("org.freecode.demo.mapper.Mapper.updateBalance", account);
		sqlSession.commit();
		
		Account returnedAccount = sqlSession.selectOne("org.freecode.demo.mapper.Mapper.findAccountById", 1151);
		closeSqlSession(sqlSession);
		assertTrue( account.getBalance() > oldBalance );
		System.out.println( returnedAccount );
	}

	public void testDropAccount()
	{
		SqlSession sqlSession = createSqlSession();
		sqlSession.delete("org.freecode.demo.mapper.Mapper.deleteAccountById", 1151);
		sqlSession.commit();
		
		Account returnedAccount = sqlSession.selectOne("org.freecode.demo.mapper.Mapper.findAccountById", 1151);
		closeSqlSession(sqlSession);
		assertNull( returnedAccount );
		System.out.println( "Account 1151 dropped." );
	}
	
	
	public void testViewTransactionByAccountId()
	{
		SqlSession sqlSession = createSqlSession();
		List<Transaction> transactionList = sqlSession.selectList("org.freecode.demo.mapper.Mapper.findTransactionsByAccountId",  accountNo);
		sqlSession.commit();
		
		assertNotNull( transactionList );
		assertTrue( transactionList.size() > 0 );
		for (Transaction transaction : transactionList) {
			System.out.println( transaction );
		}
	}
	
	public void testCreateTransaction()
	{
		Transaction trans = new Transaction();
		int transId = generateRandomId2();
		trans.setId( transId );
		trans.setUserId( accountNo );
		trans.setAmount( 30.00 );
		trans.setTransactionType( "D" );
		trans.setTransactionTime( new Date() );
		
		SqlSession sqlSession = createSqlSession();
		sqlSession.insert("org.freecode.demo.mapper.Mapper.createTransaction", trans);
		sqlSession.commit();
		
		Transaction foundTrans = sqlSession.selectOne("org.freecode.demo.mapper.Mapper.findTransactionById", transId );
		assertNotNull( foundTrans );
		System.out.println( foundTrans );
	}
	
	public void testFindFullAccountById()
	{
		SqlSession sqlSession = createSqlSession();
		Account account = sqlSession.selectOne("org.freecode.demo.mapper.Mapper.findFullAccountById", accountNo);
		assertNotNull( account );
		System.out.println( account );
	}
	
	public void testProcedureForUpdate()
	{
		Map<String, Object> params = new HashMap<String, Object>();
		Double totalBalance = 0.0d;
		params.put("v_userid",accountNo);
		params.put ("v_transactiontype","D");
		params.put ("d_amount",35.5);
		params.put ("od_balance", totalBalance);
		
		SqlSession sqlSession = createSqlSession();
		sqlSession.selectList("prc_update_balance", params);
		sqlSession.commit();
		
		totalBalance = (Double) params.get("od_balance");
		assertNotNull( totalBalance );
		assertTrue( totalBalance > 0 );
		System.out.println( "Total Balance: " + totalBalance );
		
	}

}
 