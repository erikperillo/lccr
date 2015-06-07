package player.impl;

import player.ifaces.IPlayerBuilder;
import player.excepts.*;
import item.ifaces.IItemFactory;
import item.impl.*;
import item.excepts.NoItemFoundException;
import java.io.IOException;

public class NPCBuilder implements IPlayerBuilder
{
	private Player player;
	private String name;
	private String type;
	private String message;
	private String[] attacks;
	private String[] equips;
	private String[] consumables;
	private float cr,knowledge,migue;
	
	public NPCBuilder(String name, String type, String message, String[] attacks, String[] equips, String[] consumables, float CR, float knowledge, float migue)
	{
		this.name = name;
		this.type = type;
		this.message = message;
		this.attacks = attacks;
		this.equips = equips;
		this.consumables = consumables;
		this.cr = CR;
		this.knowledge = knowledge;
		this.migue = migue;
	}

	@Override
	public void createPlayer() throws UnknownPlayerTypeException
	{
		if(this.type.equalsIgnoreCase("evil") || this.type.equalsIgnoreCase("normal"))
			this.player = new NPC(this.name,this.type,this.message,this.cr,this.knowledge,this.migue);
		else
			throw new UnknownPlayerTypeException("Invalid NPC type '" + this.type + "'");
	}	

	@Override
	public void setAttacks()
	{
		for(String attack_name: this.attacks)
			this.player.addAttack(attack_name);
	}

	@Override
	public void setEquips() throws IOException, NoItemFoundException
	{
		IItemFactory item_fact = new EquipFactory();
			
		for(String equip_name: this.equips)
		{
			try
			{
				this.player.addItem((Equip)item_fact.getItem(equip_name));
			}
			catch(IOException | NoItemFoundException e)
			{
				throw e;
			}
		}
	}

	@Override
	public void setConsumables() throws IOException, NoItemFoundException
	{
		IItemFactory item_fact = new ConsumableFactory();	

		for(String consumable_name: this.consumables)
		{
			try
			{
				this.player.addItem((Consumable)item_fact.getItem(consumable_name));
			}
			catch(IOException | NoItemFoundException e)
			{
				throw e;
			}
		}
	}

	public Player getPlayer()
	{
		return this.player;
	}
}
