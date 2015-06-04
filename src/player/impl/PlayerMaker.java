package player.impl;

import player.excepts.UnknownPlayerTypeException;

//simple factory for the class Player
public class PlayerMaker
{
	public static Player getPlayer(String type, String name) throws UnknownPlayerTypeException
	{
		Player player;
	
		if(type.equalsIgnoreCase("cinco bola") || type.equalsIgnoreCase("cincobola"))
			player = new Player(name,"cinco bola",0.5f,0.45f,0.45f);
		else if(type.equalsIgnoreCase("nerd"))
			player = new Player(name,"nerd",0.5f,0.8f,0.2f);
		else if(type.equalsIgnoreCase("varzea"))
			player = new Player(name,"varzea",0.5f,0.2f,0.8f);
		else
			throw new UnknownPlayerTypeException("Invalid Player type '" + type + "'");

		return player;
	}
}
