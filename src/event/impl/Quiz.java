package event.impl;

import event.ifaces.IEvent;
import java.util.*;
import java.lang.*;
import java.io.Serializable;

public class Quiz implements IEvent, Serializable
{
	private String name;
	private String[] questions;
	private String[][] answers;
	private int rigth_answers;

	public Quiz(String name, String[] questions, String[][] answers) throws Exception
	{
		if(questions.length != answers.length)
			throw new Exception("Number of questions and answers must match");

		this.questions = questions;
		this.answers = answers;
		this.name = name;
		this.rigth_answers = 0;
	}

	public String getName()
	{	
		return this.name;
	}

	public void routine()
	{
		Scanner keyboard = new Scanner(System.in);
		String ans, right_ans;
		List<String> shuffled_answers;

		for(int i=0; i<questions.length; i++)
		{
			right_ans = this.answers[i][0];
			shuffled_answers = Arrays.asList(this.answers[i]);
			Collections.shuffle(shuffled_answers);

			System.out.println("QUESTAO " + (i+1) + ":");
			System.out.println("\t" + this.questions[i]);
			for(int j=0; j<shuffled_answers.size(); j++)
			{
				System.out.println("\t" + (char)('a' + j) + ") " + shuffled_answers.get(j));
				if(shuffled_answers.get(j).equalsIgnoreCase(right_ans))
					right_ans = Character.toString((char)('a' + j));
			}
			System.out.print("\t>>> ");

			ans = keyboard.nextLine();	
			
			if(ans.equalsIgnoreCase(right_ans))
			{
				System.out.println("Certa respostammmmm!");
				rigth_answers++;
			}
			else
				System.out.println("Errou!");

			System.out.println();
		}
	}

	public int getRightAnswers()
	{
		return this.rigth_answers;
	}

	public int getTotalQuestions()
	{
		return this.questions.length;
	}
}
