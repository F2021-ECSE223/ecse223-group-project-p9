package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;
import java.util.*;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.model.Assignment.TripStatus;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

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
		ClimbSafePersistence.save();
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
		ClimbSafePersistence.save();
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
	  if(assignments==null) {
		  return TOAssignments;
	  }
	  int nrWeeks;
	  int startWeek;
	  int endWeek;
	  int totalCostForGuide;
	  int totalCostForEquipment;
	  boolean guideRequired;
	  String memberEmail, memberName;
	  String guideEmail = null;
	  String guideName =null;
	  boolean found = false;
	  String status = null;
	  String code = null;
	  int refund = 0;
	  int discount;
	  int index;
	  int bundlePrice;
	  
	  
	  for(int i=0; i<assignments.size(); i++) {
		  totalCostForEquipment =0;
		  totalCostForGuide =0;
		  guideEmail = null;
		  guideName = null;
		  nrWeeks = assignments.get(i).getMember().getNrWeeks();
		  guideRequired = assignments.get(i).getMember().getGuideRequired();
		  memberName = assignments.get(i).getMember().getName();
		  memberEmail = assignments.get(i).getMember().getEmail();
		  startWeek = assignments.get(i).getStartWeek();
		  endWeek = assignments.get(i).getEndWeek();
		  status = assignments.get(i).getTripStatus().toString();
		  refund = assignments.get(i).getRefund();
		  code = assignments.get(i).getAuthorizationCode();
		  
		  if(guideRequired) {
			  guideName = assignments.get(i).getGuide().getName();
			  guideEmail = assignments.get(i).getGuide().getEmail();
			  totalCostForGuide = climbSafe.getPriceOfGuidePerWeek()*nrWeeks;
		  }
		  if(assignments.get(i).getMember().hasBookedItems()) {
			  boolean isBundle = false;
			  List<BookedItem> bookedItems = assignments.get(i).getMember().getBookedItems();
			  for(int j=0; j<bookedItems.size(); j++) {
				  isBundle = false;
				  index =0;
				  found = false;
				  List<EquipmentBundle> equipmentBundle = climbSafe.getBundles();
				  for(EquipmentBundle b: equipmentBundle) {
					  if(bookedItems.get(j).getItem().getName().equals(b.getName())) {
						  isBundle = true;
					  }
				  }
				  if(isBundle) {
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
						  }else{
							  index++;
						  }
					  }
					  totalCostForEquipment += (equipment.get(index).getPricePerWeek()*nrWeeks*bookedItems.get(j).getQuantity());
				  }
				  
			  }
		  }
		  TOAssignments.add(new TOAssignment(memberEmail, memberName, guideEmail, guideName, startWeek, endWeek, totalCostForGuide, totalCostForEquipment, status, code, refund));

		  
	  }
	  return TOAssignments;
	}
}
  