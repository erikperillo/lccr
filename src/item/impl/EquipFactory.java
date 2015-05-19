package item.impl;

import item.ifaces.IItem;
import item.ifaces.IItemFactory;

public class EquipFactory implements IItemFactory
{
	private String path;
	
	public EquipFactory(String path)
	{
		this.path = path;
	}
	
	@Override
	public IItem getItem()
	{
		String name;
		String type; //may be weapon/armor
		String description;
		int power;
		boolean equipped = false; //does not equip by default
		//loads from item_path all this stuff above
		//return new Equip(name,type,description,power,equipped);
		return new Equip("b0ss","arm","vaisifude",199,true);
	}
}
