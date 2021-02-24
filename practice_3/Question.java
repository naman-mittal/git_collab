package git_collab.practice_3;

public abstract class Question {

	private Integer id;
	private String question;
	private String answer;
	
	public Question() {
		super();
	}

	public Question(Integer id,String question, String answer) {
		super();
		this.id = id;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
}
