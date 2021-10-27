package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;


public class ClimbSafeFeatureSet3Controller {
	static String error = "";

  public static void registerGuide(String email, String password, String name,
      String emergencyContact) throws InvalidInputException {
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  List<Guide> guideList = climbSafe.getGuide();
	  
	  validEmail(email);
	  error = "";
	  
	  if(email.equals("admin@nmc.nt")) {
		  error = "The email entered is not allowed for guides";
	  }
	  
	  if(guideExists(guideList, email)) {
		  error = "A guide with this email already exists";
	  }
	  
	  if(!validPassword(password)) {
		  error = "The password must not be empty";
	  }
	  
	  if(!validName(name)) {
		  error = "The name must not be empty";
	  }
	  
	  if(!validEmergencyContact(emergencyContact)) {
		  error = "The emergence contact must not be empty";
	  }
	  
	  if(error.length() != 0) {
		  throw new InvalidInputException(error.trim());
	  }
	  
	  try {
		  Guide guide = climbSafe.addGuide(email, password, name, emergencyContact);
		  
	  } catch(RuntimeException r){
		  throw new InvalidInputException(r.getMessage());
	  }
	  
  }

	  
	  
	  
	  
	  
	  
  public static void updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) throws InvalidInputException {
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  List<Guide> guideList = climbSafe.getGuide();
	  
	  validEmail(email);
	  error = "";
	  
	  if(!validPassword(newPassword)) {
		  error = "The password must not be empty";
	  }
	  
	  if(!validName(newName)) {
		  error = "The name must not be empty";
	  }
	  
	  if(!validEmergencyContact(newEmergencyContact)) {
		  error = "The emergency contact must not be empty";
	  }
	  
	  if(validGuide(guideList, email) == -1) {
		  error = "Guide not found";
	  }
	  
	  if(error.length() != 0) {
		  throw new InvalidInputException(error.trim());
	  }
	  
	  try {
		  Guide guide = climbSafe.getGuide(validMember(guideList, email));
		  Guide.setPassword(newPassword);
		  Guide.setName(newName);
		  Guide.setEmergencyContact(newEmergencyContact);
		  
		  
	climbSafe.addGuide(email, newPassword, newName, newEmergencyContact);
		  
	  } catch(RuntimeException r){
		  throw new InvalidInputException(r.getMessage());
	  }
	  
	  
	}
  
  
  private static boolean validEmail(String email) {
	  boolean validEmail = true;
	  if(!(email.indexOf("@") > 0)) {
		  validEmail = false;
	  }
	  if(!(email.indexOf("@") == email.lastIndexOf("@"))) {
		  validEmail = false;
	  }
	  if(!(email.indexOf("@") < email.lastIndexOf(".") - 1)) {
		  validEmail = false;
	  }
	  if(!(email.lastIndexOf(".") < email.length() - 1)) {
		  validEmail = false;
	  }
	  return validEmail;
  }
  
  
  private static boolean validPassword(String password) {
	  boolean validPassword = true;
	  if(password.equals("")) {
		  validPassword = false;
	  }
	  if(password.equals(null)) {
		  validPassword = false;
	  }
	  return validPassword;
  }
  
  private static boolean validName(String name) {
	  boolean validName = true;
	  if(name.equals("")) {
		  validName = false;
	  }
	  if(name.equals(null)) {
		  validName = false;
	  }
	  return validName;
  }
  
  
  private static boolean validEmergencyContact(String emergencyContact) {
	  boolean validEmergencyContact = true;
	  if(emergencyContact.equals("")) {
		  validEmergencyContact = false;
	  }
	  if(emergencyContact.equals(null)) {
		  validEmergencyContact = false;
	  }
	  return validEmergencyContact;
  }
  
  private static int validGuide(List<Guide> guideList, String email) {
	  int validMember = -1;
	  for(int i=0; i<guideList.size(); i++) {
		  if(guideList.get(i).getEmail().equals(email)) {
			  validMember = i;
			  break;
		  }
	  }
	  return validMember;
  }
  
  
  
  
  
  

}