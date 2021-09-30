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
  private List<ClimbingWeek> climbingWeeks;
  private EmergencyContact emergencyContact;
  private Hotel hotel;
  private List<EquipmentBundle> equipmentBundles;
  private List<Equipment> equipments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Member(int aPassword, String aEmailAddress, boolean aHotelStay, boolean aHireGuide, String aName, ClimbingSeason aClimbingSeason, EmergencyContact aEmergencyContact, ClimbingWeek... allClimbingWeeks)
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
    climbingWeeks = new ArrayList<ClimbingWeek>();
    boolean didAddClimbingWeeks = setClimbingWeeks(allClimbingWeeks);
    if (!didAddClimbingWeeks)
    {
      throw new RuntimeException("Unable to create Member, must have at least 1 climbingWeeks. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aEmergencyContact == null || aEmergencyContact.getMember() != null)
    {
      throw new RuntimeException("Unable to create Member due to aEmergencyContact. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    emergencyContact = aEmergencyContact;
    equipmentBundles = new ArrayList<EquipmentBundle>();
    equipments = new ArrayList<Equipment>();
  }

  public Member(int aPassword, String aEmailAddress, boolean aHotelStay, boolean aHireGuide, String aName, ClimbingSeason aClimbingSeason, String aNameForEmergencyContact, int aPhoneNumberForEmergencyContact, String aEmailAddressForEmergencyContact, MountainGuide aMountainGuideForEmergencyContact, ClimbingWeek... allClimbingWeeks)
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
    climbingWeeks = new ArrayList<ClimbingWeek>();
    boolean didAddClimbingWeeks = setClimbingWeeks(allClimbingWeeks);
    if (!didAddClimbingWeeks)
    {
      throw new RuntimeException("Unable to create Member, must have at least 1 climbingWeeks. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    emergencyContact = new EmergencyContact(aNameForEmergencyContact, aPhoneNumberForEmergencyContact, aEmailAddressForEmergencyContact, aMountainGuideForEmergencyContact, this);
    equipmentBundles = new ArrayList<EquipmentBundle>();
    equipments = new ArrayList<Equipment>();
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
    ClimbingWeek aClimbingWeek = climbingWeeks.get(index);
    return aClimbingWeek;
  }

  public List<ClimbingWeek> getClimbingWeeks()
  {
    List<ClimbingWeek> newClimbingWeeks = Collections.unmodifiableList(climbingWeeks);
    return newClimbingWeeks;
  }

  public int numberOfClimbingWeeks()
  {
    int number = climbingWeeks.size();
    return number;
  }

  public boolean hasClimbingWeeks()
  {
    boolean has = climbingWeeks.size() > 0;
    return has;
  }

  public int indexOfClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    int index = climbingWeeks.indexOf(aClimbingWeek);
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
  public boolean isNumberOfClimbingWeeksValid()
  {
    boolean isValid = numberOfClimbingWeeks() >= minimumNumberOfClimbingWeeks();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfClimbingWeeks()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasAdded = false;
    if (climbingWeeks.contains(aClimbingWeek)) { return false; }
    climbingWeeks.add(aClimbingWeek);
    if (aClimbingWeek.indexOfMember(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aClimbingWeek.addMember(this);
      if (!wasAdded)
      {
        climbingWeeks.remove(aClimbingWeek);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasRemoved = false;
    if (!climbingWeeks.contains(aClimbingWeek))
    {
      return wasRemoved;
    }

    if (numberOfClimbingWeeks() <= minimumNumberOfClimbingWeeks())
    {
      return wasRemoved;
    }

    int oldIndex = climbingWeeks.indexOf(aClimbingWeek);
    climbingWeeks.remove(oldIndex);
    if (aClimbingWeek.indexOfMember(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aClimbingWeek.removeMember(this);
      if (!wasRemoved)
      {
        climbingWeeks.add(oldIndex,aClimbingWeek);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setClimbingWeeks(ClimbingWeek... newClimbingWeeks)
  {
    boolean wasSet = false;
    ArrayList<ClimbingWeek> verifiedClimbingWeeks = new ArrayList<ClimbingWeek>();
    for (ClimbingWeek aClimbingWeek : newClimbingWeeks)
    {
      if (verifiedClimbingWeeks.contains(aClimbingWeek))
      {
        continue;
      }
      verifiedClimbingWeeks.add(aClimbingWeek);
    }

    if (verifiedClimbingWeeks.size() != newClimbingWeeks.length || verifiedClimbingWeeks.size() < minimumNumberOfClimbingWeeks())
    {
      return wasSet;
    }

    ArrayList<ClimbingWeek> oldClimbingWeeks = new ArrayList<ClimbingWeek>(climbingWeeks);
    climbingWeeks.clear();
    for (ClimbingWeek aNewClimbingWeek : verifiedClimbingWeeks)
    {
      climbingWeeks.add(aNewClimbingWeek);
      if (oldClimbingWeeks.contains(aNewClimbingWeek))
      {
        oldClimbingWeeks.remove(aNewClimbingWeek);
      }
      else
      {
        aNewClimbingWeek.addMember(this);
      }
    }

    for (ClimbingWeek anOldClimbingWeek : oldClimbingWeeks)
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
      if(index > numberOfClimbingWeeks()) { index = numberOfClimbingWeeks() - 1; }
      climbingWeeks.remove(aClimbingWeek);
      climbingWeeks.add(index, aClimbingWeek);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveClimbingWeekAt(ClimbingWeek aClimbingWeek, int index)
  {
    boolean wasAdded = false;
    if(climbingWeeks.contains(aClimbingWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbingWeeks()) { index = numberOfClimbingWeeks() - 1; }
      climbingWeeks.remove(aClimbingWeek);
      climbingWeeks.add(index, aClimbingWeek);
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
  public static int minimumNumberOfEquipmentBundles()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    boolean wasAdded = false;
    if (equipmentBundles.contains(aEquipmentBundle)) { return false; }
    equipmentBundles.add(aEquipmentBundle);
    if (aEquipmentBundle.indexOfMember(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aEquipmentBundle.addMember(this);
      if (!wasAdded)
      {
        equipmentBundles.remove(aEquipmentBundle);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    boolean wasRemoved = false;
    if (!equipmentBundles.contains(aEquipmentBundle))
    {
      return wasRemoved;
    }

    int oldIndex = equipmentBundles.indexOf(aEquipmentBundle);
    equipmentBundles.remove(oldIndex);
    if (aEquipmentBundle.indexOfMember(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aEquipmentBundle.removeMember(this);
      if (!wasRemoved)
      {
        equipmentBundles.add(oldIndex,aEquipmentBundle);
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
  public Equipment addEquipment(double aMass, int aPrice, Admin aAdmin)
  {
    return new Equipment(aMass, aPrice, this, aAdmin);
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipments.contains(aEquipment)) { return false; }
    Member existingMember = aEquipment.getMember();
    boolean isNewMember = existingMember != null && !this.equals(existingMember);
    if (isNewMember)
    {
      aEquipment.setMember(this);
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
    //Unable to remove aEquipment, as it must always have a member
    if (!this.equals(aEquipment.getMember()))
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
    ArrayList<ClimbingWeek> copyOfClimbingWeeks = new ArrayList<ClimbingWeek>(climbingWeeks);
    climbingWeeks.clear();
    for(ClimbingWeek aClimbingWeek : copyOfClimbingWeeks)
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
    ArrayList<EquipmentBundle> copyOfEquipmentBundles = new ArrayList<EquipmentBundle>(equipmentBundles);
    equipmentBundles.clear();
    for(EquipmentBundle aEquipmentBundle : copyOfEquipmentBundles)
    {
      aEquipmentBundle.removeMember(this);
    }
    for(int i=equipments.size(); i > 0; i--)
    {
      Equipment aEquipment = equipments.get(i - 1);
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