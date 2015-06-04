package player.ifaces;

import item.ifaces.IItem;
import item.excepts.NoItemFoundException;

public interface IHandler
{
	public IItem getItem(String item_name) throws NoItemFoundException;
	public void addItem(IItem item);
	public void useItem(String item_name) throws NoItemFoundException;
}
