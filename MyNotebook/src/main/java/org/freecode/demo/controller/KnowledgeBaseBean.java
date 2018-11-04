package org.freecode.demo.controller;

import java.util.List;

import org.freecode.demo.entity.KnowledgeCategory;
import org.freecode.demo.model.KnowledgeBaseDao;

public class KnowledgeBaseBean {
    private List<KnowledgeCategory> categories;
    private KnowledgeBaseDao kbDao = new KnowledgeBaseDao();

	public List<KnowledgeCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<KnowledgeCategory> categories) {
		this.categories = categories;
	}	
}
