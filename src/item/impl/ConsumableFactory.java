package item.impl;

import item.ifaces.IItem;
import item.ifaces.IItemFactory;
import item.excepts.NoItemFoundException;
import db.impl.DataBase;
import java.io.*;

public class ConsumableFactory implements IItemFactory
{
	public ConsumableFactory()
	{;}

	@Override
	public IItem getItem(String item_name) throws IOException, NoItemFoundException
	{
		DataBase db = DataBase.getInstance();

		try
		{
			return (Consumable) db.load(Consumable.class,item_name);
		}
		catch(FileNotFoundException | ClassNotFoundException e)
		{
			throw new NoItemFoundException("Consumable object not found in '" + db.getRoot());
		} 	
		catch(IOException e)
		{
			throw e;
		}
	}
}
