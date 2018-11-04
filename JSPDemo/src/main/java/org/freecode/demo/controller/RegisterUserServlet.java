package org.freecode.demo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="RegisterUserController", urlPatterns="/registerUser")
public class RegisterUserServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("first_name"); // from element name, not id
		String lastName = req.getParameter("last_name");
		
		req.setAttribute("userFirstName", firstName);
		req.setAttribute("userLastName", lastName);
				
		RequestDispatcher dispatcher = req.getRequestDispatcher("/listUsers.jsp");
		dispatcher.forward(req,  resp);
	}
}
