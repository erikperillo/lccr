package tools;

import item.impl.*;
import java.util.Scanner;
import db.impl.DataBase;
import java.io.*;

class ItensCreator
{
	public static void main(String[] argv)
	{
		if(argv.length < 1)
		{
			System.out.println("Usage: java ItensCreator [consumable | equip]");
			System.exit(0);
		}
		if(!argv[0].equalsIgnoreCase("consumable") && !argv[0].equalsIgnoreCase("equip"))
		{
			System.out.println("invalid option '" + argv[0] + "' passed. Run without arguments for more info.");
			System.exit(1);
		}

		String name,type,description,ans;
		int quantity;
		Item item = null;
		Scanner keyboard = new Scanner(System.in);

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

		DataBase db = DataBase.getInstance();
		try
		{
			db.save(item,"item_" + item.getName() + ".ser");
		}
		catch(IOException e)
		{
			System.out.println("could not save object!");
			System.exit(1);
		}
		
		System.out.println("object succesfully saved to '" + DataBase.getRoot() + "/" + "item_" + item.getName() + ".ser");
	}
}
