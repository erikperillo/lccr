package event.impl;

import event.ifaces.*;

class PackageTest
{
	public static void main(String argv[]) throws Exception
	{
		String[] questions = {"voce e homem?","setembro chove?","hue?","vaisifude"};
		String[] answers = {"sim","opa","BRBR","blz"};

		Quiz quiz = new Quiz(questions,answers);

		quiz.routine();

		System.out.println("voce acertou " + quiz.getRightAnswers() + " de " + quiz.getTotalQuestions());
	}
}
