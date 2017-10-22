package org.freecode.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/demo")
public class DemoController {

	// http://<server name>:<port#>/<Context Path>/controller/demo/sayHello
	@RequestMapping(value="/sayHello", method=RequestMethod.GET)
	public String sayHello(Model aModel) {
		aModel.addAttribute("message", "Hello, world!");
		
		return "hello";
	}
}
