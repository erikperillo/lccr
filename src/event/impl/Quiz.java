package event.impl;

import event.ifaces.IEvent;
import java.util.*;
import java.lang.*;

public class Quiz implements IEvent
{
	private String name;
	private String[] questions;
	private String[] answers;
	private int rigth_answers = 0;

	public Quiz(String[] questions, String[] answers) throws Exception
	{
		if(questions.length != answers.length)
			throw new Exception("Number of questions and answers must match");

		this.questions = questions;
		this.answers = answers;
	}

	public String getName()
	{	
		return this.name;
	}

	public void routine()
	{
		Scanner keyboard = new Scanner(System.in);
		String ans;

		for(int i=0; i<questions.length; i++)
		{
			System.out.println("QUESTAO " + i + ":");
			System.out.println("\t" + questions[i]);
			System.out.print("\t>>> ");

			ans = keyboard.nextLine();	
			
			if(ans.equalsIgnoreCase(answers[i]))
			{
				System.out.println("certa respostammmmm");
				rigth_answers++;
			}
			else
				System.out.println("errou");
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
