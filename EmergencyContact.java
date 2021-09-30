/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 45 "domain_model.ump"
public class EmergencyContact
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //EmergencyContact Attributes
  private String name;
  private int phoneNumber;
  private String emailAddress;

  //EmergencyContact Associations
  private MountainGuide mountainGuide;
  private Member member;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EmergencyContact(String aName, int aPhoneNumber, String aEmailAddress, MountainGuide aMountainGuide, Member aMember)
  {
    name = aName;
    phoneNumber = aPhoneNumber;
    emailAddress = aEmailAddress;
    if (aMountainGuide == null || aMountainGuide.getEmergencyContact() != null)
    {
      throw new RuntimeException("Unable to create EmergencyContact due to aMountainGuide. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    mountainGuide = aMountainGuide;
    if (aMember == null || aMember.getEmergencyContact() != null)
    {
      throw new RuntimeException("Unable to create EmergencyContact due to aMember. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    member = aMember;
  }

  public EmergencyContact(String aName, int aPhoneNumber, String aEmailAddress, int aPasswordForMountainGuide, String aEmailAddressForMountainGuide, String aNameForMountainGuide, int aWeeklyCostForMountainGuide, ClimbingSeason aClimbingSeasonForMountainGuide, int aPasswordForMember, String aEmailAddressForMember, boolean aHotelStayForMember, boolean aHireGuideForMember, String aNameForMember, ClimbingSeason aClimbingSeasonForMember, ClimbingWeek... allClimbingWeeksForMember)
  {
    name = aName;
    phoneNumber = aPhoneNumber;
    emailAddress = aEmailAddress;
    mountainGuide = new MountainGuide(aPasswordForMountainGuide, aEmailAddressForMountainGuide, aNameForMountainGuide, aWeeklyCostForMountainGuide, aClimbingSeasonForMountainGuide, this);
    member = new Member(aPasswordForMember, aEmailAddressForMember, aHotelStayForMember, aHireGuideForMember, aNameForMember, aClimbingSeasonForMember, this, allClimbingWeeksForMember);
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

  public boolean setPhoneNumber(int aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmailAddress(String aEmailAddress)
  {
    boolean wasSet = false;
    emailAddress = aEmailAddress;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }
  /* Code from template association_GetOne */
  public MountainGuide getMountainGuide()
  {
    return mountainGuide;
  }
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
  }

  public void delete()
  {
    MountainGuide existingMountainGuide = mountainGuide;
    mountainGuide = null;
    if (existingMountainGuide != null)
    {
      existingMountainGuide.delete();
    }
    Member existingMember = member;
    member = null;
    if (existingMember != null)
    {
      existingMember.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "emailAddress" + ":" + getEmailAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "mountainGuide = "+(getMountainGuide()!=null?Integer.toHexString(System.identityHashCode(getMountainGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null");
  }
}