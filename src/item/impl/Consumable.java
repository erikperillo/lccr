package item.impl;

import item.ifaces.IConsumable;

public class Consumable extends Item implements IConsumable
{
	private float hp;
	private float attack;
	private float defense;
	private int duration;

	public Consumable(String name, String type, String description, int quantity, float attack, float defense, int duration)
	{
		super(name,type,description,quantity);
		this.attack = attack;
		this.defense = defense;
		this.duration = duration;
	}

	public float getHP() 
	{
		return this.hp;
	}

	public float getAttack() 
	{
		return this.attack;
	}

	public float getDefense()
	{
		return this.defense;
	}

	public int getDuration()
	{
		return this.duration;
	}

}	
