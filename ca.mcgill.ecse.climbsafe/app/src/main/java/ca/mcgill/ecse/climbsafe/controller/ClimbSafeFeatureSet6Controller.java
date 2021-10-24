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
	  //create empty TOAssignment list
	  List<TOAssignment> TOAssignments = new ArrayList<TOAssignment>();
	  
	  List<Assignment> assignments = climbSafe.getAssignments();
	  //get list assignments from model method
	  int nrWeeks;
	  int startWeek;
	  int endWeek;
	  int totalCostForGuide=0;
	  int totalCostForEquipment=0;
	  boolean guideRequired;
	  String memberEmail, memberName;
	  String guideEmail = null;
	  String guideName =null;
	  String hotelName = null;
	  boolean found = false;
	  float discount;
	  int index;
	  int bundlePrice;
	  
	  
	  for(int i=0; i<assignments.size(); i++) {
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
						  discount = (1-equipmentBundle.get(index).getDiscount())/100;
						  for(int k=0; k<equipmentBundle.get(index).numberOfBundleItems(); k++) {
							  bundlePrice += equipmentBundle.get(index).getBundleItem(k).getEquipment().getPricePerWeek();
						  }
						  bundlePrice *= discount;
						  
					  }else {
						  for(int k=0; k<equipmentBundle.get(index).numberOfBundleItems(); k++) {
							  bundlePrice += equipmentBundle.get(index).getBundleItem(k).getEquipment().getPricePerWeek();
						  }
						  
					  }
					  totalCostForEquipment += bundlePrice*nrWeeks;
				  }else {
					  
					  totalCostForEquipment += findEquipmentPrice(bookedItems.get(j).getItem().getName(), nrWeeks, climbSafe);
				  }
				  
			  }
		  }
		  TOAssignments.add(new TOAssignment(memberEmail, memberName, guideEmail, guideName, hotelName, startWeek, endWeek, totalCostForGuide, totalCostForEquipment));

		  
	  }
  return TOAssignments;
  }
  
  private static int findEquipmentPrice(String aName, int nrWeeks, ClimbSafe climbSafe) {
	  int index =0;
	  boolean found = false;
	  int price;
	  List<Equipment> equipment = climbSafe.getEquipment();
	  while(!found) {
		  if(equipment.get(index).getName().equals(aName)) {
			  found = true;
		  }else {
			  index++;
		  }
	  }
	  price = (equipment.get(index).getPricePerWeek()*nrWeeks);
	  return price;
  }
  
  

}
