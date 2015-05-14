package items;

public interface IConsumable extends IITem
{
	//returns amount of CR the item increases
	public float getHP();

	//returns amount of attack the item increases
	public int getAttack();

	//returns amount of defense the item increases
	public int getDefense();

	//returns amount of time consumable lasts
	public int getDuration();

	//returns quantity of this item
	public int getQuantity();

	//stores more items
	public int stock(int quantity);	
}
