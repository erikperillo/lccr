package db.ifaces;

import java.io.IOException;

public interface IDataBase 
{
	//saves object as file named 'filename' in the data base directory
	void save(Object obj, String filename) throws IOException;
	//loads class saved as 'filename' existing in directory
	Object load(Class cls, String filename) throws IOException, ClassNotFoundException;
}
