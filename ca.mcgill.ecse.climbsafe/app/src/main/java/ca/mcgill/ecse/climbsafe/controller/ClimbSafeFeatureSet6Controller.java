package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;
import java.util.*;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.*;

public class ClimbSafeFeatureSet6Controller {

  public static void deleteEquipment(String name) throws InvalidInputException {
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  List<Equipment> equipment = climbSafe.getEquipment();
	  try {
		boolean found = false;
		int index = 0;
		while(!found) {
			if(equipment.get(index).getName().equals(name)) {
				found = true;
				equipment.get(index).delete();
				
			}
			index++;
		}	  
	  }catch(RuntimeException e){
		  throw new InvalidInputException(e.getMessage());
	  }
	  
  }

  // this method does not need to be implemented by a team with five team members
  public static void deleteEquipmentBundle(String name) {
	  //do i have to remove anything else or does it do it auto (same w ^^)
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  List<EquipmentBundle> equipmentBundles = climbSafe.getBundles();
	  boolean found = false;
	  int index = 0;
	  while(!found) {
			if(equipmentBundles.get(index).getName().equals(name)) {
				found = true;
				equipmentBundles.get(index).delete();
				
			}
			index++;
		}	  
	  
  }

  public static List<TOAssignment> getAssignments() {
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  List<TOAssignment> assignments = new ArrayList<TOAssignment>();
	  
	  //get list assignments from model method
	  
	  //assignment has: Member member, int startWeek, int endWeek, Guide guide, Hotel hotel, Climbsafe climbsafe
	  //TO assignment has: String memberEmail, String memberName, String guideEmail, String guideName, String hotelName
	  //int startWeek, int endWeek, int totalCostForGuide, int totalCostForEquipment
	  
	  //create empty TOAssignment list
	  //run for loop through assignment list
	  	//create new TOAssignment with: 
	  		//get member email and name from member in assignment, create nr weeks variable
	  		//check member is guide required(store in variable), if true: get guide email and name, if false: set to null
	  		//check hasHotel() from assignment, if false: string = null, if true: get hotel, get hotel name
	  		//add startWeek, endWeek
	  		//check guide required variable, if false: =0, if true: climbSafe.getPriceOfGuidePerWeek() and multiply by member.getNrWeeks()
	  		//check member.hasBookedItems(), if false: =0, if true: create total price variable, 
	  		//^^run for loop through list of booked items, get bookable item name, check if starts w "bundle", if false: get list equipment, while loop to find name, get priceperweek multiply by member.getNrWeeks()
	  		//if true: create total price variable, use number+1 after "bundle" as index in climbSafe.get list equipment bundles.get(i), then loop through get bundleitems get equipment get priceperweek multiply by nrWeeks(), if guide required minus get.discount and add to total price
	  
  return assignments;
  }
  
  

}
