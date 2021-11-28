/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.io.Serializable;

// line 16 "ClimbSafePersistence.ump"
public class User implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}

  // line 22 "ClimbSafePersistence.ump"
   public static  void reinitializeUniqueEmail(Administrator admin, List<Guide> guides, List<Member> members){
    usersByEmail = new HashMap<String, User>();
    usersByEmail.put(admin.getEmail(), admin);
    for (Guide guide : guides) {
      usersByEmail.put(guide.getEmail(), guide);
    }
    for (Member member : members) {
      usersByEmail.put(member.getEmail(), member);
    }
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 19 "ClimbSafePersistence.ump"
  private static final long serialVersionUID = 2L ;

  
}