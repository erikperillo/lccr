package item.impl;

import item.ifaces.IEquip;

public class Equip extends Item implements IEquip
{
	private int power;
	private boolean equipped;

	public Equip(String name, String type, String description, int power)
	{
		this.super(name, type, description);
		this.power = power;
		this.equipped = false;
	}

	public int getPower {;}
	public boolean equipped {;}
}
