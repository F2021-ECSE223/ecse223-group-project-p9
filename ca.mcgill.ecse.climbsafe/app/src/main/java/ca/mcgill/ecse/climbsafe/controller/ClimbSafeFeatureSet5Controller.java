package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;

public class ClimbSafeFeatureSet5Controller {
	
/**
 * adding equipment bundles
 * @author Joey Koay & SeJong Yoo
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
	  List<EquipmentBundle> bundles = climbSafe.getBundles();
	  for (EquipmentBundle b : bundles) {
		  if (b.getName().equals(name)) {
			  error = "The equipment bundle is already exist";
		  }
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
   * updating equipment bundles
   * @author Joey Koay & Kara Best & Victor Micha & SeJong Yoo
   * @param oldName
   * @param newName
   * @param newDiscount
   * @param newEquipmentNames
   * @param newEquipmentQuantities
   * @throws InvalidInputException
   */

  public static void updateEquipmentBundle(String oldName, String newName, int newDiscount, List<String> newEquipmentNames, List<Integer> newEquipmentQuantities) throws InvalidInputException {
	  String error = "";
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  List<Equipment> equipmentList = climbSafe.getEquipment();
	  List<EquipmentBundle> bundleList2 = climbSafe.getBundles();
	  
	  if(newEquipmentNames.size() == 1) {
		  error = "Equipment bundle must contain at least two distinct types of equipment";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  for(int i=0; i<newEquipmentNames.size(); i++) {
		  if(validEquipment(equipmentList, newEquipmentNames.get(i))==-1) {
			  error = "Equipment " + newEquipmentNames.get(i) + " does not exist";
			  throw new InvalidInputException(error.trim());
		  }
	  }
	  

	  List<String> uniqueBundleList = new ArrayList<String>();
	  for(int i=0; i<newEquipmentNames.size(); i++) {
		  if(!uniqueBundleList.contains(newEquipmentNames.get(i))) {
			  uniqueBundleList.add(newEquipmentNames.get(i));
		  }
	  }
	  
	  if(uniqueBundleList.size() < 2) {
		  error = "Equipment bundle must contain at least two distinct types of equipment";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  for(int i=0; i<newEquipmentQuantities.size(); i++) {
		  if(newEquipmentQuantities.get(i) < 1) {
			  error = "Each bundle item must have quantity greater than or equal to 1";
			  throw new InvalidInputException(error.trim());
		  }
	  }
	  
	  if(newDiscount > 100) {
		  error = "Discount must be no more than 100 ";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(newDiscount < 0) {
		  error = "Discount must be at least 0";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  
	  if(newName == "" || newName == null) {
		  error = "Equipment bundle name cannot be empty ";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(!newName.equals(oldName)) {
		  for(int i=0; i< bundleList2.size(); i++) {
			  if(bundleList2.get(i).getName().equals(newName)) {
				  error = "A bookable item called " + newName + " already exists";
				  throw new InvalidInputException(error.trim()); 
			  }
		  }
	  }
	  
	  for(int i=0; i< equipmentList.size(); i++) {
		  if(equipmentList.get(i).getName().equals(newName)) {
			  error = "A bookable item called " + newName + " already exists";
			  throw new InvalidInputException(error.trim()); 
		  }
	  }
	  
	  for(int i=0; i< bundleList2.size(); i++) {
		  if(bundleList2.get(i).getName().equals(oldName)) {
			  break;
		  }else if(i==bundleList2.size()-1) {
			  error = "Equipment bundle " + oldName +" does not exist";
			  throw new InvalidInputException(error.trim()); 
		  }
	  }

	  try {
		  List<EquipmentBundle> allBundle = climbSafe.getBundles();
		  int myBundleIndex = -1;
		  
		  for(int i=0; i<allBundle.size(); i++) {
			  if(allBundle.get(i).getName().equals(oldName)) {
				  myBundleIndex = i;
				  break;
			  }
		  }
		  
		  EquipmentBundle myBundle = climbSafe.getBundle(myBundleIndex);
		  myBundle.setDiscount(newDiscount);
		  myBundle.setName(newName);
		  
		  for(int i=0; i<myBundle.getBundleItems().size(); i++) {
			  BundleItem b = myBundle.getBundleItem(i);
			  myBundle.removeBundleItem(b);
			  myBundle.delete();
		  }
		  EquipmentBundle bundle2 = climbSafe.addBundle(newName, newDiscount);
		  List<Equipment> listOfEquipments = climbSafe.getEquipment();
		  
		  
		  for(int i=0; i<newEquipmentNames.size(); i++) {
			  for(int x=0; x< listOfEquipments.size(); x++) {
				  if(listOfEquipments.get(x).getName().equals(newEquipmentNames.get(i))) {
					  bundle2.addBundleItem(newEquipmentQuantities.get(i), climbSafe, listOfEquipments.get(x));
					  break;
				  }
			  }
		  }
		  
		  for(int i=0; i<newEquipmentNames.size(); i++) {
			  List<Equipment> equipmentList2 = climbSafe.getEquipment();
			  for(int x=0; x<equipmentList2.size(); x++) {
				  if(equipmentList2.get(x).getName() == newEquipmentNames.get(i)) {
					  bundle2.addBundleItem(newEquipmentQuantities.get(i), climbSafe, equipmentList2.get(x));
					  break;
				  }
			  }
			  
		  }
		  
	  } catch (RuntimeException e) {
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
