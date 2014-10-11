package org.freecode.demo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import weblogic.jws.WLHttpTransport;

@WebService(name="SayingHello", serviceName="SayingHello", portName="SayingHelloPort", targetNamespace="http://demo.freecode.org/JAXWS")
@SOAPBinding(style=SOAPBinding.Style.DOCUMENT, use= SOAPBinding.Use.LITERAL, parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
// @WLHttpTransport(contextPath="JAXWS", serviceUri="/SayingHello",portName="SayingHelloPort")
public class HelloService {

	@WebMethod(operationName="sayHello")
	@WebResult(name="sayHelloResponse")
	public String sayHello(@WebParam(name="aName") String name )
	{
		return "Hello " + name;
	}
}
