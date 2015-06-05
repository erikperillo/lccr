package map.excepts;

import java.lang.Exception;

public class ResourceNotFoundException extends Exception
{
	public ResourceNotFoundException()
	{
		super();
	}

	public ResourceNotFoundException(String message)
	{
		super(message);
	}
}

