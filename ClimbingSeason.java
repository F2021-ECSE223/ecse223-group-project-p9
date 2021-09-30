/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 30 "domain_model.ump"
public class ClimbingSeason
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbingSeason Attributes
  private Date startDate;
  private Date endDate;

  //ClimbingSeason Associations
  private List<Member> members;
  private List<MountainGuide> mountainGuides;
  private List<ClimbingWeek> climbingWeeks;
  private Admin admin;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbingSeason(Date aStartDate, Date aEndDate, Admin aAdmin)
  {
    startDate = aStartDate;
    endDate = aEndDate;
    members = new ArrayList<Member>();
    mountainGuides = new ArrayList<MountainGuide>();
    climbingWeeks = new ArrayList<ClimbingWeek>();
    if (aAdmin == null || aAdmin.getClimbingSeason() != null)
    {
      throw new RuntimeException("Unable to create ClimbingSeason due to aAdmin. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    admin = aAdmin;
  }

  public ClimbingSeason(Date aStartDate, Date aEndDate, int aPasswordForAdmin, String aEmailAddressForAdmin)
  {
    startDate = aStartDate;
    endDate = aEndDate;
    members = new ArrayList<Member>();
    mountainGuides = new ArrayList<MountainGuide>();
    climbingWeeks = new ArrayList<ClimbingWeek>();
    admin = new Admin(aPasswordForAdmin, aEmailAddressForAdmin, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
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
  /* Code from template association_GetMany */
  public MountainGuide getMountainGuide(int index)
  {
    MountainGuide aMountainGuide = mountainGuides.get(index);
    return aMountainGuide;
  }

  public List<MountainGuide> getMountainGuides()
  {
    List<MountainGuide> newMountainGuides = Collections.unmodifiableList(mountainGuides);
    return newMountainGuides;
  }

  public int numberOfMountainGuides()
  {
    int number = mountainGuides.size();
    return number;
  }

  public boolean hasMountainGuides()
  {
    boolean has = mountainGuides.size() > 0;
    return has;
  }

  public int indexOfMountainGuide(MountainGuide aMountainGuide)
  {
    int index = mountainGuides.indexOf(aMountainGuide);
    return index;
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
  public Admin getAdmin()
  {
    return admin;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMembers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Member addMember(int aPassword, String aEmailAddress, boolean aHotelStay, boolean aHireGuide, String aName, EmergencyContact aEmergencyContact, ClimbingWeek... allClimbingWeeks)
  {
    return new Member(aPassword, aEmailAddress, aHotelStay, aHireGuide, aName, this, aEmergencyContact, allClimbingWeeks);
  }

  public boolean addMember(Member aMember)
  {
    boolean wasAdded = false;
    if (members.contains(aMember)) { return false; }
    ClimbingSeason existingClimbingSeason = aMember.getClimbingSeason();
    boolean isNewClimbingSeason = existingClimbingSeason != null && !this.equals(existingClimbingSeason);
    if (isNewClimbingSeason)
    {
      aMember.setClimbingSeason(this);
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
    //Unable to remove aMember, as it must always have a climbingSeason
    if (!this.equals(aMember.getClimbingSeason()))
    {
      members.remove(aMember);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMountainGuides()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MountainGuide addMountainGuide(int aPassword, String aEmailAddress, String aName, int aWeeklyCost, EmergencyContact aEmergencyContact)
  {
    return new MountainGuide(aPassword, aEmailAddress, aName, aWeeklyCost, this, aEmergencyContact);
  }

  public boolean addMountainGuide(MountainGuide aMountainGuide)
  {
    boolean wasAdded = false;
    if (mountainGuides.contains(aMountainGuide)) { return false; }
    ClimbingSeason existingClimbingSeason = aMountainGuide.getClimbingSeason();
    boolean isNewClimbingSeason = existingClimbingSeason != null && !this.equals(existingClimbingSeason);
    if (isNewClimbingSeason)
    {
      aMountainGuide.setClimbingSeason(this);
    }
    else
    {
      mountainGuides.add(aMountainGuide);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMountainGuide(MountainGuide aMountainGuide)
  {
    boolean wasRemoved = false;
    //Unable to remove aMountainGuide, as it must always have a climbingSeason
    if (!this.equals(aMountainGuide.getClimbingSeason()))
    {
      mountainGuides.remove(aMountainGuide);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMountainGuideAt(MountainGuide aMountainGuide, int index)
  {  
    boolean wasAdded = false;
    if(addMountainGuide(aMountainGuide))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMountainGuides()) { index = numberOfMountainGuides() - 1; }
      mountainGuides.remove(aMountainGuide);
      mountainGuides.add(index, aMountainGuide);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMountainGuideAt(MountainGuide aMountainGuide, int index)
  {
    boolean wasAdded = false;
    if(mountainGuides.contains(aMountainGuide))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMountainGuides()) { index = numberOfMountainGuides() - 1; }
      mountainGuides.remove(aMountainGuide);
      mountainGuides.add(index, aMountainGuide);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMountainGuideAt(aMountainGuide, index);
    }
    return wasAdded;
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
  /* Code from template association_AddMandatoryManyToOne */
  public ClimbingWeek addClimbingWeek(int aWeekID)
  {
    ClimbingWeek aNewClimbingWeek = new ClimbingWeek(aWeekID, this);
    return aNewClimbingWeek;
  }

  public boolean addClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasAdded = false;
    if (climbingWeeks.contains(aClimbingWeek)) { return false; }
    ClimbingSeason existingClimbingSeason = aClimbingWeek.getClimbingSeason();
    boolean isNewClimbingSeason = existingClimbingSeason != null && !this.equals(existingClimbingSeason);

    if (isNewClimbingSeason && existingClimbingSeason.numberOfClimbingWeeks() <= minimumNumberOfClimbingWeeks())
    {
      return wasAdded;
    }
    if (isNewClimbingSeason)
    {
      aClimbingWeek.setClimbingSeason(this);
    }
    else
    {
      climbingWeeks.add(aClimbingWeek);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasRemoved = false;
    //Unable to remove aClimbingWeek, as it must always have a climbingSeason
    if (this.equals(aClimbingWeek.getClimbingSeason()))
    {
      return wasRemoved;
    }

    //climbingSeason already at minimum (1)
    if (numberOfClimbingWeeks() <= minimumNumberOfClimbingWeeks())
    {
      return wasRemoved;
    }

    climbingWeeks.remove(aClimbingWeek);
    wasRemoved = true;
    return wasRemoved;
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

  public void delete()
  {
    while (members.size() > 0)
    {
      Member aMember = members.get(members.size() - 1);
      aMember.delete();
      members.remove(aMember);
    }
    
    while (mountainGuides.size() > 0)
    {
      MountainGuide aMountainGuide = mountainGuides.get(mountainGuides.size() - 1);
      aMountainGuide.delete();
      mountainGuides.remove(aMountainGuide);
    }
    
    for(int i=climbingWeeks.size(); i > 0; i--)
    {
      ClimbingWeek aClimbingWeek = climbingWeeks.get(i - 1);
      aClimbingWeek.delete();
    }
    Admin existingAdmin = admin;
    admin = null;
    if (existingAdmin != null)
    {
      existingAdmin.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null");
  }
}