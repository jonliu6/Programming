package org.freecode.demo;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService(name="SayingHello", serviceName="SayingHello", targetNamespace="http://www.bchydro.com/CMWeb")
@SOAPBinding(style=SOAPBinding.Style.DOCUMENT, use= SOAPBinding.Use.LITERAL, parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
public class HelloService {

	@WebMethod(operationName="sayHello")
	@WebResult(name="sayHelloResponse")
	public String sayHello(@WebParam(name="aName") String name )
	{
		return "Hello " + name;
	}
}
