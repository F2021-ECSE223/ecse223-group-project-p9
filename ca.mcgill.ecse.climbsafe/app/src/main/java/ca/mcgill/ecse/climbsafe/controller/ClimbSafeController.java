package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Member;

public class ClimbSafeController {

	private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

	private ClimbSafeController() {}

	/**
	 * Get all item names
 	 * @author Joey Koay
 	 * @return string list of item names
 	 */
	public static List<String> getItemNames(){
		List<Equipment> itemNames = climbSafe.getEquipment();
		List<String> itemNamesString = new ArrayList<>();
		for(int i=0; i< itemNames.size(); i++) {
			itemNamesString.add(itemNames.get(i).getName());
		}
		return itemNamesString;
	}

	/**
	 * Get all bundle names
 	 * @author Joey Koay
 	 * @return string list of bundle names
 	 */
	public static List<String> getBundles(){
		List<EquipmentBundle> climbSafeBundle = climbSafe.getBundles();
		List<String> climbSafeBundleString = new ArrayList<>();
		List<List<String> > climbSafeBundleEquipmentString = new ArrayList<>();
		List<String> bundleAndEquipments = new ArrayList<>();

		for(int i=0; i<climbSafeBundle.size(); i++) {
			climbSafeBundleString.add(climbSafeBundle.get(i).getName());
			List<String> bundleEquipments = new ArrayList<>();
			for(int x=0; x<climbSafeBundle.get(i).getBundleItems().size(); x++) {
				bundleEquipments.add(climbSafeBundle.get(i).getBundleItem(x).getQuantity()+ " " + climbSafeBundle.get(i).getBundleItem(x).getEquipment().getName());
			}
			climbSafeBundleEquipmentString.add(bundleEquipments);
		}
		for(int i=0; i<climbSafeBundle.size(); i++) {
			bundleAndEquipments.add(climbSafeBundleString.get(i) + ": " + climbSafeBundleEquipmentString.get(i));
		}
		return bundleAndEquipments;
	}

	/**
	 * Get a specific bundle based on names
	 * @param the name of the bundle
 	 * @author Joey Koay
 	 * @return the desired bundle
 	 */
	public static EquipmentBundle getBundle(String bundleName){
		bundleName = bundleName.split(":")[0];
		List<EquipmentBundle> bundleList = climbSafe.getBundles();
		for(int i=0; i<bundleList.size(); i++) {
			if(bundleList.get(i).getName().equals(bundleName)) {
				return bundleList.get(i);
			}
		}
		return null;
	}
	  /**
	   * Gets NMC start date
	 	 * @author Kara
	 	 */
	public static Date getNMCDate() {
		return climbSafe.getStartDate();
	}

	/**
	 * Get a specific bundle's item and quantity
 	 * @author Joey Koay
 	 * @return string list of item name and quantities
 	 */
	public static List<String> getBundleItemsAndQuantity(String bundleName){
		List<String> itemsInBundle = new ArrayList<>();
		List<BundleItem> bundleItems = getBundle(bundleName).getBundleItems();
		for(int i=0; i<bundleItems.size(); i++) {
			itemsInBundle.add(bundleItems.get(i).getQuantity() + " " + bundleItems.get(i).getEquipment().getName());
		}
		return itemsInBundle;
	}

	/**
	 * Get a specific bundle's items
 	 * @author Joey Koay
 	 * @return string list of item names
 	 */
	public static List<String> getBundleItems(String bundleName){
		List<String> itemsInBundle = new ArrayList<>();
		List<BundleItem> bundleItems = getBundle(bundleName).getBundleItems();
		for(int i=0; i<bundleItems.size(); i++) {
			itemsInBundle.add(bundleItems.get(i).getEquipment().getName());
		}
		return itemsInBundle;
	}

