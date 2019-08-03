package org.freecode.demo.service;

import java.io.Serializable;
import java.util.List;

import org.freecode.demo.dao.NoteDao;
import org.freecode.demo.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService implements Serializable{

	@Autowired
	private NoteDao noteDao;
	
	public Note findNoteByTitle(String title) {
		return noteDao.findNoteByTitle(title);
	}
	
	public List<Note> findAll() {
		return noteDao.findAll();
	}
	
	public void addNote(Note aNote) {
		noteDao.addNote(aNote);
	}
	
	public void deleteNoteByTitle(String title) {
		noteDao.deleteNoteByTitle(title);
	}
}
