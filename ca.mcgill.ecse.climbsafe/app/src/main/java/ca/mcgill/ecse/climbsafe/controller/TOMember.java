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
  private String emergencyContact;
  private String password;
  private int nrWeeks;
  private boolean guideRequired;
  private boolean hotelRequired;
  private List<String> bookedItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOMember(String aEmail, String aName, String aEmergencyContact, String aPassword, int aNrWeeks, boolean aGuideRequired, boolean aHotelRequired)
  {
    email = aEmail;
    name = aName;
    emergencyContact = aEmergencyContact;
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

  public boolean setEmergencyContact(String aEmergencyContact)
  {
    boolean wasSet = false;
    emergencyContact = aEmergencyContact;
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

  public boolean setNrWeeks(int aNrWeeks)
  {
    boolean wasSet = false;
    nrWeeks = aNrWeeks;
    wasSet = true;
    return wasSet;
  }

  public boolean setGuideRequired(boolean aGuideRequired)
  {
    boolean wasSet = false;
    guideRequired = aGuideRequired;
    wasSet = true;
    return wasSet;
  }

  public boolean setHotelRequired(boolean aHotelRequired)
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

  public String getEmergencyContact()
  {
    return emergencyContact;
  }

  public String getPassword()
  {
    return password;
  }

  public int getNrWeeks()
  {
    return nrWeeks;
  }

  public boolean getGuideRequired()
  {
    return guideRequired;
  }

  public boolean getHotelRequired()
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
  /* Code from template attribute_IsBoolean */
  public boolean isGuideRequired()
  {
    return guideRequired;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isHotelRequired()
  {
    return hotelRequired;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "name" + ":" + getName()+ "," +
            "emergencyContact" + ":" + getEmergencyContact()+ "," +
            "password" + ":" + getPassword()+ "," +
            "nrWeeks" + ":" + getNrWeeks()+ "," +
            "guideRequired" + ":" + getGuideRequired()+ "," +
            "hotelRequired" + ":" + getHotelRequired()+ "]";
  }
}