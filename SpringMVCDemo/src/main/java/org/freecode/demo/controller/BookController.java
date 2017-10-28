package org.freecode.demo.controller;

import javax.validation.Valid;

import org.freecode.demo.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/library")
@SessionAttributes("newBook")
public class BookController {
	
	@ModelAttribute
	public void addBookToModel(Model book) {
		if (!book.containsAttribute("newBook")) {
			Book bk = new Book();
			book.addAttribute("newBook", bk);
		}
	}

	/**
	 * URL: http://<server name>:<port#>/<Context Path>/controller/library/add
	 * @param aBook
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addBook(@Valid @ModelAttribute ("newBook") Book aBook, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result.getErrorCount() + " error(s) when addBook");
			return "addBook";
		}
		System.out.println(aBook);
		return "addBook"; // navigate to addBook.jsp
	}
	
	@RequestMapping(value="/view", method=RequestMethod.POST)
	public String viewBook(@ModelAttribute ("newBook") Book aBook, Model bookModel) {
		
		bookModel.addAttribute("bookTitle", aBook.getTitle());
		bookModel.addAttribute("bookIsbn", aBook.getIsbn());
		bookModel.addAttribute("bookPageCount", aBook.getPageCount());
		
		return "viewBook"; // navigate to viewBook.jsp
	}
}
