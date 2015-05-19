package player.impl;

import player.excepts.UnknownPlayerTypeException;

//simple factory for the class Player
public class PlayerMaker
{
	public static Player getPlayer(String type) throws UnknownPlayerTypeException
	{
		Player player;
	
		if(type.equalsIgnoreCase("cinco bola") || type.equalsIgnoreCase("cincobola"))
			player = new Player(0.5f,0.45f,0.45f,"cinco bola");
		else if(type.equalsIgnoreCase("nerd"))
			player = new Player(0.5f,0.8f,0.2f,"nerd");
		else if(type.equalsIgnoreCase("varzea"))
			player = new Player(0.5f,0.2f,0.8f,"varzea");
		else
			throw new UnknownPlayerTypeException("Invalid Player type '" + type + "'");

		return player;
	}
}
