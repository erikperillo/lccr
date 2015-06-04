package player.impl;

import player.ifaces.*;
import player.excepts.NoItemFoundException;
import item.impl.*;
import item.ifaces.*;
import db.ifaces.IDataBase;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements IPlayer, IHandler, IWanderer, IFighter, Serializable
{
	//attributes
	float CR;
	float knowledge;
	float migue;
	String type;
	ArrayList<Item> inventory;

	//ctor
	public Player(float CR, float knowledge, float migue, String type)
	{
		this.CR = CR;
		this.knowledge = knowledge;
		this.migue = migue;
		this.type = type;
		this.inventory = new ArrayList<Item>();
	}

	//getters
	public float getCR()
	{
		return this.CR;
	}

	public float getKnowledge()
	{
		return this.knowledge;
	}

	public float getMigue()
	{
		return this.migue;
	}

	public String getType()
	{
		return this.type;
	}

	//setters
	public void setCR(float CR)
	{
		this.CR = (CR > 1.0f)?1.0f:((CR < 0.0f)?0.0f:CR);
	}

	public void setKnowledge(float knowledge)
	{
		this.knowledge = (knowledge > 1.0f)?1.0f:((knowledge < 0.0f)?0.0f:knowledge);
	}

	public void setMigue(float migue)
	{
		this.migue = (migue > 1.0f)?1.0f:((migue < 0.0f)?0.0f:migue);
	}

	public ArrayList<Item> getInventory()
	{
		return this.inventory;
	}

	//Ifighter implementation	
	public float attack(IFighter enemy, String attack)
	{
		return 0.0f;
	}	

	public float react(IFighter enemy, String attack)
	{
		return 0.0f;
	}

	//IWanderer implementation
	public String getLocation()
	{
		return "";
	}
	
	public void move(String location)
	{
		return;
	}

	//IHandler implementation
	public IItem getItem(String item_name) throws NoItemFoundException
	{
		for(IItem item: this.inventory)
			if(item.getName().equalsIgnoreCase(item_name))
				return item;

		throw new NoItemFoundException("Item '" + item_name + "' not in inventory");
	}

	public void addItem(IItem item)
	{
		for(Item it: this.inventory)
			if(item.getName().equalsIgnoreCase(it.getName()))
			{
				it.setQuantity(it.getQuantity() + 1);
				return;
			}

		inventory.add((Item)item);	
	}
	
	//uses potion 
	public void useItem(String item_name) throws NoItemFoundException
	{
		Consumable consumable = null;
		
		for(IItem item: this.inventory)
			if(item.getName().equalsIgnoreCase(item_name))
				consumable = (Consumable)item;	

		if(consumable == null)
			throw new NoItemFoundException("Consumable '" + item_name + "' not in inventory");

		this.setCR(this.getCR() + consumable.getHP());
		this.setKnowledge(this.getKnowledge() + consumable.getAttack());
		this.setMigue(this.getMigue() + consumable.getDefense());

		consumable.setQuantity(consumable.getQuantity() - 1);

		if(consumable.getQuantity() == 0)
			this.inventory.remove(consumable);
	}	
}
