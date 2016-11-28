/**
 * 
 */
package dw317.lib;

import java.io.Serializable;
import java.util.Optional;

/**
 * Defines a person, encapsulates a person's name and Address.
 * 
 * @author Alessandro Ciotola
 *
 */
public class Person implements Serializable
{
	private Name name;
	private Address address;
	private static final long  serialVersionUID = 42031768871L;
	
	public Person(String firstName, String lastName)
	{
		setName(firstName, lastName);
		setAddress(address);
	}
	
	public Person (String firstName, String lastName, Address address)
	{
		setName(firstName, lastName);
		setAddress(address);
	}
	
	public Address getAddress()
	{		
		String province = "";
		String code = "";
		Address copyAddress;
		
		if (address.getProvince() != null)
			province = address.getProvince();
		
		if (address.getCode() != null)
			code = address.getCode();
		
		if (address.getCivicNumber().isEmpty() || address.getStreetName().isEmpty() || address.getCity().isEmpty())
		{
			copyAddress = new Address();
			return copyAddress;
		}
		
		copyAddress = new Address(address.getCivicNumber(), address.getStreetName(), address.getCity(), Optional.of(province), Optional.of(code));	
		return copyAddress;
	}
	
	public Name getName()
	{
		Name copyName = new Name(name.getFirstName(), name.getLastName());
		return copyName;
	}
	
	public void setName (String firstName, String lastName)
	{
		name = new Name(firstName, lastName);
	}
	
	public void setAddress (Address address)
	{
		this.address = address;
	}
	
	@Override
	public String toString()
	{
		return name.toString() + "*" + (address == null ? "" : 
					address.toString());
	}
}
