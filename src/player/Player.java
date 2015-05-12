package lccl.player;

import lccl.player.IPlayer;

public class Player implements IPlayer, IHandler, IWanderer, IFighter
{
	//attributes
	float CR = 0.5;
	float knowledge;
	float migue;
	String type;

	public Player(float knowledge, float migue, String type)
	{
		this.knowledge = knowledge;
		this.migue = migue;
		this.type = type;
	}
	
	public Player(String descr, String type)
	{
		this.type = type;

		if(type.equalsIgnoreCase("nerd"))
		{
			CR = 0.7;
			migue = 0.3;
		}
		else if(type.equalsIgnoreCase("cinco bola"))
		{
			CR = 0.5;
			migue = 0.5; 
		}
		else if(type.equalsIgnoreCase("varzea")
		{
			CR = 0.3;
			migue = 0.7;
		}
		else; //error
	}

	public float attack(IPlayer enemy, String attack)
	{
		float result;	
		float k;
		
		params = attacks.loadParams(attack);
		k = params[4];

		result = -1.0*enemy.react(this,attack);	

		this.CR += k * result;
		this.migue += (1.0 - k) * result;

		return result > 0.0;
	}	

	public float react(IPlayer enemy, String attack)
	{
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
	}
	
}
