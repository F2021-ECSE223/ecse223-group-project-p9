package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.util.List;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
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

public class RegisterMemberPageController {

	@FXML private TextField nameTextField;
	@FXML private TextField emailTextField;
	@FXML private TextField emergencyContactTextField;
	@FXML private ChoiceBox<Integer> nrWeeksChoiceBox;
	@FXML private CheckBox guideRequiredCheckBox;
	@FXML private CheckBox hotelRequiredCheckBox;
	@FXML private PasswordField passwordTextField;

	@FXML private ChoiceBox<BookedItem> itemNameChoiceBox;
	@FXML private Spinner<Integer> itemQuantitySpinner;
	@FXML private Button addEditItemButton;
	@FXML private ListView<String> memberItemsListView;


	@FXML private Button registerMemberRegisterButton;
	private List<String> itemNames = null;
	private List<Integer> itemQuantities = null; 
	
	public void initialize() {
		emailTextField.setText("");
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
	}

	// Event Listener on Button[#registerMemberRegisterClicked].onAction
	@FXML
	public void registerMemberRegisterClicked(ActionEvent event) {
		System.out.println("Clicking");
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
				//nrWeeksChoiceBox.setItems(null);
				guideRequiredCheckBox.setSelected(false);
				hotelRequiredCheckBox.setSelected(false);
				itemNameChoiceBox.setValue(null);
				itemQuantitySpinner.setValueFactory(null);
				ClimbSafeFxmlView.getInstance().refresh();
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


}
