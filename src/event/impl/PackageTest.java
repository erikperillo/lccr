package event.impl;

import event.ifaces.*;
import player.impl.*;
import player.excepts.*;
import db.impl.*;
import java.io.*;
import item.impl.*;

class PackageTest
{
	public static void main(String argv[]) throws Exception
	{
		DataBase db = DataBase.getInstance();

		/*teste do quiz
		String[] questions = {"voce e homem?","setembro chove?","hue?","vaisifude"};
		String[] answers = {"sim","BRBR","kjh","blz"};
		Quiz quiz = null;

		try
		{
			 quiz = new Quiz("test",questions,answers);
		}
		catch(Exception e)
		{
			System.out.println("error: " + e.getMessage());
			System.exit(1);
		}

		quiz.routine();

		System.out.println("voce acertou " + quiz.getRightAnswers() + " de " + quiz.getTotalQuestions());
		*/

		Player player = null, enemy = null;
		Integer[] amor,bjok;
		ConsumableFactory consumable = new ConsumableFactory();
		EquipFactory equipf = new EquipFactory();
		Consumable cons = null;
		Equip equip = null;

		try
		{
			player = PlayerMaker.getPlayer("nerd","joao");	
			enemy = PlayerMaker.getPlayer("varzea","maria");
		}
		catch(UnknownPlayerTypeException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}

		//amor = (Integer[])db.load("attack_amor.ser");
		//bjok = (Integer[])db.load("attack_beijo na boca.ser");	

		cons = (Consumable)consumable.getItem("vodka");
		equip = (Equip)equipf.getItem("hue");

		player.getAttacks().add("attack_amor.ser");
		enemy.getAttacks().add("attack_beijo na boca.ser");

		player.addItem(cons);
		player.addItem(equip);

		Fight fight = new Fight("b0ss",player,enemy,4);

		fight.routine();
	}
}
