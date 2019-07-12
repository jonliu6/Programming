package org.freecode.demo.dao;

import java.io.Serializable;
import java.util.List;

import org.freecode.demo.model.Notebook;
import org.freecode.demo.service.wsproxy.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoteDao implements Serializable {
	
	@Autowired
	private Notebook notebook;
	
	public Note findNoteByTitle(String title) {
		List<Note> allNotes = findAll();
		for (Note aNote: allNotes) {
			if (aNote != null && title != null && title.equalsIgnoreCase(aNote.getTitle())) {
				return aNote;
			}
		}
		
		return null;
	}
	
	public List<Note> findAll() {
		return notebook.getNotes();
	}
	
	public void addNote(Note aNote) {
		notebook.addNote(aNote);
	}
	
	public void deleteNoteByTitle(String title) {
		notebook.deleteNoteByTitle(title);
	}
}
