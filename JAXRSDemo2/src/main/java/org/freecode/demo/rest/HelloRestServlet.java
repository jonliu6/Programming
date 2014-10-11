package org.freecode.demo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloRestServlet {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello()
	{
		return "Hello, RestEasy!";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{name}")
	public String sayHello(@PathParam("name") String aName )
	{
		return "Hello, " + aName;
	}
}
