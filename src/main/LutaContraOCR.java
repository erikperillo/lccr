	package main;

	import java.io.*;
	import java.util.*;
	import java.lang.*;

	import player.impl.*;
	import player.excepts.*;

	import event.ifaces.*;
	import event.impl.*;

	import item.impl.*;
	import item.ifaces.*;
	import item.excepts.*;

	import map.impl.*;
	import map.excepts.*;

	import prompt.Prompt;

	public class LutaContraOCR
	{

		public final static String welcome_message = "Bem-vindo ao jogo 'a luta contra o CR'!\nNese RPG, o seu objetivo é (aparentemente) simples: se formar.\nVoce vai passar pelo castelo da graduacao, enfrentando cinco andares de provas, baladas, professores mal-amados e tudo mais de confusao que a universidade proporciona!\nVoce tem que passar por todos os andares sem nunca zerar seu CR que, neste jogo, e a sua vida.\nNa universidade, saber e poder, entao seu poder de ataque e o seu conhecimento. Se defender tambem e importante, e aqui sua defesa e o seu migue.\tTodos seus parametros vao de zero a um.";

		public final static String nerd_info = "NERD: voce vai sair na frente com conhecimento alto (0.8), mas com um baixo migue. Nao vai ser malandro nem ter muitos amiguinhos."; 
		public final static String varzea_info = "VARZEA: voce vai ser muito malandrao e ser pop. Comeca com migue alto (0.8), mas com um baixo conhecimento (0.2). Nao espere por ser um genio."; 
		public final static String cinco_bola_info = "CINCO BOLA: o aluno padrao. Nem muito esperto, nem muito burro. Se da relativamente bem com quase todos, tira as notas que tem que tirar, tem seu circulo de amiguinhos e vai em algumas festas. Conhecimento e migue equilibrados (0.45 e 0.45)";
		
		public final static String[] player_types = {"nerd","varzea","cinco bola"};

		public static void main(String[] argv)
		{
			//constantes
			final String loop_message = "Use:\n 'mapa' para ver onde esta;\n 'player' [usar ITEMCONSUMIVEL | mover [NOME_SALA | proximo]] para acoes no player\n 'sala' [ info [NOME_NPC | NOME_ITEM] [lutar|conversar] [NOME_NPC] ]\n 'info' [mapa | player | (item ITEM) | sala] para informacao sobre os itens acima"; 
			final String[] positives = {"sim","s","yes","y","yep"}; 
			//final String os = System.getProperty("os.name");

			//variaveis de leitura de console 
			Prompt prompt = new Prompt(">>> "); 
			String ans, name, type, operand; 
			Scanner line; 

			//variaveis do player 
			Player player = null; 
			final String[] def_attacks = {"Pergunta Dificil","Resposta Enrolada"};
			final String[] def_consumables = {"Vodka Orloff"};
			final String[] def_equips = {"Colinha de bolso"};

			//variaveis do mapa
			final String[] rooms_names = {"1","2","3","1","2","3","1","2","3",};
			map.impl.Map map; //solving ambiguity

			//variaveis de evento
			IEvent event = null;

			//INICIO DO JOGO
			System.out.println(welcome_message);
			System.out.println("\nE importante decidir desde o inicio o tipo de aluno que voce vai ser. Os existentes sao:");
			System.out.println(nerd_info);
			System.out.println(varzea_info);
			System.out.println(cinco_bola_info);
			
			while(true)
			{
				type = prompt.queryValidAnswer("\nQual tipo voce quer ser? (nerd, varzea, cinco bola)",player_types);
				System.out.println("Voce escolheu ser um aluno " + type + ".\n");
				name = prompt.queryAnswer("Escolha um nome para o seu personagem:");
				System.out.println("Voce escolheu o nome '" + name + "'.");
				ans = prompt.queryAnswer("Confirma as respostas? (s/n) ",false);
				if(prompt.validAnswer(ans,positives))
					break;
			}

			try
			{
				PlayerBuilder builder = new PlayerBuilder(name,type,def_attacks,def_equips,def_consumables);
				PlayerBuilderDirector director = new PlayerBuilderDirector();
				director.construct(builder);
				player = builder.getPlayer();
			}
			catch(UnknownPlayerTypeException | NoItemFoundException | IOException e)
			{
				System.out.println("ERRO: " + e.getMessage());
				System.exit(1);
			}

			//loading map and giving it names 
			map = MapFactory.getMap(rooms_names);
			//subscribing map to player
			player.subscribe(map); 
			//main loop
			System.out.println();
				
			while(true)
			{
				//checking game variables
				if(map.end())
				{
					System.out.println("voce chegou ao fim, fera");
					System.exit(0);
				}

				if(player.getCR() <= 0)
				{
					System.out.println("voce morreu, sry");
					System.exit(0);
				}

				//updating map permissions
				map.updatePermissions();
				
				//game interaction
				ans = prompt.queryAnswer("\n" + loop_message);
				String[] operations;
				line = new Scanner(ans);
	/*
				try
				{
					if (os.contains("Windows"))
						Runtime.getRuntime().exec("cls");
					else
						Runtime.getRuntime().exec("clear");
				}
				catch (final Exception e)
				{;}
	*/
				prompt.clear();

				try
				{
					ans = line.next();
					switch(ans)
					{
						case "sala":
							ans = line.next();
							boolean found = false;
							switch(ans)
							{
								case "info":
									ans = line.next();
									for(NPC npc: map.getPlayerRoom().getNPCs())
										if(ans.equalsIgnoreCase(npc.getName()))
										{
											npc.describe();
											found = true;	
										}	
									for(Item item: map.getPlayerRoom().getItems())
										if(ans.equalsIgnoreCase(item.getName()))
										{
											item.describe();
											found = true;	
										}
									if(!found)
										System.out.println("Nao ha o personagem/item nomeado  '" + ans + "' na sala!");
									break;
								case "lutar":
									ans = line.nextLine().trim();
									for(NPC npc: map.getPlayerRoom().getNPCs())
									{
										if(ans.equalsIgnoreCase(npc.getName()))
										{ 
											found = true;
											if(npc.getType().equalsIgnoreCase("evil"))
											{
												System.out.println("lutando com '" + npc.getName() + "' ...");
												event = new Fight("Luta daora",player,npc,10);
												event.routine();
											}
											else
												System.out.println("Voce nao pode lutar com este tipo de jogador!");
										}
										if(!found)
											System.out.println("Personagem '" + ans + "' nao encontrado!");
									}
									break;
								case "conversar":
									ans = line.nextLine().trim();
									for(NPC npc: map.getPlayerRoom().getNPCs())
									{
										if(ans.equalsIgnoreCase(npc.getName()))
										{ 
											found = true;
											System.out.println(npc.getMessage());
										}
										if(!found)
											System.out.println("Personagem '" + ans + "' nao encontrado!");
									}
									break;
								default:
									System.out.println("Operador invalido para 'sala'! Tente de novo");
									break;
							}
							break;

						case "mapa":
							System.out.println("Sua localizacao:");
							map.draw();
							break;

						case "info":
							ans = line.next();
							switch(ans)
							{
								case "mapa":
									System.out.println("Use 'mover' para mover, 'onde' para ver onde esta no mapa");
									break;
								case "player":
									player.describe();
									break;
								case "item":
									ans = line.nextLine().trim();
									try
									{
										Item item = (Item)player.getItem(ans);
										item.describe();
									}
									catch(NoItemFoundException e)
									{
										System.out.println("Nao ha o item '" + ans + "' no seu inventorio!");
										break;
									}
									break;
								case "sala":
									map.getPlayerRoom().describe();
									break;
								default:
									System.out.println("Opcao invalida para info! Tente de novo");
									break;
							}
							break;
						
						case "player":
							ans = line.next();
							switch(ans)
							{
								case "usar":
									ans = line.nextLine().trim();
									try
									{
										player.useItem(ans);
										System.out.println("Item '" + ans + "' usado com sucesso!");
									}		
									catch(NoItemFoundException e)
									{
										System.out.println("Item '" + ans + "' nao encontrado em seu inventorio!");
									}
									catch(ClassCastException e)
									{
										System.out.println("Item '" + ans + "' nao pode ser consumido!");
									}
									break;
								case "mover":
									ans = line.next();
									Room room = null;
									try
									{
										if(ans.equalsIgnoreCase("proximo") || ans.equalsIgnoreCase("prox"))
											room = map.getRoomByNumber(player.getLocation()+1);	
										else
											room = map.getRoomByName(ans);

										if(room.playerAllowed())
										{
											player.move(room.getNumber());
											System.out.println("Player '" + player.getName() + "' se moveu para a sala '" + map.getPlayerRoom().getName() + "'");
										}
										else
											System.out.println("Voce ainda nao pode se mover para essa localizacao!");
																		}
									catch(RoomNotFoundException e)
									{
										System.out.println("Este local nao existe!");
									}
									break;
								default:
									System.out.println("Opcao invalida para player! Tente de novo");
									break;
							}
							break;

						default:
							System.out.println("operacao invalida: " + ans);
					}
			}
			catch(NoSuchElementException e)
			{
				System.out.println("Comando invalido! tente de novo");
			}
		}
	}
}
