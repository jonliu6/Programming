package org.freecode.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.freecode.demo.model.Question;
import org.freecode.demo.model.QuestionForm;

public class ShowAction extends Action {

	public ActionForward execute(ActionMapping mapping, 
			                     ActionForm form, 
			                     HttpServletRequest request, 
			                     HttpServletResponse response)
	{
		List questions = new ArrayList();
		Question q1 = new Question(1, "What's your name?", "TEXT");
		questions.add(q1);
		Question q2 = new Question(2, "How old are you?", "CHOICE");
		questions.add(q2);
		Question q3 = new Question(3, "Where are you?", "CHOICE");
		questions.add(q3);
		
		QuestionForm qForm = (QuestionForm) form;
		qForm.setQuestionCount(questions.size());
		
		request.setAttribute("QUESTION_LIST", questions);
		request.setAttribute("QUESTION_FORM", qForm);
				
		return mapping.findForward("success");
	}
}
