package org.freecode.demo.model;

public class Question {

	private Integer questionId;
	private String questionText;
	private String questionType;
	
	public Question()
	{
		
	}
	
	public Question(Integer id, String text, String type)
	{
		questionId = id;
		questionText = text;
		questionType = type;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	
	
}
