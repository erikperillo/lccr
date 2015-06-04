package item.ifaces;

public interface IConsumable
{
	//returns amount of CR the item increases
	public float getHP();

	//returns amount of attack the item increases
	public float getAttack();

	//returns amount of defense the item increases
	public float getDefense();

	//returns amount of time consumable lasts
	public int getDuration();
}
