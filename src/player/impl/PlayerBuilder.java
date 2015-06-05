package player.impl;

import player.ifaces.IPlayerBuilder;
import player.excepts.*;
import item.ifaces.IItemFactory;
import item.impl.*;
import item.excepts.NoItemFoundException;
import java.io.IOException;

public class PlayerBuilder implements IPlayerBuilder
{
	private Player player;
	private String name;
	private String type;
	private String[] attacks;
	private String[] equips;
	private String[] consumables;
	
	public PlayerBuilder(String name, String type, String[] attacks, String[] equips, String[] consumables)
	{
		this.name = name;
		this.type = type;
		this.attacks = attacks;
		this.equips = equips;
		this.consumables = consumables;
	}

	@Override
	public void createPlayer() throws UnknownPlayerTypeException
	{
		try
		{
			this.player = PlayerMaker.getPlayer(type,name);
		}
		catch(UnknownPlayerTypeException e)
		{
			throw e;
		}
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
