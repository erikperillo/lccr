package map.impl;

import item.impl.*;
import player.impl.*;
import event.ifaces.*;
import player.ifaces.IObserver;
import map.excepts.*;

public class Map implements IObserver
{
	public final static int N_ROOMS_FLOOR = 3;
	private Room[] rooms;
	private int player_location = 0;
	
	public Map(Room[] rooms)
	{
		this.rooms = rooms;
	}
	
	public void update(int location)
	{
		this.player_location = location;
	}

	public boolean end()
	{
		return this.player_location >= this.rooms.length;
	}

	public Room getPlayerRoom()
	{
		return this.rooms[this.player_location];
	}

	public Room getRoomByNumber(int number) throws RoomNotFoundException
	{
		if(number < 0 || number >= this.rooms.length)
			throw new RoomNotFoundException("No room with number " + Integer.toString(number));

		return this.rooms[number];
	}

	public Room getRoomByName(String name) throws RoomNotFoundException
	{
		for(Room room: this.rooms)
			if(name.equalsIgnoreCase(room.getName()))
				return room;
		throw new RoomNotFoundException("No room named '" + name + "'");
	}

	public void updatePermissions()
	{
		boolean allowed;

		for(int i=0; i<this.rooms.length-1; i++)
		{
			allowed = true;
			for(NPC npc: this.rooms[i].getNPCs())
				if(npc.getType().equalsIgnoreCase("evil") && npc.isActive())
					allowed = false;
	
			this.rooms[i+1].setPlayerAllowed(allowed);
		}
	}
/*
	public boolean playerAllowed(String room_name) throws RoomNotFoundException
	{
		int index = 0;

		try
		{
			index = this.getRoomByName(room_name).getNumber();
		}
		catch(RoomNotFoundException e)
		{
			throw e;
		}

		if(index > 0 && index < this.rooms.length - 1)
		{
			for(NPC npc: this.getRoomByNumber(index).getNPCs())
				if(npc.getType().equalsIgnoreCase("evil") && npc.isActive())
					return false;
			return true;
		}
		else
			return true;
	}
*/
	public void draw()
	{
		int floor = this.player_location / N_ROOMS_FLOOR;

		if(floor*N_ROOMS_FLOOR + N_ROOMS_FLOOR > this.rooms.length)
			return;

		for(int i=N_ROOMS_FLOOR-1; i>=0; i--)
			this.rooms[floor*N_ROOMS_FLOOR + i].draw(i==N_ROOMS_FLOOR-1,floor*N_ROOMS_FLOOR + i == this.player_location);
	}
}
