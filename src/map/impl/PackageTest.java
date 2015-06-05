package map.impl;
import item.impl.*;

public class PackageTest
{
	public static void main(String argv[])
	{
		String[] names = {"Borin"};
		String[] events = {"Domingao do Faustao"};
		String[] items = {"Vodka Orloff","Caneta Dourada","HP 50G"};

		Room room = RoomFactory.getRoom(1,names,events,items);
		room.describe();

		for(Item item: room.getItems())
			item.describe();
	}
}
