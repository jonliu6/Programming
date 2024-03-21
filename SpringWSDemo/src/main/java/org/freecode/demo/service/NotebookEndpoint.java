package org.freecode.demo.service;

import org.freecode.demo.dao.NoteDao;
import org.freecode.demo.model.Note;
import org.freecode.demo.service.wsproxy.GetNoteByTitleRequest;
import org.freecode.demo.service.wsproxy.GetNoteByTitleResponse;
import org.freecode.demo.service.wsproxy.NoteProxy;
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
    public NotebookEndpoint(NoteDao aDao) throws Exception {
    	this.noteDao = aDao;
    }
    
    @PayloadRoot(namespace=NAMESPACE_URI, localPart="getNoteByTitleRequest")
    @ResponsePayload
    public GetNoteByTitleResponse findNoteByTitle(@RequestPayload GetNoteByTitleRequest request) throws Exception {
    	Note aNote = noteDao.findNoteByTitle(request.getTitle());
    	GetNoteByTitleResponse response = new GetNoteByTitleResponse();
    	response.setNote(convertNoteToProxy(aNote));
    	
    	return response;
    }
    
    private NoteProxy convertNoteToProxy(Note aNote) {
		NoteProxy proxy = new NoteProxy();
		proxy.setTitle(aNote.getTitle());
		proxy.setCategory(aNote.getCategory());
		proxy.setSubCategory(aNote.getSubCategory());
		proxy.setDescription(aNote.getDescription());
		
		return proxy;
	}
}
