package org.freecode.demo.controller;

import org.freecode.demo.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/demo")
public class DemoController {

	// http://<server name>:<port#>/<Context Path>/controller/demo/sayHello
	@RequestMapping(value="/sayHello", method=RequestMethod.GET)
	public String sayHello(Model aModel) {
		aModel.addAttribute("message", "Hello, world!");
		
		return "hello";
	}
	
	// http://<server name>:<port#>/<Context Path>/controller/demo/sayHi?name=John
	@RequestMapping(value="/sayHi", method=RequestMethod.GET)
	/**
	 * Main difference of using ModelAndView instead of Model (interface) or ModelMap(implementation) is 
	 * ModelAndView returns both Model and View as an object
	 * URL: http://<server name>:<port#>/<Context Path>/controller/demo/sayHi?name=xxx
	 * @param userName
	 * @return
	 */
	public ModelAndView sayHi(@RequestParam(name="name") String userName) {
		
		return new ModelAndView("hello", "message", "Hi " + userName);
	}
	
	@RequestMapping(value="/makeBook", method=RequestMethod.GET)
	/**
	 * URL: http://<server name>:<port#>/<Context Path>/controller/demo/makeBook?title=x&isbn=y&pageCount=z
	 * @param title
	 * @param isbn
	 * @param pageCount
	 * @return
	 */
	public ModelAndView makeBook(@RequestParam("title") String title, 
                                 @RequestParam("isbn") String isbn,
                                 @RequestParam("pageCount") Integer pageCount) {
		Book bk= new Book();
		bk.setTitle(title);
		bk.setIsbn(isbn);
		bk.setPageCount(pageCount);
		return new ModelAndView("demoBook", "book", bk);
	}
}
