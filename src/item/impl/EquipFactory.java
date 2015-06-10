package item.impl;

import item.ifaces.IItem;
import item.ifaces.IItemFactory;
import item.excepts.NoItemFoundException;
import java.io.*;
import db.impl.DataBase;

public class EquipFactory implements IItemFactory
{
	public EquipFactory()
	{;}
	
	@Override
	public IItem getItem(String item_name) throws IOException, NoItemFoundException
	{
		DataBase db = DataBase.getInstance();

		try
		{
			return (Equip)db.load(Equip.class,item_name);
		}
		catch(FileNotFoundException | ClassNotFoundException e)
		{
			throw new NoItemFoundException("Equip object not found in '" + db.getRoot() + "'");
		}
		catch(IOException e)
		{
			throw e;
		}
	}
}
