package org.freecode.demo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name="SayingHello", serviceName="SayingHello", targetNamespace="http://demo.freecode.org/JAXWS")
@SOAPBinding(style=SOAPBinding.Style.DOCUMENT, use= SOAPBinding.Use.LITERAL, parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
public class HelloService {

	@WebMethod(operationName="sayHello")
	@WebResult(name="sayHelloResponse")
	public String sayHello(@WebParam(name="aName") String name )
	{
		return "Hello " + name;
	}
}
