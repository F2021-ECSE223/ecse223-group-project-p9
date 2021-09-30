/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 39 "domain_model.ump"
public class ClimbingWeek
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<int, ClimbingWeek> climbingweeksByWeekID = new HashMap<int, ClimbingWeek>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbingWeek Attributes
  private int weekID;

  //ClimbingWeek Associations
  private List<Member> members;
  private ClimbingSeason climbingSeason;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbingWeek(int aWeekID, ClimbingSeason aClimbingSeason)
  {
    if (!setWeekID(aWeekID))
    {
      throw new RuntimeException("Cannot create due to duplicate weekID. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    members = new ArrayList<Member>();
    boolean didAddClimbingSeason = setClimbingSeason(aClimbingSeason);
    if (!didAddClimbingSeason)
    {
      throw new RuntimeException("Unable to create climbingWeek due to climbingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWeekID(int aWeekID)
  {
    boolean wasSet = false;
    int anOldWeekID = getWeekID();
    if (anOldWeekID != null && anOldWeekID.equals(aWeekID)) {
      return true;
    }
    if (hasWithWeekID(aWeekID)) {
      return wasSet;
    }
    weekID = aWeekID;
    wasSet = true;
    if (anOldWeekID != null) {
      climbingweeksByWeekID.remove(anOldWeekID);
    }
    climbingweeksByWeekID.put(aWeekID, this);
    return wasSet;
  }

  public int getWeekID()
  {
    return weekID;
  }
  /* Code from template attribute_GetUnique */
  public static ClimbingWeek getWithWeekID(int aWeekID)
  {
    return climbingweeksByWeekID.get(aWeekID);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithWeekID(int aWeekID)
  {
    return getWithWeekID(aWeekID) != null;
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
  public ClimbingSeason getClimbingSeason()
  {
    return climbingSeason;
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
    if (aMember.indexOfClimbingWeek(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aMember.addClimbingWeek(this);
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
    if (aMember.indexOfClimbingWeek(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aMember.removeClimbingWeek(this);
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
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setClimbingSeason(ClimbingSeason aClimbingSeason)
  {
    boolean wasSet = false;
    //Must provide climbingSeason to climbingWeek
    if (aClimbingSeason == null)
    {
      return wasSet;
    }

    if (climbingSeason != null && climbingSeason.numberOfClimbingWeeks() <= ClimbingSeason.minimumNumberOfClimbingWeeks())
    {
      return wasSet;
    }

    ClimbingSeason existingClimbingSeason = climbingSeason;
    climbingSeason = aClimbingSeason;
    if (existingClimbingSeason != null && !existingClimbingSeason.equals(aClimbingSeason))
    {
      boolean didRemove = existingClimbingSeason.removeClimbingWeek(this);
      if (!didRemove)
      {
        climbingSeason = existingClimbingSeason;
        return wasSet;
      }
    }
    climbingSeason.addClimbingWeek(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    climbingweeksByWeekID.remove(getWeekID());
    ArrayList<Member> copyOfMembers = new ArrayList<Member>(members);
    members.clear();
    for(Member aMember : copyOfMembers)
    {
      if (aMember.numberOfClimbingWeeks() <= Member.minimumNumberOfClimbingWeeks())
      {
        aMember.delete();
      }
      else
      {
        aMember.removeClimbingWeek(this);
      }
    }
    ClimbingSeason placeholderClimbingSeason = climbingSeason;
    this.climbingSeason = null;
    if(placeholderClimbingSeason != null)
    {
      placeholderClimbingSeason.removeClimbingWeek(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "weekID" + ":" + getWeekID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "climbingSeason = "+(getClimbingSeason()!=null?Integer.toHexString(System.identityHashCode(getClimbingSeason())):"null");
  }
}