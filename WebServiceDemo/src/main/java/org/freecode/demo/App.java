package org.freecode.demo;

import java.util.Date;

import org.freecode.demo.entity.Book;
import org.freecode.demo.rest.RESTWebService;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.UriBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
        	UriBuilder FULL_PATH = UriBuilder.fromPath("http://localhost:8080/WebServiceDemo/rest/demo/book/add");
        	ResteasyClient client = (ResteasyClient)ClientBuilder.newClient();
        	ResteasyWebTarget target = client.target(FULL_PATH);
        	
        	RESTWebService proxy = target.proxy(RESTWebService.class);
        	
//                request.accept("application/json");
//
//                String input = "{\"isbn\":\"99999\",\"title\":\"Rest Book\",\"publishedOn\":1447487315336,\"pageNo\":999}";
//                request.body("application/json", input);
//                    
//                ClientResponse<String> response = request.post(String.class);
        	
        	proxy.addBook(new Book("99999", "Rest Book", new Date(), 999));
        	
        	//@@TODO handle web service responses
        	
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
