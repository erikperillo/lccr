package player.ifaces;

import item.ifaces.IItem;

public interface IHandler
{
	public IItem getItem(String item);
	public void addItem(IItem item);
	public void useItem(String item);
}
