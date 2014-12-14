package com.example.thepiproject.speed;



public class QuestionGame1 {
	private  String question;
	private String answer;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {

		return String.format("%s   %s", question, answer);
	}
	
}
