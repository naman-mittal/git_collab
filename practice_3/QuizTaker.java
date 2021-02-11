package git_collab.practice_3;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class QuizTaker {

	private Integer score ;
	private Integer questionCount;
	Scanner scanner;
	
	
	public QuizTaker() {
		super();
		score  = 0;
		questionCount = 0;
		scanner = new Scanner(System.in);
		System.out.println("Quiz started");
	}

	public  void takeQuiz(Quiz quiz)
	{
		
		
		for(Question question : quiz.getQuestions())
		{
			questionCount++;
			
			if(question instanceof FillInTheBlank)
			handleFillInTheBlank(question);
			else if(question instanceof SingleChoice)
				handleSingleChoice(question);
			else if(question instanceof MultipleChoice)
				handleMultipleChoice(question);
			else if(question instanceof Numeric)
				handleNumeric(question);
		}
		
	}

	private void handleMultipleChoice(Question question) {
		
		System.out.println(questionCount +" "+question.getQuestion());
		
		ArrayList<String> options = ((SingleChoice) question).getOptions();
		
		Collections.shuffle(options);
		
		for(int i = 0 ; i < options.size(); i++)
		{
			System.out.println((i+1)+" " + options.get(i));
		}
		
		System.out.println("Enter answer");
		
		
	}

	private void handleNumeric(Question question) {
		// TODO Auto-generated method stub
		
	}

	private void handleSingleChoice(Question question) {
		
		System.out.println(questionCount +" "+question.getQuestion());
		
		ArrayList<String> options = ((SingleChoice) question).getOptions();
		
		Collections.shuffle(options);
		
		for(int i = 0 ; i < options.size(); i++)
		{
			System.out.println((i+1)+" " + options.get(i));
		}
		
		System.out.println("Enter answer");
		
		String answer = scanner.next();
		
		if(answer.equalsIgnoreCase(question.getAnswer()))
		{
			System.out.println("Correct");
			score++;
		}
		else
			System.out.println("Incorrect");
		
		
	}

	private void handleFillInTheBlank(Question question) {
		
		System.out.println(questionCount +" "+question.getQuestion());
		
		System.out.println("Enter answer");
		
		String answer = scanner.next();
		
		if(answer.equalsIgnoreCase(question.getAnswer()))
		{
			System.out.println("Correct");
			score++;
		}
		else
			System.out.println("Incorrect");
			
		
	}

	
}
