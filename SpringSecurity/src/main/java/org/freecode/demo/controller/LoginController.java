package org.freecode.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
//	@RequestMapping(value="/login", method=RequestMethod.GET)
//	public String login(Model model) {
//        System.out.println("login handler invoked");
//        
//        return "login";
//	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET) 
	public ModelAndView login(@RequestParam(value="error", required=false) String err, @RequestParam(value="logout", required=false) String out) {
        System.out.println("login handler invoked");
        ModelAndView model = new ModelAndView();
        if (err != null) {
        	model.addObject("error", "Invalid login or password!");
        }
        if (out != null) {
        	model.addObject("msg", "Bye for now.");
        }
        model.setViewName("login"); // prefix with "/view/" and suffix with ".jsp"
        return model; 
	}
	
	@RequestMapping(value="/loginFail", method=RequestMethod.GET)
	public String loginFail(Model model) {
		System.out.println("loginFail handler invoked");
		
		return "login";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("logout handler invoked");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	@RequestMapping(value={"/","/home"}, method=RequestMethod.GET) // the handler HTTP method should be matched with the form action
	public String gotoHome(Model model) {
		System.out.println("Go to home page");
		
		return "home"; // prefix with "/view/" and suffix with ".jsp"
	}

}
