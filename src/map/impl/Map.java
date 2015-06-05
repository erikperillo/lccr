package map.impl;

import item.impl.*;
import player.impl.*;
import event.ifaces.*;
import player.ifaces.IObserver;

public class Map implements IObserver
{
	private final static int N_ROOMS = 3;
	private Stage[] rooms;
	private int player_location;
	
	public Map(Stage[] rooms)
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

}
