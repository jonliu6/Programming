package org.freecode.demo.service;

import org.freecode.demo.dao.NoteDao;
import org.freecode.demo.service.wsproxy.GetNoteByTitleRequest;
import org.freecode.demo.service.wsproxy.GetNoteByTitleResponse;
import org.freecode.demo.service.wsproxy.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class NotebookEndpoint {

	private final static String NAMESPACE_URI = "http://demo.freecode.org/schemas";
    private NoteDao noteDao;
    
    @Autowired
    public NotebookEndpoint(NoteDao aDao) {
    	this.noteDao = aDao;
    }
    
    @PayloadRoot(namespace=NAMESPACE_URI, localPart="getNoteByTitleRequest")
    @ResponsePayload
    public GetNoteByTitleResponse findNoteByTitle(@RequestPayload GetNoteByTitleRequest request) {
    	Note aNote = noteDao.findNoteByTitle(request.getTitle());
    	GetNoteByTitleResponse response = new GetNoteByTitleResponse();
    	response.setNote(aNote);
    	
    	return response;
    }
}
