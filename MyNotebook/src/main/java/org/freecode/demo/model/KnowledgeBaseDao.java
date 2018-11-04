package org.freecode.demo.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.freecode.demo.entity.KnowledgeCategory;
import org.freecode.demo.service.KnowledgeBaseService;

public class KnowledgeBaseDao {
	private KnowledgeBaseService kbService = new KnowledgeBaseService();
	@PersistenceContext(unitName="KnowledgeBaseUnit")
	private EntityManager em = null;

	public List<KnowledgeCategory> getAllCategory() {
		List<KnowledgeCategory> categories = null;
		em = kbService.getEntityManager();
		categories = em.createQuery("SELECT kc FROM knowledgeCategory kc ORDER BY categoryParentId, id").getResultList();
		
		return categories;
	}
	
	public KnowledgeCategory getCategoryById(int id) {
		KnowledgeCategory kc = null;
		if (id > 0) {
			em = kbService.getEntityManager();
			kc = (KnowledgeCategory) em.createQuery("SELECT kc FROM knowledgeCategory kc WHERE id = " + id).getSingleResult();
		}
		
		return kc;
	}
}
