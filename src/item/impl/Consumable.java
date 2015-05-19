package item.impl;

import item.ifaces.IConsumable;

public class Consumable extends Item implements IConsumable
{
	private float hp;
	private int attack;
	private int defense;
	private int duration;
	private int quantity;

	public Consumable(String name, String type, String description, int attack, int defense, int duration)
	{
		super(name,type,description);
		this.attack = attack;
		this.defense = defense;
		this.duration = duration;
	}

	public float getHP() {return 0.0f;}
	public int getAttack() {return 0;}
	public int getDefense() {return 0;}
	public int getDuration() {return 0;}
	public int getQuantity() {return 0;}

	public void stock(int quantity) {;}
}	
