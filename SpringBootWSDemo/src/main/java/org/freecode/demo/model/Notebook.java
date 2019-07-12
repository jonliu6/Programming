package org.freecode.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.freecode.demo.service.wsproxy.Note;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
//@Qualifier(value="notebook")
@Component // specified for bean definition
public class Notebook implements Serializable{

	private Map<String, Note> notes = null;
	
	private void addDummyNotes() {
		Note aNote = new Note();
		aNote.setTitle("Book1");
		aNote.setCategory("Programming");
		aNote.setSubCategory("Java");
		aNote.setDescription("How to development Spring RESTful Web Services");
		addNote(aNote);
		aNote = new Note();
		aNote.setTitle("Book2");
		aNote.setCategory("Home");
		aNote.setSubCategory("Landscaping");
		aNote.setDescription("How to build a fence");
		addNote(aNote);
	}

	public List<Note> getNotes() {
		if (notes == null) {
			addDummyNotes();
		}
		
		return new ArrayList<Note>(notes.values());
	}
	
	public Note getNoteByTitle(String title) {
		if (notes == null || title == null) {
			return notes.get(title);
		}
		return null;
	}
	
	public void addNote(Note aNote) {
		if (notes == null) {
			notes = new TreeMap<String, Note>();
		}
		if (aNote.getTitle() != null) {
			notes.put(aNote.getTitle(), aNote);
		}		
	}
	
	public void updateNote(Note aNote) {
		if (notes == null || aNote == null || aNote.getTitle() == null) {
			return;
		}
		String title = aNote.getTitle();
		Note existingNote = getNoteByTitle(title);
		if (existingNote != null) {
			existingNote.setCategory(aNote.getCategory());
			existingNote.setSubCategory(aNote.getSubCategory());
			existingNote.setDescription(aNote.getDescription());
			// existingNote.setTitle(aNote.getTitle());
		}
		else {
			addNote(aNote);
		}
	}
	
	public void deleteNoteByTitle(String title) {
		if (notes != null && title != null) {
			notes.remove(title);
		}
	}
}
