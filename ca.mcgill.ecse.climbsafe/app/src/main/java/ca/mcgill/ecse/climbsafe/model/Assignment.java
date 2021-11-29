/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.model;
import java.io.Serializable;

// line 107 "../../../../../ClimbSafePersistence.ump"
// line 1 "../../../../../ClimbSafeStates.ump"
// line 94 "../../../../../ClimbSafe.ump"
public class Assignment implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private int refund;
  private String authorizationCode;
  private boolean paid;
  private int startWeek;
  private int endWeek;

  //Assignment State Machines
  public enum TripStatus { Assigned, Started, OnTrip, Finished, Banned, Cancelled }
  private TripStatus tripStatus;

  //Assignment Associations
  private Member member;
  private Guide guide;
  private Hotel hotel;
  private ClimbSafe climbSafe;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assignment(int aStartWeek, int aEndWeek, Member aMember, ClimbSafe aClimbSafe)
  {
    authorizationCode = null;
    paid = false;
    startWeek = aStartWeek;
    endWeek = aEndWeek;
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create assignment due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create assignment due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    setTripStatus(TripStatus.Assigned);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRefund(int aRefund)
  {
    boolean wasSet = false;
    refund = aRefund;
    wasSet = true;
    return wasSet;
  }

  public boolean setAuthorizationCode(String aAuthorizationCode)
  {
    boolean wasSet = false;
    authorizationCode = aAuthorizationCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setPaid(boolean aPaid)
  {
    boolean wasSet = false;
    paid = aPaid;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartWeek(int aStartWeek)
  {
    boolean wasSet = false;
    startWeek = aStartWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndWeek(int aEndWeek)
  {
    boolean wasSet = false;
    endWeek = aEndWeek;
    wasSet = true;
    return wasSet;
  }

  public int getRefund()
  {
    return refund;
  }

  public String getAuthorizationCode()
  {
    return authorizationCode;
  }

  public boolean getPaid()
  {
    return paid;
  }

  public int getStartWeek()
  {
    return startWeek;
  }

  public int getEndWeek()
  {
    return endWeek;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isPaid()
  {
    return paid;
  }

  public String getTripStatusFullName()
  {
    String answer = tripStatus.toString();
    return answer;
  }

  public TripStatus getTripStatus()
  {
    return tripStatus;
  }

  public boolean payForTrip(String code)
  {
    boolean wasEventProcessed = false;
    
    TripStatus aTripStatus = tripStatus;
    switch (aTripStatus)
    {
      case Assigned:
        // line 7 "../../../../../ClimbSafeStates.ump"
        setAuthorizationCode(code);
       setPaid(true);
        setTripStatus(TripStatus.Assigned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean startTrip()
  {
    boolean wasEventProcessed = false;
    
    TripStatus aTripStatus = tripStatus;
    switch (aTripStatus)
    {
      case Assigned:
        setTripStatus(TripStatus.Started);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancelTrip()
  {
    boolean wasEventProcessed = false;
    
    TripStatus aTripStatus = tripStatus;
    switch (aTripStatus)
    {
      case Assigned:
        if (!(getPaid()))
        {
        // line 12 "../../../../../ClimbSafeStates.ump"
          setRefund(0);
          setTripStatus(TripStatus.Cancelled);
          wasEventProcessed = true;
          break;
        }
        if (getPaid())
        {
        // line 15 "../../../../../ClimbSafeStates.ump"
          setRefund(50);
          setTripStatus(TripStatus.Cancelled);
          wasEventProcessed = true;
          break;
        }
        break;
      case OnTrip:
        // line 25 "../../../../../ClimbSafeStates.ump"
        setRefund(10);
        setTripStatus(TripStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 30 "../../../../../ClimbSafeStates.ump"
        setRefund(0);
        setTripStatus(TripStatus.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean __autotransition7__()
  {
    boolean wasEventProcessed = false;
    
    TripStatus aTripStatus = tripStatus;
    switch (aTripStatus)
    {
      case Started:
        if (!(getPaid()))
        {
          setTripStatus(TripStatus.Banned);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean __autotransition8__()
  {
    boolean wasEventProcessed = false;
    
    TripStatus aTripStatus = tripStatus;
    switch (aTripStatus)
    {
      case Started:
        if (getPaid())
        {
          setTripStatus(TripStatus.OnTrip);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean finishTrip()
  {
    boolean wasEventProcessed = false;
    
    TripStatus aTripStatus = tripStatus;
    switch (aTripStatus)
    {
      case OnTrip:
        setTripStatus(TripStatus.Finished);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setTripStatus(TripStatus aTripStatus)
  {
    tripStatus = aTripStatus;

    // entry actions and do activities
    switch(tripStatus)
    {
      case Started:
        __autotransition7__();
        __autotransition8__();
        break;
    }
  }
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
  }
  /* Code from template association_GetOne */
  public Guide getGuide()
  {
    return guide;
  }

  public boolean hasGuide()
  {
    boolean has = guide != null;
    return has;
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
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setMember(Member aNewMember)
  {
    boolean wasSet = false;
    if (aNewMember == null)
    {
      //Unable to setMember to null, as assignment must always be associated to a member
      return wasSet;
    }
    
    Assignment existingAssignment = aNewMember.getAssignment();
    if (existingAssignment != null && !equals(existingAssignment))
    {
      //Unable to setMember, the current member already has a assignment, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Member anOldMember = member;
    member = aNewMember;
    member.setAssignment(this);

    if (anOldMember != null)
    {
      anOldMember.setAssignment(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setGuide(Guide aGuide)
  {
    boolean wasSet = false;
    Guide existingGuide = guide;
    guide = aGuide;
    if (existingGuide != null && !existingGuide.equals(aGuide))
    {
      existingGuide.removeAssignment(this);
    }
    if (aGuide != null)
    {
      aGuide.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setHotel(Hotel aHotel)
  {
    boolean wasSet = false;
    Hotel existingHotel = hotel;
    hotel = aHotel;
    if (existingHotel != null && !existingHotel.equals(aHotel))
    {
      existingHotel.removeAssignment(this);
    }
    if (aHotel != null)
    {
      aHotel.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setClimbSafe(ClimbSafe aClimbSafe)
  {
    boolean wasSet = false;
    if (aClimbSafe == null)
    {
      return wasSet;
    }

    ClimbSafe existingClimbSafe = climbSafe;
    climbSafe = aClimbSafe;
    if (existingClimbSafe != null && !existingClimbSafe.equals(aClimbSafe))
    {
      existingClimbSafe.removeAssignment(this);
    }
    climbSafe.addAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Member existingMember = member;
    member = null;
    if (existingMember != null)
    {
      existingMember.setAssignment(null);
    }
    if (guide != null)
    {
      Guide placeholderGuide = guide;
      this.guide = null;
      placeholderGuide.removeAssignment(this);
    }
    if (hotel != null)
    {
      Hotel placeholderHotel = hotel;
      this.hotel = null;
      placeholderHotel.removeAssignment(this);
    }
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeAssignment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "refund" + ":" + getRefund()+ "," +
            "authorizationCode" + ":" + getAuthorizationCode()+ "," +
            "paid" + ":" + getPaid()+ "," +
            "startWeek" + ":" + getStartWeek()+ "," +
            "endWeek" + ":" + getEndWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 110 "../../../../../ClimbSafePersistence.ump"
  private static final long serialVersionUID = 11L ;

  
}