package lccl.player;

import lccl.player.IPlayer;

public class Player implements IPlayer
{
	public String type;
	public DataBase attacks;
	public DataBase inventory;
	public double CR;
	public double migue;

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

	public int attack(IPlayer enemy, String attack)
	{
		double result;	
		double k;
		
		params = attacks.loadParams(attack);
		k = params[4];

		result = -1.0*enemy.react(this,attack);	

		this.CR += k * result;
		this.migue += (1.0 - k) * result;

		return result > 0.0;
	}	

	public int react(IPlayer enemy, String attack)
	{
		double result;
		double a,b,c,d,k;

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
