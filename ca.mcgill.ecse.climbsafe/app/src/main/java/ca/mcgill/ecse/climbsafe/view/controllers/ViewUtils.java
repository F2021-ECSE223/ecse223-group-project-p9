package ca.mcgill.ecse.climbsafe.view.controllers;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeController;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
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
		List<String> bundles = ClimbSafeController.getBundles();
		List<String> itemsAndBundles = new ArrayList<>();;
		for(int i=0; i< itemNames.size(); i++) {
			itemsAndBundles.add(itemNames.get(i));
		}
		for(int i=0; i< bundles.size(); i++) {
			itemsAndBundles.add(bundles.get(i));
		}
		return FXCollections.observableList(itemsAndBundles);
	}
	
	public static ObservableList<String> getBundles(){
		List<String> bundles = ClimbSafeController.getBundles();
		return FXCollections.observableList(bundles);
	}
	
	public static ObservableList<String> getBundleItemsAndQuantity(String bundleName){
		List<String> bundles = ClimbSafeController.getBundleItemsAndQuantity(bundleName);
		return FXCollections.observableList(bundles);
	}

	public static ObservableList<Integer> getNrWeeks(){
		List<Integer> weeksInTotal = ClimbSafeController.getNrWeeks();
		return FXCollections.observableList(weeksInTotal);
	}

	public static ObservableList<String> getMembers(){
		List<String> memberListString = ClimbSafeController.getMembers();
		return FXCollections.observableList(memberListString);
	}

	public static ObservableList<String> getMemberItems(String email){
		List<String> itemaNameAndQuantityList = ClimbSafeController.getMemberItems(email);
		return FXCollections.observableList(itemaNameAndQuantityList);
	}
	
	public static ObservableList<String> getMemberItemsName(String email){
		List<String> itemNameList = ClimbSafeController.getMemberItemsName(email);
		return FXCollections.observableList(itemNameList);
	}
	
	public static ObservableList<Integer> getMemberItemsQuantity(String email){
		List<Integer> itemsQuantityList = ClimbSafeController.getMemberItemsQuantity(email);
		return FXCollections.observableList(itemsQuantityList);
	}
}


@FunctionalInterface
interface Executable {
	public void execute() throws Throwable;
}
