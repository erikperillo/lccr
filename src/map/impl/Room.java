package map.impl;

import player.impl.*;
import event.ifaces.*;
import item.impl.*;

public class Room extends Stage
{
	public static void main(String[] argv)
	{
		Room room = new Room(1,null,null,null);
		room.draw(true,false);
		room.draw(false,false);
		room.draw(false,true);
	}

	private boolean random_event_visited = false;

	public Room(int number, Player[] npcs, IEvent[] events, Item[] items)
	{
		super("P" + Integer.toString(number % 10),npcs,events,items);
	}

	void setVisited(boolean visited)
	{
		this.random_event_visited = visited;
	}

	public void draw(boolean top, boolean player_here)
	{
		String wall = "|              |";
		String bottom_room = "|______________|";
		String top_room = (top?"----------------":"-----_____------"); 

		String room = 
		top_room + "\n" +
		wall + "\n" +
		wall + "\n" +
		"|      " + this.getName() + "      |" + "\n" +
		"|    " + (player_here?"_______":"       ") + "   " + (this.random_event_visited?" /":"]") + "\n" +
		"|    " + (player_here?"[° ͜ʖ ͡°]":"       ") + "   |" + "\n" + 
		"|    " + (player_here?"|_===_|":"       ") + "   |" + "\n" + 
		bottom_room + "\n";

		System.out.print(room);
	}
}
