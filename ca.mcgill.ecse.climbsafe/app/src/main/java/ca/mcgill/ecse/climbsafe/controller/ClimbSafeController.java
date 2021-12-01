package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
  
  public static List<String> getMemberItems(String email){
	  Member member = getMember(email);
	  List<String> itemaNameAndQuantityList = new ArrayList<>();
	  List<BookedItem> memberItemList = member.getBookedItems();
	  for(int i=0; i<memberItemList.size(); i++) {
		  itemaNameAndQuantityList.add(memberItemList.get(i).getItem().getName() + " " + memberItemList.get(i).getQuantity());
	  }
	  return itemaNameAndQuantityList;
  }
  
  public static List<String> getMemberItemsName(String email){
	  Member member = getMember(email);
	  List<String> itemNameList = new ArrayList<>();
	  List<BookedItem> memberItemList = member.getBookedItems();
	  for(int i=0; i<memberItemList.size(); i++) {
		  itemNameList.add(memberItemList.get(i).getItem().getName());
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
