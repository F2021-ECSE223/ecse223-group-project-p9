package ca.mcgill.ecse.climbsafe.controller;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;


public class ClimbSafeFeatureSet3Controller {
	static String error = "";
	
/**
 * @author danielchang
 * @param email
 * @param password
 * @param name
 * @param emergencyContact
 * @throws InvalidInputException
 */
  public static void registerGuide(String email, String password, String name,
      String emergencyContact) throws InvalidInputException {
	  
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  List<Guide> guideList = climbSafe.getGuides();	
	  List<ca.mcgill.ecse.climbsafe.model.Member> memberList2 = climbSafe.getMembers();
	  
	  error = "";
	  
	  if(email.contains(" ")) {
		  error = "Email must not contain any spaces ";
	  }
	  
	  if(email.equals("admin@nmc.nt")) {
		  error = "Email cannot be admin@nmc.nt";
	  }
	  
	  if(!validEmail(email)) {
		  error = "Invalid email";
	  }
	  
	  if(email == "" || email == null) {
		  error = "Email cannot be empty ";
	  }
	  
	  if(guides(guideList, email)) {
		  error = "Email already linked to a guide account";
	  }
	  
	  if(members(memberList2, email)) {
		  error = "Email already linked to a member account";
	  }
	  
	  
	  if(!validPassword(password)) {
		  error = "Password cannot be empty ";
	  }
	  
	  if(!validName(name)) {
		  error = "Name cannot be empty";
	  }
	  
	  if(!validEmergencyContact(emergencyContact)) {
		  error = "Emergency contact cannot be empty";
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

	  
	  
	  
/**
 * @author danielchang
 * @param email
 * @param newPassword
 * @param newName
 * @param newEmergencyContact
 * @throws InvalidInputException
 */
	  
  public static void updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) throws InvalidInputException {
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  List<Guide> guideList = climbSafe.getGuides();
	  
	  validEmail(email);
	  error = "";
	  
	  if(!validPassword(newPassword)) {
		  error = "Password cannot be empty";
	  }
	  
	  if(!validName(newName)) {
		  error = "Name cannot be empty";
	  }
	  
	  if(!validEmergencyContact(newEmergencyContact)) {
		  error = "Emergency contact cannot be empty";
	  }
	  
	  if(validGuide(guideList, email) == -1) {
		  error = "Guide not found";
	  }
	  
	  if(error.length() != 0) {
		  throw new InvalidInputException(error.trim());
	  }
	  
	  try {
		  Guide guide = climbSafe.getGuide(validGuide(guideList, email));
		  guide.setPassword(newPassword);
		  guide.setName(newName);
		  guide.setEmergencyContact(newEmergencyContact);
	  } catch(RuntimeException r){
		  throw new InvalidInputException(r.getMessage());
	  }
	}
  
  /**
   * @author danielchang
   * @param email
   * @return
   *  whether the email is valid or not
   */
  
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
  
  /**
   * @author danielchang
   * @param password
   * @return
   *  whether the password is valid or not
   */
  
  private static boolean validPassword(String password) {
	  boolean validPassword = true;
	  
	  if(password.equals("") || password.equals(null)) {
		  validPassword = false;
	  }
	  
	  return validPassword;
  }
  
  /**
   * @author danielchang
   * @param name
   * @return
   *  whether the name is valid or not
   */
  
  private static boolean validName(String name) {
	  boolean validName = true;
	  
	  if(name.equals("") || name.equals(null)) {
		  validName = false;
	  }
	  
	  return validName;
  }
  
  /**
   * @author danielchang
   * @param emergencyContact
   * @return
   *  whether the emergencyContact is valid or not
   */
  
  private static boolean validEmergencyContact(String emergencyContact) {
	  boolean validEmergencyContact = true;
	  
	  if(emergencyContact.equals("") || emergencyContact.equals(null)) {
		  validEmergencyContact = false;
	  }
	  
	  return validEmergencyContact;
  }
  /**
   * @author danielchang
   * @param guideList
   * @param email
   * @return
   *  
   */
  
  private static int validGuide(List<Guide> guideList, String email) {
	  int validGuide = 0;
	  for(int i=0; i<guideList.size(); i++) {
		  
		  if(guideList.get(i).getEmail().equals(email)) {
			  validGuide = i;
			  break;
		  }
	  }
	  return validGuide;
  }
  
  
  /**
   * @author danielchang
   * @param guideList
   * @param email
   * @return
   */
  
  private static boolean guides(List<Guide> guideList, String email) {
	  boolean guides = false;
	  
	  for(int i=0; i<guideList.size(); i++) {
		  
		  if(guideList.get(i).getEmail().equals(email)) {
			  guides = true;
			  break;
		  }
	  }
	  return guides;
  }
  
  /**
   * @author danielchang
   * @param memberList
   * @param email
   * @return
   */
  
  private static boolean members(List<ca.mcgill.ecse.climbsafe.model.Member> memberList, String email) {
	  boolean members = false;
	  
	  for(int i=0; i<memberList.size(); i++) {
		  
		  if(memberList.get(i).getEmail().equals(email)) {
			  members = true;
			  break;
		  }
	  }
	  return members;
  }
}
