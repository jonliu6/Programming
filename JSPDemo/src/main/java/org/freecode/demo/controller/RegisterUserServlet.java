package org.freecode.demo.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
