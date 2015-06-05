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
}
