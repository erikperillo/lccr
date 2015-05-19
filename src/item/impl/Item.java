package item.impl;

import item.ifaces.IItem;

public class Item implements IItem
{
	private String name;
	private String type;
	private String description;

	public Item(String name, String type, String description)
	{
		this.name = name;
		this.type = type;
		this.description = description;
	}

	public String getName()
	{
		return this.name;
	}

	public String getType()
	{
		return this.type;
	}

	public String getDescription()
	{
		return this.description;
	}
}
