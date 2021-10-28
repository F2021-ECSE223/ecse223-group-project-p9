package ca.mcgill.ecse.climbsafe.controller;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.model.*;

import java.util.List;

public class ClimbSafeFeatureSet2Controller {
	static String error = "";

	/**
	 * register members
	 * 
	 * @author Joey Koay
	 * @param email
	 * @param password
	 * @param name
	 * @param emergencyContact
	 * @param nrWeeks
	 * @param guideRequired
	 * @param hotelRequired
	 * @param itemNames
	 * @param itemQuantities
	 * @throws InvalidInputException
	 */
  public static void registerMember(String email, String password, String name,
      String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
      List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException {
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

	  List<Member> memberList = climbSafe.getMembers();
	  List<Guide> guideList = climbSafe.getGuides();
	  error = "";
	  
	  if(email.contains(" ")) {
		  error = "The email must not contain any spaces";
	  }
	  
	  if(!validEmail(email)) {
		  error = "Invalid email";
	  }
	  
	  if(!validPassword(password)) {
		  error = "The password cannot be empty";
	  }
	  
	  if(!validName(name)) {
		  error = "The name cannot be empty";
	  }
	  
	  if(!validEmergencyContact(emergencyContact)) {
		  error = "The emergency contact cannot be empty";
	  }
	  
	  if(!validNrWeeks(nrWeeks, climbSafe)) {
		  error = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
	  }
	  
	  if(!validItems(itemNames, climbSafe)) {
		  error = "Requested item not found";
	  }
	  
	  if(validMember(memberList, email) != -1) {
		  error = "A member with this email already exists";
	  }
	  
	  if(email.equals("admin@nmc.nt")) {
		  error = "The email entered is not allowed for members";
	  }
	  
	  if(guideExists(guideList, email)) {
		  error = "A guide with this email already exists";
	  }
	  
	  if(error.length() != 0) {
		  throw new InvalidInputException(error.trim());
	  }
	  
	  try {
		  Member member = climbSafe.addMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired);
		  for (int i=0; i<itemNames.size(); i++) {
			 climbSafe.addBookedItem(itemQuantities.get(i), member, BookableItem.getWithName(itemNames.get(i))); 
		  }
	  }catch(RuntimeException e){
		  throw new InvalidInputException(e.getMessage());
	  }
	  
  }

  	/**
	 * update members
	 * 
	 * @author Joey Koay
	 * @param email
	 * @param newPassword
	 * @param newName
	 * @param newEmergencyContact
	 * @param newNrWeeks
	 * @param newGuideRequired
	 * @param newHotelRequired
	 * @param newItemNames
	 * @param newItemQuantities
	 * @throws InvalidInputException
	 */
  public static void updateMember(String email, String newPassword, String newName,
      String newEmergencyContact, int newNrWeeks, boolean newGuideRequired,
      boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities) throws InvalidInputException {
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  List<Member> memberList = climbSafe.getMembers();
	  validEmail(email);
	  error = "";

	  if(!validPassword(newPassword)) {
		  error = "The password cannot be empty";
	  }
	  
	  if(!validName(newName)) {
		  error = "The name cannot be empty";
	  }
	  
	  if(!validEmergencyContact(newEmergencyContact)) {
		  error = "The emergency contact cannot be empty";
	  }
	  
	  if(!validNrWeeks(newNrWeeks, climbSafe)) {
		  error = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
	  }
	  
	  if(!validItems(newItemNames, climbSafe)) {
		  error = "Requested item not found";
	  }
	  
	  if(validMember(memberList, email) == -1) {
		  error = "Member not found";
	  }
	  
	  if(error.length() != 0) {
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
		  
	  }catch(RuntimeException e){
		  throw new InvalidInputException(e.getMessage());
	  }
	  
  }
  
  	/**
	 * checking if the email is valid or not
	 * 
	 * @author Joey Koay
	 * @param email
	 * @return whether the email is valid
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
	 * checking if the password is valid or not
	 * 
	 * @author Joey Koay
	 * @param password
	 * @return whether the password is valid
	 */
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
  
  /**
	 * checking if the name is valid or not
	 * 
	 * @author Joey Koay
	 * @param name
	 * @return whether the name is valid
	 */
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
  
  /**
	 * checking if the emergency contact is valid or not
	 * 
	 * @author Joey Koay
	 * @param emergencyContact
	 * @return whether the emergency contact is valid
	 */
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
  
  /**
	 * checking if the number of weeks is valid or not
	 * 
	 * @author Joey Koay
	 * @param nrWeeks - number of weeks
	 * @param climbSafe - the application
	 * @return whether the number of weeks is valid
	 */
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
  
  /**
	 * checking if the member exists
	 * 
	 * @author Joey Koay
	 * @param memberList - a list of all of the existing members
	 * @param email
	 * @return the index of the member in the list, if it does not exist, its -1
	 */
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
  
  /**
	 * checking if the item exists
	 * 
	 * @author Joey Koay
	 * @param newItemsNames - a list of all of the itemNames
	 * @param climbSafe - the application
	 * @return whether the item exists or not
	 */
  private static boolean validItems(List<String> itemNames, ClimbSafe climbSafe) {
	  boolean validItems = true;
	  for(int i=0; i<itemNames.size(); i++) {
		  if(BookableItem.getWithName(itemNames.get(i)) == null){
			  validItems = false;
			  break;
		  }
	  }
	  return validItems;
  }
  
  /**
	 * checking if the guide exists
	 * 
	 * @author Joey Koay
	 * @param guideList - a list of all of the guides
	 * @param email
	 * @return whether the guide exists or not
	 */
  private static boolean guideExists(List<Guide> guideList, String email) {
	  boolean guideExists = false;
	  for(int i=0; i<guideList.size(); i++) {
		  if(guideList.get(i).getEmail().equals(email)) {
			  guideExists = true;
			  break;
		  }
	  }
	  return guideExists;
  }
  
  

}
