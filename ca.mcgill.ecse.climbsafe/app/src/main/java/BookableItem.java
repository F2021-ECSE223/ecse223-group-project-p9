/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.io.Serializable;

/**
 * persistence for BookedItem needs to be specified in ClimbSafe.ump
 * due to a bug in Umple (association classes cannot be defined in two files)
 */
// line 61 "ClimbSafePersistence.ump"
public class BookableItem implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BookableItem()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}

  // line 67 "ClimbSafePersistence.ump"
   public static  void reinitializeUniqueName(List<Equipment> equipment, List<EquipmentBundle> bundles){
    bookableitemsByName = new HashMap<String, BookableItem>();
    for (Equipment e : equipment) {
      bookableitemsByName.put(e.getName(), e);
    }
    for (EquipmentBundle bundle : bundles) {
      bookableitemsByName.put(bundle.getName(), bundle);
    }
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 64 "ClimbSafePersistence.ump"
  private static final long serialVersionUID = 7L ;

  
}