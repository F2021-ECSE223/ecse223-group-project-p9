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
		  boolean validEmail = true;
		  
		  if(email.contains(" ")) {
			  validEmail = false;
		  }
		  if(!(email.indexOf("@") > 0)) {
			  validEmail = false;
		  }
		  if(!(email.indexOf("@") == email.lastIndexOf("@"))) {
			  validEmail = false;
		  }
		  if(!(email.indexOf("@") < email.lastIndexOf(".") - 1)) {
			  validEmail = false;
		  }
		  if(!(email.lastIndexOf(".") < email.length() - 1)) {
			  validEmail = false;
		  }
		  
//		  Since admin is already pre-registered, they will not be needing to "register" again, therefore, a member can never be "admin@nmc.nt"
		  boolean notAdmin = true;
		  if(email.equals("admin@nmc.nt")) {
			  notAdmin = false;
		  }
		  
		  boolean validPassword = true;
		  if(password.equals("")) {
			  validPassword = false;
		  }
		  if(password.equals(null)) {
			  validPassword = false;
		  }
		  
		  boolean validName = true;
		  if(name.equals("")) {
			  validName = false;
		  }
		  if(name.equals(null)) {
			  validName = false;
		  }
		  
		  boolean validEmergencyContact = true;
		  if(emergencyContact.equals("")) {
			  validName = false;
		  }
		  if(emergencyContact.equals(null)) {
			  validName = false;
		  }
		  
		  
		  boolean validNrWeeks = true;
		  if(nrWeeks<=0) {
			  validNrWeeks = false;
		  }
		  if(nrWeeks > climbSafe.getNrWeeks()) {
			  validNrWeeks = false;
		  }
		  
		  if(validEmail && validPassword && validName && validEmergencyContact && validNrWeeks && notAdmin) {
			  boolean validItems = true;
			  for(int i=0; i<itemNames.size(); i++) {
				  if(BookableItem.getWithName(itemNames.get(i)) == null){
					  validItems = false;
					  break;
				  }
			  }
			  if(validItems) {
				  Member member = climbSafe.addMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired);
				  for (int i=0; i<itemNames.size(); i++) {
					 climbSafe.addBookedItem(itemQuantities.get(i), member, BookableItem.getWithName(itemNames.get(i))); 
				  }
			  }
			  
		  }
		  
	  }catch(RuntimeException e){
		  throw new InvalidInputException(e.getMessage());
	  }
	  
  }

  public static void updateMember(String email, String newPassword, String newName,
      String newEmergencyContact, int newNrWeeks, boolean newGuideRequired,
      boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities)
      throws InvalidInputException {
	  
	  
  }

}
