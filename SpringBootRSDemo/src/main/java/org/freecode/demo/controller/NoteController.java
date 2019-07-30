package org.freecode.demo.controller;

import java.util.List;

import javax.sql.DataSource;

import org.freecode.demo.model.Note;
import org.freecode.demo.model.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {
	
	@Autowired
	DataSource dataSource;

	@Autowired
	NoteRepository noteResp;
	
//	private void createNote() {
//		Note aNote = new Note();
//		aNote.setCategory("Programming");
//		aNote.setSubCategory("SpringBoot");
//		aNote.setTitle("How to create SpringBoot RESTful web services");
//		aNote.setDescription("1. create Entity; 2. extend Repository; 3. create RestController; 4. create application.properties; 5. update Driver class");
//		noteResp.save(aNote);
//	}
	
	@GetMapping("/notes")
	/**
	 * http://<server>:<port>/notes
	 * @return
	 */
	public List<Note> findAllNotes() {
		return noteResp.findAll();
	}
	
	@GetMapping("/notes/{id}")
	/**
	 * http://<server>:<port>/notes/1
	 * @return
	 */
	public Note findNoteById(@PathVariable(required=true) Long id) {
		return noteResp.findById(id);
	}
	
	@PostMapping("/addNote")
	/**
	 * URL: http://<server>:<port>/addNotes
	 * @param aNote
	 */
	public void addNote(@RequestBody Note aNote) {
		noteResp.save(aNote);
	}
	
	@DeleteMapping("/notes/{id}")
	/**
	 * URL: http://<server>:<port>/notes/1
	 * @param id
	 */
	public void deleteNoteById(@PathVariable(required=true) Long id) {
		noteResp.deleteById(id);
	}
	
	@PutMapping("/notes/{id}")
	/**
	 * URL: http://<server>:<port>/notes/1
	 */
	public void updateNoteById(@RequestBody Note newNote, @PathVariable(required=true) Long id) {
		Note oldNote = noteResp.findById(id);
		oldNote.setTitle(newNote.getTitle());
		oldNote.setCategory(newNote.getCategory());
		oldNote.setSubCategory(newNote.getSubCategory());
		oldNote.setDescription(newNote.getDescription());
		
		noteResp.save(oldNote);
	}
}
