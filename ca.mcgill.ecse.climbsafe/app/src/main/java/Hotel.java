/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.io.Serializable;

/**
 * persistence for BundleItem needs to be specified in ClimbSafe.ump
 * due to a bug in Umple (association classes cannot be defined in two files)
 */
// line 93 "ClimbSafePersistence.ump"
public class Hotel implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Hotel()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}

  // line 99 "ClimbSafePersistence.ump"
   public static  void reinitializeUniqueName(List<Hotel> hotels){
    hotelsByName = new HashMap<String, Hotel>();
    for (Hotel hotel : hotels) {
      hotelsByName.put(hotel.getName(), hotel);
    }
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 96 "ClimbSafePersistence.ump"
  private static final long serialVersionUID = 10L ;

  
}