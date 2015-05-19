package item.impl;

public class ConsumableFactory extends BaseItemFactory
{
	public IItem createItem(String item_path)	
	{
		Consumable consumable;
		String name;
		String type = "consumable";
		String description;
		int attack; 
		int defense; 
		int duration;
		//loads from item_path all this suff above
		consumable = new Consumable(name,type,description,attack,defense,duration);	
		consumable.stock(1); //already adds one element to accounting
		return consumable;
	}
}
