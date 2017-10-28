package org.freecode.demo.controller;

import org.freecode.demo.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/library")
public class BookController {

	/**
	 * URL: http://<server name>:<port#>/<Context Path>/controller/library/add
	 * @param aBook
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addBook(@ModelAttribute ("newBook") Book aBook) {
		// System.out.println(aBook);
		return "addBook"; // navigate to addBook.jsp
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String viewBook(Model bookModel) {
		bookModel.addAttribute("bookTitle", "New Title");
		bookModel.addAttribute("bookIsbn", "New ISBN");
		bookModel.addAttribute("bookPageCount", "New Page Count");
		
		return "viewBook"; // navigate to viewBook.jsp
	}
}
