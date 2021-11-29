package ca.mcgill.ecse.climbsafe.view.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Member;
//import ca.mcgill.ecse.climbsafe.controller.TOBusVehicle;
//import ca.mcgill.ecse.climbsafe.controller.TODriver;
//import ca.mcgill.ecse.climbsafe.controller.TORoute;
//import ca.mcgill.ecse.climbsafe.controller.TORouteAssignment;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewUtils {
	private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

  /** Calls the controller and shows an error, if applicable. */
  public static boolean callController(Executable executable) {
    try {
      executable.execute();
      ClimbSafeFxmlView.getInstance().refresh();
      return true;
    } catch (InvalidInputException e) {
      showError(e.getMessage());
    } catch (Throwable t) {
      t.printStackTrace();
    }
    return false;
  }

  /** Calls the controller and returns true on success. This method is included for readability. */
  public static boolean successful(Executable controllerCall) {
    return callController(controllerCall);
  }

  /**
   * Creates a popup window.
   *
   * @param title: title of the popup window
   * @param message: message to display
   */
  public static void makePopupWindow(String title, String message) {
    Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    VBox dialogPane = new VBox();

    // create UI elements
    Text text = new Text(message);
    Button okButton = new Button("OK");
    okButton.setOnAction(a -> dialog.close());

    // display the popup window
    int innerPadding = 10; // inner padding/spacing
    int outerPadding = 100; // outer padding
    dialogPane.setSpacing(innerPadding);
    dialogPane.setAlignment(Pos.CENTER);
    dialogPane.setPadding(new Insets(innerPadding, innerPadding, innerPadding, innerPadding));
    dialogPane.getChildren().addAll(text, okButton);
    Scene dialogScene = new Scene(dialogPane, outerPadding + 5 * message.length(), outerPadding);
    dialog.setScene(dialogScene);
    dialog.setTitle(title);
    dialog.show();
  }

  public static void showError(String message) {
    makePopupWindow("Error", message);
  }
  
  public static ObservableList<String> getItemNames(){
	  List<BookedItem> itemNames = climbSafe.getBookedItems();
	  List<String> itemNamesString = new ArrayList<>();
	  for(int i=0; i< itemNames.size(); i++) {
		  itemNamesString.add(itemNames.get(i).getItem().getName());
	  }
	  return FXCollections.observableList(itemNamesString);
  }
  public static ObservableList<Integer> getNrWeeks(){
	  int nrWeeksTotal = climbSafe.getNrWeeks();
	  List<Integer> weeksInTotal = new ArrayList<>();
	  for(int i=0; i<=nrWeeksTotal; i++) {
		  weeksInTotal.add(i);
	  }
	  return FXCollections.observableList(weeksInTotal);
  }
  public static ObservableList<Member> getMembers(){
	  List<Member> memberList = climbSafe.getMembers();
	  return FXCollections.observableList(memberList);
  }
  public static ObservableList<Member> getMember(String email){
	  List<Member> memberList = climbSafe.getMembers();
	  return FXCollections.observableList(memberList);
  }
  public static ObservableList<String> getMemberItems(Member member){
	  List<String> itemaNameAndQuantityList = new ArrayList<>();
	  List<BookedItem> memberItemList = member.getBookedItems();
	  for(int i=0; i<memberItemList.size(); i++) {
		  itemaNameAndQuantityList.add(memberItemList.get(i).getItem().getName() + " " + memberItemList.get(i).getQuantity());
	  }
	  return FXCollections.observableList(itemaNameAndQuantityList);
  }
  
  

//  public static ObservableList<TODriver> getDrivers() {
//    List<TODriver> drivers = BtmsController.getDrivers();
//    // as javafx works with observable list, we need to convert the java.util.List to
//    // javafx.collections.observableList
//    return FXCollections.observableList(drivers);
//  }
//
//  public static ObservableList<TOBusVehicle> getBuses() {
//    return FXCollections.observableList(BtmsController.getBuses());
//  }
//
//  public static ObservableList<TORoute> getRoutes() {
//    return FXCollections.observableList(BtmsController.getRoutes());
//  }
//
//  public static ObservableList<TORouteAssignment> getAssignments() {
//    return FXCollections.observableList(BtmsController.getAssignments());
//  }
}


@FunctionalInterface
interface Executable {
  public void execute() throws Throwable;
}
