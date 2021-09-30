/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 53 "domain_model.ump"
public class Hotel
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Hotel Attributes
  private String name;
  private String address;
  private int numStars;

  //Hotel Associations
  private List<Member> members;
  private Admin admin;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Hotel(String aName, String aAddress, int aNumStars, Admin aAdmin)
  {
    name = aName;
    address = aAddress;
    numStars = aNumStars;
    members = new ArrayList<Member>();
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create hotel due to admin. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumStars(int aNumStars)
  {
    boolean wasSet = false;
    numStars = aNumStars;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getAddress()
  {
    return address;
  }

  public int getNumStars()
  {
    return numStars;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMembers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addMember(Member aMember)
  {
    boolean wasAdded = false;
    if (members.contains(aMember)) { return false; }
    Hotel existingHotel = aMember.getHotel();
    if (existingHotel == null)
    {
      aMember.setHotel(this);
    }
    else if (!this.equals(existingHotel))
    {
      existingHotel.removeMember(aMember);
      addMember(aMember);
    }
    else
    {
      members.add(aMember);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMember(Member aMember)
  {
    boolean wasRemoved = false;
    if (members.contains(aMember))
    {
      members.remove(aMember);
      aMember.setHotel(null);
      wasRemoved = true;
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
      existingAdmin.removeHotel(this);
    }
    admin.addHotel(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while( !members.isEmpty() )
    {
      members.get(0).setHotel(null);
    }
    Admin placeholderAdmin = admin;
    this.admin = null;
    if(placeholderAdmin != null)
    {
      placeholderAdmin.removeHotel(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "address" + ":" + getAddress()+ "," +
            "numStars" + ":" + getNumStars()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null");
  }
}