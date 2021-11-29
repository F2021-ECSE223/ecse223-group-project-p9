package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import ca.mcgill.ecse.climbsafe.view.pages.;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class UpdateMemberPageController {
	private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

	@FXML private TextField nameTextField;
	@FXML private ChoiceBox<Member> memberChoiceBox;
	@FXML private TextField emergencyContactTextField;
	@FXML private ChoiceBox<Integer> nrWeeksChoiceBox;
	@FXML private CheckBox guideRequiredCheckBox;
	@FXML private CheckBox hotelRequiredCheckBox;
	@FXML private PasswordField passwordTextField;

	@FXML private ChoiceBox<BookedItem> itemNameChoiceBox;
	@FXML private Spinner<Integer> itemQuantitySpinner;
	@FXML private Button addEditItemButton;
	@FXML private ListView<String> memberItemsListView;


	@FXML private Button memberSearchButton;
	@FXML private Button updateMemberUpdateButton;
	@FXML private Button deleteMemberdeleteButton;
	private List<String> itemNames = null;
	private List<Integer> itemQuantities = null; 
	
	public void initialize() {
		
//		emailTextField.setText("");
		passwordTextField.setText("");
		nameTextField.setText("");
		emergencyContactTextField.setText("");
		guideRequiredCheckBox.setSelected(false);
		hotelRequiredCheckBox.setSelected(false);
		nrWeeksChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			nrWeeksChoiceBox.setItems(ViewUtils.getNrWeeks());
			nrWeeksChoiceBox.setValue(null);
		});
		itemNameChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			itemNameChoiceBox.setItems(ViewUtils.getItemNames());
			itemNameChoiceBox.setValue(null);
		});
		itemQuantitySpinner.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			int minQuantity = 0;
			int maxQuantity = 99;
			int initQuantity = 0;
			SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minQuantity, maxQuantity, initQuantity);
			itemQuantitySpinner.setValueFactory(valueFactory);
		});
		memberItemsListView.setItems(null);
	}
	
	// Event Listener on Button[#memberSearchClicked].onAction
	@FXML
	public void memberSearchClicked(ActionEvent event) {
		String email = memberChoiceBox.getValue().getEmail();
		nameTextField.setText(memberChoiceBox.getValue().getName());
		passwordTextField.setText(memberChoiceBox.getValue().getName());
		emergencyContactTextField.setText(memberChoiceBox.getValue().getEmergencyContact());
		nrWeeksChoiceBox.setValue(memberChoiceBox.getValue().getNrWeeks());
		guideRequiredCheckBox.setSelected(memberChoiceBox.getValue().getGuideRequired());
		hotelRequiredCheckBox.setSelected(memberChoiceBox.getValue().getHotelRequired());
		memberItemsListView.setItems(ViewUtils.getMemberItems(memberChoiceBox.getValue()));
		ClimbSafeFxmlView.getInstance().refresh();
	}

	// Event Listener on Button[#updateMemberUpdateClicked].onAction
	@FXML
	public void updateMemberUpdateClicked(ActionEvent event) {
		System.out.println("Clicking Update");
		String email = memberChoiceBox.getValue().getEmail();
		String password = passwordTextField.getText();
		String name = nameTextField.getText();
		String emergencyContact = emergencyContactTextField.getText();
		int nrWeeks = Integer.parseInt(nrWeeksChoiceBox.getValue().toString());
		boolean guideRequired = guideRequiredCheckBox.isSelected();
		boolean hotelRequired = hotelRequiredCheckBox.isSelected();

		try {
			if(successful(() -> ClimbSafeFeatureSet2Controller.registerMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired, null, null))) {
				memberChoiceBox.setValue(null);
				passwordTextField.setText("");
				nameTextField.setText("");
				emergencyContactTextField.setText("");
				nrWeeksChoiceBox.setValue(null);
				guideRequiredCheckBox.setSelected(false);
				hotelRequiredCheckBox.setSelected(false);
				itemNameChoiceBox.setValue(null);
				itemQuantitySpinner.setValueFactory(null);
				ClimbSafeFxmlView.getInstance().refresh();
			}
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
	}


	
	//Event Listener on Button[#addEditItemClicked].onAction
	@FXML
	public void addEditItemClicked(ActionEvent event) {
		ObservableList<String> itemaNameAndQuantityList = FXCollections.observableArrayList();
		String itemName = itemNameChoiceBox.getValue().toString();
		int itemQuantity = (int) itemQuantitySpinner.getValue();
		int indexOfItem = -1;

		if(itemNames.toString().contains(itemName)) {
			//edit the quantity instead
			for(int i =0; i<itemNames.size(); i++) {
				if(itemNames.get(i) == itemName) {
					indexOfItem = i;
					break;
				}
			}
			itemQuantities.set(indexOfItem, itemQuantity);
		}else {
			//add new item
			if(itemQuantity!= 0) {
				itemNames.add(itemName);
				itemQuantities.add(itemQuantity);
			}
			itemNameChoiceBox.setValue(null);
			itemQuantitySpinner.getValueFactory().setValue(null);
		}
		
		for(int i =0; i<itemNames.size(); i++) {
			itemaNameAndQuantityList.add(itemNames.get(i) + " " + itemQuantities.get(i));
		}

		memberItemsListView.setItems(itemaNameAndQuantityList);
		ClimbSafeFxmlView.getInstance().refresh();
	}

	//Event Listener on Button[#addItemClicked].onAction
		@FXML
		public void deleteMemberClicked(ActionEvent event) {
			String email = memberChoiceBox.getValue().getEmail();
			try {
				if(successful(() -> ClimbSafeFeatureSet1Controller.deleteMember(email))) {
					memberChoiceBox.setValue(null);
					passwordTextField.setText("");
					nameTextField.setText("");
					emergencyContactTextField.setText("");
					nrWeeksChoiceBox.setValue(null);
					guideRequiredCheckBox.setSelected(false);
					hotelRequiredCheckBox.setSelected(false);
					itemNameChoiceBox.setValue(null);
					itemQuantitySpinner.setValueFactory(null);
					ClimbSafeFxmlView.getInstance().refresh();
				}
			} catch (RuntimeException e) {
				ViewUtils.showError(e.getMessage());
			}
		}

}
