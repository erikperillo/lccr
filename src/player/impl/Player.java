package player.impl;

import player.ifaces.IPlayer;
import player.ifaces.IHandler;
import player.ifaces.IWanderer;
import player.ifaces.IFighter;
import item.ifaces.IItem;

import item.impl.Item;

public class Player implements IPlayer, IHandler, IWanderer, IFighter
{
	public static void main(String[] argv)
	{
		if(argv.length != 1)
		{
			System.out.println("You must pass a test as argument");
			System.exit(1);
		}

		String[] opts = new String[]{"--testsetters"};
	
		if(argv[0].equalsIgnoreCase("--testsetters"))
		{
			float[] nums = new float[]{-1.98f,0.0f,0.34f,1.0f,233.32f};
			Player player = new Player(0.8f,0.1f,0.3f,"franku sama");

			for(int i=0; i<5; i++)
			{
				player.setCR(nums[i]);
				System.out.println("player.setCR(" + nums[i] + ") -> " + player.getCR());
			}
			System.out.println("(getKnowledge e getMigue sao implementados de maneira identica)");
		}
		else
		{
			System.out.println("invalid arguments. Tests available:");
			for (int i=0; i<opts.length; i++)
				System.out.println("   " + opts[i]);
		}
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
	public IItem getItem(String item)
	{
		IItem ret_item = new Item("bjb","jhsghbd","kjhkjh");
		return ret_item;
	}

	public void addItem(IItem item)
	{
		return;
	}
	
	public void useItem(String item)
	{
		return;
	}	
	
}
