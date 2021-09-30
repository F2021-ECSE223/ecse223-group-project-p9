/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 20 "domain_model.ump"
public class Admin extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Admin Associations
  private List<EquipmentBundle> equipmentBundles;
  private List<Equipment> equipments;
  private ClimbingSeason climbingSeason;
  private List<Hotel> hotels;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Admin(int aPassword, String aEmailAddress, ClimbingSeason aClimbingSeason)
  {
    super(aPassword, aEmailAddress);
    equipmentBundles = new ArrayList<EquipmentBundle>();
    equipments = new ArrayList<Equipment>();
    if (aClimbingSeason == null || aClimbingSeason.getAdmin() != null)
    {
      throw new RuntimeException("Unable to create Admin due to aClimbingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    climbingSeason = aClimbingSeason;
    hotels = new ArrayList<Hotel>();
  }

  public Admin(int aPassword, String aEmailAddress, Date aStartDateForClimbingSeason, Date aEndDateForClimbingSeason)
  {
    super(aPassword, aEmailAddress);
    equipmentBundles = new ArrayList<EquipmentBundle>();
    equipments = new ArrayList<Equipment>();
    climbingSeason = new ClimbingSeason(aStartDateForClimbingSeason, aEndDateForClimbingSeason, this);
    hotels = new ArrayList<Hotel>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public EquipmentBundle getEquipmentBundle(int index)
  {
    EquipmentBundle aEquipmentBundle = equipmentBundles.get(index);
    return aEquipmentBundle;
  }

  public List<EquipmentBundle> getEquipmentBundles()
  {
    List<EquipmentBundle> newEquipmentBundles = Collections.unmodifiableList(equipmentBundles);
    return newEquipmentBundles;
  }

  public int numberOfEquipmentBundles()
  {
    int number = equipmentBundles.size();
    return number;
  }

  public boolean hasEquipmentBundles()
  {
    boolean has = equipmentBundles.size() > 0;
    return has;
  }

  public int indexOfEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    int index = equipmentBundles.indexOf(aEquipmentBundle);
    return index;
  }
  /* Code from template association_GetMany */
  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipments.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipments()
  {
    List<Equipment> newEquipments = Collections.unmodifiableList(equipments);
    return newEquipments;
  }

  public int numberOfEquipments()
  {
    int number = equipments.size();
    return number;
  }

  public boolean hasEquipments()
  {
    boolean has = equipments.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipments.indexOf(aEquipment);
    return index;
  }
  /* Code from template association_GetOne */
  public ClimbingSeason getClimbingSeason()
  {
    return climbingSeason;
  }
  /* Code from template association_GetMany */
  public Hotel getHotel(int index)
  {
    Hotel aHotel = hotels.get(index);
    return aHotel;
  }

  public List<Hotel> getHotels()
  {
    List<Hotel> newHotels = Collections.unmodifiableList(hotels);
    return newHotels;
  }

  public int numberOfHotels()
  {
    int number = hotels.size();
    return number;
  }

  public boolean hasHotels()
  {
    boolean has = hotels.size() > 0;
    return has;
  }

  public int indexOfHotel(Hotel aHotel)
  {
    int index = hotels.indexOf(aHotel);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipmentBundles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public EquipmentBundle addEquipmentBundle(String aName, int aPercentDiscount, Equipment... allEquipments)
  {
    return new EquipmentBundle(aName, aPercentDiscount, this, allEquipments);
  }

  public boolean addEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    boolean wasAdded = false;
    if (equipmentBundles.contains(aEquipmentBundle)) { return false; }
    Admin existingAdmin = aEquipmentBundle.getAdmin();
    boolean isNewAdmin = existingAdmin != null && !this.equals(existingAdmin);
    if (isNewAdmin)
    {
      aEquipmentBundle.setAdmin(this);
    }
    else
    {
      equipmentBundles.add(aEquipmentBundle);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    boolean wasRemoved = false;
    //Unable to remove aEquipmentBundle, as it must always have a admin
    if (!this.equals(aEquipmentBundle.getAdmin()))
    {
      equipmentBundles.remove(aEquipmentBundle);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEquipmentBundleAt(EquipmentBundle aEquipmentBundle, int index)
  {  
    boolean wasAdded = false;
    if(addEquipmentBundle(aEquipmentBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipmentBundles()) { index = numberOfEquipmentBundles() - 1; }
      equipmentBundles.remove(aEquipmentBundle);
      equipmentBundles.add(index, aEquipmentBundle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentBundleAt(EquipmentBundle aEquipmentBundle, int index)
  {
    boolean wasAdded = false;
    if(equipmentBundles.contains(aEquipmentBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipmentBundles()) { index = numberOfEquipmentBundles() - 1; }
      equipmentBundles.remove(aEquipmentBundle);
      equipmentBundles.add(index, aEquipmentBundle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentBundleAt(aEquipmentBundle, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Equipment addEquipment(double aMass, int aPrice, Member aMember)
  {
    return new Equipment(aMass, aPrice, aMember, this);
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipments.contains(aEquipment)) { return false; }
    Admin existingAdmin = aEquipment.getAdmin();
    boolean isNewAdmin = existingAdmin != null && !this.equals(existingAdmin);
    if (isNewAdmin)
    {
      aEquipment.setAdmin(this);
    }
    else
    {
      equipments.add(aEquipment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    //Unable to remove aEquipment, as it must always have a admin
    if (!this.equals(aEquipment.getAdmin()))
    {
      equipments.remove(aEquipment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEquipmentAt(Equipment aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipments()) { index = numberOfEquipments() - 1; }
      equipments.remove(aEquipment);
      equipments.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipments.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipments()) { index = numberOfEquipments() - 1; }
      equipments.remove(aEquipment);
      equipments.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHotels()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Hotel addHotel(String aName, String aAddress, int aNumStars)
  {
    return new Hotel(aName, aAddress, aNumStars, this);
  }

  public boolean addHotel(Hotel aHotel)
  {
    boolean wasAdded = false;
    if (hotels.contains(aHotel)) { return false; }
    Admin existingAdmin = aHotel.getAdmin();
    boolean isNewAdmin = existingAdmin != null && !this.equals(existingAdmin);
    if (isNewAdmin)
    {
      aHotel.setAdmin(this);
    }
    else
    {
      hotels.add(aHotel);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHotel(Hotel aHotel)
  {
    boolean wasRemoved = false;
    //Unable to remove aHotel, as it must always have a admin
    if (!this.equals(aHotel.getAdmin()))
    {
      hotels.remove(aHotel);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHotelAt(Hotel aHotel, int index)
  {  
    boolean wasAdded = false;
    if(addHotel(aHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotels()) { index = numberOfHotels() - 1; }
      hotels.remove(aHotel);
      hotels.add(index, aHotel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHotelAt(Hotel aHotel, int index)
  {
    boolean wasAdded = false;
    if(hotels.contains(aHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotels()) { index = numberOfHotels() - 1; }
      hotels.remove(aHotel);
      hotels.add(index, aHotel);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHotelAt(aHotel, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=equipmentBundles.size(); i > 0; i--)
    {
      EquipmentBundle aEquipmentBundle = equipmentBundles.get(i - 1);
      aEquipmentBundle.delete();
    }
    for(int i=equipments.size(); i > 0; i--)
    {
      Equipment aEquipment = equipments.get(i - 1);
      aEquipment.delete();
    }
    ClimbingSeason existingClimbingSeason = climbingSeason;
    climbingSeason = null;
    if (existingClimbingSeason != null)
    {
      existingClimbingSeason.delete();
    }
    for(int i=hotels.size(); i > 0; i--)
    {
      Hotel aHotel = hotels.get(i - 1);
      aHotel.delete();
    }
    super.delete();
  }

}