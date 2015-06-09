package event.impl;

import event.ifaces.IEvent;

import item.impl.*;

import java.io.Serializable;

import player.impl.*;

public class RandomThingOfLife implements IEvent, Serializable
{
	private Player player;
	private String name;
	private String message;
	private Item item;
	private float cr_incr;
	private float knowledge_incr;
	private float migue_incr;

	public RandomThingOfLife(String name, String message, Item item, float cr_incr, float knowledge_incr, float migue_incr)
	{
		this.player = null;
		this.name = name;
		this.message = message;
		this.item = item;
		this.cr_incr = cr_incr;
		this.knowledge_incr = knowledge_incr;
		this.migue_incr = migue_incr;
	}

	String getMessage()
	{
		return this.message;
	}

	public String getName()
	{
		return this.name;
	}

	public void setPlayer(Player player)
	{
		this.player = player;	
	}

	public void routine()
	{
		if(player == null)
			return;

		System.out.println(this.getMessage());

		if(this.cr_incr != 0)
		{
			this.player.setCR(this.player.getCR() + this.cr_incr);	
			System.out.println("Voce ganhou " + this.cr_incr + " de CR!");
		}
		if(this.knowledge_incr != 0)
		{
			this.player.setKnowledge(this.player.getKnowledge() + this.knowledge_incr);	
			System.out.println("Voce ganhou " + this.knowledge_incr + " de conhecimento!");
		}
		if(this.migue_incr != 0)
		{
			this.player.setMigue(this.player.getMigue() + this.migue_incr);	
			System.out.println("Voce ganhou " + this.migue_incr + " de migue!");
		}
		if(this.item != null)
		{
			this.player.addItem(item);
			System.out.println("Voce ganhou o item '" + item.getName() + "'!");
		}
	}
}
