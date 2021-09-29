/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 6 "domain_model.ump"
public class MountainGuide extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MountainGuide Attributes
  private String name;
  private int weeklyCost;

  //MountainGuide Associations
  private List<Member> member;
  private ClimbingSeason climbingSeason;
  private EmergencyContact emergencyContact;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MountainGuide(int aPassword, String aEmailAddress, String aName, int aWeeklyCost, ClimbingSeason aClimbingSeason, EmergencyContact aEmergencyContact)
  {
    super(aPassword, aEmailAddress);
    name = aName;
    weeklyCost = aWeeklyCost;
    member = new ArrayList<Member>();
    boolean didAddClimbingSeason = setClimbingSeason(aClimbingSeason);
    if (!didAddClimbingSeason)
    {
      throw new RuntimeException("Unable to create mountainGuide due to climbingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aEmergencyContact == null || aEmergencyContact.getMountainGuide() != null)
    {
      throw new RuntimeException("Unable to create MountainGuide due to aEmergencyContact. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    emergencyContact = aEmergencyContact;
  }

  public MountainGuide(int aPassword, String aEmailAddress, String aName, int aWeeklyCost, ClimbingSeason aClimbingSeason, String aNameForEmergencyContact, int aPhoneNumberForEmergencyContact, String aEmailAddressForEmergencyContact, Member aMemberForEmergencyContact)
  {
    super(aPassword, aEmailAddress);
    name = aName;
    weeklyCost = aWeeklyCost;
    member = new ArrayList<Member>();
    boolean didAddClimbingSeason = setClimbingSeason(aClimbingSeason);
    if (!didAddClimbingSeason)
    {
      throw new RuntimeException("Unable to create mountainGuide due to climbingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    emergencyContact = new EmergencyContact(aNameForEmergencyContact, aPhoneNumberForEmergencyContact, aEmailAddressForEmergencyContact, this, aMemberForEmergencyContact);
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

  public boolean setWeeklyCost(int aWeeklyCost)
  {
    boolean wasSet = false;
    weeklyCost = aWeeklyCost;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getWeeklyCost()
  {
    return weeklyCost;
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
  public ClimbingSeason getClimbingSeason()
  {
    return climbingSeason;
  }
  /* Code from template association_GetOne */
  public EmergencyContact getEmergencyContact()
  {
    return emergencyContact;
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
    MountainGuide existingMountainGuide = aMember.getMountainGuide();
    if (existingMountainGuide == null)
    {
      aMember.setMountainGuide(this);
    }
    else if (!this.equals(existingMountainGuide))
    {
      existingMountainGuide.removeMember(aMember);
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
      aMember.setMountainGuide(null);
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
      existingClimbingSeason.removeMountainGuide(this);
    }
    climbingSeason.addMountainGuide(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while( !member.isEmpty() )
    {
      member.get(0).setMountainGuide(null);
    }
    ClimbingSeason placeholderClimbingSeason = climbingSeason;
    this.climbingSeason = null;
    if(placeholderClimbingSeason != null)
    {
      placeholderClimbingSeason.removeMountainGuide(this);
    }
    EmergencyContact existingEmergencyContact = emergencyContact;
    emergencyContact = null;
    if (existingEmergencyContact != null)
    {
      existingEmergencyContact.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "weeklyCost" + ":" + getWeeklyCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "climbingSeason = "+(getClimbingSeason()!=null?Integer.toHexString(System.identityHashCode(getClimbingSeason())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "emergencyContact = "+(getEmergencyContact()!=null?Integer.toHexString(System.identityHashCode(getEmergencyContact())):"null");
  }
}