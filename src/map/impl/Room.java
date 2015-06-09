package map.impl;

import player.impl.*;

import event.ifaces.*;
import event.impl.*;

import item.impl.*;

public class Room extends Stage
{
	private boolean random_event_visited = false;
	private boolean player_allowed = false;
	private String message;
	private int number;

	public Room(int number, NPC[] npcs, IEvent[] events, Item[] items, String message)
	{
		super(Integer.toString(number),npcs,events,items);
		this.setNumber(number);
		this.message = message;
	}

	public void setNumber(int number)
	{
		this.number = number;
	}

	public int getNumber()
	{
		return this.number;
	}

	public String getMessage()
	{
		return this.message;
	}

	public void setVisited(boolean visited)
	{
		this.random_event_visited = visited;
	}

	public boolean randomEventVisited()
	{
		return this.random_event_visited;
	}

	void setPlayerAllowed(boolean allowed)
	{
		this.player_allowed = allowed;
	}

	public boolean playerAllowed()
	{
		return this.player_allowed;
	}

	public void draw(boolean top, boolean player_here)
	{
		String wall = "|               |";
		String bottom_room = "|_______________|";
		String top_room = (top?"-----------------":"------_____------"); 

		String room = 
		top_room + "\n" +
		wall + "\n" +
		wall + " <- " + this.getName() + "\n" +
		"|       " + (this.player_allowed?" ":"x") + "       |" + "\n" +
		"|     " + (player_here?"_______":"       ") + "   " + (this.random_event_visited?" /":"]") + "\n" +
		"|     " + (player_here?"[° ͜ʖ ͡°]":"       ") + "   |" + "\n" + 
		"|     " + (player_here?"|_===_|":"       ") + "   |" + "\n" + 
		bottom_room + "\n";

		System.out.print(room);
	}

	public void describe()
	{
		System.out.println("Room '" + this.getName() + "'");
		System.out.println(this.getMessage());

		if(this.getNPCs().length > 0)
			System.out.println("NPCS:");
		for(NPC npc: this.getNPCs())
			System.out.println("\t-" + npc.getName() + " (type: " + npc.getType() + ")");

		if(this.getItems().size() > 0)
			System.out.println("Items:");
		for(Item item: this.getItems())
			System.out.println("\t-" + item.getName() + " (type: " + item.getType() + ")");

		if(this.getEvents().size() > 0)
			System.out.println("Events:");
		for(IEvent event: this.getEvents())
		{
			if(event instanceof RandomThingOfLife && !this.random_event_visited)
				System.out.println("\t-?????? (abra a porta pra descobrir!)");
			else
				System.out.println("\t-" + event.getName());
		}
	}
		
}
