package item.impl;

import item.ifaces.IItem;
import item.ifaces.IItemFactory;
import item.excepts.NoItemFoundException;
import java.io.*;
import db.impl.DataBase;

public class EquipFactory implements IItemFactory
{
	private String item_name;
	
	public EquipFactory(String item_name)
	{
		this.item_name = item_name;
	}
	
	@Override
	public IItem getItem() throws IOException, NoItemFoundException
	{
		DataBase db = DataBase.getInstance();
		String filename = "equip_" + item_name + ".ser";

		try
		{
			return (Equip)db.load(filename);
		}
		catch(FileNotFoundException | ClassNotFoundException e)
		{
			throw new NoItemFoundException("Equip object not found in '" + db.getRoot() + "/" + "filename");
		}
		catch(IOException e)
		{
			throw e;
		}
	}
}
