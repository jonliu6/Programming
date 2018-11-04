package org.freecode.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet; // introduced from Servlet API 3.0, replacing the description in web.xml
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.freecode.demo.entity.KnowledgeCategory;
import org.freecode.demo.model.KnowledgeBaseDao;

@WebServlet(name="KnowledgeBaseBoundary", urlPatterns = "/knowledgeBaseController")
public class KnowledgeBaseController extends HttpServlet {

	private KnowledgeBaseDao kbDao = new KnowledgeBaseDao();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String categoryId = req.getParameter("id");
		List<KnowledgeCategory> categories = null;
		if (categoryId == null || categoryId.trim().length() < 1) { //http://localhost:8080/MyNotebook/knowledgeBaseController
			categories = kbDao.getAllCategory();
		}
		else { // http://localhost:8080/MyNotebook/knowledgeBaseController?id=2
			categories = new ArrayList<KnowledgeCategory>();
			categories.add(kbDao.getCategoryById(Integer.valueOf(categoryId)));
		}
		
		if (categories != null) {
			req.setAttribute("categories", categories);
		}
		RequestDispatcher disp = req.getRequestDispatcher("/listCategories.jsp");
		disp.forward(req,  resp);
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
