package item.impl;

import item.ifaces.IEquip;

public class Equip extends Item implements IEquip
{
	private int power;
	private boolean equipped;

	public Equip(String name, String type, String description, int power, boolean equipped)
	{
		super(name, type, description);
		this.power = power;
		this.equipped = equipped;
	}

	public int getPower() {return 0;}
	public boolean equipped() {return false;}
}
