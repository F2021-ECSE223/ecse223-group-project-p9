/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;
import java.util.*;

// line 18 "../../../../../ClimbSafeTransferObjects.ump"
public class TOMember
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOMember Attributes
  private String email;
  private String name;
  private String emergencyContactName;
  private String emergencyContactEmail;
  private String password;
  private String nrWeeks;
  private String guideRequired;
  private String hotelRequired;
  private List<String> bookedItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOMember(String aEmail, String aName, String aEmergencyContactName, String aEmergencyContactEmail, String aPassword, String aNrWeeks, String aGuideRequired, String aHotelRequired)
  {
    email = aEmail;
    name = aName;
    emergencyContactName = aEmergencyContactName;
    emergencyContactEmail = aEmergencyContactEmail;
    password = aPassword;
    nrWeeks = aNrWeeks;
    guideRequired = aGuideRequired;
    hotelRequired = aHotelRequired;
    bookedItems = new ArrayList<String>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmergencyContactName(String aEmergencyContactName)
  {
    boolean wasSet = false;
    emergencyContactName = aEmergencyContactName;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmergencyContactEmail(String aEmergencyContactEmail)
  {
    boolean wasSet = false;
    emergencyContactEmail = aEmergencyContactEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setNrWeeks(String aNrWeeks)
  {
    boolean wasSet = false;
    nrWeeks = aNrWeeks;
    wasSet = true;
    return wasSet;
  }

  public boolean setGuideRequired(String aGuideRequired)
  {
    boolean wasSet = false;
    guideRequired = aGuideRequired;
    wasSet = true;
    return wasSet;
  }

  public boolean setHotelRequired(String aHotelRequired)
  {
    boolean wasSet = false;
    hotelRequired = aHotelRequired;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetMany */
  public boolean addBookedItem(String aBookedItem)
  {
    boolean wasAdded = false;
    wasAdded = bookedItems.add(aBookedItem);
    return wasAdded;
  }

  public boolean removeBookedItem(String aBookedItem)
  {
    boolean wasRemoved = false;
    wasRemoved = bookedItems.remove(aBookedItem);
    return wasRemoved;
  }

  public String getEmail()
  {
    return email;
  }

  public String getName()
  {
    return name;
  }

  public String getEmergencyContactName()
  {
    return emergencyContactName;
  }

  public String getEmergencyContactEmail()
  {
    return emergencyContactEmail;
  }

  public String getPassword()
  {
    return password;
  }

  public String getNrWeeks()
  {
    return nrWeeks;
  }

  public String getGuideRequired()
  {
    return guideRequired;
  }

  public String getHotelRequired()
  {
    return hotelRequired;
  }
  /* Code from template attribute_GetMany */
  public String getBookedItem(int index)
  {
    String aBookedItem = bookedItems.get(index);
    return aBookedItem;
  }

  public String[] getBookedItems()
  {
    String[] newBookedItems = bookedItems.toArray(new String[bookedItems.size()]);
    return newBookedItems;
  }

  public int numberOfBookedItems()
  {
    int number = bookedItems.size();
    return number;
  }

  public boolean hasBookedItems()
  {
    boolean has = bookedItems.size() > 0;
    return has;
  }

  public int indexOfBookedItem(String aBookedItem)
  {
    int index = bookedItems.indexOf(aBookedItem);
    return index;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "name" + ":" + getName()+ "," +
            "emergencyContactName" + ":" + getEmergencyContactName()+ "," +
            "emergencyContactEmail" + ":" + getEmergencyContactEmail()+ "," +
            "password" + ":" + getPassword()+ "," +
            "nrWeeks" + ":" + getNrWeeks()+ "," +
            "guideRequired" + ":" + getGuideRequired()+ "," +
            "hotelRequired" + ":" + getHotelRequired()+ "]";
  }
}