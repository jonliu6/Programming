package org.freecode.demo;

import org.freecode.demo.service.wsproxy.GetNoteByTitleRequest;
import org.freecode.demo.service.wsproxy.GetNoteByTitleResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class NoteServiceClient extends WebServiceGatewaySupport {

	public GetNoteByTitleResponse getNoteByTitle(String title) {
		String wsdlUrl = "http://localhost:8080/ws/noteWS.wsdl"; // web service wsdl URL
		GetNoteByTitleRequest request = new GetNoteByTitleRequest();
		request.setTitle(title);
		GetNoteByTitleResponse response = (GetNoteByTitleResponse) getWebServiceTemplate().marshalSendAndReceive(wsdlUrl, request);
		return response;
	}
}
