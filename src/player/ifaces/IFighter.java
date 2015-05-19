package player.ifaces;

public interface IFighter
{
	public float attack(IFighter enemy, String attack);
	public float react(IFighter enemy, String attack);
}
