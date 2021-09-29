/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 60 "domain_model.ump"
public class EquipmentBundle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //EquipmentBundle Attributes
  private String name;
  private int percentDiscount;

  //EquipmentBundle Associations
  private List<Member> member;
  private Admin admin;
  private List<Equipment> equipment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EquipmentBundle(String aName, int aPercentDiscount, Admin aAdmin, Equipment... allEquipment)
  {
    name = aName;
    percentDiscount = aPercentDiscount;
    member = new ArrayList<Member>();
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create equipmentBundle due to admin. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    equipment = new ArrayList<Equipment>();
    boolean didAddEquipment = setEquipment(allEquipment);
    if (!didAddEquipment)
    {
      throw new RuntimeException("Unable to create EquipmentBundle, must have at least 2 equipment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
    Member aMember = member.get(index);
    return aMember;
  }

  public List<Member> getMember()
  {
    List<Member> newMember = Collections.unmodifiableList(member);
    return newMember;
  }

  public int numberOfMember()
  {
    int number = member.size();
    return number;
  }

  public boolean hasMember()
  {
    boolean has = member.size() > 0;
    return has;
  }

  public int indexOfMember(Member aMember)
  {
    int index = member.indexOf(aMember);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMember()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addMember(Member aMember)
  {
    boolean wasAdded = false;
    if (member.contains(aMember)) { return false; }
    member.add(aMember);
    if (aMember.indexOfEquipmentBundle(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aMember.addEquipmentBundle(this);
      if (!wasAdded)
      {
        member.remove(aMember);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeMember(Member aMember)
  {
    boolean wasRemoved = false;
    if (!member.contains(aMember))
    {
      return wasRemoved;
    }

    int oldIndex = member.indexOf(aMember);
    member.remove(oldIndex);
    if (aMember.indexOfEquipmentBundle(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aMember.removeEquipmentBundle(this);
      if (!wasRemoved)
      {
        member.add(oldIndex,aMember);
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
      if(index > numberOfMember()) { index = numberOfMember() - 1; }
      member.remove(aMember);
      member.add(index, aMember);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMemberAt(Member aMember, int index)
  {
    boolean wasAdded = false;
    if(member.contains(aMember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMember()) { index = numberOfMember() - 1; }
      member.remove(aMember);
      member.add(index, aMember);
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
  public static int minimumNumberOfEquipment()
  {
    return 2;
  }
  /* Code from template association_AddMNToOptionalOne */
  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    EquipmentBundle existingEquipment = aEquipment.getEquipment();
    if (existingEquipment != null && existingEquipment.numberOfEquipment() <= minimumNumberOfEquipment())
    {
      return wasAdded;
    }
    else if (existingEquipment != null)
    {
      existingEquipment.equipment.remove(aEquipment);
    }
    equipment.add(aEquipment);
    setEquipment(aEquipment,this);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    if (equipment.contains(aEquipment) && numberOfEquipment() > minimumNumberOfEquipment())
    {
      equipment.remove(aEquipment);
      setEquipment(aEquipment,null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_SetMNToOptionalOne */
  public boolean setEquipment(Equipment... newEquipment)
  {
    boolean wasSet = false;
    if (newEquipment.length < minimumNumberOfEquipment())
    {
      return wasSet;
    }

    ArrayList<Equipment> checkNewEquipment = new ArrayList<Equipment>();
    HashMap<EquipmentBundle,Integer> equipmentToNewEquipment = new HashMap<EquipmentBundle,Integer>();
    for (Equipment aEquipment : newEquipment)
    {
      if (checkNewEquipment.contains(aEquipment))
      {
        return wasSet;
      }
      else if (aEquipment.getEquipment() != null && !this.equals(aEquipment.getEquipment()))
      {
        EquipmentBundle existingEquipment = aEquipment.getEquipment();
        if (!equipmentToNewEquipment.containsKey(existingEquipment))
        {
          equipmentToNewEquipment.put(existingEquipment, Integer.valueOf(existingEquipment.numberOfEquipment()));
        }
        Integer currentCount = equipmentToNewEquipment.get(existingEquipment);
        int nextCount = currentCount - 1;
        if (nextCount < 2)
        {
          return wasSet;
        }
        equipmentToNewEquipment.put(existingEquipment, Integer.valueOf(nextCount));
      }
      checkNewEquipment.add(aEquipment);
    }

    equipment.removeAll(checkNewEquipment);

    for (Equipment orphan : equipment)
    {
      setEquipment(orphan, null);
    }
    equipment.clear();
    for (Equipment aEquipment : newEquipment)
    {
      if (aEquipment.getEquipment() != null)
      {
        aEquipment.getEquipment().equipment.remove(aEquipment);
      }
      setEquipment(aEquipment, this);
      equipment.add(aEquipment);
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
    ArrayList<Member> copyOfMember = new ArrayList<Member>(member);
    member.clear();
    for(Member aMember : copyOfMember)
    {
      aMember.removeEquipmentBundle(this);
    }
    Admin placeholderAdmin = admin;
    this.admin = null;
    if(placeholderAdmin != null)
    {
      placeholderAdmin.removeEquipmentBundle(this);
    }
    for(Equipment aEquipment : equipment)
    {
      setEquipment(aEquipment,null);
    }
    equipment.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "percentDiscount" + ":" + getPercentDiscount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null");
  }
}