package lccr.player;

public interface IPlayer
{
	public String getPosition();
	public String status();
	public int attack(IPlayer player, String attack);
	public int react(IPlayer player, String attack);
	public void useItem(String item);
	public void useItem(String item, IPlayer target);
}
