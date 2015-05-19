package item.impl;

class ConsumableFactory implements IItemFactory
{
	private String path;

	public ConsumableFactory(String path)
	{
		this.path = path;
	}

	@Override
	public Item getItem()
	{
		String name;
		String type;
		String description;
		int attack;
		int defense;	
		int duration;
		//opens from database
		return new Consumable("ai","limao","caralhos alados",4,2,-1);
	}
}
