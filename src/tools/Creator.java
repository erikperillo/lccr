package tools;

import java.util.Scanner;
import db.impl.DataBase;
import event.impl.*;
import item.impl.*;
import java.io.*;
import java.lang.*;
import player.impl.*;
import player.ifaces.*;
import map.impl.*;

class Creator
{
	public static void main(String[] argv) throws Exception, FileNotFoundException
	{
		String[] options = {"npc","attack","equip","consumable","quiz","room","randomev"};

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
			int n_questions, n_ans = 4;
			Quiz quiz;
			String name;
			String[][] answers;
			String[] questions;

			if(argv.length < 2) System.out.print("quiz name: ");
			name = scanner.nextLine();

			if(argv.length < 2) System.out.print("how many questions? ");
			n_questions	= Integer.parseInt(scanner.nextLine());

			questions = new String[n_questions];
			answers = new String[n_questions][n_ans];

			for(int i=0; i<n_questions; i++)
			{
				if(argv.length < 2) System.out.print("question " + (i+1) + ": ");
				questions[i] = scanner.nextLine();

				for(int j=0; j<n_ans; j++)
				{
					if(argv.length < 2) System.out.print("\tanswer " + (j+1) + " out of " + n_ans + ((j==0)?" (correct)":"") + ": ");
					answers[i][j] = scanner.nextLine();	
				}
			}

			quiz = new Quiz(name,questions,answers);
			db.save(quiz,"quiz_" + name + ".ser");
			
			System.out.println("Quiz succesfully saved to '" + db.getRoot() + "/quiz_" + name + ".ser'");
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
		else if(argv[0].equalsIgnoreCase("npc"))
		{
			
			String name, type, message; 
			String[] equip_names, consumable_names, attack_names; 
			NPC npc;
			PlayerBuilderDirector director = new PlayerBuilderDirector();
			NPCBuilder builder;
			int n;
			float cr,migue,knowledge;
				
			if(argv.length < 2) System.out.print("name of npc: ");
			name = scanner.nextLine();

			if(argv.length < 2) System.out.print("type of npc (evil/normal): ");
			type = scanner.nextLine();

			if(argv.length < 2) System.out.print("message of npc: ");
			message = scanner.nextLine();

			if(argv.length < 2) System.out.print("cr: ");
			cr = Float.parseFloat(scanner.nextLine());

			if(argv.length < 2) System.out.print("knowledge: ");
			knowledge = Float.parseFloat(scanner.nextLine());

			if(argv.length < 2) System.out.print("migue: ");
			migue = Float.parseFloat(scanner.nextLine());

			if(argv.length < 2) System.out.print("how many equips? ");
			n = Integer.parseInt(scanner.nextLine());
			
			equip_names = new String[n];

			for(int i=0; i<n; i++)
			{
				if(argv.length < 2) System.out.print("item " + (i+1) + " name: ");
				equip_names[i] = scanner.nextLine();	
			}

			if(argv.length < 2) System.out.print("how many consumables? ");
			n = Integer.parseInt(scanner.nextLine());
			
			consumable_names = new String[n];

			for(int i=0; i<n; i++)
			{
				if(argv.length < 2) System.out.print("item " + (i+1) + " name: ");
				consumable_names[i] = scanner.nextLine();	
			}

			if(argv.length < 2) System.out.print("how many attacks? ");
			n = Integer.parseInt(scanner.nextLine());
			
			attack_names = new String[n];

			for(int i=0; i<n; i++)
			{
				if(argv.length < 2) System.out.print("attack " + (i+1) + " name: ");
				attack_names[i] = scanner.nextLine();	
			}

			builder = new NPCBuilder(name,type,message,attack_names,equip_names,consumable_names,cr,knowledge,migue);
			director.construct(builder);
			npc = (NPC)builder.getPlayer();

			db.save(npc,"npc_" + npc.getName() + ".ser");
			System.out.println("npc saved to '" + db.getRoot() + "/npc_" + npc.getName() + ".ser'");
		}
		else if(argv[0].equalsIgnoreCase("room"))
		{	
			String[] players_names;
			String[] items_names;
			String[] events_names;
			String message;
			Room room = null;
			int number = 0, n;

			if(argv.length < 2) System.out.print("room number: ");
			number = Integer.parseInt(scanner.nextLine());

			if(argv.length < 2) System.out.print("room message: ");
			message = scanner.nextLine();
			
			if(argv.length < 2) System.out.print("how many players? ");
			n = Integer.parseInt(scanner.nextLine());

			players_names = new String[n];

			for(int i=0; i<n; i++)
			{
				if(argv.length < 2) System.out.print("player " + (i+1) + " name: ");
				players_names[i] = scanner.nextLine();	

			}

			if(argv.length < 2) System.out.print("how many items? ");
			n = Integer.parseInt(scanner.nextLine());
			
			items_names = new String[n];

			for(int i=0; i<n; i++)
			{
				if(argv.length < 2) System.out.print("item " + (i+1) + " name: ");
				items_names[i] = scanner.nextLine();	
			}

			if(argv.length < 2) System.out.print("how many events? ");
			n = Integer.parseInt(scanner.nextLine());
			
			events_names = new String[n];

			for(int i=0; i<n; i++)
			{
				if(argv.length < 2) System.out.print("event " + (i+1) + " name: ");
				events_names[i] = scanner.nextLine();	
			}

			room = RoomFactory.getRoom(number,players_names,events_names,items_names,message);

			db.save(room,"room_" +  Integer.toString(number)+ ".ser");
			System.out.println("room saved to '" + db.getRoot() + "/room_" + Integer.toString(number) + ".ser'");

		}
		else if(argv[0].equalsIgnoreCase("randomev"))
		{
			String name, message;
			Item item;
			RandomThingOfLife random;
			float cr,knowledge,migue;	
			
			if(argv.length < 2) System.out.print("name: ");
			name = scanner.nextLine();

			if(argv.length < 2) System.out.print("message: ");
			message = scanner.nextLine();

			if(argv.length < 2) System.out.print("item name (null for nothing): ");
			ans = scanner.nextLine();
			if(ans.equalsIgnoreCase("null"))
				item = null;
			else
				item = (Item)db.load("randomev_" + ans + ".ser");	

			if(argv.length < 2) System.out.print("cr increment: ");
			cr = Float.parseFloat(scanner.nextLine());

			if(argv.length < 2) System.out.print("knowledge increment: ");
			knowledge = Float.parseFloat(scanner.nextLine());

			if(argv.length < 2) System.out.print("migue increment: ");
			migue = Float.parseFloat(scanner.nextLine());

			random = new RandomThingOfLife(name,message,item,cr,knowledge,migue);
			db.save(random,"randomev_" + random.getName());
			System.out.println("randomev saved to '" + db.getRoot() + "/random_" + random.getName() + ".ser'");
		}
	}
}
