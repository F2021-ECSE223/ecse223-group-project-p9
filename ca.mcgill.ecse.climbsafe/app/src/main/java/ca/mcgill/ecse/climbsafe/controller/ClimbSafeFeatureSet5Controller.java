package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;

public class ClimbSafeFeatureSet5Controller {
	
/**
 * @author Joey Koay
 * @param name
 * @param discount
 * @param equipmentNames
 * @param equipmentQuantities
 * @throws InvalidInputException
 */

  public static void addEquipmentBundle(String name, int discount, List<String> equipmentNames, List<Integer> equipmentQuantities) throws InvalidInputException {
	  String error = "";
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  List<Equipment> equipmentList = climbSafe.getEquipment();
	  List<EquipmentBundle> bundleList2 = climbSafe.getBundles();
	  

	  for(int i=0; i<equipmentNames.size(); i++) {
		  if(validEquipment(equipmentList, equipmentNames.get(i))==-1) {
			  error = "Equipment " + equipmentNames.get(i) + " does not exist";
			  throw new InvalidInputException(error.trim());
		  }
	  }
	  

	  List<String> uniqueBundleList = new ArrayList<String>();
	  for(int i=0; i<equipmentNames.size(); i++) {
		  if(!uniqueBundleList.contains(equipmentNames.get(i))) {
			  uniqueBundleList.add(equipmentNames.get(i));
		  }
	  }
	  
	  if(uniqueBundleList.size() < 2) {
		  error = "Equipment bundle must contain at least two distinct types of equipment";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  for(int i=0; i<equipmentQuantities.size(); i++) {
		  if(equipmentQuantities.get(i) < 1) {
			  error = "Each bundle item must have quantity greater than or equal to 1";
			  throw new InvalidInputException(error.trim());
		  }
	  }
	  
	  if(discount > 100) {
		  error = "Discount must be no more than 100 ";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(discount < 0) {
		  error = "Discount must be at least 0";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(name == "" || name == null) {
		  error = "Equipment bundle name cannot be empty ";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  
	  for(int i=0; i< bundleList2.size(); i++) {
		  if(bundleList2.get(i).getName().equals(name)) {
			  error = "A bookable item called " + name + " already exists";
			  throw new InvalidInputException(error.trim()); 
		  }
	  }
	  
	  for(int i=0; i< equipmentList.size(); i++) {
		  if(equipmentList.get(i).getName().equals(name)) {
			  error = "A bookable item called " + name + " already exists";
			  throw new InvalidInputException(error.trim()); 
		  }
	  }
	  try {
		  EquipmentBundle bundleList = climbSafe.addBundle(name, discount);
		  List<BundleItem> bundleItems = climbSafe.getBundleItems();
		  
		  for(int i=0; i<equipmentNames.size(); i++) {
			  for(int x=0; x< bundleItems.size(); x++) {
				  if(bundleItems.get(x).getBundle().getName() == equipmentNames.get(i)) {
					  bundleList.addBundleItem(bundleItems.get(x).getBundle().getBundleItem(x));
				  }
			  }
		  }
	  } catch (RuntimeException e) {
		  throw new InvalidInputException(e.getMessage());
	  }
  }
  /**
   * @author SeJong Yoo
   * @param oldName
   * @param newName
   * @param newDiscount
   * @param newEquipmentNames
   * @param newEquipmentQuantities
   * @throws InvalidInputException
   */

  public static void updateEquipmentBundle(String oldName, String newName, int newDiscount, List<String> newEquipmentNames, List<Integer> newEquipmentQuantities) throws InvalidInputException {
	  
//	  EquipmentBundle foundEquipmentBundle = findEquipmentBundle(oldName);
//	  if (foundEquipmentBundle == null) {
//		  throw new InvalidInputException ("The Equipment Bundle does not exist");
//	  }
//	  
//	  String error = "";
//	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
//	  
//	  if (newDiscount <= 0) {
//		  error = "The discount must be greater than 0";
//	  }
//	  if (newDiscount >= 100) {
//		  error = "The discount must be less than 100";
//	  } 
//	  if (newEquipmentQuantities.get(newDiscount) != 0) {
//		  error = "The equipment quantities must be greater than 0";
//	  }
//	  
//	  List <EquipmentBundle> equipmentBundle = climbSafe.getEquipmentBundle();
//	  for (EquipmentBundle e : equipmentBundle) {
//		  if ((e.getName()).equals(foundEquipmentBundle.getName())) {
//			  throw new InvalidInputException ("This name is already exist for another equipment bundle")
//		  }
//	  }
	  try {
		  
	  } catch (RuntimeException e) {
		  throw new InvalidInputException(e.getMessage());
	  }
  }
//private static EquipmentBundle findEquipmentBundle(String oldName) {
//	ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
//	List <EquipmentBundle> myEquipmentBundle = climbSafe.getEquipementBundle();
//	for (EquipmentBundle e : myEquipmentBundle) {
//		String name = e.getName();
//		if (name.equals(oldName)) {
//			return e;
//		}
//	}
//	return null;
//}
  
  	private static int validEquipment (List <Equipment> e, String oldName) {
		 for(int i=0; i<e.size();i++) {
			 if ((e.get(i).getName()).equals(oldName)) {
				 return i;
			 }
		 }
		return -1;
	}

}
