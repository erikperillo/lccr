package player.excepts;

import java.lang.Exception;

//exception thrown when attack is not found in inventory

public class AttackNotFoundException extends Exception
{
	public AttackNotFoundException()
	{
		super();
	}

	public AttackNotFoundException(String message)
	{
		super(message);
	}
}

