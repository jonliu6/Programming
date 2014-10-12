package org.freecode.demo;


import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

// @Stateless
@Local(EmployeeFactory.class)
@Remote(EmployeeFactoryRemote.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EmployeeFactoryImpl implements EmployeeFactory {

	@PersistenceContext(unitName = "JPADemo2", type = PersistenceContextType.TRANSACTION)
	EntityManager theEntityManager;
	
//	@PersistenceUnit(unitName="JPADemo2")
//	EntityManagerFactory theEntityManagerFactory;
	
//	public static final String PERSISTENT_UNIT_NAME = "JPADemo1";
//	private EntityManagerFactory theEntityManagerFactory;
	
//	public EmployeeFactory()
//	{
//		theEntityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
//		theEntityManager = theEntityManagerFactory.createEntityManager();
//	}
	
	public List<Employee> findAllEmployees() throws EJBException
	{
		List<Employee> employees = null;
		Query query = theEntityManager.createNamedQuery("findAllEmployees");
		employees = query.getResultList();
		
		return employees;
	}
}