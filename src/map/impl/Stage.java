package map.impl;

import java.util.*;
import player.impl.*;
import event.ifaces.*;
import item.impl.*;

public class Stage
{
	private Player[] npcs;
	private IEvent[] events;
	private Item[] items;
	private String name;

	public Stage(String name, Player[] npcs, IEvent[] events, Item[] items)
	{
		this.npcs = npcs;
		this.events = events;
		this.items = items;
		this.name = name;
	}

	public Player[] getNPCs()
	{
		return this.npcs;
	}

	public IEvent[] getEvents()
	{
		return this.events;
	}

	public Item[] getItems()
	{
		return this.items;
	}

	public String getName()
	{
		return this.name;
	}

	public void describe()
	{
		System.out.println("Stage named '" + this.getName() + "':");

		System.out.println("NPCs:");
		for(Player player: this.npcs)
			System.out.println("\t" + player.getName());	

		System.out.println("Items:");
		for(Item item: this.items)
			System.out.println("\t" + item.getName());	

		System.out.println("Events:");
		for(IEvent event: this.events)
			System.out.println("\t" + event.getName());	
	}
}
