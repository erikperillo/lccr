package player.ifaces;

import player.impl.*;
import player.excepts.*;
import java.io.IOException;
import item.excepts.*;

public interface IDirector
{
	void construct(IPlayerBuilder builder) throws UnknownPlayerTypeException, NoItemFoundException, IOException;
}
