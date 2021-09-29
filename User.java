/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 1 "domain_model.ump"
public class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByEmailAddress = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private int password;
  private String emailAddress;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(int aPassword, String aEmailAddress)
  {
    password = aPassword;
    if (!setEmailAddress(aEmailAddress))
    {
      throw new RuntimeException("Cannot create due to duplicate emailAddress. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPassword(int aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  /**
   * only user can access its password so it should be private
   */
  public int getPassword()
  {
    return password;
  }

  /**
   * immutable because it can't be changed
   */
  public String getEmailAddress()
  {
    return emailAddress;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithEmailAddress(String aEmailAddress)
  {
    return usersByEmailAddress.get(aEmailAddress);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEmailAddress(String aEmailAddress)
  {
    return getWithEmailAddress(aEmailAddress) != null;
  }

  public void delete()
  {
    usersByEmailAddress.remove(getEmailAddress());
  }


  public String toString()
  {
    return super.toString() + "["+
            "password" + ":" + getPassword()+ "," +
            "emailAddress" + ":" + getEmailAddress()+ "]";
  }
}