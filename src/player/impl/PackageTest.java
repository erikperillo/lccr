package player.impl;

import item.impl.*;
import item.ifaces.*;
import item.excepts.*;
import player.excepts.UnknownPlayerTypeException;
import item.excepts.*;
import java.io.IOException;

import java.io.*;

public class PackageTest
{
	public static void main(String argv[])
	{
		String[] attacks = {"Pergunta Dificil","Resposta Enrolada"};
		String[] equips = {"Caneta Dourada","Colinha de bolso"};
		String[] consumables = {"Vodka Orloff"};

		PlayerBuilder pb = new PlayerBuilder("varzea","jose",attacks,equips,consumables);

		try
		{
			pb.createPlayer();
			pb.setAttacks();
			pb.setEquips();
			pb.setConsumables();
		}
		catch(UnknownPlayerTypeException | IOException | NoItemFoundException e)
		{
			System.out.println("WARNING: " + e.getMessage());
		}

		Player player = pb.getPlayer();

		player.describe();
		System.out.println();

		try
		{
			player.getItem("Caneta Dourada").describe();
			System.out.println();
			player.getItem("Vodka Orloff").describe();
			System.out.println();
		}
		catch(NoItemFoundException e)
		{
			System.out.println("WARNING: " + e.getMessage());
		}
	}
}
