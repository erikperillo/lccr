package item.ifaces;

public interface IItem
{
	//returns the item's name
	String getName();

	//returns the item's type
	String getType();

	//returns the item's description in a formatted string
	void describe();
}
