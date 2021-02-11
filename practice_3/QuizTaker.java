package git_collab.practice_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
		
		
		for(Question question : quiz.getQuestions().values())
		{
			questionCount++;
			
			System.out.println("Q" +questionCount +". "+question.getQuestion());

			
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

	public Integer getScore() {
		return score;
	}

	private void handleMultipleChoice(Question question) {
		
		
		List<String> options = ((MultipleChoice) question).getOptions();

		List<String> expectedAnswers = ((MultipleChoice) question).getAnswers();
		
		Collections.shuffle(options);
		
		for(int i = 0 ; i < options.size(); i++)
		{
			System.out.println((i+1)+" " + options.get(i));
		}
		
		System.out.println("Enter answers seperated by space");
		
		List<String> answers = Arrays.asList(scanner.nextLine().split(" "));
		
		scanner.nextLine();
		
		if(answers.equals(expectedAnswers))
		{
			System.out.println("Correct");
			score++;
		}
		else
			System.out.println("Incorrect");
	}

	private void handleNumeric(Question question) {
		
		//System.out.println(questionCount +". "+question.getQuestion());
		
		System.out.println("Enter answer");
		
		Double answer = scanner.nextDouble();
		Double expectedAnswer = Double.parseDouble(question.getAnswer());
		
		if(answer.equals(expectedAnswer))
		{
			System.out.println("Correct");
			score++;
		}
		else
			System.out.println("Incorrect");
		
		
	}

	private void handleSingleChoice(Question question) {
		
	//	System.out.println(questionCount +". "+question.getQuestion());
		
		List<String> options = ((SingleChoice) question).getOptions();
		
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
		
	//	System.out.println(questionCount +". "+question.getQuestion());
		
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
