package main;

import java.lang.*;
import java.io.*;
import java.util.*;

import player.impl.*;

import event.ifaces.*;
import event.impl.*;

import item.impl.*;
import item.ifaces.*;

public class LutaContraOCR
{

	public final static String welcome_message = "Bem-vindo ao jogo 'a luta contra o CR'!\nNese RPG, o seu objetivo é (aparentemente) simples: se formar.\nVoce vai passar pelo castelo da graduacao, enfrentando cinco andares de provas, baladas, professores mal-amados e tudo mais de confusao que a universidade proporciona!\nVoce tem que passar por todos os andares sem nunca zerar seu CR que, neste jogo, e a sua vida.\nNa universidade, saber e poder, entao seu poder de ataque e o seu conhecimento. Se defender tambem e importante, e aqui sua defesa e o seu migue.\tTodos seus parametros vao de zero a um.";

	public final static String nerd_info = "NERD: voce vai sair na frente com conhecimento alto (0.8), mas com um baixo migue. Nao vai ser malandro nem ter muitos amiguinhos."; 
	public final static String varzea_info = "VARZEA: voce vai ser muito malandrao e ser pop. Comeca com migue alto (0.8), mas com um baixo conhecimento (0.2). Nao espere por ser um genio."; 
	public final static String cinco_bola_info = "CINCO BOLA: o aluno padrao. Nem muito esperto, nem muito burro. Se da relativamente bem com quase todos, tira as notas que tem que tirar, tem seu circulo de amiguinhos e vai em algumas festas. Conhecimento e migue equilibrados (0.45 e 0.45)";
	
	public final static String[] player_types = {"nerd","varzea","cinco bola"};

	public static boolean validAnswer(String ans, String[] options)
	{
		for(String opt: options)
			if(ans.equalsIgnoreCase(opt))
				return true;

		return false;
	}
	
	public static String queryAnswer(String message, boolean prompt)
	{
		Scanner keyboard = new Scanner(System.in);

		System.out.print(message + (prompt?"\n>>> ":""));

		return keyboard.nextLine();
	}

	public static String queryAnswer(String message)
	{
		return queryAnswer(message,true);
	}

	public static String queryValidAnswer(String message, String[] options)
	{
		String ans;
		ans = queryAnswer(message);

		while(true)
		{
			if(validAnswer(ans,options))
				break;

			System.out.println("Resposta invalida! Tente de novo.");	
			ans = queryAnswer(">>> ",false);
		}

		return ans;
	}

	public static void main(String[] argv)
	{
		final String loop_message = "Use:\n\t'aqui' para ver onde esta;\n\t'listar' para listar alguma propriedade (itens/ataques)\n\t'info' <NOME> para informacao sobre alguma coisa";
		final String[] def_opts = {"aqui","listar","info"};
		String ans;

		System.out.println(welcome_message);

		System.out.println("\nE importante decidir desde o inicio o tipo de aluno que voce vai ser. Os existentes sao:");
		System.out.println(nerd_info);
		System.out.println(varzea_info);
		System.out.println(cinco_bola_info);
		
		ans = queryValidAnswer("Qual voce quer ser? (nerd, varzea, cinco bola)",player_types);
		System.out.println("voce escolheu ser um aluno " + ans + ".\n");
			
		while(true)
		{
			ans = queryValidAnswer(loop_message,def_opts);
			System.out.println("tomando acao com '" + ans + " ...");
		}
	}
}
