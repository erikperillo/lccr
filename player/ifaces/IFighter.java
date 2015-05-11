package lccr.player.ifaces;

public interface IFighter
{
	public double attack(IFighter enemy, String attack);
	public double react(IFighter enemy, String attack);
}
