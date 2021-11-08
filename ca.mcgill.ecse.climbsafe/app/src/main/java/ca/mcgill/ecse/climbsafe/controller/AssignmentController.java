package ca.mcgill.ecse.climbsafe.controller;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.model.Assignment.TripStatus;
import java.sql.Date;
import java.util.List;

import org.checkerframework.checker.units.qual.m;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;

public class AssignmentController { 
	static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	static String error = "";

	/**
	 * @throws InvalidInputException
	 * @author Victor Micha
	 */
	public static void initiateAssignment() throws InvalidInputException{
		try {
			List<Member> members = climbSafe.getMembers();
			List<Guide> guides = climbSafe.getGuides();
			int g = 0;
			Guide guide = guides.get(g);
			List<Assignment> guideAssignments = guide.getAssignments();
			for (int m=0; m<members.size(); m++) {
				Member member = members.get(m);
				if (member.getGuideRequired()){
					if (guideAvailableForNumWeeks(guideAssignments, member.getNrWeeks(), guide)) {
						int startWeek;
						int endWeek;
						if (guideAssignments.size()==0) {
							startWeek = 1;
							endWeek = member.getNrWeeks();
						} else {
							startWeek = guideAssignments.get(guideAssignments.size()-1).getEndWeek()+1;
							endWeek = startWeek+ member.getNrWeeks()-1;
						}

						Assignment assignment = new Assignment(startWeek, endWeek, member, climbSafe);
						assignment.setGuide(guide);
						if (endWeek == climbSafe.getNrWeeks()){
							if (!(g+1<guides.size())) {
								error = "Assignments could not be completed for all members";
								throw new InvalidInputException(error.trim());
							}
							guide = guides.get(g++);
							guideAssignments = guide.getAssignments();
						}
						else {
						if (!(g+1<guides.size())) {
							error = "Assignments could not be completed for all members";
							throw new InvalidInputException(error.trim());
						}
						guide = guides.get(g++);
						guideAssignments = guide.getAssignments();
						}
				}
				else {
					Assignment assignment = new Assignment(1, member.getNrWeeks(), member, climbSafe);
				}
				}
			}
		}
		catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}

	}



	public static boolean guideAvailableForNumWeeks(List<Assignment> guideAssignments, int memberNumWeeks, Guide guide) {
		if (guideAssignments.size()==0) {
			return true;
		}
		return (guideAssignments.get(guideAssignments.size()-1).getEndWeek()+memberNumWeeks<=climbSafe.getNrWeeks());
	}





	//doesn't need any arguments
	/**
	 * 
	 * @param email
	 * @param week
	 * @throws InvalidInputException
	 * @author Enzo Benoit-Jeannin
	 */
	public static void startTrips(String email, int week) throws InvalidInputException{
		List<Assignment> myAssignments = climbSafe.getAssignments(); 

		for (Assignment a : myAssignments) {
			if (a.getMember().getEmail().equals(email)) {
				if (a.getBanned()) {
					error = "Cannot start the trip due to a ban";
				}else if (a.getTripStatus().equals(TripStatus.Finished)) {
					error="Cannot start a trip which has been cancelled";
				}else if (a.getTripStatus().equals(TripStatus.Cancelled)) {
					error="Cannot start a trip which has finished";
				}
			}
		}

		if(error.length() != 0) {
			throw new InvalidInputException(error.trim());
		}

		try {
			for (Assignment a : myAssignments) {
				if (a.getMember().getEmail().equals(email)) {
					if (a.getFullyPaid()) {
						a.setTripStatus(TripStatus.Started);
					}
				}
			}
		}catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param email
	 * @throws InvalidInputException
	 * @author Enzo Benoit-Jeannin
	 */
	public static void cancelTrip(String email) throws InvalidInputException {	
		List<Assignment> myAssignments = climbSafe.getAssignments(); 
		List<Member> members = climbSafe.getMembers();
		if (validMember(members,email)==-1) { 
			error="Member with email address "+ email +" does not exist";
		}
		for (Assignment a : myAssignments) {
			if (a.getMember().getEmail().equals(email)) {
				if (a.getBanned()) {
					error = "Cannot cancel the trip due to a ban";
				}else if (a.getTripStatus().equals(TripStatus.Finished)) {
					error="Cannot cancel a trip which has finished";
				}
			}
		}

		if(error.length() != 0) {
			throw new InvalidInputException(error.trim());
		}

		try {
			for (Assignment a : myAssignments) {
				if (a.getMember().getEmail().equals(email)) {
					a.setTripStatus(TripStatus.Cancelled);
					if (a.getTripStatus().equals(TripStatus.Started)) {
						a.setTotalPayment(a.getTotalPayment()-a.getTotalPayment()*(10/100));
					}else if (a.getFullyPaid()) {
						a.setTotalPayment(a.getTotalPayment()-a.getTotalPayment()*(50/100));
					}
				}
			}
		}catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param email
	 * @throws InvalidInputException
	 * @author Kara Best
	 */
	public static void finishTrip(String email) throws InvalidInputException {
		List<Assignment> assignments = climbSafe.getAssignments();
		String error ="";
		try {
			if(validMember(climbSafe.getMembers(), email)==-1) {
				error = "Member with email address "+ email +" does not exist";
				throw new InvalidInputException(error.trim());
			}
			for(Assignment a: assignments) {
				if(a.getMember().getEmail().equals(email)){
					if(a.getBanned()) {
						error = "Cannot finish the trip due to a ban";
						throw new InvalidInputException(error.trim());
					}else if(a.getTripStatus().equals(TripStatus.Started)) {
						a.setTripStatus(TripStatus.Finished);
					}else if(a.getTripStatus().equals(TripStatus.Cancelled)) {
						error = "Cannot finish a trip which has been cancelled";
						throw new InvalidInputException(error.trim());
					}else if(a.getTripStatus().equals(TripStatus.Assigned)) {
						error = "Cannot finish a trip which has not started";
						throw new InvalidInputException(error.trim());
					}else if(a.getTripStatus().equals(TripStatus.Finished)) {
						error = "Cannot finish a trip which has already been finished";
						throw new InvalidInputException(error.trim());
					}
				}
			}			
		}catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	//pay for a trip
	public static void payForTrip(String email, String code) throws InvalidInputException {
		List<Member> memberList = climbSafe.getMembers();
		List<Assignment> allAssignments = climbSafe.getAssignments();

		if(validMember(memberList, email) == -1) {
			error = "Member with email address " + email + " does not exist";
			throw new InvalidInputException(error.trim());
		}

		if(code == null || code == "") {
			error = "Invalid authorization code";
			throw new InvalidInputException(error.trim());
		}
		try {
			for(int i=0; i<allAssignments.size(); i++) {
				if(allAssignments.get(i).getMember().getEmail() == email) {
					Assignment myAssignment = allAssignments.get(i);
					myAssignment.setFullyPaid(true);
					break;
				}
			}
		}catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}

	/**
	 * checking if the member exists
	 * 
	 * @author Joey Koay
	 * @param memberList - a list of all of the existing members
	 * @param email
	 * @return the index of the member in the list, if it does not exist, its -1
	 */
	private static int validMember(List<Member> memberList, String email) {
		int validMember = -1;
		for(int i=0; i<memberList.size(); i++) {
			if(memberList.get(i).getEmail().equals(email)) {
				validMember = i;
				break;
			}
		}
		return validMember;
	}

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
