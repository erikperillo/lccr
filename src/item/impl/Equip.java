package item.impl;

import item.ifaces.IEquip;

public class Equip extends Item implements IEquip
{
	private int power;
	private boolean equipped;

	public Equip(String name, String type, String description, int quantity, int power, boolean equipped)
	{
		super(name,type,description,quantity);
		this.power = power;
		this.equipped = equipped;
	}

	public int getPower()
	{
		return this.power;
	}

	public boolean equipped()
	{
		return this.equipped;
	}	

	public void setEquipped(boolean value)
	{
		this.equipped = value;
	}
}
