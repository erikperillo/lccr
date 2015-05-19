package player.excepts;

import java.lang.Exception;

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

