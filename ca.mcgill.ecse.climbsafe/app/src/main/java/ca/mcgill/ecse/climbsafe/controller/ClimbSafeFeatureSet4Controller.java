package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

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
	  List<Equipment> myEquipments = climbSafe.getEquipment();
	  
	  //checks the constraints
	  if (weight <= 0) {
		  error="The weight must be greater than 0. ";
	  }
	  if (pricePerWeek < 0) {
		  error="The price per week must be greater than or equal to 0 ";
	  }
	  if (name.equals("") || name == null) {
		 error="The name must not be empty";
	  }
	  
	  if(validEquipment(myEquipments, name) != -1) {
		  error = "The piece of equipment already exists";
	  }
	  
	  //Checks if an EquipmentBundle with the same name already exists
	  List<EquipmentBundle> bundles = climbSafe.getBundles();
	  for (EquipmentBundle b : bundles) {
		  if(b.getName().equals(name)) {
			  error="The equipment bundle already exists";
		  }
	  }
	  
	  if(error.length() != 0) {
		  throw new InvalidInputException(error.trim());
	  }
	 
	  //try adding the equipment to the ClimbSafe application and catch errors if any
	  try {
		  climbSafe.addEquipment(name, weight, pricePerWeek);
		  ClimbSafePersistence.save();
	  }catch (RuntimeException e) {
	      throw new InvalidInputException(e.getMessage());
	  }
}

  /**
   * @author Enzo Benoit-Jeannin
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
	  
	  String error = "";
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  List<Equipment> myEquipments = climbSafe.getEquipment();
	  
	  // Check if the equipment we want to update exists in the ClimbSafe application
	  if ((validEquipment(myEquipments, oldName))==-1) {
		error="The piece of equipment does not exist";
	  }
	  
	  //Check the constraints on the updated weight and price 
	  if (newWeight <= 0) {
		  error="The weight must be greater than 0";
	  }
	  if (newPricePerWeek < 0) {
		  error="The price per week must be greater than or equal to 0";
	  }
	  
	  if (newName.equals("") || newName == null) {
			 error="The name must not be empty";
		 }
	  
	  //Checks if an equipment with the same name already exists
	  if(!oldName.equals(newName)) {
		  List<Equipment> equipments = climbSafe.getEquipment();
		  for (Equipment e : equipments) {
				if ((e.getName()).equals(newName)) {
					error="The piece of equipment already exists";
				}
			}
	  }
	  
	  //Checks if an EquipmentBundle with the same name already exists
	  List<EquipmentBundle> bundles = climbSafe.getBundles();
	  for (EquipmentBundle b : bundles) {
		  if(b.getName().equals(newName)) {
			  error="An equipment bundle with the same name already exists";
		  }
	  }
	  if(error.length() != 0) {
		  throw new InvalidInputException(error.trim());
	  }
	  
	 //update the old equipment's data to the new desired data.
	 try {
		 Equipment myEquipment = climbSafe.getEquipment(validEquipment(myEquipments, oldName));
		 myEquipment.setName(newName);
		 myEquipment.setPricePerWeek(newPricePerWeek);
		 myEquipment.setWeight(newWeight);
		 ClimbSafePersistence.save();
	 }catch(RuntimeException e){
		  throw new InvalidInputException(e.getMessage());
	  }
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
private static int validEquipment (List <Equipment> e, String oldName) {
	 for(int i=0; i<e.size();i++) {
		 if ((e.get(i).getName()).equals(oldName)) {
			 return i;
		 }
	 }
	return -1;
}
}
