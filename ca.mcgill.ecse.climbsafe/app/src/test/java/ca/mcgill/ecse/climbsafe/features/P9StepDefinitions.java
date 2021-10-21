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
import io.cucumber.java.After;

public class P9StepDefinitions {
	private ClimbSafe climbSafe;
	private String error;


	/**
	 * @param dataTable 
	 * @author KaraBest
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
	 * @author EnzoBenoitJeannin
	 */
	@Given("the following equipment exists in the system: \\(p9)")
	public void the_following_equipment_exists_in_the_system_p9(io.cucumber.datatable.DataTable dataTable) {

		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> r : rows) {
			String name = r.get("name");
			int weight = Integer.parseInt(r.get("weight"));
			int pricePerWeek = Integer.parseInt(r.get("pricePerWeek"));
			List<Equipment> equipments = climbSafe.getEquipment();
			List<String> names = new ArrayList<String>();
			for (Equipment x :equipments)
				names.add(x.getName());
			if (!names.contains(name)){
				new Equipment(name, weight, pricePerWeek, this.climbSafe);
			}
		}
	} 
	/**
	 * @param dataTable
	 * @author Victor&Eunjun&Enzo
	 */
	@Given("the following equipment bundles exist in the system: \\(p9)")
	public void the_following_equipment_bundles_exist_in_the_system_p9(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> r : rows) {
			String name = r.get("name");
			int discount = Integer.parseInt(r.get("discount"));
			List<EquipmentBundle> equipBundles = climbSafe.getBundles();
			List<String> listA = new ArrayList<String>();
			for (EquipmentBundle x : equipBundles)
				listA.add(x.getName());
			if (!listA.contains(name)) {
				EquipmentBundle bundle = new EquipmentBundle(name, discount, climbSafe);

				List<String> items = Arrays.asList(r.get("items").split(","));
				List<Integer> quantities = Arrays.asList(r.get("quantity").split(",")).stream().map(String::trim).mapToInt(Integer::parseInt).boxed().toList();
				int i=0;
				for (String x:items) {
					new BundleItem(quantities.get(i), this.climbSafe, bundle, (Equipment) Equipment.getWithName(items.get(i)));
					i++;
				}
			}
		}
	}
	/**
	 * @param dataTable
	 * @author EnzoBenoitJeannin
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
			//List<Integer> requestedQuantities = Arrays.asList(r.get("requestedQuantities").split(",")).stream().map(String::trim).mapToInt(Integer::parseInt).boxed().toList();
			List<String> requestedQuantities = Arrays.asList(r.get("requestedQuantities").split(","));
			boolean guideRequired = Boolean.parseBoolean(r.get("guideRequired"));
			boolean hotelRequired = Boolean.parseBoolean(r.get("hotelRequired"));

			List<Member> members = climbSafe.getMembers();
			List<String> emails = new ArrayList<String>();
			for (Member m:members)
				emails.add(m.getEmail());
			if (!emails.contains(email)) {
				Member m = new Member(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired, this.climbSafe);
		
				for (int i = 0; i < bookableItems.size(); i++) {
					BookableItem bookableItem = BookableItem.getWithName(bookableItems.get(i));
					m.addBookedItem(Integer.parseInt(requestedQuantities.get(i)), this.climbSafe, bookableItem); 
				}
			}
		}
	}

	/**
	 * @param dataTable
	 * @author Victor&Eunjun
	 */
	@Given("the following guides exist in the system: \\(p9)")
	public void the_following_guides_exist_in_the_system_p9(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		List<Guide> guides = climbSafe.getGuides();
		List<String> emails = new ArrayList<String>();
		for (Guide g:guides)
			emails.add(g.getEmail());
		
		String email;
		for (Map<String, String> r : rows) {
			email = r.get("email");
			if (!emails.contains(email)) {
			new Guide(email, r.get("password"), r.get("name"), r.get("emergencyContact"), climbSafe);
			}

		}
	}


	/**
	 * @param email
	 * @param password
	 * @param name
	 * @param emergencyContact
	 * @param xnrWeeks
	 * @param xitemNames
	 * @param xitemQuantities
	 * @param xguideRequired
	 * @param xhotelRequired
	 * @author KaraBest
	 */

	@When("a new member attempts to register with {string} , {string} , {string}, {string}, {string}, {string}, {string}, {string}, and {string} \\(p9)") 
	public void a_new_member_attempts_to_register_with_and_p9(String email, String password, String name, String emergencyContact, String xnrWeeks, String xbookableItems, String xitemQuantities, String xguideRequired, String xhotelRequired) {
		List<String> bookableItems = Arrays.asList(xbookableItems.split(","));
		List<Integer> itemQuantities = new ArrayList<Integer>();
		boolean guideRequired = Boolean.parseBoolean(xguideRequired);
		boolean hotelRequired = Boolean.parseBoolean(xhotelRequired);
		int nrWeeks = Integer.parseInt(xnrWeeks);
		for(String s : xitemQuantities.split(",")) 
			itemQuantities.add(Integer.parseInt(s));

		try {
			ClimbSafeFeatureSet2Controller.registerMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired, bookableItems, itemQuantities);
		}catch (InvalidInputException e) {
			error = e.getMessage();
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
	 * @author Joey&Victor
	 */
	@Then("a new member account shall exist with {string} , {string} , {string}, {string}, {string}, {string}, {string}, {string}, and {string} \\(p9)")
	public void a_new_member_account_shall_exist_with_and_p9(String email, String password, String name, String emergencyContact, String nrWeeks, String bookableItems, String requestedQuantities, String guideRequired, String hotelRequired) { 
		Member member = (Member) Member.getWithEmail(email);
		assertNotNull(member);
		assertEquals(password, member.getPassword()); //User.java
		assertEquals(name, member.getName()); //NameUser.java
		assertEquals(emergencyContact, member.getEmergencyContact()); //NameUser.java
		assertEquals(Integer.parseInt(nrWeeks), member.getNrWeeks()); //Member.java
		assertEquals(Boolean.parseBoolean(guideRequired), member.getGuideRequired()); //Member.java
		assertEquals(Boolean.parseBoolean(hotelRequired), member.getHotelRequired()); //Member.java

		List<String> argBookableItemsList = Arrays.asList(bookableItems.split(","));
		List<BookedItem> compBookableItemsList = member.getBookedItems();
		List<String> argRequestedQuantitiesList = Arrays.asList(requestedQuantities.split(","));
		List<String> nameBookedItems = new ArrayList<String>();
		List<Integer> quantBookedItems = new ArrayList<Integer>();
		for (BookedItem x : compBookableItemsList) {
			nameBookedItems.add(x.getItem().getName());
			quantBookedItems.add(x.getQuantity());
		}
		for (String s:argBookableItemsList) {
			assertTrue(nameBookedItems.contains(s));

		}
		for (int j=0; j<quantBookedItems.size(); j++) {
			assertEquals(quantBookedItems.get(j), (Integer) Integer.parseInt(argRequestedQuantitiesList.get(j)));
		}


	} 

	/**
	 * @param numMembers
	 * @author VictorMicha
	 */
	@Then("there are {int} members in the system. \\(p9)")
	public void there_are_members_in_the_system_p9(Integer numMembers) {
		assertEquals(numMembers, (Integer)climbSafe.numberOfMembers());
	}

	/**
	 * @param errorString
	 * @author JoeyKoay
	 */
	@Then("the following {string} shall be raised. \\(p9)")
	public void the_following_shall_be_raised_p9(String errorString) {
		assertTrue(errorString.contains(errorString));
	}

	/**
	 * @param email 
	 * @author KaraBest
	 */
	@Then("there is no member account for {string} \\(p9)")
	public void there_is_no_member_account_for_p9(String email) {
		List<Member> members = climbSafe.getMembers();
		for(int i=0; i<climbSafe.numberOfMembers(); i++) {
			assertNotEquals(email, members.get(i).getEmail()); 
		}
	}
	
	@After
	public void tearDown() {
		error = null;
		climbSafe.delete();
	}

}
