package item.impl;

public class EquipFactory extends BaseItemFactory
{
	public IItem createItem(String item_path)
	{
		String name;
		String type; //may be weapon/armor
		String description;
		int power;
		boolean equipped = false; //does not equip by default
		//loads from item_path all this stuff above
		return new Equip(name,type,description,power,equipped);
	}
}
