package org.freecode.demo.webservice;

import org.freecode.demo.model.Note;
import org.freecode.demo.service.NoteService;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class NoteEndPoint {
    private final static String NAMESPACE_URI = "http://demo.freecode.org/schemas";
    private NoteService noteService;
    
    @Autowired
    public NoteEndPoint(NoteService dataService) throws JDOMException {
    	this.noteService = dataService;
    }
    
    @PayloadRoot(namespace=NAMESPACE_URI, localPart="getNoteByTitleRequest")
    @ResponsePayload
    public GetNoteByTitleResponse findNoteByTitle(@RequestPayload GetNoteByTitleRequest request) throws JDOMException {
    	Note aNote = noteService.findNoteByTitle(request.getTitle());
    	GetNoteByTitleResponse response = new GetNoteByTitleResponse();
    	response.setNote(noteService.convertNoteToProxy(aNote));
    	
    	return response;
    }
}
