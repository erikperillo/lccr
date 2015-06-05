package map.ifaces;

import item.excepts.NoItemFoundException;
import java.io.IOException;

public interface IItemFactory
{
	IItem getItem(String item_name) throws IOException, NoItemFoundException;
}
