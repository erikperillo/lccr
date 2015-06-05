package prompt;

import java.util.Scanner;

public class Prompt
{
	private String prompt_symbol;

	public Prompt(String symbol)
	{
		this.prompt_symbol = symbol;
	}

	public boolean validAnswer(String ans, String[] options)
	{
		for(String opt: options)
			if(ans.equalsIgnoreCase(opt))
				return true;

		return false;
	}
	
	public String queryAnswer(String message, boolean prompt)
	{
		Scanner keyboard = new Scanner(System.in);

		System.out.print(message + (prompt?("\n" + this.prompt_symbol):""));

		return keyboard.nextLine();
	}

	public String queryAnswer(String message)
	{
		return queryAnswer(message,true);
	}

	public String queryValidAnswer(String message, String[] options)
	{
		String ans;
		ans = queryAnswer(message);

		while(true)
		{
			if(validAnswer(ans,options))
				break;

			System.out.println("Resposta invalida! Tente de novo.");	
			ans = queryAnswer(this.prompt_symbol,false);
		}

		return ans;
	}


}
