package map.impl;

import db.impl.*;
import java.io.*;

public class MapFactory
{
	public static Map getMap(String[] rooms_names)
	{
		DataBase db = DataBase.getInstance();
		Room[] rooms = new Room[rooms_names.length];
		String name;

		try
		{
			for(int i=0; i<rooms.length; i++)
			{
				rooms[i] = (Room)db.load("room_" + rooms_names[i] + ".ser");

				name = "A" + Integer.toString((i / Map.N_ROOMS_FLOOR)+1) + (((i+1)%Map.N_ROOMS_FLOOR == 0)?"EXAME":("P" + Integer.toString((i+1)%Map.N_ROOMS_FLOOR)));
				rooms[i].setNumber(i);
				rooms[i].setName(name);
				rooms[i].setPlayerAllowed(i==0);
			}
		}
		catch(IOException | ClassNotFoundException e)
		{
			System.out.println("error: " + e.getMessage());
		}

		return new Map(rooms);
	}
}