	/**
	 * Get a specific bundle's quantity
 	 * @author Joey Koay
 	 * @return integer list of item quantities
 	 */
	public static List<Integer> getBundleQuantity(String bundleName){
		List<Integer> itemsInBundle = new ArrayList<>();
		List<BundleItem> bundleItems = getBundle(bundleName).getBundleItems();
		for(int i=0; i<bundleItems.size(); i++) {
			itemsInBundle.add(bundleItems.get(i).getQuantity());
		}
		return itemsInBundle;
	}

	/**
	 * Get the number of weeks in a list form
 	 * @author Joey Koay
 	 * @return Integer list of week number
 	 */
	public static List<Integer> getNrWeeks(){
		int nrWeeksTotal = climbSafe.getNrWeeks();
		List<Integer> weeksInTotal = new ArrayList<>();
		for(int i=0; i<=nrWeeksTotal; i++) {
			weeksInTotal.add(i);
		}
		return weeksInTotal;
	}
	
	/**
	 * Get all member names
 	 * @author Joey Koay
 	 * @return string list of member names
 	 */
	public static List<String> getMembers(){
		List<Member> memberList = climbSafe.getMembers();
		List<String> memberListString = new ArrayList<>();
		for(int i=0; i< memberList.size(); i++) {
			memberListString.add(memberList.get(i).getEmail());
		}
		return memberListString;
	}
	  /**
	   * Gets list of assignment transfer objects for the view all table
	 	 * @author Kara
	 	 */
	public static List<TOTableAssignment> getTOTableAssignments() {
		List<TOAssignment> assignments = ClimbSafeFeatureSet6Controller.getAssignments();
		List<TOTableAssignment> assign = new ArrayList<TOTableAssignment>();
		String guideEmail;
		String guideName;
		String code;
		String status;
		if(assignments.isEmpty()) {
			return assign;
		}
		for(TOAssignment t: assignments) {
			String startToEnd = t.getStartWeek() + "-"+ t.getEndWeek();
			String refund = t.getRefundedPercentageAmount()+"%";
			if(t.getGuideEmail()==null) {
				guideEmail = "N/A";
				guideName = "N/A";
			}else {
				guideEmail = t.getGuideEmail();
				guideName = t.getGuideName();
			}
			if(t.getAuthorizationCode()==null) {
				status = t.getStatus();
				code = "N/A"; 
			}else if((t.getAuthorizationCode()!=null) && t.getStatus().equals("Assigned")){
				status = "Paid";
				code = t.getAuthorizationCode();
			}else {
				status = t.getStatus();
				code = t.getAuthorizationCode(); 
			}
			assign.add(new TOTableAssignment(t.getMemberEmail(), t.getMemberName(), guideEmail, guideName, startToEnd, t.getTotalCostForEquipment(),t.getTotalCostForGuide(), status, code, refund));
		}
		return assign;

	}
	  /**
	   * Gets list of member transfer objects
	 	 * @author Kara
	 	 */
	public static List<TOMember> getTOMembers() {
		List<Member> members = climbSafe.getMembers();
		List<TOMember> TOMembers = new ArrayList<TOMember>();
		TOMember mem;
		List<BookedItem> bookedItems;
		for(Member m: members) {
			mem = new TOMember(m.getEmail(), m.getName(), m.getEmergencyContact(), m.getPassword(), m.getNrWeeks(), m.getGuideRequired(), m.getHotelRequired());
			bookedItems = m.getBookedItems();
			for(BookedItem b: bookedItems) {
				mem.addBookedItem(b.toString());
			}
			TOMembers.add(mem);
		}
		return TOMembers;

	}
	  /**
	   * Gets list of guide transfer objects
	 	 * @author Kara
	 	 */
	public static List<TOGuide> getTOGuides() {
		List<Guide> guides = climbSafe.getGuides();
		List<TOGuide> TOGuides = new ArrayList<TOGuide>();
		TOGuide guide;
		for(Guide g: guides) {
			guide = new TOGuide(g.getEmail(), g.getName(), g.getEmergencyContact(), g.getPassword());

			TOGuides.add(guide);
		}
		return TOGuides;

	}
	  /**
	   * Gets list of equipment transfer objects
	 	 * @author Kara
	 	 */
	public static List<TOEquipment> getTOEquipment() {
		List<Equipment> equipment = climbSafe.getEquipment();
		List<TOEquipment> TOEquipment = new ArrayList<TOEquipment>();
		TOEquipment equip;
		for(Equipment e: equipment) {
			equip = new TOEquipment(e.getName(), e.getWeight(), e.getPricePerWeek());
			TOEquipment.add(equip);
		}
		return TOEquipment;
	}
	  /**
	   * Gets list of bundle transfer objects
	 	 * @author Kara
	 	 */
	public static List<TOBundle> getTOBundle(){
		List<EquipmentBundle> bundles = climbSafe.getBundles();
		List<TOBundle> TOBundles = new ArrayList<TOBundle>();
		TOBundle bundle;
		List<BundleItem> bundleItems;
		for(EquipmentBundle b: bundles) {
			bundle = new TOBundle(b.getName(), b.getDiscount());
			bundleItems = b.getBundleItems();
			for(BundleItem i: bundleItems) {
				bundle.addBundleItem(i.toString());
			}
			TOBundles.add(bundle);
		}
		return TOBundles;
	}

