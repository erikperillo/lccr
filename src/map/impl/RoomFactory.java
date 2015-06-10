package map.impl;

import db.impl.DataBase;
import player.impl.*;
import event.ifaces.*;
import event.impl.*;
import java.io.IOException;
import item.ifaces.*;
import item.impl.*;
import item.excepts.*;

public class RoomFactory
{
	public static Room getRoom(int number, String[] players_names, String[] events_names, String[] items_names, String message)
	{
		NPC[] npcs = new NPC[players_names.length];
		Item[] items = new Item[items_names.length];
		IEvent[] events = new IEvent[events_names.length];
		IItemFactory item_fact;
		DataBase db = DataBase.getInstance();
		
		try
		{
			for(int i=0; i<players_names.length; i++)
				npcs[i] = (NPC)db.load(NPC.class,players_names[i]);
		}
		catch(IOException | ClassNotFoundException e)
		{
			System.out.println("could not load room properly.\nmessages: " + e.getMessage());
			System.exit(1);
		}

		for(int i=0; i<items_names.length; i++)
		{
			try
			{
				item_fact = new EquipFactory();
				items[i] = (Equip)item_fact.getItem(items_names[i]);
			}
			catch(NoItemFoundException | IOException e)
			{
				try
				{
					item_fact = new ConsumableFactory();
					items[i] = (Consumable)item_fact.getItem(items_names[i]);
				}
				catch(NoItemFoundException | IOException e2)
				{
					System.out.println("could not load room properly.\nmessages: " + e2.getMessage());
					System.exit(1);
				}
			}
		}

		for(int i=0; i<events_names.length; i++)
			try
			{
				events[i] = (RandomThingOfLife)db.load(RandomThingOfLife.class,events_names[i]);
			}
			catch(IOException | ClassNotFoundException e)
			{
				try
				{
					events[i] = (Quiz)db.load(Quiz.class,events_names[i]);
				}
				catch(IOException | ClassNotFoundException e2)
				{
					System.out.println("could not load room properly.\nmessages: " + e2.getMessage());
					System.exit(1);
				}
			}

		return new Room(number,npcs,events,items,message);
	}
}
