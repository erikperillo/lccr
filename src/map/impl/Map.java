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

	public Stage getPlayerRoom()
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

	public void draw()
	{
		int floor = this.player_location / N_ROOMS_FLOOR;

		if(floor*N_ROOMS_FLOOR + N_ROOMS_FLOOR > this.rooms.length)
			return;

		for(int i=N_ROOMS_FLOOR-1; i>=0; i--)
			this.rooms[floor*N_ROOMS_FLOOR + i].draw(i==N_ROOMS_FLOOR-1,floor*N_ROOMS_FLOOR + i == this.player_location);
	}
}
