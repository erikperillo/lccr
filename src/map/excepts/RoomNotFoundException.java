package map.excepts;

import java.lang.Exception;

//exception thrown when the user specifies an unknown player for the factory 

public class RoomNotFoundException extends Exception
{
	public RoomNotFoundException()
	{
		super();
	}

	public RoomNotFoundException(String message)
	{
		super(message);
	}
}

