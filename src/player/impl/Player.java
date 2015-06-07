package player.impl;

import player.ifaces.*;
import player.excepts.*;

import item.excepts.NoItemFoundException;
import item.impl.*;
import item.ifaces.*;

import db.impl.DataBase;

import java.io.*;
import java.util.ArrayList;

public class Player implements IPlayer, IHandler, IWanderer, IFighter, ISubject, Serializable
{
	//attributes
	private float CR;
	private float knowledge;
	private float migue;
	private String type;
	private String name;
	private ArrayList<Item> inventory;
	private ArrayList<String> attacks;
	private ArrayList<IObserver> people_that_want_my_position;
	private int room;

	//ctor
	public Player(String name, String type, float CR, float knowledge, float migue)
	{
		this.CR = CR;
		this.knowledge = knowledge;
		this.migue = migue;
		this.type = type;
		this.name = name;
		this.inventory = new ArrayList<Item>();
		this.attacks = new ArrayList<String>();
		this.people_that_want_my_position = new ArrayList<IObserver>();
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

	public String getName()
	{
		return this.name;
	}
	
	public ArrayList<Item> getInventory()
	{
		return this.inventory;
	}

	public ArrayList<String> getAttacks()
	{
		return this.attacks;
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

	//listing functions
	public String[] getLabeledLevels()
	{
		String[] ret = {"CR: " + Float.toString(this.getCR()),
                   		"conhecimento: " + Float.toString(this.getKnowledge()),
						"migue: " + Float.toString(this.getMigue())};
		return ret;
	}

	public String[] getAttacksNames()
	{
		String[] ret = new String[this.attacks.size()];
		return this.attacks.toArray(ret);
	}

	public String[] getItemsNames()
	{
		String[] ret;
		ArrayList<String> names = new ArrayList<String>();

		for(Item item: this.inventory)
			names.add(item.getName());	

		ret = new String[names.size()];
		return names.toArray(ret);
	}

	//IWanderer implementation
	public int getLocation()
	{
		return this.room;
	}
	
	public void move(int room)
	{
		this.room = room;
		this.notifyObservers();
	}
	
	//attack functions
	public void addAttack(String attack_name)
	{
		this.attacks.add(attack_name);
	}

	public boolean hasAttack(String attack_name)
	{
		for(String atk: this.attacks)
			if(atk.equalsIgnoreCase(attack_name))
				return true;

		return false;
	}

	protected Integer[] getAttackFromDB(String attack_name) throws AttackNotFoundException, IOException
	{
		DataBase db = DataBase.getInstance();
		Integer[] weights;

		try
		{
			weights = (Integer[])db.load(attack_name);
			return weights;
		}
		catch(IOException e)
		{
			throw e;
		}
		catch(ClassNotFoundException e)
		{	
			throw new AttackNotFoundException("Attack not found in '" + db.getRoot() + "'");
		}
	}

	//Ifighter implementation	
	public float attack(String attack_name) throws AttackNotFoundException, IOException
	{
		DataBase db;
		Integer[] weights = null;
		int attack_additional = 0;

		for(Item item: this.inventory)
			if(item.getType().equalsIgnoreCase("arma") || item.getType().equalsIgnoreCase("weapon"))
				attack_additional += ((Equip)item).equipped()?((Equip)item).getPower():0;

		if(this.hasAttack(attack_name))
		{
			try
			{
				weights = getAttackFromDB(attack_name);
			}
			catch(IOException | AttackNotFoundException e)
			{
				throw e;
			}

			return this.knowledge*(attack_additional + weights[0]) + this.migue*weights[1];
		}	
		else
			throw new AttackNotFoundException("No such attack '" + attack_name + "' in player's list of attacks");
	}	

	public float react(String attack_name) throws AttackNotFoundException, IOException
	{
		Integer[] weights = null;		
		int defense_additional = 0;

		for(Item item: this.inventory)
			if(!item.getType().equalsIgnoreCase("arma") && !item.getType().equalsIgnoreCase("weapon"))
				defense_additional += ((Equip)item).equipped()?((Equip)item).getPower():0;
		
		try
		{
			weights = getAttackFromDB(attack_name);
		}
		catch(AttackNotFoundException | IOException e)
		{
			throw e;
		}
		
		return this.migue*(defense_additional + weights[2]) + this.knowledge*weights[3];
	}


	//items functions
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

	public void describe()
	{
		System.out.println("Player '" + this.getName() + "' of type '" + this.getType() + "':");

		System.out.println("Levels:");
		for(String lvl: this.getLabeledLevels())
			System.out.println("\t" + lvl);

		System.out.println("Attacks:");
		for(String atk: this.getAttacksNames())
			System.out.println("\t-" + atk);

		System.out.println("Items:");
		for(Item it: this.inventory)
		{
			System.out.print("\t-"+ it.getName() + " (" + it.getType() + ")");
			if(it instanceof Equip)
				System.out.print(((Equip)it).equipped()?" [equipped]":"");
			System.out.println();
		}
	}

	public void subscribe(IObserver observer)
	{
		this.people_that_want_my_position.add(observer);
	}

	public void unsubscribe(IObserver observer)
	{
		this.people_that_want_my_position.remove(observer);
	}

	public void notifyObservers()
	{
		for(IObserver obs: people_that_want_my_position)
			obs.update(this.room);
	}
}
