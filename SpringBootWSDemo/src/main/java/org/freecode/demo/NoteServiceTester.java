package org.freecode.demo;

import org.freecode.demo.service.wsproxy.GetNoteByTitleResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public class NoteServiceTester {

	public static void main(String[] args) {
		NoteServiceClient wsCli = new NoteServiceClient();
		// if come with Spring 5 and Spring Boot 2.1.x, it requires Java 9 to 11 runtime
		// for JDK 8, need to use 4.3.x for spring-beans, spring-oxm, and 2.4.x for spring-ws. compile and runtime should have the matching or same versions of Java
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("org.freecode.demo.service.wsproxy"); // package of generated WS stubs
		wsCli.setMarshaller(marshaller);
		wsCli.setUnmarshaller(marshaller);
		GetNoteByTitleResponse response = wsCli.getNoteByTitle("Book2");
		
		StringBuilder noteInfo = new StringBuilder();
		noteInfo.append("\tTitle: " + response.getNote().getTitle() + "\n");
		noteInfo.append("\tCategory: " + response.getNote().getCategory() + "\n");
		noteInfo.append("\tSub-category: " + response.getNote().getSubCategory() + "\n");
		noteInfo.append("\tDescription: " + response.getNote().getDescription());
		
		System.out.println("Note:\n" + noteInfo.toString());
	}
}
