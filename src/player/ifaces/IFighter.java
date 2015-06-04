package player.ifaces;

import player.excepts.*;
import java.io.IOException;

public interface IFighter
{
	public float attack(String attack) throws IOException, AttackNotFoundException;
	public float react(String attack) throws IOException, AttackNotFoundException;
}
