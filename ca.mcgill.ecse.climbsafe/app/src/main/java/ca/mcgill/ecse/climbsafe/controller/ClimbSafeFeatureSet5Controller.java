package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;

public class ClimbSafeFeatureSet5Controller {
	
/**
 * @author SeJong Yoo
 * @param name
 * @param discount
 * @param equipmentNames
 * @param equipmentQuantities
 * @throws InvalidInputException
 */

  public static void addEquipmentBundle(String name, int discount, List<String> equipmentNames,
      List<Integer> equipmentQuantities) 
      throws InvalidInputException {
	  String error = "";
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  List<Equipment> myEquipments = climbSafe.getEquipment();
	  
	  //constraints
	  if (discount <= 0) {
		  error = "The discount must be greater than 0";
	  }
	  if (discount >= 0) {
		  error = "The discount must be less than 100";
	  }
	  if (equipmentQuantities.get(discount) != null) {
		  error = "The equipment quantities must be great than 0";
	  }
	  List<EquipmentBundle> bundles = climbSafe.getBundles();
	  for (EquipmentBundle b : bundles) {
		  if (b.getName().equals(name)) {
			  error = "The equipment bundle is already exist";
		  }
	  }
	  
	  try {
		  climbSafe.addEquipmentBundle(name, discount, equipmentNames, equipmentQuantities);
	  } catch (RuntimeException e) {
		  //a bundle contains at least two different kinds of equipment
		   // (i.e., at least two instances of bundleItems exist for a bundle)
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

  public static void updateEquipmentBundle(String oldName, String newName, int newDiscount,
      List<String> newEquipmentNames, List<Integer> newEquipmentQuantities)
      throws InvalidInputException {
	  
	  String error = "";
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  List<EquipmentBundle> myBundle = climbSafe.getBundles();

	  if ((validEquipmentBundle(myBundle, oldName) == -1))  {
		  error = "Equipment Bundle does not exist";
	  }
	  if (newDiscount <= 0) {
		  error = "The discount must be greater than 0";
	  }
	  if (newDiscount >= 100) {
		  error = "The discount must be less than 100";
	  } 
	  if (newEquipmentQuantities.get(newDiscount) != 0) {
		  error = "The equipment quantities must be greater than 0";
	  }
	  List<EquipmentBundle> bundles = climbSafe.getBundles();
	  for (EquipmentBundle b : bundles) {
		  if(b.getName().equals(newName)) {
			  error="An equipment bundle with the same name already exists";
		  }
	  }
	  if(error.length() != 0) {
		  throw new InvalidInputException(error.trim());
	  }
	  
	 try {
		 EquipmentBundle mybundle = climbSafe.getBundle(validEquipmentBundle(myBundle, oldName));
		 ((BookableItem) myBundle).setName(newName);
		 ((EquipmentBundle) myBundle).setDiscount(newDiscount);
	 }catch(RuntimeException e){
		  throw new InvalidInputException(e.getMessage());
	  }
  }
	  
  private static int validEquipmentBundle(List<EquipmentBundle> myBundle, String oldName) {
	for (int i=0; i<myBundle.size(); i++) {
		if ((myBundle.get(i).getName()).equals(oldName)) {
			return i;
		}
	}
	return -1;
}
}
