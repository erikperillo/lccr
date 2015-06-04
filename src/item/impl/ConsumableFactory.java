package item.impl;

import item.ifaces.IItem;
import item.ifaces.IItemFactory;
import item.excepts.NoItemFoundException;
import db.impl.DataBase;
import java.io.*;

public class ConsumableFactory implements IItemFactory
{
	private String item_name;

	public ConsumableFactory(String item_name)
	{
		this.item_name = item_name;
	}

	@Override
	public IItem getItem() throws IOException, NoItemFoundException
	{
		DataBase db = DataBase.getInstance();
		String filename = "consumable_" + item_name + ".ser";

		try
		{
			return (Consumable) db.load(filename);
		}
		catch(FileNotFoundException | ClassNotFoundException e)
		{
			throw new NoItemFoundException("Consumable object not found in '" + db.getRoot() + "/" + filename);
		} 	
		catch(IOException e)
		{
			throw e;
		}
	}
}
