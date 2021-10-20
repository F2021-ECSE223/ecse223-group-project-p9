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
	private String error;
	private List <Equipment> equipment; 
	private List <EquipmentBundle> equipmentBundles;
	private int errorCntr;
	private List<Member> members;
	private List<Guide> guides;

	
  @Given("the following ClimbSafe system exists: \\(p9)") 
  public void the_following_climb_safe_system_exists_p9(io.cucumber.datatable.DataTable dataTable) {
	  
  }
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
 * @author Kara Best
 */


  
  @Given("the following equipment exists in the system: \\(p9)")
  public void the_following_equipment_exists_in_the_system_p9(io.cucumber.datatable.DataTable dataTable) {
	 
	  List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
      for (Map<String, String> r : rows) {
          String name = r.get("name");
          int weight = Integer.parseInt(r.get("weight"));
          int pricePerWeek = Integer.parseInt(r.get("pricePerWeek"))
          Equipment equipment = new Equipment(name, weight, pricePerWeek, this.climbSafe);
          climbSafe.addEquipment(equipment);
  }

  }  
  @Given("the following equipment bundles exist in the system: \\(p9)")
  public void the_following_equipment_bundles_exist_in_the_system_p9(
      io.cucumber.datatable.DataTable dataTable) {
	  bundle=ClimbSafe.getBundles();
	  error="";
	  errorCntr=0;
  }
  
  @Given("the following members exist in the system: \\(p9)")
  public void the_following_members_exist_in_the_system_p9(

      io.cucumber.datatable.DataTable List<Member>) {
	  
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
              BookableItem bookableItem = BookableItem.getWithName(bookableItems.get(i));;
              m.addBookedItem(requestedQuantities.get(i), this.climbSafe, bookableItem); 
          }
          climbSafe.addMember(m);
      }
  }

  }

  @Given("the following guides exist in the system: \\(p9)")
  public void the_following_guides_exist_in_the_system_p9(
      io.cucumber.datatable.DataTable dataTable) {
	  guides = ClimbSafe.getGuides();
	  error = "";
	  errorCntr = 0;
  }


  @When("a new member attempts to register with {string} , {string} , {string}, {string}, {string}, {string}, {string}, {string}, and {string} \\(p9)") //Kara
  public void a_new_member_attempts_to_register_with_and_p9(String email, String password, String name,
	      String emergencyContact, String nrWeeks, String guideRequired, String hotelRequired,
	      String itemNames, String itemQuantities) { //change back to 9 strings
    try {
    	ClimbSafeFreatureSet2Controller.registerMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired, itemNames, itemQuantities);
    }catch (InvalidInputException e) {
    	error += e.getMessage();
    	errorCntr++;
    }
  }


  @Then("a new member account shall exist with {string} , {string} , {string}, {string}, {string}, {string}, {string}, {string}, and {string} \\(p9)")
  public void a_new_member_account_shall_exist_with_and_p9(String email, String password, String name,
	      String emergencyContact, String nrWeeks, String guideRequired, String hotelRequired,
	      String itemNames, String itemQuantities) { 
    
	  	
		 //assertEquals(member.nrWeeks, nrWeeks);
		 //assertEquals(member.item)
		 
    
  } //then uses model methods to check if controller features work properly

  /**
   * @param int1: the number of members
   * @author Victor Micha
   */
  @Then("there are {int} members in the system. \\(p9)")
  public void there_are_members_in_the_system_p9(Integer int1) {
	  assertEquals(int1, ClimbSafe.numberOfMembers());
  }

  /**
   * @param string: the error to be expected 
   * @author Joey Koay
   */
  @Then("the following {string} shall be raised. \\(p9)")
  public void the_following_shall_be_raised_p9(String string) {
	  assertTrue(error.contains(string));
  }

  @Then("there is no member account for {string} \\(p9)")
  public void there_is_no_member_account_for_p9(String string) {
//not sure if it is correct
	 List<Member> members =  ClimbSafe.getMembers();
	 boolean done = false;
	 int i=0;
	 while (!done) {
		 if (members.get(i).getName().equals(string)) {
			 assertEquals(members.get(i).getName(), string);
			 done = true;
		 }
		 i++; //this is going to run an infinite loop if theres no member or produce an error once the index gets too high
	 }
  }
}
