package org.freecode.demo;


import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

//@Stateless
//@TransactionManagement(TransactionManagementType.CONTAINER)
public class EmployeeFactory {

//	@PersistenceContext(unitName = "JPADemo1", type = PersistenceContextType.TRANSACTION)
	EntityManager theEntityManager;
	public static final String PERSISTENT_UNIT_NAME = "JPADemo1";
//	@PersistenceUnit(name="JPADemo1")
	private EntityManagerFactory theEntityManagerFactory;
	
	public EmployeeFactory()
	{
		theEntityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
		theEntityManager = theEntityManagerFactory.createEntityManager();
	}
	
	public List<Employee> findAllEmployees()
	{
		List<Employee> employees = null;
		Query query = theEntityManager.createNamedQuery("findAllEmployees");
		employees = query.getResultList();
		
		return employees;
	}
}