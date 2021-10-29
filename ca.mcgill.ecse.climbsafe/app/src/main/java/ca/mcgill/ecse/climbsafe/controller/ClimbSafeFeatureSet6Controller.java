package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;
import java.util.*;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;

public class ClimbSafeFeatureSet6Controller {
	

	/**
	 * Deleting equipments
	 *@param name the name of equipment to be deleted
	 *@throws InvalidInputException if equipment does not exist
	 *@author Kara Best
	 */
	//do i delete a bundle if theres less than 3 items w/o it or do I throw exception
	//at least 2 different kinds of equipment
	public static void deleteEquipment(String name) throws InvalidInputException {
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  List<Equipment> equipment = climbSafe.getEquipment();
	  try {
		boolean found = false;
		int index = 0;
		while(!found) {
			if(equipment.get(index).getName().equals(name)) {
				found = true;	
			}else {
				index++;
			}	
		}
		int index2;
		found = false;
		List<EquipmentBundle> equipmentBundles = climbSafe.getBundles(); 
		for(int i=0; i<equipmentBundles.size(); i++) {
			List<BundleItem> bundleItems = equipmentBundles.get(i).getBundleItems();
			index2 =0;
			while(!found && index2<bundleItems.size()){
				if(bundleItems.get(index2).getEquipment().getName().equals(name)) {
					found = true;	
				}
				index2++;
			}
			if(found) {
				break;
			}
		}
		if(found == false) {
			equipment.get(index).delete();
		}else {
			String error = "The piece of equipment is in a bundle and cannot be deleted";
			throw new InvalidInputException(error.trim());
		}
		
	  }catch(RuntimeException e){
		  throw new InvalidInputException(e.getMessage());
	  }
	  
	}
	/**
	 * Delete equipment bundles
	 *@param name the name of equipment bundle to be deleted
	 *@author Kara Best
	 */
	public static void deleteEquipmentBundle(String name) {
		
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
			if(index==equipmentBundles.size()) {
				return;
			}
		}
	
	}
	/**
	 * View assignment
	 *@return TOAssignments list of ClimbSafe assignments
	 *@author Kara Best
	 */
	public static List<TOAssignment> getAssignments() {
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  List<TOAssignment> TOAssignments = new ArrayList<TOAssignment>();	  
	  List<Assignment> assignments = climbSafe.getAssignments();
	  int nrWeeks;
	  int startWeek;
	  int endWeek;
	  int totalCostForGuide;
	  int totalCostForEquipment;
	  boolean guideRequired;
	  String memberEmail, memberName;
	  String guideEmail = null;
	  String guideName =null;
	  String hotelName = null;
	  boolean found = false;
	  int discount;
	  int index;
	  int bundlePrice;
	  
	  
	  for(int i=0; i<assignments.size(); i++) {
		  totalCostForEquipment =0;
		  totalCostForGuide =0;
		  guideEmail = null;
		  guideName = null;
		  hotelName = null;
		  nrWeeks = assignments.get(i).getMember().getNrWeeks();
		  guideRequired = assignments.get(i).getMember().getGuideRequired();
		  memberName = assignments.get(i).getMember().getName();
		  memberEmail = assignments.get(i).getMember().getEmail();
		  startWeek = assignments.get(i).getStartWeek();
		  endWeek = assignments.get(i).getEndWeek();
		  
		  if(guideRequired) {
			  guideName = assignments.get(i).getGuide().getName();
			  guideEmail = assignments.get(i).getGuide().getEmail();
			  totalCostForGuide = climbSafe.getPriceOfGuidePerWeek()*nrWeeks;
		  }
		  if(assignments.get(i).getMember().getHotelRequired()) {
			  hotelName = assignments.get(i).getHotel().getName();
		  }
		  if(assignments.get(i).getMember().hasBookedItems()) {
			  List<BookedItem> bookedItems = assignments.get(i).getMember().getBookedItems();
			  for(int j=0; j<bookedItems.size(); j++) {
				  index =0;
				  found = false;
				  if(bookedItems.get(j).getItem().getName().contains("bundle")) {
					  List<EquipmentBundle> equipmentBundle = climbSafe.getBundles();
					  while(!found) {
						  if(equipmentBundle.get(index).getName().equals(bookedItems.get(j).getItem().getName())) {
							  found = true;
						  }else {
							  index++;
						  }
					  }
					  bundlePrice =0;
					  if(guideRequired) { 
						  discount = 100-equipmentBundle.get(index).getDiscount();
						  for(int k=0; k<equipmentBundle.get(index).numberOfBundleItems(); k++) {
							  bundlePrice += equipmentBundle.get(index).getBundleItem(k).getEquipment().getPricePerWeek()*equipmentBundle.get(index).getBundleItem(k).getQuantity();
						  }
						  bundlePrice = (bundlePrice*discount)/100;
						  

					  }else {
						  for(int k=0; k<equipmentBundle.get(index).numberOfBundleItems(); k++) {
							  bundlePrice += equipmentBundle.get(index).getBundleItem(k).getEquipment().getPricePerWeek()*equipmentBundle.get(index).getBundleItem(k).getQuantity();
						  }
						  
					  }
					  totalCostForEquipment += bundlePrice*nrWeeks*bookedItems.get(j).getQuantity();
				  }else {
					  List<Equipment> equipment = climbSafe.getEquipment();
					  index =0;
					  found = false;
					  while(!found) {
						  if(equipment.get(index).getName().equals(bookedItems.get(j).getItem().getName())) {
							  found = true;
						  }else {
							  index++;
						  }
					  }
					  totalCostForEquipment += (equipment.get(index).getPricePerWeek()*nrWeeks*bookedItems.get(j).getQuantity());
				  }
				  
			  }
		  }
		  TOAssignments.add(new TOAssignment(memberEmail, memberName, guideEmail, guideName, hotelName, startWeek, endWeek, totalCostForGuide, totalCostForEquipment));

		  
	  }
	  return TOAssignments;
	}
}
  