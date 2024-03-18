package org.freecode.demo.repository;

import org.freecode.demo.model.QueryField;
import org.freecode.demo.model.QueryFieldKey;
import org.springframework.data.repository.CrudRepository;

public interface QueryFieldRepository extends CrudRepository<QueryField, QueryFieldKey>{
	
}
