package player.ifaces;

import player.excepts.UnknownPlayerTypeException;
import java.io.IOException;
import item.excepts.NoItemFoundException;

public interface IPlayerBuilder
{
	void createPlayer() throws UnknownPlayerTypeException;
	void setAttacks();
	void setEquips() throws IOException, NoItemFoundException;
	void setConsumables() throws IOException, NoItemFoundException;
}
