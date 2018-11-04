package org.freecode.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.freecode.demo.entity.KnowledgeCategory;

/**
 * Hibernate accesses DB thru JPA EntityManager
 * requiring: persistence.xml, Entity class, persistence unit name defined in persistence.xml
 */
public class HibernateJPATest {
    public static void main(String[] args) {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("KnowledgeBaseUnit");
        EntityManager em = emf.createEntityManager();
        List<KnowledgeCategory> categories = em.createQuery("SELECT kc FROM knowledgeCategory kc ORDER BY categoryParentId, id").getResultList();
        if (categories != null) {
			for(KnowledgeCategory cg : categories) {
				System.out.println(cg.getCategoryId() + ": " + cg.getCategoryName());
			}
		}
        em.close();
        emf.close();
        
        System.exit(0);
    }
}
