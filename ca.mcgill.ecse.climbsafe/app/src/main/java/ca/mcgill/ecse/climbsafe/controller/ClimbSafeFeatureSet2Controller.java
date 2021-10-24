package ca.mcgill.ecse.climbsafe.controller;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.model.*;


import java.util.List;

public class ClimbSafeFeatureSet2Controller {
	static String error = "";

  public static void registerMember(String email, String password, String name,
      String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
      List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException {
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  try {
		  
//		  Since admin is already pre-registered, they will not be needing to "register" again, therefore, a member can never be "admin@nmc.nt"
		  boolean notAdmin = true;
		  if(email.equals("admin@nmc.nt")) {
			  notAdmin = false;
		  }
		  
		  boolean validEmail = validEmail(email);
		  boolean validPassword = validPassword(password);
		  boolean validName = validName(name);
		  boolean validEmergencyContact = validEmergencyContact(emergencyContact);
		  boolean validNrWeeks = validNrWeeks(nrWeeks, climbSafe);
		  
		  if(validEmail && validPassword && validName && validEmergencyContact && validNrWeeks && notAdmin) {
			  boolean validItems = true;
			  for(int i=0; i<itemNames.size(); i++) {
				  if(BookableItem.getWithName(itemNames.get(i)) == null){
					  validItems = false;
					  break;
				  }
			  }
			  if(validItems) {
				  Member member = climbSafe.addMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired);
				  for (int i=0; i<itemNames.size(); i++) {
					 climbSafe.addBookedItem(itemQuantities.get(i), member, BookableItem.getWithName(itemNames.get(i))); 
				  }
			  }
			  
		  }
		  
	  }catch(RuntimeException e){
		  System.out.println("---CONTROLLER---e.getMessage(): " + e.getMessage() );
		  throw new InvalidInputException(e.getMessage());
	  }
	  
  }

  public static void updateMember(String email, String newPassword, String newName,
      String newEmergencyContact, int newNrWeeks, boolean newGuideRequired,
      boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities) throws InvalidInputException {
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  error = "";
	  List<Member> memberList = climbSafe.getMembers();
	  validEmail(email);

	  if(!validPassword(newPassword)) {
		  System.out.println("Error trim becomes => " + error.trim());
		  error = "The password cannot be empty";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(!validName(newName)) {
		  System.out.println("Error trim becomes => " + error.trim());
		  error = "The name cannot be empty";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(!validEmergencyContact(newEmergencyContact)) {
		  System.out.println("Error trim becomes => " + error.trim());
		  error = "The emergence contact cannot be empty";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(!validNrWeeks(newNrWeeks, climbSafe)) {
		  System.out.println("Error trim becomes => " + error.trim());
		  error = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(!validItems(newItemNames, climbSafe)) {
		  System.out.println("Error trim becomes => " + error.trim());
		  error = "Requested item not found";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(!validMember(memberList, email)) {
		  System.out.println("Error trim becomes => " + error.trim());
		  error = "Member not found";
		  throw new InvalidInputException(error.trim());
	  }
	  try {
		  climbSafe.addMember(email, newPassword, newName, newEmergencyContact, newNrWeeks, newGuideRequired, newHotelRequired);
			  
		  
	  }catch(RuntimeException e){
		  error = e.getMessage();
		  if(error == "Cannot create due to duplicate email. See http://manual.umple.org?RE003ViolationofUniqueness.html") {
//			  error = 
		  }
		  throw new InvalidInputException(e.getMessage());
	  }
	  
  }
  
  private static boolean validEmail(String email) {
	  boolean validEmail = true;
	  
	  if(email.contains(" ")) {
		  validEmail = false;
	  }
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
  
  private static boolean validNrWeeks(int nrWeeks, ClimbSafe climbSafe) {
	  boolean validNrWeeks = true;
	  if(nrWeeks<=0) {
		  validNrWeeks = false;
	  }
	  if(nrWeeks > climbSafe.getNrWeeks()) {
		  validNrWeeks = false;
	  }
	  return validNrWeeks;
  }
  
  private static boolean validMember(List<Member> memberList, String email) {
	  boolean validMember = false;
	  for(int i=0; i<memberList.size(); i++) {
		  if(memberList.get(i).getEmail().equals(email)) {
			  validMember = true;
			  break;
		  }
	  }
	  return validMember;
  }
  
  private static boolean validItems(List<String> newItemNames, ClimbSafe climbSafe) {
	  boolean validItems = true;
	  for(int i=0; i<newItemNames.size(); i++) {
		  if(BookableItem.getWithName(newItemNames.get(i)) == null){
			  validItems = false;
			  break;
		  }
	  }
	  return validItems;
  }

}
