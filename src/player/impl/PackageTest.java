package player.impl;

import item.impl.*;
import player.excepts.UnknownPlayerTypeException;

import java.net.URL;
import java.io.*;

public class PackageTest
{
	public static void main(String argv[])
	{
		String cp = System.getProperty("java.class.path");
		//try{
		//	path = file.getCanonicalPath();
		//	System.out.println(path);}
		//catch(IOException e){;}	
		System.exit(0);

		Player player = null;

		Item generic = new Item("some item","generic item","blablabla",1);
		Item equip = new Equip("sword","equip","lethal",2,2,false);
		Consumable consumable = new Consumable("potion","consumable","gts life",3,2.3f,1.9f,9);

		try
		{
			player = PlayerMaker.getPlayer("varzea");
		}
		catch(UnknownPlayerTypeException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}

		System.out.println("Player type '" + player.getType());
		System.out.println("\tmigue: " + player.getMigue());
		System.out.println("\tCR: " + player.getCR());
		System.out.println("\tknowledge: " + player.getKnowledge());

		player.addItem(generic);
		player.addItem(equip);
		player.addItem(consumable);
		
		System.out.println("Itens:");
		for(Item it: player.getInventory())
			System.out.println("\t" + it.getDescription());
	}
}
