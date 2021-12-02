/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;

// line 30 "../../../../../ClimbSafeTransferObjects.ump"
public class TOGuide
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOGuide Attributes
  private String email;
  private String name;
  private String emergencyContactName;
  private String emergencyContactEmail;
  private String password;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOGuide(String aEmail, String aName, String aEmergencyContactName, String aEmergencyContactEmail, String aPassword)
  {
    email = aEmail;
    name = aName;
    emergencyContactName = aEmergencyContactName;
    emergencyContactEmail = aEmergencyContactEmail;
    password = aPassword;
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

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "name" + ":" + getName()+ "," +
            "emergencyContactName" + ":" + getEmergencyContactName()+ "," +
            "emergencyContactEmail" + ":" + getEmergencyContactEmail()+ "," +
            "password" + ":" + getPassword()+ "]";
  }
}