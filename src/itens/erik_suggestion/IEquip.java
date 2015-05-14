package itens;

public interface IEquip extends IItem
{
	//returns amount of attack/defense item provides
	public int getPower();
	
	//returns whether item is equipped or not
	public boolean equipped();	
}
