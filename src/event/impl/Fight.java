package event.impl;

import event.ifaces.IEvent;
import player.impl.*;
import java.util.Scanner;
import item.impl.*;
import item.excepts.*;
import player.excepts.*;
import java.io.IOException;

public class Fight implements IEvent
{
	private static int counter = 0;
	private String name;
	private int rounds;
	private Player player;
	private NPC enemy;

	public Fight(String name, Player player, NPC enemy, int rounds)
	{
		this.player = player;
		this.enemy = enemy;
		this.name = name;
		this.rounds = rounds;
	}

	public String getName()
	{
		return this.name;
	}

	public boolean validAnswer(String answer, String[] options)
	{
		for(String opt: options)
			if(answer.equalsIgnoreCase(opt))
				return true;

		return false;
	}

	public void routine()
	{
		Player attacker, reactor;
		Scanner keyboard = new Scanner(System.in);
		String ans;
		String[] opts = {"l","i","u","p"};
		Item item = null;
		Player[] convenience = {this.player,this.enemy};
		float attack_val = 0, defense_val = 0;
	
		Fight.counter++;
		System.out.println("LUTA ENTRE '" + this.player + "' (player 1) E '" + this.enemy + "' (player 2)");

		if(counter%2 == 0)
			attacker = player;
		else
			attacker = enemy;	

		reactor = (attacker==player)?enemy:player;	

		for(int i=0; i<this.rounds; i++)
		{
			if(this.player.getCR() <= 0)
			{
				System.out.println("Voce morreu!");
				break;
			}
			if(this.enemy.getCR() <= 0)
			{
				System.out.println("Voce venceu!");
				this.enemy.setActive(false);
				break;
			}
			
			System.out.println("ROUND " + (i+1));

			for(String item_name: this.player.getItemsNames())
			{
				try
				{
					item = (Item)this.player.getItem(item_name);
					if(item instanceof Equip)
						((Equip)item).setEquipped(false);
				}
				catch(NoItemFoundException e)
				{
					System.out.println(e.getMessage());
					System.exit(1);
				}
			} 

			while(true)
			{
				System.out.println("Use 'l' para listar seus ataques e itens, 'i' para informacao sobre seu CR, conhecimento e migue, 'u' para equipar/usar algum item ou 'p' para prosseguir.");

				while(true)
				{
					System.out.print(">>> ");
					ans = keyboard.nextLine();
					if(!validAnswer(ans,opts))
						System.out.println("Resposta invalida! Tente de novo.");
					else
						break;
				}

				if(ans.equalsIgnoreCase("l"))
				{
					System.out.println("Ataques:");
					for(String atk: this.player.getAttacksNames())
						System.out.println("\t-" + atk);

					System.out.println("Itens:");
					for(String it: this.player.getItemsNames())
						System.out.println("\t-" + it);
				}
				else if(ans.equalsIgnoreCase("i"))
					for(String lvl: this.player.getLabeledLevels())
						System.out.println("\t-" + lvl);
				else 
				{
					if(ans.equalsIgnoreCase("u"))
					{
						System.out.println("Itens disponiveis:");
						for(String it: this.player.getItemsNames())
							System.out.println("\t-" + it);

						System.out.println("\tDigite o nome do item:");

						while(true)
						{
							System.out.print("\t>>> ");
							ans = keyboard.nextLine();
							try
							{
								item = (Item)this.player.getItem(ans);
								break;
							}
							catch(NoItemFoundException e)
							{
								System.out.println("Nao ha o item '" + ans + "' no inventorio! tente de novo");
							}
						}

						if(item instanceof Equip)
						{							
							((Equip)item).setEquipped(true);
							System.out.println("Equipado item '" + item.getName());
						}
						else
						{
							try
							{
								this.player.useItem(ans);
								System.out.println("Item '" + ans + "' usado");
							}
							catch(NoItemFoundException e)
							{
								System.out.println("wtf: " + e.getMessage());
								System.exit(1);
							}
						}	
					}
					break;
				}

			}
			
			System.out.println("E a vez do '" + attacker.getName() + "' atacar!");
			{
				while(true)
				{
					if(attacker == this.player)
					{
						System.out.println("Escolha seu ataque dentre os disponiveis:");
						for(String atk: this.player.getAttacksNames())
							System.out.println("\t-" + atk);
						System.out.println(">>> ");	
						ans = keyboard.nextLine();
					}
					else
						ans = attacker.getAttacks().get(0);

					try
					{
						attack_val = attacker.attack(ans);
						break;
					}
					catch(AttackNotFoundException e)
					{
						System.out.println("Esse ataque nao existe! Tente de novo, cara palida.");
					}
					catch(IOException e)
					{
						System.out.println(e.getMessage());
					}
				}	
			}
			
			System.out.println("'" + reactor.getName() + "' se defende como pode...");
			try
			{
				defense_val = reactor.react(ans);
			}
			catch(AttackNotFoundException | IOException e)
			{
				System.out.println("ops: " + e.getMessage());
			}

			System.out.println("valor de ataque: " + attack_val + " | valor de defesa: " + defense_val);
			
		}
		return;
	}
}
