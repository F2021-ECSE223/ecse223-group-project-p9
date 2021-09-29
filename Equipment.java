/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 66 "domain_model.ump"
public class Equipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private double mass;
  private int price;

  //Equipment Associations
  private Member member;
  private EquipmentBundle equipment;
  private Admin admin;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Equipment(double aMass, int aPrice, Member aMember, Admin aAdmin)
  {
    mass = aMass;
    price = aPrice;
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create equipment due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create equipment due to admin. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aMass<=0||aPrice<=0)
    {
      throw new RuntimeException("Please provide a valid mass and price [mass>0&&price>0]");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMass(double aMass)
  {
    boolean wasSet = false;
    if (aMass>0&&getPrice()>0)
    {
    mass = aMass;
    wasSet = true;
    }
    return wasSet;
  }

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    if (getMass()>0&&aPrice>0)
    {
    price = aPrice;
    wasSet = true;
    }
    return wasSet;
  }

  public double getMass()
  {
    return mass;
  }

  public int getPrice()
  {
    return price;
  }
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
  }
  /* Code from template association_GetOne */
  public EquipmentBundle getEquipment()
  {
    return equipment;
  }

  public boolean hasEquipment()
  {
    boolean has = equipment != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Admin getAdmin()
  {
    return admin;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMember(Member aMember)
  {
    boolean wasSet = false;
    if (aMember == null)
    {
      return wasSet;
    }

    Member existingMember = member;
    member = aMember;
    if (existingMember != null && !existingMember.equals(aMember))
    {
      existingMember.removeEquipment(this);
    }
    member.addEquipment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMandatoryMany */
  public boolean setEquipment(EquipmentBundle aEquipment)
  {
    //
    // This source of this source generation is association_SetOptionalOneToMandatoryMany.jet
    // This set file assumes the generation of a maximumNumberOfXXX method does not exist because 
    // it's not required (No upper bound)
    //   
    boolean wasSet = false;
    EquipmentBundle existingEquipment = equipment;

    if (existingEquipment == null)
    {
      if (aEquipment != null)
      {
        if (aEquipment.addEquipment(this))
        {
          existingEquipment = aEquipment;
          wasSet = true;
        }
      }
    } 
    else if (existingEquipment != null)
    {
      if (aEquipment == null)
      {
        if (existingEquipment.minimumNumberOfEquipment() < existingEquipment.numberOfEquipment())
        {
          existingEquipment.removeEquipment(this);
          existingEquipment = aEquipment;  // aEquipment == null
          wasSet = true;
        }
      } 
      else
      {
        if (existingEquipment.minimumNumberOfEquipment() < existingEquipment.numberOfEquipment())
        {
          existingEquipment.removeEquipment(this);
          aEquipment.addEquipment(this);
          existingEquipment = aEquipment;
          wasSet = true;
        }
      }
    }
    if (wasSet)
    {
      equipment = existingEquipment;
    }
    return wasSet;
  }
    /* Code from template association_SetOneToMany */
  public boolean setAdmin(Admin aAdmin)
  {
    boolean wasSet = false;
    if (aAdmin == null)
    {
      return wasSet;
    }

    Admin existingAdmin = admin;
    admin = aAdmin;
    if (existingAdmin != null && !existingAdmin.equals(aAdmin))
    {
      existingAdmin.removeEquipment(this);
    }
    admin.addEquipment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Member placeholderMember = member;
    this.member = null;
    if(placeholderMember != null)
    {
      placeholderMember.removeEquipment(this);
    }
    if (equipment != null)
    {
      if (equipment.numberOfEquipment() <= 2)
      {
        equipment.delete();
      }
      else
      {
        EquipmentBundle placeholderEquipment = equipment;
        this.equipment = null;
        placeholderEquipment.removeEquipment(this);
      }
    }
    Admin placeholderAdmin = admin;
    this.admin = null;
    if(placeholderAdmin != null)
    {
      placeholderAdmin.removeEquipment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "mass" + ":" + getMass()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "equipment = "+(getEquipment()!=null?Integer.toHexString(System.identityHashCode(getEquipment())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null");
  }
}