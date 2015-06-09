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
			final String loop_message = "Use:\n 'mapa' para ver onde esta;\n 'player' [usar ITEMCONSUMIVEL | mover [NOME_SALA | proximo]] para acoes no player;\n 'sala' [ info [NOME_NPC | NOME_ITEM] lutar|conversar [NOME_NPC] | evento [NOME_EVENTO] | pegar [NOME_ITEM] | portinha] para acoes na sala;\n 'info' [mapa | player | item [NOME_ITEM] | sala] para informacao sobre os itens acima"; 
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

			//variavel para coisas aleatorias
			Random rand = new Random();

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
					System.out.println("Voce chegou ao fim, fera");
					System.exit(0);
				}

				if(player.getCR() <= 0)
				{
					System.out.println("Voce morreu, sry");
					System.exit(0);
				}

				//updating map permissions
				map.updatePermissions();
				
				//game interaction
				ans = prompt.queryAnswer("\n" + loop_message);
				String[] operations;
				line = new Scanner(ans);

				prompt.clear();

				try
				{
					ans = line.next();
					//actions on sala
					if(ans.equalsIgnoreCase("sala"))
					{
						ans = line.next();
						boolean found = false;

						if(ans.equalsIgnoreCase("info"))
						{
							ans = line.nextLine().trim();
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
						}
						else if(ans.equalsIgnoreCase("lutar"))
						{
							ans = line.nextLine().trim();

							for(NPC npc: map.getPlayerRoom().getNPCs())
							{
								if(ans.equalsIgnoreCase(npc.getName()))
								{ 
									found = true;
									if(npc.getType().equalsIgnoreCase("evil"))
									{
										ans = prompt.queryAnswer("Lutar com '" + npc.getName() + "' (s/n) ?");
										if(!ans.equalsIgnoreCase("s") && !ans.equalsIgnoreCase("sim"))
											break;
										System.out.println("lutando com '" + npc.getName() + "' ...");
										event = new Fight("Luta daora",player,npc,3);
										event.routine();
									}
									else
										System.out.println("Voce nao pode lutar com este tipo de jogador!");
								}
								if(!found)
									System.out.println("Personagem '" + ans + "' nao encontrado!");
							}
						}
						else if(ans.equalsIgnoreCase("conversar"))
						{
							ans = line.nextLine().trim();

							for(NPC npc: map.getPlayerRoom().getNPCs())
							{
								if(ans.equalsIgnoreCase(npc.getName()))
								{ 
									found = true;
									System.out.println(npc.getName() + " diz: '" + npc.getMessage() + "'");
								}
								if(!found)
									System.out.println("Personagem '" + ans + "' nao encontrado!");
							}
						}
						else if(ans.equalsIgnoreCase("portinha"))
						{
							if(!map.getPlayerRoom().randomEventVisited())
							{
								ArrayList<RandomThingOfLife> random_evs = new ArrayList<RandomThingOfLife>();
								int index;

								for(IEvent ev: map.getPlayerRoom().getEvents())
									if(ev instanceof RandomThingOfLife)
										random_evs.add((RandomThingOfLife)ev);
								
								if(random_evs.size() > 0)
								{
									index = rand.nextInt(random_evs.size());
									random_evs.get(index).setPlayer(player);
									random_evs.get(index).routine();
									map.getPlayerRoom().setVisited(true);
								}
								else
									System.out.println("Nao ha nada! :)))");
							}
							else
								System.out.println("A portinha ja foi aberta!");
						}
						else if(ans.equalsIgnoreCase("evento"))
						{
							ans = line.nextLine().trim();
							event = null;

							for(IEvent ev: map.getPlayerRoom().getEvents())
								if(ev.getName().equalsIgnoreCase(ans) && !(ev instanceof RandomThingOfLife))
									event = ev;
									
							if(event == null)
								System.out.println("O evento '" + ans + "' nao existe na sala atual!");										
							else
							{
								if(event instanceof Quiz)
								{
									float gain;

									event.routine();

									System.out.println("Voce acertou " + ((Quiz)event).getRightAnswers() + " de " + ((Quiz)event).getTotalQuestions() + " questoes");												
									gain = ((float)((Quiz)event).getRightAnswers() / (float)((Quiz)event).getTotalQuestions());	
									gain = gain * gain * 0.1f;
									player.setKnowledge(player.getKnowledge() + gain);
									System.out.println("Seu conhecimento subiu " + gain + " pontos");

									map.getPlayerRoom().getEvents().remove(event);									
								}
							}
						}
						else if(ans.equalsIgnoreCase("pegar"))
						{
							ans = line.nextLine().trim();
							Item item = null;
							
							for(Item it: map.getPlayerRoom().getItems())
								if(ans.equalsIgnoreCase(it.getName()))
									item = it;
							
							if(item == null)
								System.out.println("Nao ha o item '" + ans + "' nesta sala!");	
							else
							{
								player.addItem(item);
								map.getPlayerRoom().getItems().remove(item);
								System.out.println("Item '" + item.getName() + "' adicionado ao inventario");
							}
						}
						else
							System.out.println("Operador invalido para 'sala'! Tente de novo");
					}
					//displays map
					else if(ans.equalsIgnoreCase("mapa"))
					{
						System.out.println("Sua localizacao:");
						map.draw();
					}
					//game info
					else if(ans.equalsIgnoreCase("info"))
					{
						ans = line.next();

						if(ans.equalsIgnoreCase("mapa"))
							System.out.println("Use 'mover' para mover, 'onde' para ver onde esta no mapa");
						else if(ans.equalsIgnoreCase("player"))
							player.describe();
						else if(ans.equalsIgnoreCase("item"))
						{
							ans = line.nextLine().trim();

							try
							{
								Item item = (Item)player.getItem(ans);
								item.describe();
							}
							catch(NoItemFoundException e)
							{
								System.out.println("Nao ha o item '" + ans + "' no seu inventorio!");
							}
						}
						else if(ans.equalsIgnoreCase("sala"))
							map.getPlayerRoom().describe();
						else
							System.out.println("Opcao invalida para info! Tente de novo");
					}
					//player actions
					else if(ans.equalsIgnoreCase("player"))
					{
						ans = line.next();

						if(ans.equalsIgnoreCase("usar"))
						{
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
						}
						else if(ans.equalsIgnoreCase("mover"))
						{
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
						}
						else
							System.out.println("Opcao invalida para player! Tente de novo");
					}
					//invalid option
					else
						System.out.println("Operacao invalida: " + ans);
			}
			catch(NoSuchElementException e)
			{
				System.out.println("Comando invalido! tente de novo");
			}
		}
	}
}
