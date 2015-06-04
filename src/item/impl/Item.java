package item.impl;

import item.ifaces.IItem;
import java.io.Serializable;

public class Item implements IItem, Serializable
{
	private String name;
	private String type;
	private String description;
	private int quantity;

	public Item(String name, String type, String description, int quantity)
	{
		this.name = name;
		this.type = type;
		this.description = description;
		this.setQuantity(quantity);
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

	public void describe()
	{
		System.out.println("Item '" + this.getName() + "' of type '" + this.getType() + "':");
		System.out.println("\tdescription: " + this.getDescription());
		System.out.println("\tquantity: " + this.getQuantity());
	}

	public int getQuantity()
	{
		return this.quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = (quantity >= 0)?quantity:0;
	}
}
