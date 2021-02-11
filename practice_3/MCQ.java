package practice_3;

import java.util.List;

public abstract class MCQ extends Question {

	private List<String> options;

	protected MCQ() {
		super();
	}

	public MCQ(String question, String answer, List<String> options) {
		super(question, answer);
		this.options = options;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}
	
	
	
}
