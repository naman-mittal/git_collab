package git_collab.practice_3;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		Quiz quiz = new Quiz();
		QuizTaker quizTaker = new QuizTaker();
		
		
		Question q1 = new FillInTheBlank(101, "Java is a _____ language", "Programming");
		
		Question q2 = new FillInTheBlank(102, "PHP is a _____ language", "Scripting");
		
		
		Question q3 = new SingleChoice(103, "Which is the base class of all classes ? ", "Object", Arrays.asList("Base","Object","Parent","None"));

		Question q4 = new SingleChoice(103, "Which is a light weight process ? ", "Thread", Arrays.asList("Main","Any process","Thread","None"));
		
		Question q5 = new MultipleChoice(105, "Which of these dont't allow duplicates ? ", Arrays.asList("HashSet","ArrayList","HashMap","LinkedHashSet"), Arrays.asList("HashSet","HashMap","LinkedHashSet"));
		
		Question q6 = new Numeric(106, "What is square root of 5 ? ", "2.23");
		
		quiz.addQuestion(q1);
		quiz.addQuestion(q2);
		quiz.addQuestion(q3);
		quiz.addQuestion(q4);
		quiz.addQuestion(q5);
		quiz.addQuestion(q6);
		
		quizTaker.takeQuiz(quiz);
		
		System.out.println("Score = " + quizTaker.getScore()+"/"+quiz.getQuestions().size());
		
	}

}
