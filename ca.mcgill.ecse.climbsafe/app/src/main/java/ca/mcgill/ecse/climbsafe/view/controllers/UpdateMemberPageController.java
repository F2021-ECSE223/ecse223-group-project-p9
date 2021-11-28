package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.util.List;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
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
import javafx.scene.control.TextField;

public class UpdateMemberPageController {

	@FXML private TextField nameTextField;
	@FXML private TextField emailTextField;
	@FXML private TextField emergencyContactTextField;
	@FXML private ChoiceBox nrWeeksChoiceBox;
	@FXML private CheckBox guideRequiredCheckBox;
	@FXML private CheckBox hotelRequiredCheckBox;
	@FXML private PasswordField passwordTextField;

	@FXML private ChoiceBox itemNameChoiceBox;
	@FXML private Spinner itemQuantitySpinner;
	@FXML private Button addEditItemButton;
	@FXML private ListView<String> memberItemsListView;


	@FXML private Button registerMemberP1NextButton;
	//	ObservableList<String> itemNames = FXCollections.observableArrayList();
	//	ObservableList<Integer> itemQuantities = FXCollections.observableArrayList();
	private List<String> itemNames = null;
	private List<Integer> itemQuantities = null; 

	// Event Listener on Button[#registerMemberP1NextClicked].onAction
	@FXML
	public void registerMemberP1NextClicked(ActionEvent event) {
		String email = emailTextField.getText();
		String password = passwordTextField.getText();
		String name = nameTextField.getText();
		String emergencyContact = emergencyContactTextField.getText();
		int nrWeeks = Integer.parseInt(nrWeeksChoiceBox.getValue().toString());
		boolean guideRequired = guideRequiredCheckBox.isSelected();
		boolean hotelRequired = hotelRequiredCheckBox.isSelected();

		try {
			if(successful(() -> ClimbSafeFeatureSet2Controller.registerMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired, null, null))) {
				emailTextField.setText("");
				passwordTextField.setText("");
				nameTextField.setText("");
				emergencyContactTextField.setText("");
				//        	nrWeeksChoiceBox.setItems(null);
				guideRequiredCheckBox.setSelected(false);
				hotelRequiredCheckBox.setSelected(false);
				//set item selected to null
				//set item quantity to null
			}
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
		


		//uncomment from here
		//    if(successful(() -> ClimbSafeFeatureSet2Controller.registerMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired, null, null))) {
		//    	emailTextField.setText("");
		//    	passwordTextField.setText("");
		//    	nameTextField.setText("");
		//    	emergencyContactTextField.setText("");
		////    	nrWeeksChoiceBox.setItems(null);
		//    	guideRequiredCheckBox.setSelected(false);
		//    	hotelRequiredCheckBox.setSelected(false);
		//    	//set item selected to null
		//    	//set item quantity to null
		//    }else {
		////    	ViewUtils.showError(/*error shown when trying to register member*/);
		////    	ViewUtils.showError();
		//    }
		//    
		////    if (name == null || name.trim().isEmpty()) {
		////      ViewUtils.showError("Please input a valid driver name");
		////    } else {
		////      // reset the driver text field if success
		////      if (successful(() -> BtmsController.createDriver(name))) {
		////        driverNameTextField.setText("");
		////      }
		////    }

		//uncomment to here
	}

	//Event Listener on Button[#addItemClicked].onAction
	@FXML
	public void addEditItemClicked(ActionEvent event) {
		ObservableList<String> itemaNameAndQuantityList = FXCollections.observableArrayList();
		String itemName = (String) itemNameChoiceBox.getValue();
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


}
