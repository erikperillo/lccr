package player.excepts;

import java.lang.Exception;

//exception thrown when the user specifies an unknown player for the factory 

public class UnknownPlayerTypeException extends Exception
{
	public UnknownPlayerTypeException()
	{
		super();
	}

	public UnknownPlayerTypeException(String message)
	{
		super(message);
	}
}

