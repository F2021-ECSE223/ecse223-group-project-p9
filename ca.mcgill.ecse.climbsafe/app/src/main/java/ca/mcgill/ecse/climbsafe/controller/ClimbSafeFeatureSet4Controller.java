package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.model.*;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
/**
 * 
 * @author Enzo Benoit-Jeannin
 *
 *
 * Implementation of the Controller methods for the addEquipment and updateEquipment domain concepts.
 */
public class ClimbSafeFeatureSet4Controller {

/**
 * @author Enzo Benoit-Jeannin
 * @param name
 * @param weight
 * @param pricePerWeek
 * @throws InvalidInputException
 * 
 * addEquipment controller method
 */
  public static void addEquipment(String name, int weight, int pricePerWeek)
      throws InvalidInputException {
	  String error = "";
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  
	  //checks the constraints
	  if (weight <= 0) {
		  error="The weight must be greater than 0. ";
	  }
	  if (pricePerWeek < 0) {
		  error="The price per week must be greater than or equal to 0 ";
	  }
	  
	  
	 if (name.equals("") || name.equals(null)) {
		 error="The name must not be empty";
	 }
	  
	  //try adding the equipment to the ClimbSafe application and catch errors if any
	  try {
		  climbSafe.addEquipment(name, weight, pricePerWeek);
	  }catch (RuntimeException e) {
		  error = e.getMessage();
	      if (error.startsWith("Unable to create equipment due to climbSafe.")) {
	        error = "The piece of equipment already exists";
	      }
	      throw new InvalidInputException(error);
	  }
}

  /**
   * 
   * @param oldName
   * @param newName
   * @param newWeight
   * @param newPricePerWeek
   * @throws InvalidInputException
   * 
   * updateEquipment controller method
   */
  public static void updateEquipment(String oldName, String newName, int newWeight,
      int newPricePerWeek) throws InvalidInputException {
	  
	  // Get the desired equipment in the ClimbSafe application
	  Equipment foundEquipment = findEquipment(oldName);
		if (foundEquipment == null) {
			throw new InvalidInputException("The piece of equipment does not exist");
		}
		
	  String error = "";
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  
	  //Check the constraints on the updated weight and price 
	  if (newWeight <= 0) {
		  error="The weight must be greater than 0. ";
	  }
	  if (newPricePerWeek < 0) {
		  error="The price per week cannot be lower than 0. ";
	  }
	  if (newName.equals("") || newName.equals(null)) {
			 error="The name must not be empty";
		 }
	 
	  //Checks if an equipment with the same name already exists
	  List<Equipment> equipments = climbSafe.getEquipment();
	  for (Equipment e : equipments) {
			if ((e.getName()).equals(foundEquipment.getName())) {
				throw new InvalidInputException("The piece of equipment already exists");
			}
		}
	  
	  
	 //update the old equipments' data to the new desired data.
	  
	 foundEquipment.setName(newName);
	 foundEquipment.setPricePerWeek(newPricePerWeek);
	 foundEquipment.setWeight(newWeight);
  }
  
  
/**
 * This method finds the old Equipment to change, using its name and the list of all 
 * the equipments in the current ClimbSafe application.
 * 
 * @author Enzo Benoit-Jeannin
 * @param oldName
 * @return null if no equipment with this name are in the ClimbSafe Application, 
 * otherwise it returns the equipment.
 */
private static Equipment findEquipment(String oldName) {
	 ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	 List<Equipment> myEquipments = climbSafe.getEquipment();
	 for(Equipment e : myEquipments) {
		 String name = e.getName();
		 if (name.equals(oldName)) {
			 return e;
		 }
	 }
	return null;
}
}
