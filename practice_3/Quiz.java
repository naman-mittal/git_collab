package git_collab.practice_3;

import java.util.List;

public class Quiz {
	List<String> fillUpsAns;
	List<String> mcqAns;
	List<Integer> numAns;
	
	public Quiz() {}
	
	public Quiz(List<String> fillUpsAns, List<String> mcqAns, List<Integer> numAns) {
		super();
		this.fillUpsAns = fillUpsAns;
		this.mcqAns = mcqAns;
		this.numAns = numAns;
	}

	public List<String> getFillUpsAns() {
		return fillUpsAns;
	}

	public void setFillUpsAns(List<String> fillUpsAns) {
		this.fillUpsAns = fillUpsAns;
	}

	public List<String> getMcqAns() {
		return mcqAns;
	}

	public void setMcqAns(List<String> mcqAns) {
		this.mcqAns = mcqAns;
	}

	public List<Integer> getNumAns() {
		return numAns;
	}

	public void setNumAns(List<Integer> numAns) {
		this.numAns = numAns;
	}
	
//	public double totalMarks(Quiz q) {
//		
//	}
	
	

}
