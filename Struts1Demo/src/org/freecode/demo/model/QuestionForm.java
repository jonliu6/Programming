package org.freecode.demo.model;

import org.apache.struts.action.ActionForm;

public class QuestionForm extends ActionForm{
	
	private String userName;
	private int questionCount;
	
	public QuestionForm()
	{
		super();
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public void setUserName (String aName)
	{
		userName = aName;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	
}
