package event.excepts;

import java.lang.Exception;

public class NoEventFoundException extends Exception
{
	public NoEventFoundException()
	{
		super();
	}
	
	public NoEventFoundException(String message)
	{
		super(message);
	}
}
