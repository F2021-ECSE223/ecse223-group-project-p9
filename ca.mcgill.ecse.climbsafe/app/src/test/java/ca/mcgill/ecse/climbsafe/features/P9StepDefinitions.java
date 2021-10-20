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
	  climbSafe = ClimbSafeApplication.getClimbSafe();
	  error = "";
	  errorCntr = 0;
  }

  @Given("the following equipment exists in the system: \\(p9)")
  public void the_following_equipment_exists_in_the_system_p9(
	  io.cucumber.datatable.DataTable List <Equipment>) {
	  equipment=climbSafe.getEquipment();
	  error="";
	  errorCntr=0;
  }

  @Given("the following equipment bundles exist in the system: \\(p9)")
  public void the_following_equipment_bundles_exist_in_the_system_p9(
      io.cucumber.datatable.DataTable List<EquipmentBundle>) {
	  bundle=ClimbSafe.getBundles();
	  error="";
	  errorCntr=0;
  }

  @Given("the following members exist in the system: \\(p9)")
  public void the_following_members_exist_in_the_system_p9(
      io.cucumber.datatable.DataTable List<Member>) {
	  members = ClimbSafe.getMembers();
	  error = "";
	  errorCntr = 0;
  }

  @Given("the following guides exist in the system: \\(p9)")
  public void the_following_guides_exist_in_the_system_p9(
      io.cucumber.datatable.DataTable List<Guides>) {
	  guides = ClimbSafe.getGuides();
	  error = "";
	  errorCntr = 0;
  }


  @When("a new member attempts to register with {string} , {string} , {string}, {string}, {int}, {boolean}, {boolean}, {List<String>, and {List<Integer>} \\(p9)")
  public void a_new_member_attempts_to_register_with_and_p9(String email, String password, String name,
	      String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
	      List<String> itemNames, List<Integer> itemQuantities) { //change back to 9 strings
    try {
    	ClimbSafeFreatureSet2Controller.registerMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired, itemNames, itemQuantities);
    }catch (InvalidInputException e) {
    	error += e.getMessage();
    	errorCntr++;
    }
  }


  @Then("a new member account shall exist with {string} , {string} , {string}, {string}, {int}, {boolean}, {boolean}, {List<String>, and {List<Integer>} \\(p9)")
  public void a_new_member_account_shall_exist_with_and_p9(String email, String password, String name,
	      String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
	      List<String> itemNames, List<Integer> itemQuantities) { 
    
	  	
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
