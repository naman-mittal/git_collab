package git_collab.practice_3;

public abstract class Question {

	private Integer id;
	private String question;
	private String answer;
	
	public Question() {
		super();
	}

	public Question(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
	}

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
	
	
	
	
}
