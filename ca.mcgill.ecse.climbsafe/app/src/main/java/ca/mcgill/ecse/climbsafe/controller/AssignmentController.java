package ca.mcgill.ecse.climbsafe.controller;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.model.Assignment.TripStatus;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;

public class AssignmentController {
	//initiate assignments
	//start trips
	public static void cancelTrip(Assignment assignment) {
		List<Assignment> myAssignemnts = climbSafe.getAssignments();
		
		for (Assignment a : myAssignemnts) {
			if (a.getMember().equals(assignment.getMember())) {
				a.setTripStatus(Cancelled);
			}
		}
	}
	//finish trip
	public static void finishTrip(Assignment assignment) {
		
	}
	//pay for a trip
	
}
