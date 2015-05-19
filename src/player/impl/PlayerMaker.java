package player.impl;

//simple factory for the class Player
public class PlayerMaker
{
	public Player getPlayer(String type)
	{
		Player player;
	
		if(type.equalsIgnoreCase("cinco bola") || type.equalsIgnoreCase("cincobola"))
			player = new Player(0.5,0.45,0.45,"cinco bola");
		else if(type.equalsIgnoreCase("nerd"))
			player = new Player(0.5,0.8,0.2,"nerd");
		else if(type.equalsIgnoreCase("varzea")
			player = new Player(0.5,0.2,0.8,"varzea");
		else
			throw UnknownPlayerTypeException;

		return player;
	}
}
