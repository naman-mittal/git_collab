package git_collab.practice_3;

import java.util.ArrayList;
import java.util.List;

public class Grade {
	
	private List<Quiz> quizzes;
	
	public Grade() {
		quizzes = new ArrayList<>();
	}
	
	
	
	
	public List<Quiz> getQuizzes() {
		return quizzes;
	}



	public void setQuizzes(List<Quiz> quizzes) {
		this.quizzes = quizzes;
	}

	public void addQuiz(Quiz quiz)
	{
		quizzes.add(quiz);
	}

	
	

}
