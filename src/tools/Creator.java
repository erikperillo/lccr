package tools;

import item.impl.*;
import java.util.Scanner;
import db.impl.DataBase;
import java.io.*;

class Creator
{
	public static void main(String[] argv) throws FileNotFoundException
	{
		String[] options = {"attack","equip","consumable","quiz"};

		if(argv.length < 1)
		{
			System.out.println("Usage: java Creator [class] (file)\nAvailable classes to create:");
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

		Scanner scanner;
		DataBase db = DataBase.getInstance();
		String ans;

		if(argv.length > 1)
			scanner = new Scanner(new File(argv[1]));
		else
			scanner = new Scanner(System.in);

		if(argv[0].equalsIgnoreCase("consumable") || argv[0].equalsIgnoreCase("equip"))
		{
			String name,type,description;
			int quantity;
			Item item = null;

			if(argv.length < 2) System.out.println("give " + argv[0] + " information ...");

			type = argv[0];

			if(argv.length < 2) System.out.print("name: ");
			name = scanner.nextLine();

			if(argv.length < 2) System.out.print("description: ");
			description = scanner.nextLine();

			if(argv.length < 2) System.out.print("quantity: ");
			quantity = scanner.nextInt();

			if(argv[0].equalsIgnoreCase("consumable"))
			{
				float hp,attack,defense;
				int duration;
					
				if(argv.length < 2) System.out.print("hp: ");
				hp = scanner.nextFloat();

				if(argv.length < 2) System.out.print("attack: ");
				attack = scanner.nextFloat();

				if(argv.length < 2) System.out.print("defense: ");
				defense = scanner.nextFloat();

				if(argv.length < 2) System.out.print("duration: ");
				duration = scanner.nextInt();

				item = (Consumable) new Consumable(name,type,description,quantity,hp,attack,defense,duration);
			}
			else if(argv[0].equalsIgnoreCase("equip"))
			{
				int power;
				boolean equipped;
				
				if(argv.length < 2) System.out.print("power: ");
				power = scanner.nextInt();
				
				if(argv.length < 2) System.out.print("equipped? (y/n) ");
				ans = scanner.next();
				if(ans.equalsIgnoreCase("y"))
					equipped = true;
				else
					equipped = false;

				item = (Equip) new Equip(name,type,description,quantity,power,equipped);
			}

			try
			{
				db.save(item, argv[0] + "_" + item.getName() + ".ser");
			}
			catch(IOException e)
			{
				System.out.println("could not save object!");
				System.exit(1);
			}
			
			System.out.println("object succesfully saved to '" + db.getRoot() + "/" + argv[0] + "_" + item.getName() + ".ser'");
		}
		else if(argv[0].equalsIgnoreCase("quiz"))
		{
			System.out.println("not implemented yet :)");
		}
		else if(argv[0].equalsIgnoreCase("attack"))
		{
			String name;
			Integer[] consts = new Integer[4];

			if(argv.length < 2) System.out.print("name of attack: ");
			name = scanner.nextLine();
			
			if(argv.length < 2) System.out.print("constant of attacker for knowledge: ");
			consts[0] = scanner.nextInt();

			if(argv.length < 2) System.out.print("constant of attacker for migue: ");
			consts[1] = scanner.nextInt();

			if(argv.length < 2) System.out.print("constant of reactor for knowledge: ");
			consts[2] = scanner.nextInt();

			if(argv.length < 2) System.out.print("constant of reactor for migue: ");
			consts[3] = scanner.nextInt();

			try
			{
				db.save(consts, argv[0] + "_" + name + ".ser");
			}
			catch(IOException e)
			{
				System.out.println("could not save object!");
				System.exit(1);
			}

			System.out.println("object succesfully saved to '" + db.getRoot() + "/" + argv[0] + "_" + name + ".ser'");
		}
	}
}
