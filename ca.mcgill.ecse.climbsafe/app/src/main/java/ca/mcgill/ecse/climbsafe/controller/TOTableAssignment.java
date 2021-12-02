/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;

// line 48 "../../../../../ClimbSafeTransferObjects.ump"
public class TOTableAssignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOTableAssignment Attributes
  private String memberEmail;
  private String memberName;
  private String guideEmail;
  private String guideName;
  private String startToEnd;
  private int cost;
  private String status;
  private String code;
  private int refund;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOTableAssignment(String aMemberEmail, String aMemberName, String aGuideEmail, String aGuideName, String aStartToEnd, int aCost, String aStatus, String aCode, int aRefund)
  {
    memberEmail = aMemberEmail;
    memberName = aMemberName;
    guideEmail = aGuideEmail;
    guideName = aGuideName;
    startToEnd = aStartToEnd;
    cost = aCost;
    status = aStatus;
    code = aCode;
    refund = aRefund;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMemberEmail(String aMemberEmail)
  {
    boolean wasSet = false;
    memberEmail = aMemberEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setMemberName(String aMemberName)
  {
    boolean wasSet = false;
    memberName = aMemberName;
    wasSet = true;
    return wasSet;
  }

  public boolean setGuideEmail(String aGuideEmail)
  {
    boolean wasSet = false;
    guideEmail = aGuideEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setGuideName(String aGuideName)
  {
    boolean wasSet = false;
    guideName = aGuideName;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartToEnd(String aStartToEnd)
  {
    boolean wasSet = false;
    startToEnd = aStartToEnd;
    wasSet = true;
    return wasSet;
  }

  public boolean setCost(int aCost)
  {
    boolean wasSet = false;
    cost = aCost;
    wasSet = true;
    return wasSet;
  }

  public boolean setStatus(String aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public boolean setCode(String aCode)
  {
    boolean wasSet = false;
    code = aCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setRefund(int aRefund)
  {
    boolean wasSet = false;
    refund = aRefund;
    wasSet = true;
    return wasSet;
  }

  public String getMemberEmail()
  {
    return memberEmail;
  }

  public String getMemberName()
  {
    return memberName;
  }

  public String getGuideEmail()
  {
    return guideEmail;
  }

  public String getGuideName()
  {
    return guideName;
  }

  public String getStartToEnd()
  {
    return startToEnd;
  }

  public int getCost()
  {
    return cost;
  }

  public String getStatus()
  {
    return status;
  }

  public String getCode()
  {
    return code;
  }

  public int getRefund()
  {
    return refund;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "memberEmail" + ":" + getMemberEmail()+ "," +
            "memberName" + ":" + getMemberName()+ "," +
            "guideEmail" + ":" + getGuideEmail()+ "," +
            "guideName" + ":" + getGuideName()+ "," +
            "startToEnd" + ":" + getStartToEnd()+ "," +
            "cost" + ":" + getCost()+ "," +
            "status" + ":" + getStatus()+ "," +
            "code" + ":" + getCode()+ "," +
            "refund" + ":" + getRefund()+ "]";
  }
}