	/**
	 * Get all guide names
 	 * @author Joey Koay
 	 * @return string list of guide names
 	 */
	public static List<String> getGuides(){
		List<Guide> guideList = climbSafe.getGuides();
		List<String> guideListString = new ArrayList<>();
		for(int i=0; i< guideList.size(); i++) {
			guideListString.add(guideList.get(i).getEmail());
		}
		return guideListString;
	}

	/**
	 * Get a specific member with email
	 * @param the member's email
 	 * @author Joey Koay
 	 * @return the desired member
 	 */
	public static Member getMember(String email){
		List<Member> memberList = climbSafe.getMembers();
		Member m = null;
		for(int i=0; i< memberList.size(); i++) {
			if(memberList.get(i).getEmail() == email) {
				m = memberList.get(i);
				break;
			}
		}
		return m;
	}

	/**
	 * Get a specific guide with email
	 * @param the guide's email
 	 * @author Joey Koay
 	 * @return the desired guide
 	 */
	public static Guide getGuide(String email){
		List<Guide> guideList = climbSafe.getGuides();
		Guide g = null;
		for(int i=0; i< guideList.size(); i++) {
			if(guideList.get(i).getEmail() == email) {
				g = guideList.get(i);
				break;
			}
		}
		return g;
	}
	
	public static Equipment getEquipment(String equipment){
		List<Equipment> equipmentList = climbSafe.getEquipment();
		for(int i=0; i<equipmentList.size(); i++) {
			equipmentList.get(i).getName();
			if(equipmentList.get(i).getName() == equipment) {
				return equipmentList.get(i);
			}
		}
		return null;
	}

