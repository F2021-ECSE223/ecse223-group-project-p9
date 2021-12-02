/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;
import java.util.*;

// line 42 "../../../../../ClimbSafeTransferObjects.ump"
public class TOBundle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOBundle Attributes
  private String name;
  private int discount;
  private List<String> bundleItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOBundle(String aName, int aDiscount)
  {
    name = aName;
    discount = aDiscount;
    bundleItems = new ArrayList<String>();
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

  public boolean setDiscount(int aDiscount)
  {
    boolean wasSet = false;
    discount = aDiscount;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetMany */
  public boolean addBundleItem(String aBundleItem)
  {
    boolean wasAdded = false;
    wasAdded = bundleItems.add(aBundleItem);
    return wasAdded;
  }

  public boolean removeBundleItem(String aBundleItem)
  {
    boolean wasRemoved = false;
    wasRemoved = bundleItems.remove(aBundleItem);
    return wasRemoved;
  }

  public String getName()
  {
    return name;
  }

  public int getDiscount()
  {
    return discount;
  }
  /* Code from template attribute_GetMany */
  public String getBundleItem(int index)
  {
    String aBundleItem = bundleItems.get(index);
    return aBundleItem;
  }

  public String[] getBundleItems()
  {
    String[] newBundleItems = bundleItems.toArray(new String[bundleItems.size()]);
    return newBundleItems;
  }

  public int numberOfBundleItems()
  {
    int number = bundleItems.size();
    return number;
  }

  public boolean hasBundleItems()
  {
    boolean has = bundleItems.size() > 0;
    return has;
  }

  public int indexOfBundleItem(String aBundleItem)
  {
    int index = bundleItems.indexOf(aBundleItem);
    return index;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "discount" + ":" + getDiscount()+ "]";
  }
}