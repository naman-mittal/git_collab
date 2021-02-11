package git_collab.practice_3;

import java.util.ArrayList;
import java.util.List;

public abstract class MCQ extends Question {

	private List<String> options;

	protected MCQ() {
		super();
	}

	public MCQ(Integer id,String question, String answer, List<String> options) {
		super(id,question, answer);
		this.options = options;
	}

	public ArrayList<String> getOptions() {
		return (ArrayList<String>) options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}
	
}