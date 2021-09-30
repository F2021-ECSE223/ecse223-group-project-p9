/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 61 "domain_model.ump"
public class EquipmentBundle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //EquipmentBundle Attributes
  private String name;
  private int percentDiscount;

  //EquipmentBundle Associations
  private List<Member> members;
  private Admin admin;
  private List<Equipment> equipments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EquipmentBundle(String aName, int aPercentDiscount, Admin aAdmin, Equipment... allEquipments)
  {
    name = aName;
    percentDiscount = aPercentDiscount;
    members = new ArrayList<Member>();
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create equipmentBundle due to admin. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    equipments = new ArrayList<Equipment>();
    boolean didAddEquipments = setEquipments(allEquipments);
    if (!didAddEquipments)
    {
      throw new RuntimeException("Unable to create EquipmentBundle, must have at least 2 equipments. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPercentDiscount(int aPercentDiscount)
  {
    boolean wasSet = false;
    percentDiscount = aPercentDiscount;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getPercentDiscount()
  {
    return percentDiscount;
  }
  /* Code from template association_GetMany */
  public Member getMember(int index)
  {
    Member aMember = members.get(index);
    return aMember;
  }

  public List<Member> getMembers()
  {
    List<Member> newMembers = Collections.unmodifiableList(members);
    return newMembers;
  }

  public int numberOfMembers()
  {
    int number = members.size();
    return number;
  }

  public boolean hasMembers()
  {
    boolean has = members.size() > 0;
    return has;
  }

  public int indexOfMember(Member aMember)
  {
    int index = members.indexOf(aMember);
    return index;
  }
  /* Code from template association_GetOne */
  public Admin getAdmin()
  {
    return admin;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMembers()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addMember(Member aMember)
  {
    boolean wasAdded = false;
    if (members.contains(aMember)) { return false; }
    members.add(aMember);
    if (aMember.indexOfEquipmentBundle(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aMember.addEquipmentBundle(this);
      if (!wasAdded)
      {
        members.remove(aMember);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeMember(Member aMember)
  {
    boolean wasRemoved = false;
    if (!members.contains(aMember))
    {
      return wasRemoved;
    }

    int oldIndex = members.indexOf(aMember);
    members.remove(oldIndex);
    if (aMember.indexOfEquipmentBundle(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aMember.removeEquipmentBundle(this);
      if (!wasRemoved)
      {
        members.add(oldIndex,aMember);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMemberAt(Member aMember, int index)
  {  
    boolean wasAdded = false;
    if(addMember(aMember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMembers()) { index = numberOfMembers() - 1; }
      members.remove(aMember);
      members.add(index, aMember);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMemberAt(Member aMember, int index)
  {
    boolean wasAdded = false;
    if(members.contains(aMember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMembers()) { index = numberOfMembers() - 1; }
      members.remove(aMember);
      members.add(index, aMember);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMemberAt(aMember, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAdmin(Admin aAdmin)
  {
    boolean wasSet = false;
    if (aAdmin == null)
    {
      return wasSet;
    }

    Admin existingAdmin = admin;
    admin = aAdmin;
    if (existingAdmin != null && !existingAdmin.equals(aAdmin))
    {
      existingAdmin.removeEquipmentBundle(this);
    }
    admin.addEquipmentBundle(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipments()
  {
    return 2;
  }
  /* Code from template association_AddMNToOptionalOne */
  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipments.contains(aEquipment)) { return false; }
    EquipmentBundle existingEquipment = aEquipment.getEquipment();
    if (existingEquipment != null && existingEquipment.numberOfEquipments() <= minimumNumberOfEquipments())
    {
      return wasAdded;
    }
    else if (existingEquipment != null)
    {
      existingEquipment.equipments.remove(aEquipment);
    }
    equipments.add(aEquipment);
    setEquipment(aEquipment,this);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    if (equipments.contains(aEquipment) && numberOfEquipments() > minimumNumberOfEquipments())
    {
      equipments.remove(aEquipment);
      setEquipment(aEquipment,null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_SetMNToOptionalOne */
  public boolean setEquipments(Equipment... newEquipments)
  {
    boolean wasSet = false;
    if (newEquipments.length < minimumNumberOfEquipments())
    {
      return wasSet;
    }

    ArrayList<Equipment> checkNewEquipments = new ArrayList<Equipment>();
    HashMap<EquipmentBundle,Integer> equipmentToNewEquipments = new HashMap<EquipmentBundle,Integer>();
    for (Equipment aEquipment : newEquipments)
    {
      if (checkNewEquipments.contains(aEquipment))
      {
        return wasSet;
      }
      else if (aEquipment.getEquipment() != null && !this.equals(aEquipment.getEquipment()))
      {
        EquipmentBundle existingEquipment = aEquipment.getEquipment();
        if (!equipmentToNewEquipments.containsKey(existingEquipment))
        {
          equipmentToNewEquipments.put(existingEquipment, Integer.valueOf(existingEquipment.numberOfEquipments()));
        }
        Integer currentCount = equipmentToNewEquipments.get(existingEquipment);
        int nextCount = currentCount - 1;
        if (nextCount < 2)
        {
          return wasSet;
        }
        equipmentToNewEquipments.put(existingEquipment, Integer.valueOf(nextCount));
      }
      checkNewEquipments.add(aEquipment);
    }

    equipments.removeAll(checkNewEquipments);

    for (Equipment orphan : equipments)
    {
      setEquipment(orphan, null);
    }
    equipments.clear();
    for (Equipment aEquipment : newEquipments)
    {
      if (aEquipment.getEquipment() != null)
      {
        aEquipment.getEquipment().equipments.remove(aEquipment);
      }
      setEquipment(aEquipment, this);
      equipments.add(aEquipment);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_GetPrivate */
  private void setEquipment(Equipment aEquipment, EquipmentBundle aEquipment)
  {
    try
    {
      java.lang.reflect.Field mentorField = aEquipment.getClass().getDeclaredField("equipment");
      mentorField.setAccessible(true);
      mentorField.set(aEquipment, aEquipment);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Issue internally setting aEquipment to aEquipment", e);
    }
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
    ArrayList<Member> copyOfMembers = new ArrayList<Member>(members);
    members.clear();
    for(Member aMember : copyOfMembers)
    {
      aMember.removeEquipmentBundle(this);
    }
    Admin placeholderAdmin = admin;
    this.admin = null;
    if(placeholderAdmin != null)
    {
      placeholderAdmin.removeEquipmentBundle(this);
    }
    for(Equipment aEquipment : equipments)
    {
      setEquipment(aEquipment,null);
    }
    equipments.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "percentDiscount" + ":" + getPercentDiscount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null");
  }
}