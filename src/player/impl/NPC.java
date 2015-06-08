package player.impl;	

public class NPC extends Player
{
	private String message;
	private boolean evil;
	private boolean active;

	public NPC(String name, String type, String message, float CR, float knowledge, float migue)
	{
		super(name,type,CR,knowledge,migue);
		this.message = message;
		this.active = true;
	}

	public String getMessage()
	{
		return this.message;
	}

	public boolean isEvil()
	{
		return this.getType().equalsIgnoreCase("evil");
	}

	public boolean isActive()
	{
		return this.active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}
}
