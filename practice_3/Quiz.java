package git_collab.practice_3;

import java.util.HashMap;

public class Quiz {
	
	private HashMap<Integer, Question> questions;
	
	public Quiz() {
		questions = new HashMap<>();
	}

	public Quiz(HashMap<Integer, Question> question) {
		super();
		this.questions = question;
	}

	public HashMap<Integer, Question> getQuestions() {
		return questions;
	}

	public void setQuestions(HashMap<Integer, Question> questions) {
		this.questions = questions;
	}
	
	public void addQuestion(Question q) {
		questions.put(q.getId(), q);		
	}

}
