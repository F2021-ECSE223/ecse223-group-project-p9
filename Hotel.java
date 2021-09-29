/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 52 "domain_model.ump"
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
  private List<Member> member;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Hotel(String aName, String aAddress, int aNumStars)
  {
    name = aName;
    address = aAddress;
    numStars = aNumStars;
    member = new ArrayList<Member>();
    if (aNumStars<1||aNumStars>5)
    {
      throw new RuntimeException("Please provide a valid numStars [numStars>=1&&numStars<=5]");
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
    if (aNumStars>=1&&aNumStars<=5)
    {
    numStars = aNumStars;
    wasSet = true;
    }
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMember()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addMember(Member aMember)
  {
    boolean wasAdded = false;
    if (member.contains(aMember)) { return false; }
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
      member.add(aMember);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMember(Member aMember)
  {
    boolean wasRemoved = false;
    if (member.contains(aMember))
    {
      member.remove(aMember);
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

  public void delete()
  {
    while( !member.isEmpty() )
    {
      member.get(0).setHotel(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "address" + ":" + getAddress()+ "," +
            "numStars" + ":" + getNumStars()+ "]";
  }
}