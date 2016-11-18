package group8.util;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Utility 
{
	private Utility()
	{}
	
	public static void serializeObject	(Object object, String fileSpecification) throws IOException   
	{
		ObjectOutputStream out = null;
		try
		{
			out = new ObjectOutputStream (new FileOutputStream (fileSpecification));
			out.writeObject (object);
		}
		catch (IOException e)
		{
			//normally the exception would be logged to file then thrown
			throw new IOException ("Error serializing object to \n" + fileSpecification  + " " + e);
		}
		finally
		{
			if (out != null)
				out.close ();
		}
	 }
	
	public static Object deserializeObject (String fileSpecification)
			throws IOException, ClassNotFoundException 
	{
		ObjectInputStream in = null;
		try 
		{
			Object obj = null;
			in = new ObjectInputStream(new FileInputStream (fileSpecification));
			if (in != null)
				obj = in.readObject ();
			return obj;
		}
		catch (ClassNotFoundException | IOException e)
		{
			//normally the exception would be logged to file then thrown
			throw new IOException ("Error deserializing object from " + fileSpecification + "\n" + e);
		}
		finally
		{
			if (in != null)
				in.close ();
		}
	}
	
	public static <T> T copyOf(T obj)
	{
		try 
		{
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			ObjectOutputStream objOut = new ObjectOutputStream(byteArrayOut);
			objOut.writeObject(obj);
			objOut.close();

			ByteArrayInputStream byteArrayIn = new ByteArrayInputStream(byteArrayOut.toByteArray());
			ObjectInputStream objIn = new ObjectInputStream(byteArrayIn);
			@SuppressWarnings("unchecked")
			T deepCopy = (T) objIn.readObject();
			objIn.close();
			return deepCopy;
		} 
		catch (Exception e)
		{
			// this shouldn't happen if all objects are serializable
			System.err.println("Deep copy error. ");
			return null;
		}
	}
}
