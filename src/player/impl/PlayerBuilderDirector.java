package player.impl;

import player.ifaces.*;
import player.excepts.*;
import java.io.IOException;
import item.excepts.*;

public class PlayerBuilderDirector implements IDirector
{
	public void construct(IPlayerBuilder builder) throws UnknownPlayerTypeException, NoItemFoundException, IOException
	{
		builder.createPlayer();
		builder.setAttacks();
		builder.setEquips();
		builder.setConsumables();			
	}
}
