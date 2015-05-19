package player.impl;

import player.excepts.UnknownPlayerTypeException;

public class PackageTest
{
	public static void main(String argv[])
	{
		Player player;
		try
		{
			player = PlayerMaker.getPlayer("kljdhf");
			System.out.println("knowledge: " + player.getKnowledge());
			System.out.println("migue: " + player.getMigue());
		}
		catch (UnknownPlayerTypeException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}
