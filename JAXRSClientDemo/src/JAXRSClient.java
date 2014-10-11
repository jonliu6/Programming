import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;


public class JAXRSClient {

	public static void main( String[] args )
	{
		JAXRSClient client = new JAXRSClient();
		
		client.testGet();
	}
	
	public void testGet()
	{
		ClientRequest request = new ClientRequest("http://localhost:8080/JAXRSDemo2/rest/hello");
		ClientResponse response = null;
		
		try
		{
			response = request.get();
			System.out.println( response.getEntity(String.class) );
		}
		catch ( Exception ex )
		{
			ex.printStackTrace();
		}
		
		
	}
}
