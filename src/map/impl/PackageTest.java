package map.impl;
import item.impl.*;

public class PackageTest
{
	public static void main(String argv[])
	{
		/*room fac test
		String[] names = {"Borin"};
		String[] events = {"Domingao do Faustao"};
		String[] items = {"Vodka Orloff","Caneta Dourada","HP 50G"};

		Room room = RoomFactory.getRoom(1,names,events,items);
		room.describe();

		for(Item item: room.getItems())
			item.describe();
		*/
		String[] rooms_names = {"P1","P1","P1","P1","P1","P1","P1","P1","P1"};
		Map map = MapFactory.getMap(rooms_names);
		map.draw();
	}
}
