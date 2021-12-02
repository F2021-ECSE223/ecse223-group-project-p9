/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;

// line 38 "../../../../../ClimbSafeTransferObjects.ump"
public class TOEquipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOEquipment Attributes
  private String name;
  private String weight;
  private String pricePerWeek;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOEquipment(String aName, String aWeight, String aPricePerWeek)
  {
    name = aName;
    weight = aWeight;
    pricePerWeek = aPricePerWeek;
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

  public boolean setWeight(String aWeight)
  {
    boolean wasSet = false;
    weight = aWeight;
    wasSet = true;
    return wasSet;
  }

  public boolean setPricePerWeek(String aPricePerWeek)
  {
    boolean wasSet = false;
    pricePerWeek = aPricePerWeek;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getWeight()
  {
    return weight;
  }

  public String getPricePerWeek()
  {
    return pricePerWeek;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "weight" + ":" + getWeight()+ "," +
            "pricePerWeek" + ":" + getPricePerWeek()+ "]";
  }
}