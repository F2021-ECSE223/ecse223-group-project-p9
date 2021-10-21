package ca.mcgill.ecse.climbsafe.controller;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.model.*;


import java.util.List;

public class ClimbSafeFeatureSet2Controller {

  public static void registerMember(String email, String password, String name,
      String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
      List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException {
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  try {
		  Member a = climbSafe.addMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired);
		  for (int i=0; i<itemNames.size(); i++) {
			 
		  a.addBookedItem(new BookedItem(itemQuantities.get(i), climbSafe, a, BookableItem.getWithName(itemNames.get(i))));
		  
		  }
	  }catch(RuntimeException e){
		  throw new InvalidInputException(e.getMessage());
	  }
	  
  }

  public static void updateMember(String email, String newPassword, String newName,
      String newEmergencyContact, int newNrWeeks, boolean newGuideRequired,
      boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities)
      throws InvalidInputException {}

}
