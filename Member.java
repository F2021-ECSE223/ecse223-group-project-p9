/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 12 "domain_model.ump"
public class Member extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Member Attributes
  private boolean hotelStay;
  private boolean hireGuide;
  private String name;

  //Member Associations
  private MountainGuide mountainGuide;
  private ClimbingSeason climbingSeason;
  private List<ClimbingWeek> climbingWeek;
  private EmergencyContact emergencyContact;
  private Hotel hotel;
  private List<EquipmentBundle> equipmentBundle;
  private List<Equipment> equipment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Member(int aPassword, String aEmailAddress, boolean aHotelStay, boolean aHireGuide, String aName, ClimbingSeason aClimbingSeason, EmergencyContact aEmergencyContact, ClimbingWeek... allClimbingWeek)
  {
    super(aPassword, aEmailAddress);
    hotelStay = aHotelStay;
    hireGuide = aHireGuide;
    name = aName;
    boolean didAddClimbingSeason = setClimbingSeason(aClimbingSeason);
    if (!didAddClimbingSeason)
    {
      throw new RuntimeException("Unable to create member due to climbingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    climbingWeek = new ArrayList<ClimbingWeek>();
    boolean didAddClimbingWeek = setClimbingWeek(allClimbingWeek);
    if (!didAddClimbingWeek)
    {
      throw new RuntimeException("Unable to create Member, must have at least 1 climbingWeek. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aEmergencyContact == null || aEmergencyContact.getMember() != null)
    {
      throw new RuntimeException("Unable to create Member due to aEmergencyContact. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    emergencyContact = aEmergencyContact;
    equipmentBundle = new ArrayList<EquipmentBundle>();
    equipment = new ArrayList<Equipment>();
  }

  public Member(int aPassword, String aEmailAddress, boolean aHotelStay, boolean aHireGuide, String aName, ClimbingSeason aClimbingSeason, String aNameForEmergencyContact, int aPhoneNumberForEmergencyContact, String aEmailAddressForEmergencyContact, MountainGuide aMountainGuideForEmergencyContact, ClimbingWeek... allClimbingWeek)
  {
    super(aPassword, aEmailAddress);
    hotelStay = aHotelStay;
    hireGuide = aHireGuide;
    name = aName;
    boolean didAddClimbingSeason = setClimbingSeason(aClimbingSeason);
    if (!didAddClimbingSeason)
    {
      throw new RuntimeException("Unable to create member due to climbingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    climbingWeek = new ArrayList<ClimbingWeek>();
    boolean didAddClimbingWeek = setClimbingWeek(allClimbingWeek);
    if (!didAddClimbingWeek)
    {
      throw new RuntimeException("Unable to create Member, must have at least 1 climbingWeek. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    emergencyContact = new EmergencyContact(aNameForEmergencyContact, aPhoneNumberForEmergencyContact, aEmailAddressForEmergencyContact, aMountainGuideForEmergencyContact, this);
    equipmentBundle = new ArrayList<EquipmentBundle>();
    equipment = new ArrayList<Equipment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setHotelStay(boolean aHotelStay)
  {
    boolean wasSet = false;
    hotelStay = aHotelStay;
    wasSet = true;
    return wasSet;
  }

  public boolean setHireGuide(boolean aHireGuide)
  {
    boolean wasSet = false;
    hireGuide = aHireGuide;
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

  public boolean getHotelStay()
  {
    return hotelStay;
  }

  public boolean getHireGuide()
  {
    return hireGuide;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetOne */
  public MountainGuide getMountainGuide()
  {
    return mountainGuide;
  }

  public boolean hasMountainGuide()
  {
    boolean has = mountainGuide != null;
    return has;
  }
  /* Code from template association_GetOne */
  public ClimbingSeason getClimbingSeason()
  {
    return climbingSeason;
  }
  /* Code from template association_GetMany */
  public ClimbingWeek getClimbingWeek(int index)
  {
    ClimbingWeek aClimbingWeek = climbingWeek.get(index);
    return aClimbingWeek;
  }

  public List<ClimbingWeek> getClimbingWeek()
  {
    List<ClimbingWeek> newClimbingWeek = Collections.unmodifiableList(climbingWeek);
    return newClimbingWeek;
  }

  public int numberOfClimbingWeek()
  {
    int number = climbingWeek.size();
    return number;
  }

  public boolean hasClimbingWeek()
  {
    boolean has = climbingWeek.size() > 0;
    return has;
  }

  public int indexOfClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    int index = climbingWeek.indexOf(aClimbingWeek);
    return index;
  }
  /* Code from template association_GetOne */
  public EmergencyContact getEmergencyContact()
  {
    return emergencyContact;
  }
  /* Code from template association_GetOne */
  public Hotel getHotel()
  {
    return hotel;
  }

  public boolean hasHotel()
  {
    boolean has = hotel != null;
    return has;
  }
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
  /* Code from template association_SetOptionalOneToMany */
  public boolean setMountainGuide(MountainGuide aMountainGuide)
  {
    boolean wasSet = false;
    MountainGuide existingMountainGuide = mountainGuide;
    mountainGuide = aMountainGuide;
    if (existingMountainGuide != null && !existingMountainGuide.equals(aMountainGuide))
    {
      existingMountainGuide.removeMember(this);
    }
    if (aMountainGuide != null)
    {
      aMountainGuide.addMember(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setClimbingSeason(ClimbingSeason aClimbingSeason)
  {
    boolean wasSet = false;
    if (aClimbingSeason == null)
    {
      return wasSet;
    }

    ClimbingSeason existingClimbingSeason = climbingSeason;
    climbingSeason = aClimbingSeason;
    if (existingClimbingSeason != null && !existingClimbingSeason.equals(aClimbingSeason))
    {
      existingClimbingSeason.removeMember(this);
    }
    climbingSeason.addMember(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfClimbingWeekValid()
  {
    boolean isValid = numberOfClimbingWeek() >= minimumNumberOfClimbingWeek();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfClimbingWeek()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasAdded = false;
    if (climbingWeek.contains(aClimbingWeek)) { return false; }
    climbingWeek.add(aClimbingWeek);
    if (aClimbingWeek.indexOfMember(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aClimbingWeek.addMember(this);
      if (!wasAdded)
      {
        climbingWeek.remove(aClimbingWeek);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasRemoved = false;
    if (!climbingWeek.contains(aClimbingWeek))
    {
      return wasRemoved;
    }

    if (numberOfClimbingWeek() <= minimumNumberOfClimbingWeek())
    {
      return wasRemoved;
    }

    int oldIndex = climbingWeek.indexOf(aClimbingWeek);
    climbingWeek.remove(oldIndex);
    if (aClimbingWeek.indexOfMember(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aClimbingWeek.removeMember(this);
      if (!wasRemoved)
      {
        climbingWeek.add(oldIndex,aClimbingWeek);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setClimbingWeek(ClimbingWeek... newClimbingWeek)
  {
    boolean wasSet = false;
    ArrayList<ClimbingWeek> verifiedClimbingWeek = new ArrayList<ClimbingWeek>();
    for (ClimbingWeek aClimbingWeek : newClimbingWeek)
    {
      if (verifiedClimbingWeek.contains(aClimbingWeek))
      {
        continue;
      }
      verifiedClimbingWeek.add(aClimbingWeek);
    }

    if (verifiedClimbingWeek.size() != newClimbingWeek.length || verifiedClimbingWeek.size() < minimumNumberOfClimbingWeek())
    {
      return wasSet;
    }

    ArrayList<ClimbingWeek> oldClimbingWeek = new ArrayList<ClimbingWeek>(climbingWeek);
    climbingWeek.clear();
    for (ClimbingWeek aNewClimbingWeek : verifiedClimbingWeek)
    {
      climbingWeek.add(aNewClimbingWeek);
      if (oldClimbingWeek.contains(aNewClimbingWeek))
      {
        oldClimbingWeek.remove(aNewClimbingWeek);
      }
      else
      {
        aNewClimbingWeek.addMember(this);
      }
    }

    for (ClimbingWeek anOldClimbingWeek : oldClimbingWeek)
    {
      anOldClimbingWeek.removeMember(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addClimbingWeekAt(ClimbingWeek aClimbingWeek, int index)
  {  
    boolean wasAdded = false;
    if(addClimbingWeek(aClimbingWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbingWeek()) { index = numberOfClimbingWeek() - 1; }
      climbingWeek.remove(aClimbingWeek);
      climbingWeek.add(index, aClimbingWeek);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveClimbingWeekAt(ClimbingWeek aClimbingWeek, int index)
  {
    boolean wasAdded = false;
    if(climbingWeek.contains(aClimbingWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbingWeek()) { index = numberOfClimbingWeek() - 1; }
      climbingWeek.remove(aClimbingWeek);
      climbingWeek.add(index, aClimbingWeek);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addClimbingWeekAt(aClimbingWeek, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setHotel(Hotel aHotel)
  {
    boolean wasSet = false;
    Hotel existingHotel = hotel;
    hotel = aHotel;
    if (existingHotel != null && !existingHotel.equals(aHotel))
    {
      existingHotel.removeMember(this);
    }
    if (aHotel != null)
    {
      aHotel.addMember(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipmentBundle()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    boolean wasAdded = false;
    if (equipmentBundle.contains(aEquipmentBundle)) { return false; }
    equipmentBundle.add(aEquipmentBundle);
    if (aEquipmentBundle.indexOfMember(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aEquipmentBundle.addMember(this);
      if (!wasAdded)
      {
        equipmentBundle.remove(aEquipmentBundle);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    boolean wasRemoved = false;
    if (!equipmentBundle.contains(aEquipmentBundle))
    {
      return wasRemoved;
    }

    int oldIndex = equipmentBundle.indexOf(aEquipmentBundle);
    equipmentBundle.remove(oldIndex);
    if (aEquipmentBundle.indexOfMember(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aEquipmentBundle.removeMember(this);
      if (!wasRemoved)
      {
        equipmentBundle.add(oldIndex,aEquipmentBundle);
      }
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
  public Equipment addEquipment(double aMass, int aPrice, Admin aAdmin)
  {
    return new Equipment(aMass, aPrice, this, aAdmin);
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    Member existingMember = aEquipment.getMember();
    boolean isNewMember = existingMember != null && !this.equals(existingMember);
    if (isNewMember)
    {
      aEquipment.setMember(this);
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
    //Unable to remove aEquipment, as it must always have a member
    if (!this.equals(aEquipment.getMember()))
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
    if (mountainGuide != null)
    {
      MountainGuide placeholderMountainGuide = mountainGuide;
      this.mountainGuide = null;
      placeholderMountainGuide.removeMember(this);
    }
    ClimbingSeason placeholderClimbingSeason = climbingSeason;
    this.climbingSeason = null;
    if(placeholderClimbingSeason != null)
    {
      placeholderClimbingSeason.removeMember(this);
    }
    ArrayList<ClimbingWeek> copyOfClimbingWeek = new ArrayList<ClimbingWeek>(climbingWeek);
    climbingWeek.clear();
    for(ClimbingWeek aClimbingWeek : copyOfClimbingWeek)
    {
      aClimbingWeek.removeMember(this);
    }
    EmergencyContact existingEmergencyContact = emergencyContact;
    emergencyContact = null;
    if (existingEmergencyContact != null)
    {
      existingEmergencyContact.delete();
    }
    if (hotel != null)
    {
      Hotel placeholderHotel = hotel;
      this.hotel = null;
      placeholderHotel.removeMember(this);
    }
    ArrayList<EquipmentBundle> copyOfEquipmentBundle = new ArrayList<EquipmentBundle>(equipmentBundle);
    equipmentBundle.clear();
    for(EquipmentBundle aEquipmentBundle : copyOfEquipmentBundle)
    {
      aEquipmentBundle.removeMember(this);
    }
    for(int i=equipment.size(); i > 0; i--)
    {
      Equipment aEquipment = equipment.get(i - 1);
      aEquipment.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "hotelStay" + ":" + getHotelStay()+ "," +
            "hireGuide" + ":" + getHireGuide()+ "," +
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "mountainGuide = "+(getMountainGuide()!=null?Integer.toHexString(System.identityHashCode(getMountainGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbingSeason = "+(getClimbingSeason()!=null?Integer.toHexString(System.identityHashCode(getClimbingSeason())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "emergencyContact = "+(getEmergencyContact()!=null?Integer.toHexString(System.identityHashCode(getEmergencyContact())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null");
  }
}