package org.freecode.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model) {
        System.out.println("login handler invoked");
        
        return "login";
	}
	
	@RequestMapping(value="/loginFail", method=RequestMethod.GET)
	public String loginFail(Model model) {
		System.out.println("loginFail handler invoked");
		
		return "login";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(Model model) {
		System.out.println("logout handler invoked");
		
		return "logout";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String gotoHome(Model model) {
		System.out.println("Go to home page");
		
		return "home";
	}

}
