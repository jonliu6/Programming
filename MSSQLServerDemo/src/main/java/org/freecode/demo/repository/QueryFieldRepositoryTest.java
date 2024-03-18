package org.freecode.demo.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.freecode.demo.model.QueryField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QueryFieldRepositoryTest {

	@Autowired
	private QueryFieldRepository queryFieldRepo;
	
	@Test
	public void testFetchData(){
        /*Test data retrieval*/
		Iterable<QueryField> queryFields = queryFieldRepo.findAll();
		assertNotNull(queryFields);
		int count = 0;
		for (QueryField queryField : queryFields) {
			System.out.println(queryField.toString());
			count++;
		}
		assertTrue(count > 0);
    } 
}
