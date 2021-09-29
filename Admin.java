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
  private List<EquipmentBundle> equipmentBundle;
  private List<Equipment> equipment;
  private ClimbingSeason climbingSeason;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Admin(int aPassword, String aEmailAddress, ClimbingSeason aClimbingSeason)
  {
    super(aPassword, aEmailAddress);
    equipmentBundle = new ArrayList<EquipmentBundle>();
    equipment = new ArrayList<Equipment>();
    if (aClimbingSeason == null || aClimbingSeason.getAdmin() != null)
    {
      throw new RuntimeException("Unable to create Admin due to aClimbingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    climbingSeason = aClimbingSeason;
  }

  public Admin(int aPassword, String aEmailAddress, Date aStartDateForClimbingSeason, Date aEndDateForClimbingSeason)
  {
    super(aPassword, aEmailAddress);
    equipmentBundle = new ArrayList<EquipmentBundle>();
    equipment = new ArrayList<Equipment>();
    climbingSeason = new ClimbingSeason(aStartDateForClimbingSeason, aEndDateForClimbingSeason, this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public EquipmentBundle getEquipmentBundle(int index)
  {
    EquipmentBundle aEquipmentBundle = equipmentBundle.get(index);
    return aEquipmentBundle;
  }

  public List<EquipmentBundle> getEquipmentBundle()
  {
    List<EquipmentBundle> newEquipmentBundle = Collections.unmodifiableList(equipmentBundle);
    return newEquipmentBundle;
  }

  public int numberOfEquipmentBundle()
  {
    int number = equipmentBundle.size();
    return number;
  }

  public boolean hasEquipmentBundle()
  {
    boolean has = equipmentBundle.size() > 0;
    return has;
  }

  public int indexOfEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    int index = equipmentBundle.indexOf(aEquipmentBundle);
    return index;
  }
  /* Code from template association_GetMany */
  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipment.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipment()
  {
    List<Equipment> newEquipment = Collections.unmodifiableList(equipment);
    return newEquipment;
  }

  public int numberOfEquipment()
  {
    int number = equipment.size();
    return number;
  }

  public boolean hasEquipment()
  {
    boolean has = equipment.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipment.indexOf(aEquipment);
    return index;
  }
  /* Code from template association_GetOne */
  public ClimbingSeason getClimbingSeason()
  {
    return climbingSeason;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipmentBundle()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public EquipmentBundle addEquipmentBundle(String aName, int aPercentDiscount, Equipment... allEquipment)
  {
    return new EquipmentBundle(aName, aPercentDiscount, this, allEquipment);
  }

  public boolean addEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    boolean wasAdded = false;
    if (equipmentBundle.contains(aEquipmentBundle)) { return false; }
    Admin existingAdmin = aEquipmentBundle.getAdmin();
    boolean isNewAdmin = existingAdmin != null && !this.equals(existingAdmin);
    if (isNewAdmin)
    {
      aEquipmentBundle.setAdmin(this);
    }
    else
    {
      equipmentBundle.add(aEquipmentBundle);
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
      equipmentBundle.remove(aEquipmentBundle);
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
      if(index > numberOfEquipmentBundle()) { index = numberOfEquipmentBundle() - 1; }
      equipmentBundle.remove(aEquipmentBundle);
      equipmentBundle.add(index, aEquipmentBundle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentBundleAt(EquipmentBundle aEquipmentBundle, int index)
  {
    boolean wasAdded = false;
    if(equipmentBundle.contains(aEquipmentBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipmentBundle()) { index = numberOfEquipmentBundle() - 1; }
      equipmentBundle.remove(aEquipmentBundle);
      equipmentBundle.add(index, aEquipmentBundle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentBundleAt(aEquipmentBundle, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipment()
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
    if (equipment.contains(aEquipment)) { return false; }
    Admin existingAdmin = aEquipment.getAdmin();
    boolean isNewAdmin = existingAdmin != null && !this.equals(existingAdmin);
    if (isNewAdmin)
    {
      aEquipment.setAdmin(this);
    }
    else
    {
      equipment.add(aEquipment);
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
      equipment.remove(aEquipment);
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
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipment.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=equipmentBundle.size(); i > 0; i--)
    {
      EquipmentBundle aEquipmentBundle = equipmentBundle.get(i - 1);
      aEquipmentBundle.delete();
    }
    for(int i=equipment.size(); i > 0; i--)
    {
      Equipment aEquipment = equipment.get(i - 1);
      aEquipment.delete();
    }
    ClimbingSeason existingClimbingSeason = climbingSeason;
    climbingSeason = null;
    if (existingClimbingSeason != null)
    {
      existingClimbingSeason.delete();
    }
    super.delete();
  }

}