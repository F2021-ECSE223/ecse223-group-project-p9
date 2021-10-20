package ca.mcgill.ecse.climbsafe.features;

import io.cucumber.java.en.Given;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.*;
import java.sql.Date;
import java.util.*;
import static org.junit.Assert.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class P9StepDefinitions {
	private ClimbSafe climbSafe;


	/**
	 * @param dataTable 
	 * @author Kara Best
	 */
  @Given("the following ClimbSafe system exists: \\(p9)") 
  public void the_following_climb_safe_system_exists_p9(io.cucumber.datatable.DataTable dataTable) {
	  
	  List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
	  climbSafe = ClimbSafeApplication.getClimbSafe();
	  for(Map<String, String>row : rows) {
		  Date startDate = java.sql.Date.valueOf(row.get("startDate"));
		  climbSafe.setStartDate(startDate);
		  int nrWeeks = Integer.parseInt(row.get("nrWeeks"));
		  climbSafe.setNrWeeks(nrWeeks);
		  int priceOfGuidePerWeek = Integer.parseInt(row.get("priceOfGuidePerWeek"));
		  climbSafe.setPriceOfGuidePerWeek(priceOfGuidePerWeek); 
	  }  
  }


  /**
   * @param dataTable
   * @author Enzo Benoit-Jeannin
   */
  @Given("the following equipment exists in the system: \\(p9)")
  public void the_following_equipment_exists_in_the_system_p9(io.cucumber.datatable.DataTable dataTable) {
	 
	  List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
      for (Map<String, String> r : rows) {
          String name = r.get("name");
          int weight = Integer.parseInt(r.get("weight"));
          int pricePerWeek = Integer.parseInt(r.get("pricePerWeek"));
          Equipment equipment = new Equipment(name, weight, pricePerWeek, this.climbSafe);
      }
  } 
  /**
   * @param dataTable
   * @author Victor, Eunjun
   */
  @Given("the following equipment bundles exist in the system: \\(p9)")
  public void the_following_equipment_bundles_exist_in_the_system_p9(io.cucumber.datatable.DataTable dataTable) {
	  List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
	  for (Map<String, String> r : rows) {
		  EquipmentBundle bundle = new EquipmentBundle(r.get("name"), r.get("discount"), climbsafe);
		  List<BundleItems> items = Array.asList(r.get("items").split(","));
		  for (BundleItems x:items)
			  bundle.addBundleItem(x);
	  }
  }
  /**
   * @param dataTable
   * @author Enzo Benoit-Jeannin
   */
  @Given("the following members exist in the system: \\(p9)")
  public void the_following_members_exist_in_the_system_p9(io.cucumber.datatable.DataTable dataTable) {
	  
	  List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

      for (Map<String, String> r : rows) {
    	  String email = r.get("email");
    	  String password = r.get("password");
    	  String name = r.get("name");
          String emergencyContact = r.get("emergencyContact");
          int nrWeeks = Integer.parseInt(r.get("nrWeeks"));
          List<String> bookableItems = Arrays.asList(r.get("bookableItems").split(","));
          List<Integer> requestedQuantities = Arrays.asList(r.get("requestedQuantities").split(",")).stream().map(String::trim).mapToInt(Integer::parseInt).boxed().toList();
          boolean guideRequired = Boolean.parseBoolean(r.get("guideRequired"));
          boolean hotelRequired = Boolean.parseBoolean(r.get("hotelRequired"));
          Member m = new Member(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired, this.climbSafe);

          for (int i = 0; i < bookableItems.size(); i++) {
              BookableItem bookableItem = BookableItem.getWithName(bookableItems.get(i));
              m.addBookedItem(requestedQuantities.get(i), this.climbSafe, bookableItem); 
          }
      }
  }

/**
 * @param dataTable
 * @author Victor, Eunjun
 */
  @Given("the following guides exist in the system: \\(p9)")
  public void the_following_guides_exist_in_the_system_p9(io.cucumber.datatable.DataTable dataTable) {
	  List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
	  for (Map<String, String> r : rows) {
		  Guide g = new Guide(r.get("email"), r.get("password"), r.get("name"), r.get("emergencyContact"), climbsafe);
		  
	  }
  }


  /**
   * @param email
   * @param password
   * @param name
   * @param emergencyContact
   * @param nrWeeks
   * @param guideRequired
   * @param hotelRequired
   * @param xitemNames
   * @param xitemQuantities
   * @author Kara Best
   */
  
  @When("a new member attempts to register with {string} , {string} , {string}, {string}, {string}, {string}, {string}, {string}, and {string} \\(p9)") 
  public void a_new_member_attempts_to_register_with_and_p9(String email, String password, String name, String emergencyContact, String nrWeeks, String guideRequired, String hotelRequired, String xitemNames, String xitemQuantities) {
	List<String> itemNames = Arrays.asList(itemsNames.split(","));
	List<Integer> itemQuantities = newArrayList<>();
	for(String s : xitemQuantities.split(",")) itemQuantities.add(Integer.parseInt(s));
	
    try {
    	ClimbSafeFeatureSet2Controller.registerMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired, itemNames, itemQuantities);
    }catch (InvalidInputException e) {
    	String error += e.getMessage();
    }
  }

  /**
   * @param email
   * @param password
   * @param name
   * @param emergencyContact
   * @param nrWeeks
   * @param bookableItems
   * @param requestedQuantities
   * @param guideRequired
   * @param hotelRequired
   * @author Joey, Victor
   */
  @Then("a new member account shall exist with {string} , {string} , {string}, {string}, {string}, {string}, {string}, {string}, and {string} \\(p9)")
  public void a_new_member_account_shall_exist_with_and_p9(String email, String password, String name, String emergencyContact, String nrWeeks, String bookableItems, String requestedQuantities, String guideRequired, String hotelRequired) { 
	  Member member = (Member) Member.getWithEmail(email);
	  assetNotNull(member);
	  assertEquals(password, member.getPassword); //User.java
	  assertEquals(name, member.getName); //NameUser.java
	  assertEquals(emergencyContact, member.getEmergencyContact()); //NameUser.java
	  assertEquals(nrWeeks, member.getNrWeeks); //Member.java
	  assertEquals(guideRequired, member.getGuideRequired); //Member.java
	  assertEquals(hotelRequired, member.getHotelRequired); //Member.java
	  
	  List<String> argBookableItemsList = Array.asList(bookableItems.split(","));
	  List<BookedItem> compBookableItemsList = member.getBookedItems();
	  List<String> argRequestedQuantitiesList = Array.asList(requestedQuantities.split(","));
	  int i=0;
	  for (String s:argBookableItemsList) {
		  assertTrue(compBookableItemsList.contain(s));
		  BookableItem desiredItem = compBookableItemsList.getWithName(s);
		  assertEquals(desiredItem.getQuantity(),argRequestedQuantitiesList.get(i));
		  i++;
	  }
		 
    
  } 

  /**
   * @param int1
   * @author Victor Micha
   */
  @Then("there are {int} members in the system. \\(p9)")
  public void there_are_members_in_the_system_p9(Integer numMembers) {
	  assertEquals(numMembers, climbSafe.numberOfMembers());
  }

  /**
   * @param string
   * @author Joey Koay
   */
  @Then("the following {string} shall be raised. \\(p9)")
  public void the_following_shall_be_raised_p9(String errorString) {
	  assertTrue(error.contains(errorString));
  }

  /**
   * @param email 
   * @author Kara Best
   */
  @Then("there is no member account for {string} \\(p9)")
  public void there_is_no_member_account_for_p9(String email) {
	 List<Member> members = climbSafe.getMembers();
	 for(int i=0; i<numberOfMembers(); i++) {
		assertNotEquals(email, members.get(i).email); 
	 }
  }
  
}
