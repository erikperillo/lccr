package map.impl;

import db.impl.*;
import java.io.*;

public class MapFactory
{
	public static Map getMap(String[] rooms_names)
	{
		DataBase db = DataBase.getInstance();
		Room[] rooms = new Room[rooms_names.length];

		try
		{
			for(int i=0; i<rooms.length; i++)
				rooms[i] = (Room)db.load("room_" + rooms_names + ".ser");
		}
		catch(IOException | ClassNotFoundException e)
		{
			System.out.println("error: " + e.getMessage());
		}

		return new Map(rooms);
	}
}
