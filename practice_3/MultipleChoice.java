package git_collab.practice_3;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoice extends MCQ {

	private List<String> answers;

	public MultipleChoice() {
		super();
	}

	public MultipleChoice(Integer id,String question, List<String> options, List<String> answers) {
		super(id,question, "", options);
		this.answers = answers;
	}

	public List<String> getAnswers() {
		return  answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
	
	
	
	
	
}
