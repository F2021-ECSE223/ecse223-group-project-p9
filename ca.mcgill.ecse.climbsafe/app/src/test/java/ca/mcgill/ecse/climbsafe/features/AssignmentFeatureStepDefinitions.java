package ca.mcgill.ecse.climbsafe.features;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import ca.mcgill.ecse.climbsafe.model.*;

public class AssignmentFeatureStepDefinitions {
	  private ClimbSafe climbSafe;
	  private String error;
	  
	  
	  /**
	   * @param dataTable
	   * @author KaraBest & JoeyKoay & VictorMicha (from p9 step defs)
	   */

  @Given("the following ClimbSafe system exists:")
  public void the_following_climb_safe_system_exists(io.cucumber.datatable.DataTable dataTable) {
	  error = "";
	    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
	    climbSafe = ClimbSafeApplication.getClimbSafe();
	    for (Map<String, String> row : rows) {
	      Date startDate = java.sql.Date.valueOf(row.get("startDate"));
	      climbSafe.setStartDate(startDate);

	      int nrWeeks = Integer.parseInt(row.get("nrWeeks"));
	      climbSafe.setNrWeeks(nrWeeks);

	      int priceOfGuidePerWeek = Integer.parseInt(row.get("priceOfGuidePerWeek"));
	      climbSafe.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
	    }
  }

  @Given("the following pieces of equipment exist in the system:") //from p9 step
  public void the_following_pieces_of_equipment_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
	  List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
	    for (Map<String, String> r : rows) {
	      String name = r.get("name");
	      int weight = Integer.parseInt(r.get("weight"));
	      int pricePerWeek = Integer.parseInt(r.get("pricePerWeek"));
	      new Equipment(name, weight, pricePerWeek, this.climbSafe);
	    }
  }

  @Given("the following equipment bundles exist in the system:") //from p9 step
  public void the_following_equipment_bundles_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
	  List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
	    for (Map<String, String> r : rows) {
	      String name = r.get("name");
	      int discount = Integer.parseInt(r.get("discount"));
	      EquipmentBundle bundle = new EquipmentBundle(name, discount, climbSafe);
	      List<String> items = Arrays.asList(r.get("items").split(","));
	      List<String> quantities = Arrays.asList(r.get("quantity").split(","));
	      for (int i = 0; i < items.size(); i++) {
	        new BundleItem(Integer.parseInt(quantities.get(i)), this.climbSafe, bundle,
	            (Equipment) Equipment.getWithName(items.get(i)));
	      }
	    }
  }

  @Given("the following guides exist in the system:") //from p9 step
  public void the_following_guides_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
	  List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
	    List<Guide> guides = climbSafe.getGuides();
	    List<String> emails = new ArrayList<String>();
	    for (Guide g : guides)
	      emails.add(g.getEmail());

	    for (int i = 0; i < rows.size(); i++) {
	      String email = rows.get(i).get("email");
	      new Guide(email, rows.get(i).get("password"), rows.get(i).get("name"),
	          rows.get(i).get("emergencyContact"), climbSafe);
	    }
  }

  @Given("the following members exist in the system:") //from p9
  public void the_following_members_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
	  List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

	    for (Map<String, String> r : rows) {
	      String email = r.get("email");
	      String password = r.get("password");
	      String name = r.get("name");
	      String emergencyContact = r.get("emergencyContact");
	      int nrWeeks = Integer.parseInt(r.get("nrWeeks"));
	      List<String> bookableItems = Arrays.asList(r.get("bookableItems").split(","));
	      List<String> requestedQuantities = Arrays.asList(r.get("requestedQuantities").split(","));
	      boolean guideRequired = Boolean.parseBoolean(r.get("guideRequired"));
	      boolean hotelRequired = Boolean.parseBoolean(r.get("hotelRequired"));
	      Member m = new Member(email, password, name, emergencyContact, nrWeeks, guideRequired,
	          hotelRequired, this.climbSafe);

	      for (int i = 0; i < bookableItems.size(); i++) {
	        BookableItem bookableItem = BookableItem.getWithName(bookableItems.get(i));
	        m.addBookedItem(Integer.parseInt(requestedQuantities.get(i)), this.climbSafe, bookableItem);
	      }
	    }
  }

  @When("the administrator attempts to initiate the assignment process")
  public void the_administrator_attempts_to_initiate_the_assignment_process() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the following assignments shall exist in the system:")
  public void the_following_assignments_shall_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    
  }

  @Then("the assignment for {string} shall be marked as {string}")
  public void the_assignment_for_shall_be_marked_as(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the number of assignments in the system shall be {string}") //Kara
  public void the_number_of_assignments_in_the_system_shall_be(String string) {
    assertEquals(Integer.parseInt(string), climbSafe.numberOfAssignments());
    
  }

  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String string) {
	    assertTrue(error.contains(string));

  }

  @Given("the following assignments exist in the system:") //Kara
  public void the_following_assignments_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
	  List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

	    for (Map<String, String> r : rows) {
	      String memberEmail = r.get("memberEmail");
	      String guideEmail = r.get("guideEmail");
	      
	      int startWeek = Integer.parseInt(r.get("startWeek"));
	      int endWeek = Integer.parseInt(r.get("endWeek"));
	      List <Member> members = climbSafe.getMembers();
	      boolean found = false;
	      int i =0;
	      while(!found) {
	    	  if(memberEmail.equals(members.get(i).getEmail())) {
	    		  found = true;
	    	  }else {
	    		  i++;
	    	  }
	      }
	      Assignment a = new Assignment(startWeek, endWeek, members.get(i), climbSafe);
	    }
  }

  @When("the administrator attempts to confirm payment for {string} using authorization code {string}")
  public void the_administrator_attempts_to_confirm_payment_for_using_authorization_code(
      String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the assignment for {string} shall record the authorization code {string}")
  public void the_assignment_for_shall_record_the_authorization_code(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the member account with the email {string} does not exist")
  public void the_member_account_with_the_email_does_not_exist(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("there are {string} members in the system")
  public void there_are_members_in_the_system(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the error {string} shall be raised")
  public void the_error_shall_be_raised(String string) {
	    assertTrue(error.contains(string));

  }

  @When("the administrator attempts to cancel the trip for {string}")
  public void the_administrator_attempts_to_cancel_the_trip_for(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Given("the member with {string} has paid for their trip")
  public void the_member_with_has_paid_for_their_trip(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the member with email address {string} shall receive a refund of {string} percent")
  public void the_member_with_email_address_shall_receive_a_refund_of_percent(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Given("the member with {string} has started their trip")
  public void the_member_with_has_started_their_trip(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the administrator attempts to finish the trip for the member with email {string}")
  public void the_administrator_attempts_to_finish_the_trip_for_the_member_with_email(
      String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Given("the member with {string} is banned")
  public void the_member_with_is_banned(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the member with email {string} shall be {string}")
  public void the_member_with_email_shall_be(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the administrator attempts to start the trips for week {string}")
  public void the_administrator_attempts_to_start_the_trips_for_week(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Given("the member with {string} has cancelled their trip")
  public void the_member_with_has_cancelled_their_trip(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Given("the member with {string} has finished their trip")
  public void the_member_with_has_finished_their_trip(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
}
