package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Member;

public class ClimbSafeController {

  private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

  private ClimbSafeController() {}
  
  public static List<String> getItemNames(){
	  List<Equipment> itemNames = climbSafe.getEquipment();
	  List<String> itemNamesString = new ArrayList<>();
	  for(int i=0; i< itemNames.size(); i++) {
		  itemNamesString.add(itemNames.get(i).getName());
	  }
	  return itemNamesString;
  }
  
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
  
  public static List<String> getBundleItemsAndQuantity(String bundleName){
	  List<String> itemsInBundle = new ArrayList<>();
	  List<BundleItem> bundleItems = getBundle(bundleName).getBundleItems();
	  for(int i=0; i<bundleItems.size(); i++) {
		  itemsInBundle.add(bundleItems.get(i).getQuantity() + " " + bundleItems.get(i).getEquipment().getName());
	  }
	return itemsInBundle;
  }

  public static List<Integer> getNrWeeks(){
	  int nrWeeksTotal = climbSafe.getNrWeeks();
	  List<Integer> weeksInTotal = new ArrayList<>();
	  for(int i=0; i<=nrWeeksTotal; i++) {
		  weeksInTotal.add(i);
	  }
	  return weeksInTotal;
  }
  public static List<String> getMembers(){
	  List<Member> memberList = climbSafe.getMembers();
	  List<String> memberListString = new ArrayList<>();
	  for(int i=0; i< memberList.size(); i++) {
		  memberListString.add(memberList.get(i).getEmail());
	  }
	  return memberListString;
  }
  
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
  
  public static List<Integer> getMemberItemsQuantity(String email){
	  Member member = getMember(email);
	  List<Integer> itemsQuantityList = new ArrayList<>();
	  List<BookedItem> memberItemList = member.getBookedItems();
	  for(int i=0; i<memberItemList.size(); i++) {
		  itemsQuantityList.add(memberItemList.get(i).getQuantity());
	  }
	  return itemsQuantityList;
  }
}
