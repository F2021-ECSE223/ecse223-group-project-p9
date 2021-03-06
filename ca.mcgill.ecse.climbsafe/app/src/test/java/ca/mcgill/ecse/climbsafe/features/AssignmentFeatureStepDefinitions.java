package ca.mcgill.ecse.climbsafe.features;
import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.Assignment.TripStatus;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Hotel;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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

	/**
	 * @param dataTable
	 * @author EnzoBenoitJeannin & KaraBest & JoeyKoay & VictorMicha (from p9 step defs)
	 */
	@Given("the following pieces of equipment exist in the system:") //from p9 step
	public void the_following_pieces_of_equipment_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> r : rows) {
			String name = r.get("name");
			int weight = Integer.parseInt(r.get("weight"));
			int pricePerWeek = Integer.parseInt(r.get("pricePerWeek"));
			new Equipment(name, weight, pricePerWeek, this.climbSafe);
		}
	}

	/**
	 * @param dataTable
	 * @author EnzoBenoitJeannin & KaraBest & EunjunChang & JoeyKoay & VictorMicha & SejongYoon (from p9 step defs)
	 */
	@Given("the following equipment bundles exist in the system:")
	public void the_following_equipment_bundles_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
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

	/**
	 * @param dataTable
	 * @author EnzoBenoitJeannin & KaraBest & EunjunChang & JoeyKoay & VictorMicha & SejongYoon (from p9 step defs)
	 */
	@Given("the following guides exist in the system:")
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

	/**
	 * @param dataTable
	 * @author EnzoBenoitJeannin & KaraBest & JoeyKoay & VictorMicha (from p9 step defs)
	 */
	@Given("the following members exist in the system:") 
	public void the_following_members_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> r : rows) {
			String email = r.get("email");
			String password = r.get("password");
			String name = r.get("name");
			String emergencyContact = r.get("emergencyContact");
			int nrWeeks = Integer.parseInt(r.get("nrWeeks"));
			List<String> bookedItems = Arrays.asList(r.get("bookedItems").split(","));
			List<String> bookedItemQuantities = Arrays.asList(r.get("bookedItemQuantities").split(","));
			boolean guideRequired = Boolean.parseBoolean(r.get("guideRequired"));
			boolean hotelRequired = Boolean.parseBoolean(r.get("hotelRequired"));
			Member m = new Member(email, password, name, emergencyContact, nrWeeks, guideRequired,
					hotelRequired, this.climbSafe);

			for (int i = 0; i < bookedItems.size(); i++) {
				BookableItem bookableItem = BookableItem.getWithName(bookedItems.get(i));
				m.addBookedItem(Integer.parseInt(bookedItemQuantities.get(i)), this.climbSafe, bookableItem);
			}
		}
	}

	@When("the administrator attempts to initiate the assignment process")
	public void the_administrator_attempts_to_initiate_the_assignment_process() {
		try {
			AssignmentController.initiateAssignment();
		} catch (InvalidInputException e) {
			error += e.getMessage();
		}
	}

	/**
	 * 
	 * @param dataTable
	 * @author Enzo Benoit-Jeannin & Kara Best
	 */
	@Then("the following assignments shall exist in the system:")
	public void the_following_assignments_shall_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		var rows = dataTable.asMaps();
		for (var row : rows) {
			String memberEmail = row.get("memberEmail");
			String guideEmail = row.get("guideEmail");
			int startWeek = Integer.parseInt(row.get("startWeek"));
			int endWeek = Integer.parseInt(row.get("endWeek"));

			var member = (Member) Member.getWithEmail(memberEmail);
			var assignment = member.getAssignment();
				assertEquals(true, assignment.getStartWeek() == startWeek);
				assertEquals(true, assignment.getEndWeek() == endWeek);
				if(!(guideEmail==null)) {
					assertEquals(true, assignment.getGuide().getEmail().equals(guideEmail));
				}
			
		}
	}

	/**
	 * @param email
	 * @param string 2 - the trip status the assignment should be in
	 * @author Joey Koay
	 */
	@Then("the assignment for {string} shall be marked as {string}") 
	public void the_assignment_for_shall_be_marked_as(String email, String string2) {
		Member member = (Member) Member.getWithEmail(email);
		TripStatus argTripStatus = null;
		if(string2.equals("Started")) {
			argTripStatus = TripStatus.OnTrip;
		}else if(string2.equals("Finished")) {
			argTripStatus = TripStatus.Finished;
		}else if(string2.equals("Cancelled")) {
			argTripStatus = TripStatus.Cancelled;
		}else if(string2.equals("Assigned")) {
			argTripStatus = TripStatus.Assigned;
		}else if(string2.equals("Banned")) {
			argTripStatus = TripStatus.Banned;
		}
		
		if(string2.equals("Paid")){
			assertEquals(true, member.getAssignment().getPaid());
		}else {
			assertEquals(argTripStatus, member.getAssignment().getTripStatus());
		}
	}
	/**
	 * 
	 * @param string
	 * @author Kara Best
	 */
	@Then("the number of assignments in the system shall be {string}") 
	public void the_number_of_assignments_in_the_system_shall_be(String string) {
		assertEquals(Integer.parseInt(string), climbSafe.numberOfAssignments());

	}

	@Then("the system shall raise the error {string}")
	public void the_system_shall_raise_the_error(String string) {
		assertEquals(true, error.contains(string)); 
	}


	/**
	 * @param dataTable
	 * @author Enzo Benoit-Jeannin
	 */
	@Given("the following assignments exist in the system:") 
	public void the_following_assignments_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		var rows = dataTable.asMaps();
		for (var row : rows) {
			int startWeek = Integer.valueOf(row.get("startWeek"));
			int endWeek = Integer.valueOf(row.get("endWeek"));
			var assignmentMember = (Member) Member.getWithEmail(row.get("memberEmail"));
			var assignmentGuide = (Guide) User.getWithEmail(row.get("guideEmail"));
			var assignmentHotel = Hotel.getWithName(row.get("hotelName"));
			Assignment assignment = climbSafe.addAssignment(startWeek, endWeek, assignmentMember);
			assignment.setGuide(assignmentGuide);
			assignment.setHotel(assignmentHotel);
		}
	}
	/**
	 * @param string
	 * @param string2
	 * @author Kara Best
	 */
	@When("the administrator attempts to confirm payment for {string} using authorization code {string}") 
	public void the_administrator_attempts_to_confirm_payment_for_using_authorization_code(String string, String string2) {
		try {
			AssignmentController.payForTrip(string, string2);
		} catch (InvalidInputException e) {
			error += e.getMessage();
		}


	}
	/**
	 * @param string
	 * @param string2
	 * @author Enzo Benoit-Jeannin
	 */
	@Then("the assignment for {string} shall record the authorization code {string}")
	public void the_assignment_for_shall_record_the_authorization_code(String string, String string2) {
		Member member = (Member) Member.getWithEmail(string);	  
		assertEquals(member.getAssignment().getAuthorizationCode(),string2);
	}

	/**
	 * @param email
	 * @author Joey Koay
	 */
	@Then("the member account with the email {string} does not exist")
	public void the_member_account_with_the_email_does_not_exist(String email) {
		List<Member> memberList = climbSafe.getMembers();
		boolean validMember = false;
		for(int i=0; i<memberList.size(); i++) {
			if(memberList.get(i).getEmail().equals(email)) {
				validMember = true;
				break;
			}
		}
		assertEquals(false, validMember);
	}
	/**
	 * @param string
	 * @author Kara Best
	 */
	@Then("there are {string} members in the system")
	public void there_are_members_in_the_system(String string) {
		assertEquals(Integer.parseInt(string), climbSafe.numberOfMembers());
	}

	@Then("the error {string} shall be raised")
	public void the_error_shall_be_raised(String string) {
		assertEquals(true, error.contains(string));

	}

	@When("the administrator attempts to cancel the trip for {string}")
	public void the_administrator_attempts_to_cancel_the_trip_for(String string) {
		try {
			AssignmentController.cancelTrip(string);
		} catch (InvalidInputException e) {
			error+= e.getMessage();
		}
	}

	/**
	 * @param email
	 * @author Joey Koay
	 */
	@Given("the member with {string} has paid for their trip")
	public void the_member_with_has_paid_for_their_trip(String email) {
		List<Member> memberList = climbSafe.getMembers();
		int memberIndex = -1;
		for(int i=0; i<memberList.size(); i++) {
			if(memberList.get(i).getEmail().equals(email)) {
				memberIndex = i;
				break;
			}
		}
		climbSafe.getMember(memberIndex).getAssignment().setPaid(true);
	}

	@Then("the member with email address {string} shall receive a refund of {string} percent")
	public void the_member_with_email_address_shall_receive_a_refund_of_percent(String string, String string2) {
		List<Assignment> assignments = climbSafe.getAssignments();
		for(Assignment a: assignments) {
			if(a.getMember().getEmail().equals(string)) {
				assertEquals(Integer.parseInt(string2), a.getRefund());
			}
		}
	}
	/**
	 * @param string
	 * @author Kara Best
	 */
	@Given("the member with {string} has started their trip") 
	public void the_member_with_has_started_their_trip(String string) {
		List<Assignment> assignments = climbSafe.getAssignments();
		for(Assignment a: assignments) {
			if(a.getMember().getEmail().equals(string)) {
				a.setPaid(true);
				a.startTrip();
			}
		}
	}
	/**
	 * 
	 * @param string
	 * @author Kara Best
	 */
	@When("the administrator attempts to finish the trip for the member with email {string}") 
	public void the_administrator_attempts_to_finish_the_trip_for_the_member_with_email(
			String string) {
		try {
			AssignmentController.finishTrip(string);
		}catch(InvalidInputException e) {
			error+= e.getMessage();
		}
	}
	/**
	 * 
	 * @param string
	 * @author Kara Best
	 */
	@Given("the member with {string} is banned") 
	public void the_member_with_is_banned(String string) {
		List<Assignment> assignments = climbSafe.getAssignments();
		for(Assignment a: assignments) {
			if(a.getMember().getEmail().equals(string)) {
				a.setPaid(false);
				a.startTrip();
			}
		}
	}
	/**
	 * 
	 * @param string
	 * @param string2
	 * @author Enzo Benoit-Jeannin
	 */
	@Then("the member with email {string} shall be {string}")
	public void the_member_with_email_shall_be(String string, String string2) {
		Member member = (Member) Member.getWithEmail(string);	
		String banned = "";
		if (member.getAssignment().getTripStatus() == TripStatus.Banned) {
			banned = "Banned";
		}
		assertEquals(banned,string2);
	}
	/**
	 * 
	 * @param string
	 * @author Kara Best
	 */
	@When("the administrator attempts to start the trips for week {string}") 
	public void the_administrator_attempts_to_start_the_trips_for_week(String string) {
		try {
			AssignmentController.startTrips(Integer.parseInt(string));
		}catch(InvalidInputException e){
			error += e.getMessage();
		}
	}

	/**
	 * @param email
	 * @author Joey Koay
	 */
	@Given("the member with {string} has cancelled their trip")
	public void the_member_with_has_cancelled_their_trip(String email) {
		List<Member> memberList = climbSafe.getMembers();
		int memberIndex = -1;
		for(int i=0; i<memberList.size(); i++) {
			if(memberList.get(i).getEmail().equals(email)) {
				memberIndex = i;
				break;
			}
		}
		climbSafe.getMember(memberIndex).getAssignment().cancelTrip();
	}

	/**
	 * @param email
	 * @author Joey Koay
	 */
	@Given("the member with {string} has finished their trip")
	public void the_member_with_has_finished_their_trip(String email) {
		List<Member> memberList = climbSafe.getMembers();
		int memberIndex = -1;
		for(int i=0; i<memberList.size(); i++) {
			if(memberList.get(i).getEmail().equals(email)) {
				memberIndex = i;
				break;
			}
		}
		climbSafe.getMember(memberIndex).getAssignment().setPaid(true);
		climbSafe.getMember(memberIndex).getAssignment().startTrip();
		climbSafe.getMember(memberIndex).getAssignment().finishTrip();
	}
}
