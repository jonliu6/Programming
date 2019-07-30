package org.freecode.demo.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository; // or CrudRepository

public interface NoteRepository extends Repository<Note, Long> {
	void delete(Note note);
	void deleteById(Long id);
	Note save(Note saved);
	Note findById(Long id);
	List<Note> findAll();
	Page<Note> findAll(Pageable pageable);
}
