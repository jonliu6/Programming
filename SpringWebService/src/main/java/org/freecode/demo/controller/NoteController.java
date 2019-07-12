package org.freecode.demo.controller;

import java.util.List;

import org.freecode.demo.model.Note;
import org.freecode.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="noteRS")
public class NoteController {
	
	@Autowired
	private NoteService noteService; 
	
	@GetMapping(value="/notes", produces = MediaType.APPLICATION_JSON_VALUE)
	/**
	 * URL: http://<server>:<port>/<context path>/rs/noteRS/notes
	 * context path: in pom.xml as the .war file by default; or can be specified in pom.xml for build
	 * by default, CrossOrigin allows all
	 * @return
	 */
	@CrossOrigin
	@ResponseBody
	public List<Note> findAllNotes() {
		return noteService.findAll();
	}
	
	@PostMapping(value="/notes/add", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void addNote(@RequestBody Note aNote) {
		noteService.addNote(aNote);
	}
	
	@DeleteMapping(value="/notes/{title}")
	public void deleteNote(@PathVariable String title) {
		noteService.deleteNoteByTitle(title);
	}
}
