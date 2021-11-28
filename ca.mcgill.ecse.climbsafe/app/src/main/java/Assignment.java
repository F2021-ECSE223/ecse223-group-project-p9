/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 1 "ClimbSafeStates.ump"
public class Assignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private int refund;
  private String authorizationCode;
  private boolean paid;

  //Assignment State Machines
  public enum TripStatus { Assigned, Started, OnTrip, Finished, Banned, Cancelled }
  private TripStatus tripStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assignment()
  {
    authorizationCode = null;
    paid = false;
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
        // line 7 "ClimbSafeStates.ump"
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
        // line 12 "ClimbSafeStates.ump"
          setRefund(0);
          setTripStatus(TripStatus.Cancelled);
          wasEventProcessed = true;
          break;
        }
        if (getPaid())
        {
        // line 15 "ClimbSafeStates.ump"
          setRefund(50);
          setTripStatus(TripStatus.Cancelled);
          wasEventProcessed = true;
          break;
        }
        break;
      case OnTrip:
        // line 25 "ClimbSafeStates.ump"
        setRefund(10);
        setTripStatus(TripStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 30 "ClimbSafeStates.ump"
        setRefund(0);
        setTripStatus(TripStatus.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean __autotransition3__()
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

  private boolean __autotransition4__()
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
        __autotransition3__();
        __autotransition4__();
        break;
    }
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "refund" + ":" + getRefund()+ "," +
            "authorizationCode" + ":" + getAuthorizationCode()+ "," +
            "paid" + ":" + getPaid()+ "]";
  }
}