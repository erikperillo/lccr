package player.excepts;

import java.lang.Exception;

public class NoItemFoundException extends Exception
{
	public NoItemFoundException()
	{
		super();
	}
	
	public NoItemFoundException(String message)
	{
		super(message);
	}
}
