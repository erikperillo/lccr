package item.impl;

public class BaseItemFactory
{
	//loads item imformation from item_path
	public abstract IItem createItem(String item_path);

	//returns item
	public IItem getItem()
	{
		IItem item = this.createItem();
		return item;
	}
}
