package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class UpdateMemberPageController {

	@FXML private TextField nameTextField;
	@FXML private ChoiceBox<String> memberChoiceBox;
	@FXML private TextField emergencyContactTextField;
	@FXML private ChoiceBox<Integer> nrWeeksChoiceBox;
	@FXML private CheckBox guideRequiredCheckBox;
	@FXML private CheckBox hotelRequiredCheckBox;
	@FXML private PasswordField passwordTextField;

	@FXML private ChoiceBox<String> itemNameChoiceBox;
	@FXML private Spinner<Integer> itemQuantitySpinner;
	@FXML private Button addEditItemButton;
	@FXML private ListView<String> memberItemsListView;


	@FXML private Button memberSearchButton;
	@FXML private Button updateMemberUpdateButton;
	@FXML private Button deleteMemberdeleteButton;
	@FXML private CheckBox deleteConfirmButton;
	@FXML private Text updateMessageLabel;


	private List<String> itemNames = new ArrayList<>();;
	private List<Integer> itemQuantities = new ArrayList<>();; 
	private Member m = null;

	public void initialize() {
		passwordTextField.setText("");
		nameTextField.setText("");
		emergencyContactTextField.setText("");
		guideRequiredCheckBox.setSelected(false);
		hotelRequiredCheckBox.setSelected(false);
		memberChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			memberChoiceBox.setItems(ViewUtils.getMembers());
			memberChoiceBox.setValue(null);
		});
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
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(nrWeeksChoiceBox);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(itemNameChoiceBox);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(itemQuantitySpinner);
	}

	// Event Listener on Button[#memberSearchClicked].onAction
	@FXML
	public void memberSearchClicked(ActionEvent event) {
		m = ClimbSafeController.getMember(memberChoiceBox.getValue());
		if(m != null) {
			nameTextField.setText(m.getName());
			passwordTextField.setText(m.getPassword());
			emergencyContactTextField.setText(m.getEmergencyContact());
			guideRequiredCheckBox.setSelected(m.getGuideRequired());
			hotelRequiredCheckBox.setSelected(m.getHotelRequired());
			memberItemsListView.setItems(ViewUtils.getMemberItems(m.getEmail()));
			ClimbSafeFxmlView.getInstance().refresh();
			memberChoiceBox.setValue(m.getEmail());
			nrWeeksChoiceBox.setValue(m.getNrWeeks());
			itemNames = ViewUtils.getMemberItemsName(m.getEmail());
			itemQuantities = ViewUtils.getMemberItemsQuantity(m.getEmail());
			updateMessageLabel.setText("");
		}
	}

	// Event Listener on Button[#updateMemberUpdateClicked].onAction
	@FXML
	public void updateMemberUpdateClicked(ActionEvent event) {
		String email = memberChoiceBox.getValue();
		String password = passwordTextField.getText();
		String name = nameTextField.getText();
		String emergencyContact = emergencyContactTextField.getText();
		int nrWeeks = getNumberFromField(nrWeeksChoiceBox);
		boolean guideRequired = guideRequiredCheckBox.isSelected();
		boolean hotelRequired = hotelRequiredCheckBox.isSelected();
		for(int i=0; i< itemNames.size(); i++) {
			if(itemNames.get(i).contains(":")) {
				itemNames.set(i, itemNames.get(i).split(":")[0]);
			}
		}
		if(email == null || nrWeeks == -1) {
			if(email != null) {
				ViewUtils.showError("Please click 'search' to populate the member's data");
			}else {
				ViewUtils.showError("No member was selected");
			}

		}else {
			if(nrWeeks == 0) {
				ViewUtils.showError("The number of weeks is set to be 0. Please delete this member instead of updating it");
			}else {
				try {
					if(successful(() -> ClimbSafeFeatureSet2Controller.updateMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired, itemNames, itemQuantities))) {
						memberChoiceBox.setValue(null);
						passwordTextField.setText("");
						nameTextField.setText("");
						emergencyContactTextField.setText("");
						nrWeeksChoiceBox.setValue(null);
						guideRequiredCheckBox.setSelected(false);
						hotelRequiredCheckBox.setSelected(false);
						itemNameChoiceBox.setValue(null);
						itemQuantitySpinner.setValueFactory(null);
						memberItemsListView.setItems(null);
						ClimbSafeFxmlView.getInstance().refresh();
						updateMessageLabel.setText("Member updated successfully");
					}
				} catch (RuntimeException e) {
					ViewUtils.showError(e.getMessage());
				}
			}

		}

	}

	//Event Listener on Button[#addEditItemClicked].onAction
	@FXML
	public void addEditItemClicked(ActionEvent event) {
		ObservableList<String> itemaNameAndQuantityList = FXCollections.observableArrayList();
		String itemName = "";
		if(itemNameChoiceBox.getValue() != null) {
			itemName = itemNameChoiceBox.getValue().toString();
		}
		int itemQuantity = itemQuantitySpinner.getValue();
		int indexOfItem = -1;

		if(itemNames.contains(itemName)) {
			//edit the quantity instead
			for(int i =0; i<itemNames.size(); i++) {
				if(itemNames.get(i).equals(itemName)) {
					indexOfItem = i;
					break;
				}
			}
			if(itemQuantity == 0) {
				itemNames.remove(indexOfItem);
				itemQuantities.remove(indexOfItem);
			}else {
				itemQuantities.set(indexOfItem, itemQuantity);
			}
		}else {
			//add new item
			if(itemQuantity != 0 && itemName != "") {
				itemNames.add(itemName);
				itemQuantities.add(itemQuantity);
			}
			itemNameChoiceBox.setValue(null);
			itemQuantitySpinner.getValueFactory().setValue(0);
		}

		for(int i =0; i<itemNames.size(); i++) {
			itemaNameAndQuantityList.add(itemQuantities.get(i) + " " + itemNames.get(i));
		}

		memberItemsListView.setItems(itemaNameAndQuantityList);
		ClimbSafeFxmlView.getInstance().refresh();
		if(m!= null) {
			memberChoiceBox.setValue(m.getEmail());
			nrWeeksChoiceBox.setValue(m.getNrWeeks());
		}
	}

	//Event Listener on Button[#deleteMemberClicked].onAction
	@FXML
	public void deleteMemberClicked(ActionEvent event) {
		String email = memberChoiceBox.getValue();
		boolean confirmDelete = deleteConfirmButton.isSelected();
		if(confirmDelete) {
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
					memberItemsListView.setItems(null);
					ClimbSafeFxmlView.getInstance().refresh();
					updateMessageLabel.setText("Member deleted successfully");
				}
			} catch (RuntimeException e) {
				ViewUtils.showError(e.getMessage());
			}
			ClimbSafeFxmlView.getInstance().refresh();
		}
	}

	private int getNumberFromField(ChoiceBox<Integer> field) {
		if(field.getValue() != null) {
			return field.getValue();
		}else {
			return -1;
		}
	}

}
