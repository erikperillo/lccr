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
		final String loop_message = "Use:\n\t'mapa' [mover/aqui] para acoes no mapa;\n\t'listar' [itens/ataques] para listar alguma propriedade\n\t'info' [mapa/player/(item ITEM)/(ataque ATAQUE)] para informacao sobre alguma coisa";
		final String[] def_opts = {"aqui","listar","info"};
		final String[] map_opts = {"mover","aqui"};
		final String[] info_opts = {"mapa","player","item","ataque"};
		final String[] list_opts = {"itens","ataques"};
		final String[] positives = {"sim","s","yes","y","yep"};

		//variaveis de leitura de console
		Prompt prompt = new Prompt(">>> ");
		String ans, name, type, operand;
		Scanner line;
	
		//variaveis do player
		Player player;

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
			player = PlayerMaker.getPlayer(type,name);
		}
		catch(UnknownPlayerTypeException e)
		{
			System.out.println("ERRO: " + e.getMessage());
			System.exit(1);
		}

		//main loop
		System.out.println();
			
		while(true)
		{
			ans = prompt.queryAnswer(loop_message);
			String[] operations;
			line = new Scanner(ans);
	
			try
			{
				ans = line.next();

				switch(ans)
				{
					case "mapa":
						operations = map_opts;
						ans = line.next();
						if(!prompt.validAnswer(ans,operations))
						{
							System.out.println("Opcao invalida para mapa! Tente de novo");
							break;
						}
						System.out.println("tomando acao com 'mapa " + ans + "' ...");
						break;

					case "info":
						operations = info_opts;
						ans = line.next();
						if(!prompt.validAnswer(ans,operations))
						{
							System.out.println("Opcao invalida para listagem! Tente de novo");
							break;
						}
						System.out.println("tomando acao com 'info " + ans + "' ...");
						break;

					default:
						System.out.println("operacao: " + ans);
				}
			}
			catch(NoSuchElementException e)
			{
				System.out.println("Comando invalido! tente de novo");
			}
		}
	}
}
