package org.freecode.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.freecode.demo.aop.RandomAnnotation;

public class KnowledgeBaseDAO {

	private List<KnowledgePoint> knowledgeBase;
	
	public void initializeKnowledgeBase() {
		knowledgeBase = new ArrayList<KnowledgePoint>();
	}
	
	public void addKnowledgePoint(KnowledgePoint kp) {
		if (knowledgeBase == null) {
			initializeKnowledgeBase();
		}
		knowledgeBase.add(kp);
		System.out.println("Added " + kp);
	}
	
	public List<KnowledgePoint> findKnowledgeByKeyword(String keyword) {
		List<KnowledgePoint> foundKPs = new ArrayList<KnowledgePoint>();
		
		if (knowledgeBase != null && knowledgeBase.size() > 0) {
			for (int i=0, len=knowledgeBase.size(); i<len; ++i) {
				KnowledgePoint kp = knowledgeBase.get(i);
				if (kp != null && (kp.getTitle().toUpperCase().indexOf(keyword.toUpperCase()) > -1 || kp.getDescription().toUpperCase().indexOf(keyword.toUpperCase()) > -1)) {
					foundKPs.add(kp);
				}
			}
		}
				
		return foundKPs;
	}
	
	@RandomAnnotation
	public List<KnowledgePoint> findAll() {
		List<KnowledgePoint> foundKPs = new ArrayList<KnowledgePoint>();
		if (knowledgeBase != null && knowledgeBase.size() > 0) {
			for (int i=0, len=knowledgeBase.size(); i<len; ++i) {
				KnowledgePoint kp = knowledgeBase.get(i);
				if (kp != null) {
					foundKPs.add(kp);
				}
			}
		}
		return foundKPs;
	}
}
