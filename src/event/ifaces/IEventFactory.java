package event.ifaces;

import event.excepts.NoEventFoundException;
import java.io.IOException;

public interface IEventFactory
{
	IEvent getEvent(String event_name) throws IOException, NoEventFoundException;
}
