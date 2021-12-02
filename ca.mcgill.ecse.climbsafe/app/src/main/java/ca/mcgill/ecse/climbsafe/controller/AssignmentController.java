package ca.mcgill.ecse.climbsafe.controller;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.model.Assignment.TripStatus;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

import java.util.List;

//import org.checkerframework.checker.units.qual.m;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;

public class AssignmentController { 
	static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	static String error = "";

	/**
	 * @throws InvalidInputException
	 * @author Victor Micha
	 */
	public static void initiateAssignment() throws InvalidInputException{
		error = "";
		List<Member> members = climbSafe.getMembers();
		List<Guide> guides = climbSafe.getGuides();
		int g = 0;
		Guide guide = guides.get(g);
		Member member;
		try {
			for (int m=0; m<members.size(); m++) {
				member = members.get(m);
				if (member.getGuideRequired()){
					if (guideAvailableForNumWeeks(guide.getAssignments(), member.getNrWeeks(), guide)) {
						int startWeek;
						int endWeek;
						if (guide.getAssignments().size()==0) {
							startWeek = 1;
							endWeek = member.getNrWeeks();
						} else {
							startWeek = guide.getAssignments().get(guide.getAssignments().size()-1).getEndWeek()+1;
							endWeek = startWeek+ member.getNrWeeks()-1;
						}
						Assignment assignment = new Assignment(startWeek, endWeek, member, climbSafe);
						assignment.setGuide(guide);
						if (endWeek == climbSafe.getNrWeeks()){
							if (!(g+1<guides.size())) {
								error = "Assignments could not be completed for all members";
								throw new InvalidInputException(error.trim());
							}
							g++;
							guide = guides.get(g);
							
						}

					}else {
						if(guide.getAssignments().get(guide.getAssignments().size()-1).getEndWeek()+member.getNrWeeks()>climbSafe.getNrWeeks()){
							int temp = g;
							//add member to next guide that is available
							//to find first available guide
							boolean guideNotFound = true;
							int index = 0;
							while(guideNotFound&&index<guides.size()) {
								if (guides.get(index).getAssignments().size()==0) {
									guideNotFound = false;
								}else if (guides.get(index).getAssignments().get(guides.get(index).getAssignments().size()-1).getEndWeek()+member.getNrWeeks()<=climbSafe.getNrWeeks()) {
									guideNotFound = false;
									
								}
								index++;
							}
							if(guideNotFound) {
								error = "Assignments could not be completed for all members";
								throw new InvalidInputException(error.trim());
							}
							index--;
							guide = guides.get(index);
							int startWeek;
							int endWeek;
							if (guide.getAssignments().size()==0) {
								startWeek = 1;
								endWeek = member.getNrWeeks();
							} else {
								startWeek = guide.getAssignments().get(guide.getAssignments().size()-1).getEndWeek()+1;
								endWeek = startWeek+ member.getNrWeeks()-1;
							}
							
							Assignment assignment = new Assignment(startWeek, endWeek, member, climbSafe);
							assignment.setGuide(guide);
							
							g = temp;
							guide = guides.get(g);
							
						}else {
							if (!(g+1<guides.size())) {
								error = "Assignments could not be completed for all members";
								throw new InvalidInputException(error.trim());
							}
							g++;
							guide = guides.get(g);
						}
					}
				}
				else {
					Assignment a = new Assignment(1, member.getNrWeeks(), member, climbSafe);
				}
			}
			ClimbSafePersistence.save();
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
	
	/**
	 * @param email
	 * @param week
	 * @throws InvalidInputException
	 * @author Enzo Benoit-Jeannin
	 */
	public static void startTrips(int week) throws InvalidInputException{
		error = "";
		List<Assignment> myAssignments = climbSafe.getAssignments(); 

		for (Assignment a : myAssignments) {
			if (a.getStartWeek() == week) {
				if (a.getTripStatus().equals(TripStatus.Banned)) {
					error += "Cannot start the trip due to a ban\n";
				}else if (a.getTripStatus().equals(TripStatus.Finished)) {
					error+="Cannot start a trip which has finished\n";
				}else if (a.getTripStatus().equals(TripStatus.Cancelled)) {
					error+="Cannot start a trip which has been cancelled\n";
				}
			}
		}
		if(!error.isEmpty()) {
			throw new InvalidInputException(error.trim());
		}

		try {
			for (Assignment a : myAssignments) {
				if (a.getStartWeek()==week) {
					a.startTrip();
				}
			}
			ClimbSafePersistence.save();
		}catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}

	/**
	 * @param email
	 * @throws InvalidInputException
	 * @author Enzo Benoit-Jeannin
	 */
	public static void cancelTrip(String email) throws InvalidInputException {	
		error = "";
		List<Assignment> myAssignments = climbSafe.getAssignments(); 
		List<Member> members = climbSafe.getMembers();
		try {
			if (validMember(members,email)==-1) { 
				error="Member with email address "+ email + " does not exist";
				throw new InvalidInputException(error.trim());
			}
			if (myAssignments.size()!=0) {
				for (Assignment a : myAssignments) {
					if (a.getMember().getEmail().equals(email)) {
						if (a.getTripStatus().equals(TripStatus.Banned)) {
							error = "Cannot cancel the trip due to a ban";
							throw new InvalidInputException(error.trim());
						}else if (a.getTripStatus().equals(TripStatus.Finished)) {
							error="Cannot cancel a trip which has finished";
							throw new InvalidInputException(error.trim());
						}
					}
				}
			}
			for (Assignment a : myAssignments) {
				if (a.getMember().getEmail().equals(email)) {
					a.cancelTrip();
				}
			}
			ClimbSafePersistence.save();
		}catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}

	/**
	 * @param email
	 * @throws InvalidInputException
	 * @author Kara Best
	 */
	public static void finishTrip(String email) throws InvalidInputException {
		error = "";
		List<Assignment> assignments = climbSafe.getAssignments();
		try {
			if(validMember(climbSafe.getMembers(), email)==-1) {
				error = "Member with email address "+ email +" does not exist";
				throw new InvalidInputException(error.trim());

			}
			for(Assignment a: assignments) {
				if(a.getMember().getEmail().equals(email)){
					if(a.getTripStatus().equals(TripStatus.Banned)) {
						error = "Cannot finish the trip due to a ban";
						throw new InvalidInputException(error.trim());

					}else if(a.getTripStatus().equals(TripStatus.OnTrip)) {
						a.finishTrip();
						a.setRefund(0);
					}else if(a.getTripStatus().equals(TripStatus.Cancelled)) {
						error = "Cannot finish a trip which has been cancelled";
						throw new InvalidInputException(error.trim());

					}else if(a.getTripStatus().equals(TripStatus.Assigned)|| a.getPaid() == true) {
						error = "Cannot finish a trip which has not started";
						throw new InvalidInputException(error.trim());

					}else if(a.getTripStatus().equals(TripStatus.Finished)) {
						error = "Cannot finish a trip which has already been finished";
						throw new InvalidInputException(error.trim());

					}
				}
			}	
			ClimbSafePersistence.save();
		}catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	/**
	 * member paying for a trip
	 * 
	 * @author Joey Koay
	 * @param email
	 * @param code - payment code
	 */
	public static void payForTrip(String email, String code) throws InvalidInputException {
		error = "";
		List<Member> memberList = climbSafe.getMembers();
		List<Assignment> allAssignments = climbSafe.getAssignments();
		Assignment myAssignment = null;
		if(validMember(memberList, email) == -1) {
			error = "Member with email address " + email + " does not exist";
			throw new InvalidInputException(error.trim());
		}

		if(code == null || code == "") {
			error = "Invalid authorization code";
			throw new InvalidInputException(error.trim());
		}
		for(int i=0; i<allAssignments.size(); i++) {
			if(allAssignments.get(i).getMember().getEmail().equals(email)) {
				myAssignment = allAssignments.get(i);
				break;
			}
		}
		if(myAssignment.getTripStatus().equals(TripStatus.Started)) {
			error = "Trip has already been paid for";
			throw new InvalidInputException(error.trim());
		}
		if(myAssignment.getTripStatus().equals(TripStatus.Banned)) {
			error = "Cannot pay for the trip due to a ban";
			throw new InvalidInputException(error.trim());
		}
		if(myAssignment.getTripStatus().equals(TripStatus.Cancelled)) {
			error = "Cannot pay for a trip which has been cancelled";
			throw new InvalidInputException(error.trim());
		}
		if(myAssignment.getTripStatus().equals(TripStatus.Finished)) {
			error = "Cannot pay for a trip which has finished";
			throw new InvalidInputException(error.trim());
		}
		if(myAssignment.getPaid()==true) {
			error = "Trip has already been paid for";
			throw new InvalidInputException(error.trim());
		}
		try {
			myAssignment.setPaid(true);
			myAssignment.setAuthorizationCode(code);
			ClimbSafePersistence.save();
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
}
