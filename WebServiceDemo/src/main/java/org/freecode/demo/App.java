package org.freecode.demo;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            ClientRequest request = new ClientRequest(
                    "http://localhost:8080/WebServiceDemo/rest/demo/book/add");
                request.accept("application/json");

                String input = "{\"isbn\":\"99999\",\"title\":\"Rest Book\",\"publishedOn\":1447487315336,\"pageNo\":999}";
                request.body("application/json", input);
                    
                ClientResponse<String> response = request.post(String.class);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
