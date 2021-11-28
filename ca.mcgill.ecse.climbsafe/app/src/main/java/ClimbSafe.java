/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.io.Serializable;

// line 3 "ClimbSafePersistence.ump"
public class ClimbSafe implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbSafe()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}

  // line 9 "ClimbSafePersistence.ump"
   public void reinitialize(){
    User.reinitializeUniqueEmail(this.getAdministrator(), this.getGuides(), this.getMembers());
    BookableItem.reinitializeUniqueName(this.getEquipment(), this.getBundles());
    Hotel.reinitializeUniqueName(this.getHotels());
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 6 "ClimbSafePersistence.ump"
  private static final long serialVersionUID = 1L ;

  
}