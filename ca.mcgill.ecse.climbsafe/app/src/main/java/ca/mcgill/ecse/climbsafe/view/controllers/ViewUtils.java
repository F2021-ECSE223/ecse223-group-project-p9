package ca.mcgill.ecse.climbsafe.view.controllers;

import java.util.List;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeController;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
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
	//	private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

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
		List<String> itemNames = ClimbSafeController.getItemNames();
		return FXCollections.observableList(itemNames);
	}

	public static ObservableList<Integer> getNrWeeks(){
		List<Integer> weeksInTotal = ClimbSafeController.getNrWeeks();
		return FXCollections.observableList(weeksInTotal);
	}

	public static ObservableList<String> getMembers(){
		List<String> memberListString = ClimbSafeController.getMembers();
		return FXCollections.observableList(memberListString);
	}

	public static Member getMember(String email){
		Member m = ClimbSafeController.getMember(email);
		return m;
	}

	public static ObservableList<String> getMemberItems(Member member){
		List<String> itemaNameAndQuantityList = ClimbSafeController.getMemberItems(member);
		return FXCollections.observableList(itemaNameAndQuantityList);
	}
	
	public static ObservableList<String> getMemberItemsName(Member member){
		List<String> itemNameList = ClimbSafeController.getMemberItemsName(member);
		return FXCollections.observableList(itemNameList);
	}
	
	public static ObservableList<Integer> getMemberItemsQuantity(Member member){
		List<Integer> itemsQuantityList = ClimbSafeController.getMemberItemsQuantity(member);
		return FXCollections.observableList(itemsQuantityList);
	}
}


@FunctionalInterface
interface Executable {
	public void execute() throws Throwable;
}
