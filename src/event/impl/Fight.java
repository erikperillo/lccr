package event.impl;

import event.ifaces.IEvent;

import player.impl.*;

import item.impl.*;
import item.excepts.*;

import player.excepts.*;

import prompt.*;

import java.io.*;
import java.lang.*;
import java.util.*;

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
		Scanner keyboard = new Scanner(System.in), line = null;
		String ans, selected_attack;
		String[] opts = {"listar","info","usar","prox"};
		Prompt prompt = new Prompt(">>> ");
		Item item = null;
		Random random = new Random();
		float attack_val=0, defense_val=0, damage, player_score=0, enemy_score=0;
		boolean atk_found;
		int n_prizes;
	
		Fight.counter++;
		System.out.println("LUTA ENTRE '" + this.player.getName() + "' (player 1) E '" + this.enemy.getName() + "' (player 2)\n");

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
				enemy_score = 1;
				player_score = 0;
				break;
			}

			if(this.enemy.getCR() <= 0)
			{
				System.out.println("Seu inimigo morreu!");
				player_score = 1;
				enemy_score = 0;
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
					System.out.println("ERROR:" + e.getMessage());
					System.exit(1);
				}
			} 

			selected_attack = "nenhum";
			atk_found = false;

			System.out.println("\nE a vez do '" + attacker.getName() + "' atacar!\n");

			while(true)
			{
				ans = prompt.queryAnswer("Use:\n 'info' [player | item [NOME_ITEM]] para informacao sobre seu CR, conhecimento, migue e listar seus ataques e items ou sobre um item\n 'usar' [NOME_ITEM] para equipar/usar algum item" + ((attacker==this.player)?"\n 'ataque' [NOME_ATAQUE] para selecionar o ataque a ser usado":"") + "\n 'prox' para prosseguir.");
				line = new Scanner(ans);
				prompt.clear();

				try
				{
					ans = line.next();

					if(ans.equalsIgnoreCase("info"))
					{
						ans = line.next();

						if(ans.equalsIgnoreCase("player"))
							this.player.describe();

						else if(ans.equalsIgnoreCase("item"))
						{
							ans = line.nextLine().trim();
							try
							{
								player.getItem(ans).describe();
							}
							catch(NoItemFoundException e)
							{
								System.out.println("Nao ha o item '" + ans + "' no seu inventario!");
							}
						}
						if(attacker == this.player)
							System.out.println("Ataque selecionado: '" + selected_attack + "'\n");
					}

					else if(ans.equalsIgnoreCase("ataque") && attacker == this.player)
					{
						ans = line.nextLine().trim();
					
						for(String atk: player.getAttacksNames())
							if(ans.equals(atk))
							{
								selected_attack = ans;
								atk_found = true;
								System.out.println("Ataque '" + ans + "' selecionado");
								break;			
							}
						if(!atk_found)	
							System.out.println("Ataque '" + ans + "' nao existe em sua gama de ataques! (case sensitive)");
					}

					else if(ans.equalsIgnoreCase("usar"))
					{
						ans = line.nextLine().trim();
						try
						{
							item = (Item)this.player.getItem(ans);

							if(item instanceof Equip)
							{							
								((Equip)item).setEquipped(true);
								System.out.println("Equipado item '" + item.getName() + "'");
							}
							else
							{
								this.player.useItem(ans);
								System.out.println("Item '" + ans + "' usado");
							}
						}
						catch(NoItemFoundException e)
						{
							System.out.println("Nao ha o item '" + ans + "' no inventorio! tente de novo");
						}
					}

					else if(ans.equalsIgnoreCase("prox"))
					{
						if(!atk_found && attacker == this.player)
							System.out.println("Voce PRECISA selecionar um ataque para continuar na sua vez!");
						else
							break;
					}

					else
						System.out.println("Comando '" + ans + "' invalido!");
				}
				catch(NoSuchElementException e)
				{
					System.out.println("Comando invalido! tente de novo");
				}
			}
			
			if(attacker != this.player)
				selected_attack = attacker.getAttacksNames()[random.nextInt(attacker.getAttacksNames().length)];
			try
			{
				attack_val = attacker.attack(selected_attack);
			}
			catch(AttackNotFoundException | IOException e)
			{
				System.out.println("ERROR: " + e.getMessage());
				i--;
				continue;
			}
			
			System.out.println("'" + attacker.getName() + "' usa o ataque '" + selected_attack + "' !");
			System.out.println("'" + reactor.getName() + "' se defende como pode...");

			try
			{
				defense_val = reactor.react(selected_attack);
			}
			catch(AttackNotFoundException | IOException e)
			{
				System.out.println("ERROR: " + e.getMessage());
				i--;
				continue;
			}

			System.out.println("valor de ataque: " + attack_val + " | valor de defesa: " + defense_val);

			if(attack_val > defense_val)
			{
				System.out.println("A forca do atacante '" + attacker.getName() + "' prevalesceu!");
				damage = 0.1f*(1.0f - ((attack_val != 0) ? (defense_val / attack_val) : 0.0f));
				System.out.println("O dano causado a '" + reactor.getName() + "' foi de " + damage);
				reactor.setCR(reactor.getCR() - damage);
			}
			else 
			{
				System.out.println("O ataque de '" + attacker.getName() + "' nao foi efetivo!");
				if(defense_val > 3.1416f*attack_val)
				{
					damage = 0.1f*(1.0f - ((defense_val != 0) ? (attack_val / defense_val) : 0.0f));	
					System.out.println("A reacao de '" + reactor.getName() + "' foi tao poderosa que causou dano de " + damage + " a '" + attacker.getName() + "' !");
				}	
			}

			if(attacker == this.player)
			{
				reactor = this.player;
				attacker = this.enemy;
				player_score += attack_val - 0.9f*defense_val;	
			}
			else
			{
				reactor = this.enemy;
				attacker = this.player;
				enemy_score += attack_val - 0.9f*defense_val;	
			}
			System.out.println();
		}

		//number of prizes to the winner
		Player winner, loser;
		n_prizes = 1 + ((random.nextInt(100) < 10)?1:0);					

		if(player_score > enemy_score)
		{
			System.out.println("Voce venceu!");
			this.enemy.setActive(false);
			winner = this.player;
			loser = this.enemy;
		}
		else
		{
			System.out.println("Voce perdeu!");
			winner = this.enemy;
			loser = this.player;
		}

		for(int i=0; i<n_prizes && loser.getItemsNames().length > 0; i++)
		{
			int index = random.nextInt(loser.getItemsNames().length);
			String item_name = loser.getItemsNames()[index];

			try
			{
				winner.addItem(loser.getItem(item_name));
				loser.removeItem(item_name);
				System.out.println("'" + winner.getName() + "' ganhou o item '" + item_name + "' do perdedor '" + loser.getName() + "'!");
			}
			catch(NoItemFoundException e)
			{
				System.out.println("ERROR: " + e.getMessage());
				System.exit(1);
			}
		}

		System.out.println("player score: " + player_score + " | enemy score: " + enemy_score);
		return;
	}
}
