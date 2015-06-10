package db.impl;

import java.io.*;
import db.ifaces.IDataBase;

public class DataBase implements IDataBase
{
	private static String root;
	private static DataBase db = null;

	private DataBase()
	{
		DataBase.root = DataBase.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/" + "gamedata";
		File file = new File(DataBase.root);
		
		if(!file.exists())
			file.mkdirs();
	}

	public static DataBase getInstance()
	{
		if(DataBase.db == null)
			DataBase.db = new DataBase();
	
		return DataBase.db;
	}			
			
	public void save(Object obj, String filename) throws IOException
	{
		FileOutputStream fos;
		ObjectOutputStream oos;

		try
		{
			fos = new FileOutputStream(DataBase.root + "/" + obj.getClass().getCanonicalName().replace(".","_") + "_" + filename.trim().toLowerCase().replace(" ","_") + ".ser");
			oos = new ObjectOutputStream(fos);

			oos.writeObject(obj);

			oos.close();
		}
		catch(IOException e)
		{
			throw e;
		}
	}

	public Object load(Class cls, String filename) throws IOException, ClassNotFoundException
	{
		FileInputStream fis;
		ObjectInputStream ois;
		Object obj;
		
		try
		{
			fis = new FileInputStream(DataBase.root + "/" + cls.getCanonicalName().replace(".","_") + "_" + filename.trim().toLowerCase().replace(" ","_") + ".ser");
			ois = new ObjectInputStream(fis);

			obj = ois.readObject();

			ois.close();
		}
		catch(IOException e)
		{
			throw e;
		}
		catch(ClassNotFoundException e)
		{
			throw e;
		}
	
		return obj;
	}

	public String getRoot()
	{
		return DataBase.root;
	}
}
