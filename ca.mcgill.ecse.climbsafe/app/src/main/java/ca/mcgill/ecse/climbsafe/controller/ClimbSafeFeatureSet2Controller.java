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

	  List<Member> memberList = climbSafe.getMembers();
	  List<Guide> guideList = climbSafe.getGuides();
	  
	  
	  if(email.contains(" ")) {
		  error = "The email must not contain any spaces";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(!validEmail(email)) {
		  error = "Invalid email";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(!validEmail(email)) {
		  error = "Invalid email";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(!validPassword(password)) {
		  error = "The password cannot be empty";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(!validName(name)) {
		  error = "The name cannot be empty";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(!validEmergencyContact(emergencyContact)) {
		  error = "The emergency contact cannot be empty";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(!validNrWeeks(nrWeeks, climbSafe)) {
		  error = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(!validItems(itemNames, climbSafe)) {
		  error = "Requested item not found";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(validMember(memberList, email) != -1) {
		  error = "A member with this email already exists";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(email.equals("admin@nmc.nt")) {
		  error = "The email entered is not allowed for members";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(guideExists(guideList, email)) {
		  error = "A guide with this email already exists";
		  throw new InvalidInputException(error.trim());
	  }
	  try {
		  Member member = climbSafe.addMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired);
		  for (int i=0; i<itemNames.size(); i++) {
			 climbSafe.addBookedItem(itemQuantities.get(i), member, BookableItem.getWithName(itemNames.get(i))); 
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
		  error = "The password cannot be empty";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(!validName(newName)) {
		  error = "The name cannot be empty";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(!validEmergencyContact(newEmergencyContact)) {
		  error = "The emergence contact cannot be empty";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(!validNrWeeks(newNrWeeks, climbSafe)) {
		  error = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(!validItems(newItemNames, climbSafe)) {
		  error = "Requested item not found";
		  throw new InvalidInputException(error.trim());
	  }
	  
	  if(validMember(memberList, email) == -1) {
		  error = "Member not found";
		  throw new InvalidInputException(error.trim());
	  }
	  try {
		  Member member = climbSafe.getMember(validMember(memberList, email));
		  member.setPassword(newPassword);
		  member.setName(newName);
		  member.setEmergencyContact(newEmergencyContact);
		  member.setNrWeeks(newNrWeeks);
		  member.setGuideRequired(newGuideRequired);
		  member.setHotelRequired(newHotelRequired);
		  
		  
		  List<BookedItem> bookedItems = member.getBookedItems();
		  while(bookedItems.size() !=0) {
			  member.getBookedItems().get(0).delete();
		  }
		  for(int i=0; i<newItemNames.size(); i++) {
			  climbSafe.addBookedItem(newItemQuantities.get(i), member, BookableItem.getWithName(newItemNames.get(i))); 
		  }
		  
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
  
  private static int validMember(List<Member> memberList, String email) {
	  int validMember = -1;
	  for(int i=0; i<memberList.size(); i++) {
		  if(memberList.get(i).getEmail().equals(email)) {
			  validMember = i;
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
  
  private static boolean guideExists(List<Guide> guideList, String email) {
	  boolean validEmail = false;
	  for(int i=0; i<guideList.size(); i++) {
		  if(guideList.get(i).getEmail().equals(email)) {
			  validEmail = true;
			  break;
		  }
	  }
	  return validEmail;
  }
  
  

}