	/**
	 * Get a specific member's items and quantity
	 * @param the member's email
 	 * @author Joey Koay
 	 * @return string list of the name of the member's items and quantity
 	 */
	public static List<String> getMemberItems(String email){
		//getting climbsafe bundle and each bundle equipment into a list
		List<EquipmentBundle> climbSafeBundle = climbSafe.getBundles();
		List<String> climbSafeBundleString = new ArrayList<>();
		List<List<String> > climbSafeBundleEquipmentString = new ArrayList<>();

		for(int i=0; i<climbSafeBundle.size(); i++) {
			climbSafeBundleString.add(climbSafeBundle.get(i).getName());
			List<String> bundleEquipments = new ArrayList<>();
			for(int x=0; x<climbSafeBundle.get(i).getBundleItems().size(); x++) {
				bundleEquipments.add(climbSafeBundle.get(i).getBundleItem(x).getQuantity()+ " " + climbSafeBundle.get(i).getBundleItem(x).getEquipment().getName());
			}
			climbSafeBundleEquipmentString.add(bundleEquipments);
		}

		//populating the member's already booked items
		Member member = getMember(email);
		List<String> itemNameAndQuantityList = new ArrayList<>();
		List<BookedItem> memberItemList = member.getBookedItems();

		for(int i=0; i<memberItemList.size(); i++) {
			int bundleIndex = -1;
			for(int x=0; x<climbSafeBundleString.size(); x++) {
				if(climbSafeBundleString.get(x) == memberItemList.get(i).getItem().getName()) {
					bundleIndex = x;
					break;
				}
			}
			if(bundleIndex != -1) {
				itemNameAndQuantityList.add(memberItemList.get(i).getQuantity() + " " + memberItemList.get(i).getItem().getName() + ": " + climbSafeBundleEquipmentString.get(bundleIndex));
			}else {
				itemNameAndQuantityList.add(memberItemList.get(i).getQuantity() + " " + memberItemList.get(i).getItem().getName());  
			}
		}
		return itemNameAndQuantityList;
	}

	/**
	 * Get a specific member's items
	 * @param the member's email
 	 * @author Joey Koay
 	 * @return string list of the name of the member's items
 	 */
	public static List<String> getMemberItemsName(String email){
		List<EquipmentBundle> climbSafeBundle = climbSafe.getBundles();
		List<String> climbSafeBundleString = new ArrayList<>();
		List<List<String> > climbSafeBundleEquipmentString = new ArrayList<>();

		for(int i=0; i<climbSafeBundle.size(); i++) {
			climbSafeBundleString.add(climbSafeBundle.get(i).getName());
			List<String> bundleEquipments = new ArrayList<>();
			for(int x=0; x<climbSafeBundle.get(i).getBundleItems().size(); x++) {
				bundleEquipments.add(climbSafeBundle.get(i).getBundleItem(x).getQuantity()+ " " + climbSafeBundle.get(i).getBundleItem(x).getEquipment().getName());
			}
			climbSafeBundleEquipmentString.add(bundleEquipments);
		}

		Member member = getMember(email);
		List<String> itemNameList = new ArrayList<>();
		List<BookedItem> memberItemList = member.getBookedItems();
		for(int i=0; i<memberItemList.size(); i++) {
			//		  itemNameList.add(memberItemList.get(i).getItem().getName());
			int bundleIndex = -1;
			for(int x=0; x<climbSafeBundleString.size(); x++) {
				if(climbSafeBundleString.get(x) == memberItemList.get(i).getItem().getName()) {
					bundleIndex = x;
					break;
				}
			}
			if(bundleIndex != -1) {
				itemNameList.add(memberItemList.get(i).getItem().getName() + ": " + climbSafeBundleEquipmentString.get(bundleIndex));
			}else {
				itemNameList.add(memberItemList.get(i).getItem().getName());  
			}
		}
		return itemNameList;
	}

	/**
	 * Get a specific member's quantity
	 * @param the member's email
 	 * @author Joey Koay
 	 * @return string list of the name of the member's quantity
 	 */
	public static List<Integer> getMemberItemsQuantity(String email){
		Member member = getMember(email);
		List<Integer> itemsQuantityList = new ArrayList<>();
		List<BookedItem> memberItemList = member.getBookedItems();
		for(int i=0; i<memberItemList.size(); i++) {
			itemsQuantityList.add(memberItemList.get(i).getQuantity());
		}
		return itemsQuantityList;
	}
	
	public static List<Integer> getPriceOfGuidePerWeek(){
		int weeklyPrice = climbSafe.getPriceOfGuidePerWeek();
		List<Integer> weeklyPricesGuide = new ArrayList<>();
		for(int i=0; i<=weeklyPrice; i++) {
			weeklyPricesGuide.add(i);
		}
		return weeklyPricesGuide;
	}
}
