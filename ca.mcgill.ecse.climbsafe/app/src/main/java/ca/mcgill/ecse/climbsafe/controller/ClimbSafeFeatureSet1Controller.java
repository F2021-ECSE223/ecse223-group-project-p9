package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import java.util.List;

public class ClimbSafeFeatureSet1Controller {

	/**
	 * @author Victor Micha
	 * @param startDate Beginning of climbing season
	 * @param nrWeeks length of climbing season in weeks
	 * @param priceOfGuidePerWeek weekly fee for hiring one guide
	 */

	static String error = "";

	public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek) throws InvalidInputException {
		ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();		
//		if (climbSafe.getStartDate()!=null)
//			return;
		String[] s = startDate.toString().split("-");
		error = "";
		if (!(Integer.parseInt(s[1])<=12)){
			error = "Invalid date";
		}

		if(!(Integer.parseInt(s[2])<=31 )) {
			error = "Invalid date";
		}

		if(!(nrWeeks>0)) {
			error = "The number of climbing weeks must be greater than or equal to zero";
		}

		if(!(priceOfGuidePerWeek>0)) {
			error = "The price of guide per week must be greater than or equal to zero";
		}

		if (error.length()!=0)
			throw new InvalidInputException(error.trim());

		try {
			climbSafe.setStartDate(startDate);
			climbSafe.setNrWeeks(nrWeeks);
			climbSafe.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
			ClimbSafePersistence.save();
		}catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}

	/**
	 * @author Victor Micha
	 * @param email Member's email
	 */

	public static void deleteMember(String email) {
		if (email.indexOf("@") > 0 && email.indexOf("@") == email.lastIndexOf("@") && email.indexOf("@") < email.lastIndexOf(".") - 1 && email.lastIndexOf(".") < email.length() - 1) {
			ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
			List<Member> members = climbSafe.getMembers();
			boolean memberFound = false;
			int i=0;
			while (!memberFound && i<members.size()){
				if (members.get(i).getEmail().equals(email))
					memberFound = true;
				i++;
			}
			if (memberFound) {
				climbSafe.removeMember(members.get(i-1));
				members.get(i-1).delete();
			}
		}
		ClimbSafePersistence.save();
	}

	/**
	 * @author Victor Micha
	 * @param email Guide's email
	 */

	public static void deleteGuide(String email) {
		ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
		if (email.indexOf("@") > 0 && email.indexOf("@") == email.lastIndexOf("@") && email.indexOf("@") < email.lastIndexOf(".") - 1 && email.lastIndexOf(".") < email.length() - 1) {
			List<Guide> guides = climbSafe.getGuides();
			boolean guideFound = false;
			int i=0;
			while (!guideFound&&i<guides.size()){
				if (guides.get(i).getEmail().equals(email))
					guideFound = true;
				i++;
			}
			if (guideFound) {
				climbSafe.removeGuide(guides.get(i-1));
				guides.get(i-1).delete();
			}
		}
		ClimbSafePersistence.save();
	}



	// this method needs to be implemented only by teams with seven team members

	public static void deleteHotel(String name) {}



}

