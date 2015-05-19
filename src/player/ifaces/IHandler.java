package player.ifaces;

public interface IHandler
{
	public IItem getItem(String item);
	public IItem addItem(String item);
	public IItem useItem(String item);
}
