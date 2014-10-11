package org.freecode.demo;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class HelloClient {
	// wsimport http://localhost:8080/JAXWS/SayingHello?WSDL -Xnocompile -d c:/Temp -p org.freecode.demo
	public static void main(String[] args)
	{
		try
		{
		SayingHelloStub stub = new SayingHelloStub("http://localhost:7001/JAXWS/SayingHello?WSDL");
		stub._getServiceClient().getOptions().setTimeOutInMilliSeconds( 60000 );
		SayingHelloStub.SayHello requestWrapper = new SayingHelloStub.SayHello();
        SayingHelloStub.SayHello1 request = new SayingHelloStub.SayHello1();
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
