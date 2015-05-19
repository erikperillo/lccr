package item.impl;

import item.ifaces.IItemFactory;

public class PackageTest 
{
	public static void main(String argv[])
	{
		//String options[] = new String[] {"-equipfact", "-consumablefact"};
			
		if(argv.length > 0)
		{
			if(argv[0].equalsIgnoreCase("equipfact"))
			{
				for(int i=1; i<argv.length; i++)
				{
					System.out.println("Simulating Equip fabrication from path '" + argv[i] + "' ...");
					testEquip(argv[i]);
				}
			}
			else if(argv[0].equalsIgnoreCase("consumablefact"))
			{
				for(int i=1; i<argv.length; i++)
				{
					System.out.println("Simulating Consumable fabrication from path '" + argv[i] + "' ...");
					testConsumable(argv[i]);
				}
			}
			else
			{
				System.out.println("invalid test name");
			}
		}
	}

	public static void testEquip(String path)
	{
		IItemFactory fact = new EquipFactory(path);
		Equip equip = (Equip) fact.getItem();
		
		System.out.println("\ttype: " + equip.getType());
		System.out.println("\tdescription: " + equip.getDescription());
		System.out.println("\tequipped: " + equip.equipped());
	}
	public static void testConsumable(String path)
	{
		ConsumableFactory fact = new ConsumableFactory(path);
		Consumable cons = (Consumable) fact.getItem();
		
		System.out.println("\ttype: " + cons.getType());
		System.out.println("\tdescription: " + cons.getDescription());
		System.out.println("\tHP: " + cons.getHP());
	}
}
