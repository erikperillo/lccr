package src.player.impl;

import src.player.ifaces.IPlayer;
import src.player.ifaces.IHandler;
import src.player.ifaces.IWanderer;
import src.player.ifaces.IFighter;
import src.itens.Equipments;

public class Player implements IPlayer, IHandler, IWanderer, IFighter
{
	public static void main(String[] argv)
	{
		System.out.println("oi");
	}
	
	//attributes
	float CR;
	float knowledge;
	float migue;
	String type;

	//ctor
	public Player(float CR, float knowledge, float migue, String type)
	{
		this.CR = CR;
		this.knowledge = knowledge;
		this.migue = migue;
		this.type = type;
	}

	//getters
	public float getCR()
	{
		return this.CR;
	}

	public float getKnowledge()
	{
		return this.CR;
	}

	public float getMigue()
	{
		return this.CR;
	}

	public float getType()
	{
		return this.CR;
	}

	//setters
	public void setCR(float CR)
	{
		if(CR > 1.0f)
			this.CR = 1.0f;
		else if(CR < 0.0f)
			this.CR = 0.0f;
		else
			this.CR = CR;
	}

	public void setKnowledge(float knowledge)
	{
		if(knowledge > 1.0f)
			this.knowledge = 1.0f;
		else if(knowledge < 0.0f)
			this.knowledge = 0.0f;
		else
			this.knowledge = knowledge;
	}

	public void setMigue(float migue)
	{
		if(migue > 1.0f)
			this.migue = 1.0f;
		else if(migue < 0.0f)
			this.migue = 0.0f;
		else
			this.migue = migue;
	}

	//Ifighter implementation	
	public float attack(IFighter enemy, String attack)
	{
		/*
		float result;	
		float k;
		
		params = attacks.loadParams(attack);
		k = params[4];

		result = -1.0*enemy.react(this,attack);	

		this.CR += k * result;
		this.migue += (1.0 - k) * result;

		return result > 0.0;*/
		return 0.0f;
	}	

	public float react(IFighter enemy, String attack)
	{/*
		float result;
		float a,b,c,d,k;

		params = attacks.loadParams(attack);

		a = params[0];
		b = params[1];
		c = params[2];
		d = params[3];
		k = params[4];

		result = (a*this.CR + b*this.migue + c*enemy.CR + d*enemy.migue) / (a+b+c+d);

		this.CR += k * result;
		this.migue += (1.0 - k) * result;
		*/
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
	public void getItem(String item)
	{
		return;
	}

	public void addItem(String item)
	{
		return;
	}
	
	public void useItem(String item)
	{
		return;
	}	
	
}
