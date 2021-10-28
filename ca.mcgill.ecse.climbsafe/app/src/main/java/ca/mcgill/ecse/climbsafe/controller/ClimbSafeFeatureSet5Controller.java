package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
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
	  
	  try {
		  climbSafe.addEquipmentBundle(name, discount, equipmentNames, equipmentQuantities);
	  } catch (RuntimeException e) {
		  error = e.getMessage();
		  if (error.startsWith("Cannot create duplicated name for Equipment Bundle")) {
			  error = "This name is already exist for another Equipment Bundle";
		  }
		  //a bundle contains at least two different kinds of equipment
		   // (i.e., at least two instances of bundleItems exist for a bundle)
		  throw new InvalidInputException(error);
		  
		  
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
	  
	  EquipmentBundle foundEquipmentBundle = findEquipmentBundle(oldName);
	  if (foundEquipmentBundle == null) {
		  throw new InvalidInputException ("The Equipment Bundle does not exist");
	  }
	  
	  String error = "";
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  
	  if (newDiscount <= 0) {
		  error = "The discount must be greater than 0";
	  }
	  if (newDiscount >= 100) {
		  error = "The discount must be less than 100";
	  } 
	  if (newEquipmentQuantities.get(newDiscount) != 0) {
		  error = "The equipment quantities must be greater than 0";
	  }
	  
	  List <EquipmentBundle> equipmentBundle = climbSafe.getEquipmentBundle();
	  for (EquipmentBundle e : equipmentBundle) {
		  if ((e.getName()).equals(foundEquipmentBundle.getName())) {
			  throw new InvalidInputException ("This name is already exist for another equipment bundle");
		  }
	  }
  }
private static EquipmentBundle findEquipmentBundle(String oldName) {
	ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	List <EquipmentBundle> myEquipmentBundle = climbSafe.getEquipmentBundle();
	for (EquipmentBundle e : myEquipmentBundle) {
		String name = e.getName();
		if (name.equals(oldName)) {
			return e;
		}
	}
	return null;
}

}
