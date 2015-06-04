package tools;

import item.impl.*;
import java.util.Scanner;
import db.impl.DataBase;
import java.io.*;

class Creator
{
	public static void main(String[] argv)
	{
		String[] options = {"attack","equip","quiz"};

		if(argv.length < 1)
		{
			System.out.println("Usage: java Creator [class]\nAvailable classes to create:");
			for(String name: options)
				System.out.println("\t" + name);

			System.exit(0);
		}

		for(int i=0; i<options.length; i++)
		{
			if(argv[0].equalsIgnoreCase(options[i]))
				break;
		
			if(i == options.length - 1)
			{
				System.out.println("invalid option '" + argv[0] + "' passed. Run without arguments for more info.");
				System.exit(1);
			}
		}

		Scanner keyboard = new Scanner(System.in);
		DataBase db = DataBase.getInstance();
		String ans;

		if(argv[0].equalsIgnoreCase("consumable") || argv[0].equalsIgnoreCase("equip"))
		{
			String name,type,description;
			int quantity;
			Item item = null;

			System.out.println("give " + argv[0] + " information ...");

			type = argv[0];

			System.out.print("name: ");
			name = keyboard.nextLine();

			System.out.print("description: ");
			description = keyboard.nextLine();

			System.out.print("quantity: ");
			quantity = keyboard.nextInt();

			if(argv[0].equalsIgnoreCase("consumable"))
			{
				float hp,attack,defense;
				int duration;
					
				System.out.print("hp: ");
				hp = keyboard.nextFloat();

				System.out.print("attack: ");
				attack = keyboard.nextFloat();

				System.out.print("defense: ");
				defense = keyboard.nextFloat();

				System.out.print("duration: ");
				duration = keyboard.nextInt();

				item = (Consumable) new Consumable(name,type,description,quantity,attack,defense,duration);
			}
			else if(argv[0].equalsIgnoreCase("equip"))
			{
				int power;
				boolean equipped;
				
				System.out.print("power: ");
				power = keyboard.nextInt();
				
				System.out.print("equipped? (y/n) ");
				ans = keyboard.next();
				if(ans.equalsIgnoreCase("y"))
					equipped = true;
				else
					equipped = false;

				item = (Equip) new Equip(name,type,description,quantity,power,equipped);
			}

			System.out.print("Save object? (y/n) ");
			ans = keyboard.next();
			if(!ans.equalsIgnoreCase("y"))
				System.exit(0);

			try
			{
				db.save(item, argv[0] + "_" + item.getName() + ".ser");
			}
			catch(IOException e)
			{
				System.out.println("could not save object!");
				System.exit(1);
			}
			
			System.out.println("object succesfully saved to '" + db.getRoot() + "/" + argv[0] + "_" + item.getName() + ".ser");
		}
		else if(argv[0].equalsIgnoreCase("quiz"))
		{
			;
		}
		else if(argv[0].equalsIgnoreCase("attack"))
		{
			String name;
			Integer[] consts = new Integer[4];

			System.out.print("name of attack: ");
			name = keyboard.nextLine();
			
			System.out.print("constant of attacker for knowledge: ");
			consts[0] = keyboard.nextInt();

			System.out.print("constant of attacker for migue: ");
			consts[1] = keyboard.nextInt();

			System.out.print("constant of reactor for knowledge: ");
			consts[2] = keyboard.nextInt();

			System.out.print("constant of reactor for migue: ");
			consts[3] = keyboard.nextInt();

			System.out.print("save object? (y/n) ");
			ans = keyboard.next();
			
			if(!ans.equalsIgnoreCase("y"))
				System.exit(0);

			try
			{
				db.save(consts, argv[0] + "_" + name + ".ser");
			}
			catch(IOException e)
			{
				System.out.println("could not save object!");
				System.exit(1);
			}

			System.out.println("object succesfully saved to '" + db.getRoot() + "/" + argv[0] + "_" + name + ".ser");
		}
	}
}
