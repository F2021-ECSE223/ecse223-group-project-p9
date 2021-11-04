package ca.mcgill.ecse.climbsafe.controller;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.model.Assignment.TripStatus;
import java.util.List;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;

public class AssignmentController { 
	public static void initiateAssignment (int startWeek, int endWeek, int nrWeeks, int totalCostForGuide, int totalCostForEquipment, boolean guideRequired) 
			throws InvalidInputException {
		ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
		
	}
	//start trips
	
	/**
	 * 
	 * @param assignment
	 * @author Enzo Benoit-Jeannin
	 */
	public static void cancelTrip(Assignment assignment) {
		String error="";
		String email = assignment.getMember().getEmail();
		
		if (!validEmail(email)) {
			error="Member with email address "+ email +" does not exist";
		}
		
		if(error.length() != 0) {
			  throw new InvalidInputException(error.trim());
		  }
		
		try {
			List<Assignment> myAssignments = climbSafe.getAssignments();
			for (Assignment a : myAssignments) {
				if (a.getMember().getEmail().equals(assignment.getMember().getEmail())) {
					a.setTripStatus(TripStatus.Cancelled);
					
				}
			}
		 }catch(RuntimeException e){
			  throw new InvalidInputException(e.getMessage());
		  }
		}

	//finish trip
	public static void finishTrip(Assignment assignment) {
		
	}
	//pay for a trip
	
	/**
	 * checking if the email is valid or not
	 * 
	 * @author Joey Koay
	 * @param email
	 * @return whether the email is valid
	 */
  private static boolean validEmail(String email) {
	  boolean validEmail = true;
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
	  return validEmail;
  }
}
