package org.freecode.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// @Controller - Spring MVC v3.0
@RestController
@RequestMapping("/hello")
public class HelloController {

	@RequestMapping(method=RequestMethod.GET, produces="text/plain")
	public String sayHello()
	{
		return "Hello";
	}
}
