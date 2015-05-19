package item.impl;

import item.ifaces.IItem;

public interface IItemFactory
{
	//loads item imformation from item_path
	public abstract Item getItem();
}
