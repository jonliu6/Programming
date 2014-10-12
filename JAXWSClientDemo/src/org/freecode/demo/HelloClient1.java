package org.freecode.demo;

import org.apache.axis2.client.ServiceClient;


public class HelloClient1 {
	// wsimport http://localhost:8080/JAXWS/SayingHello?WSDL -Xnocompile -d c:/Temp -p org.freecode.demo
// genStubFromWsdlUsingAxis2.sh --location=http://localhost:7001/JAXWS/SayingHello?WSDL --output=c:/temp/stubs --package=org.freecode.demo
	public static void main(String[] args)
	{
		try
		{
			String wsdlURL = "http://localhost:7001/JAXWS/SayingHello?WSDL";
		SayingHelloStub stub = new SayingHelloStub(wsdlURL);
		
		ServiceClient serviceClient = stub._getServiceClient();
		// serviceClient.addStringHeader(headerName, headerText)
		// stub._getServiceClient().getOptions().setProperty("jaxb-validation-event-handler", false);
		SayingHelloStub.SayHello requestWrapper = new SayingHelloStub.SayHello();
		requestWrapper.setAName("Fujitsu");
        SayingHelloStub.SayHello1 request = new SayingHelloStub.SayHello1();
        request.setSayHello( requestWrapper );
		SayingHelloStub.SayHelloResponse0 responseWrapper = stub.sayHello(request);
		
		SayingHelloStub.SayHelloResponse response = responseWrapper.getSayHelloResponse();
		System.out.println( response.getSayHelloResponse() );
			
//			SayingHello_Service service = new SayingHello_Service(new URL("http://localhost:8080/JAXWS/SayingHello?WSDL"));
//			SayingHello hello = service.getSayingHelloPort();
//			System.out.print( hello.sayHello("Jonathan") );
			
		}
		catch (Exception ex)
		{
            ex.printStackTrace();
		}
	}

}
