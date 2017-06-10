package org.freecode.demo;

import javax.jws.WebService;

//Service Implementation  
@WebService(endpointInterface = "org.freecode.demo.HelloWorld")  
public class HelloWorldImpl implements HelloWorld{  
    @Override  
    public String getHelloWorldAsString(String name) {  
        return "Hello World JAX-WS " + name;  
    }  
}  