package git_collab.practice_3;

import java.util.HashMap;

public class Quiz {
	
	private HashMap<Integer, Question> question;
	
	public Quiz() {}

	public Quiz(HashMap<Integer, Question> question) {
		super();
		this.question = question;
	}

	public HashMap<Integer, Question> getQuestion() {
		return question;
	}

	public void setQuestion(HashMap<Integer, Question> question) {
		this.question = question;
	}
	
	public void addQuestion(int key, Question q) {
		question.put(key, q);		
	}

}
