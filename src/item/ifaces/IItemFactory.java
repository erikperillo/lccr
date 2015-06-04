package item.ifaces;

import item.excepts.NoItemFoundException;
import java.io.IOException;

public interface IItemFactory
{
	public abstract IItem getItem() throws IOException, NoItemFoundException;
}
