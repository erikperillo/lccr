package event.impl;

import java.io.*;
import event.excepts.NoEventFoundException;
import event.ifaces.*;
import db.impl.DataBase;

public class QuizFactory implements IEventFactory
{
	public QuizFactory()
	{;}

	@Override
	public IEvent getEvent(String event_name) throws IOException, NoEventFoundException
	{
		DataBase db = DataBase.getInstance();

		try
		{
			return (Quiz)db.load(Quiz.class,event_name);
		}
		catch(FileNotFoundException | ClassNotFoundException e)
		{
			throw new NoEventFoundException("Quiz object not found in '" + db.getRoot());
		} 	
		catch(IOException e)
		{
			throw e;
		}
	}
}
