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
		this.super(name,type,description);
		this.attack = attack;
		this.defense = defense;
		this.duration = duration;
	}

	public float getHP() {;}
	public int getAttack() {;}
	public int getDefense() {;}
	public int getDuration() {;}
	public int getQuantity() {;}

	public void stock(int quantity) {;}
}	
