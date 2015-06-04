package player.impl;

import item.impl.*;
import item.ifaces.*;
import item.excepts.*;
import player.excepts.UnknownPlayerTypeException;

import java.io.*;

public class PackageTest
{
	public static void main(String argv[])
	{
		Player player = null;
		String[] consumables_names = {"vodka","agua de coco","lel"};
		String[] equips_names = {"Espada","daora","caneta dourada"};
		IItemFactory item_loader;

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

		for(String cons_name: consumables_names)
		{
			item_loader = new ConsumableFactory(cons_name);
			try
			{
				player.addItem(item_loader.getItem());
			}
			catch(IOException|NoItemFoundException e)
			{
				System.out.println("error : " + e.getMessage());
			}
		}
		for(String equip_name: equips_names)
		{
			item_loader = new EquipFactory(equip_name);
			try
			{
				player.addItem(item_loader.getItem());
			}
			catch(IOException|NoItemFoundException e)
			{
				System.out.println("error: " + e.getMessage());
			}
		}

		if(player.getInventory().size() > 0)
			System.out.println("Itens:");
		for(Item it: player.getInventory())
			System.out.println("\t" + it.getDescription());
	}
}
