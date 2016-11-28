/**
 * Define and Address type.
 */
package dw317.lib;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author Alessandro Ciotola
 * Takes the city, civicnumber, province, 
 * code and streetname and returns the address.
 */ 
public class Address implements Serializable
{
	private String city = "";
	private String civicNumber = "";
	private String province = "";
	private String code = "";
	private String streetName = "";
	private static final long  serialVersionUID = 42031768871L;

	public Address() 
	{}
	
	/**
	 * Assigns the inputed civic number, street name, city, to their
	 * respecting fields in the Address class
	 * 
	 * @param civicNumber civic number of the address
	 * @param streetName name of the street of the address 
	 * @param city name of the city of the address
	 */
	public Address(String civicNumber, String streetName, String city) 
	{
		setCivicNumber(civicNumber);
		setStreetName(streetName);
		setCity(city);

	}
	
	
	public Address(String civicNumber, String streetName, String city, 			
			Optional<String> province, Optional<String> code)
	{
		setCivicNumber(civicNumber);
		setStreetName(streetName);
		setCity(city);
		
		if(province.isPresent())			
			setProvince(province);
		
		if(code.isPresent())
			setCode(code);
	}
	
	/**
	 * Returns a String representation of the address.
	 * @return String representation of the address.
	 */
	public String getAddress() 
	{
		String address = civicNumber + " " + streetName + "\n" + city;
		address += (province.equals("") ? "" : (", " + province))
				+ (code.equals("") ? "" : ("\n" + code));

		return address;
	}
	
	/**
	 * Returns the name of the city
	 * @return the name of the city.
	 */
	public String getCity() 
	{
		return city;
	}
	
	/**
	 * Returns the civicNumber field of the Address
	 * @return Civic number of the address.
	 */
	public String getCivicNumber()
	{
		return civicNumber;
	}
	
	/**
	 * Returns the province field of the Address
	 * @return Name of the province.
	 */
	public String getProvince()
	{
			return province;
	}

	/**
	 * Returns the postal code of the Address
	 * @return postal code uniquely identifying the address.
	 */
	public String getCode() 
	{
		
			return code;
	}
	
	/**
	 * Returns the streetName field of the Address
	 * @return Name of the Street.
	 */
	public String getStreetName() 
	{
		return streetName;
	}

	/**
	 * Modify the city field
	 * 
	 * @param city The String representing the name of the city of the address
	 */
	public void setCity(String city) 
	{
		this.city = validateExistence("City", city);
	}
	
	/**
	 * Modify the civicNumber field.
	 * @param civicNumber The String representing the civic number of the address
	 */
	public void setCivicNumber(String civicNumber)
	{
		this.civicNumber = validateExistence("civicNumber", civicNumber);
	}
	
	/**
	 * Modify the setProvince field.
	 * @param province The String representing the name of the province of the address
	 */
	public void setProvince(Optional <String> province) 
	{				
		this.province = validateExistence("Province", province.orElse(""));
	}
	
	/**
	 * Modify the code field.
	 * @param code The String representing the postal code of the address
	 */
	public void setCode(Optional <String> code)
	{
		this.code = validateExistence("Code", code.orElse(""));
	}
	
	/**
	 * Modify the streetName field.
	 * @param streetName The String representing the name of the street in the address
	 */
	public void setStreetName(String streetName) 
	{
		this.streetName = validateExistence("streetName", streetName);
	}

	/**
	 * Returns a String representation of the address.
	 * 
	 * @return address a formatted address.
	 */
	@Override
	public String toString() 
	{
		return (civicNumber + "*" + streetName + "*" + city + "*" + province
				+ "*" + code);
	}
	
	/**
	 * Validates the inputed value to ensure valid data.
	 * @param fieldName name of the field that is being validated
	 * @param fieldValue value of the field that is being validated
	 * @return trimmedString inputed value without trailing/leading whitespaces
	 * 
	 */
	
	//for empty string you can decide whether you want it or not
	private String validateExistence(String fieldName, String fieldValue) 
	{
		if (fieldValue == null)
			throw new IllegalArgumentException("Address Error -" + fieldName
					+ " must exist. Invalid value=" + fieldValue);
		
		String trimmedString = fieldValue.trim();
		if (trimmedString.trim().isEmpty())
			throw new IllegalArgumentException("Address Error - " + fieldName
					+ " must exist. Invalid value = " + fieldValue);
		return trimmedString;
	}
}