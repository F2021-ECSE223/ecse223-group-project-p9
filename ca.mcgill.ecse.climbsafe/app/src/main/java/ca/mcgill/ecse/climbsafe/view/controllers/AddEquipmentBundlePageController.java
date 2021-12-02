package ca.mcgill.ecse.climbsafe.view.controllers;

import java.util.ArrayList;
import java.util.List;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet5Controller;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddEquipmentBundlePageController {
	@FXML private TextField nameTextField;
	@FXML private TextField discountTextField;
	@FXML private ChoiceBox<String> itemNameChoiceBox;
	@FXML private Spinner<Integer> itemQuantitySpinner;

	@FXML private ListView<String> itemsInBundleListView;

	@FXML private Button addEquipmentButton;
	@FXML private Button addItem;
	@FXML private Text returnMessageText;


	private List<String> itemNames = new ArrayList<>();
	private List<Integer> itemQuantities = new ArrayList<>();

	public void initialize() {
		nameTextField.setText("");
		discountTextField.setText("");
		itemNameChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			itemNameChoiceBox.setItems(ViewUtils.getEquipments());
			itemNameChoiceBox.setValue(null);
		});
		returnMessageText.setText("");
		
		itemQuantitySpinner.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			int minQuantity = 0;
			int maxQuantity = 99;
			int initQuantity = 0;
			SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minQuantity, maxQuantity, initQuantity);
			itemQuantitySpinner.setValueFactory(valueFactory);
		});

		ClimbSafeFxmlView.getInstance().registerRefreshEvent(itemNameChoiceBox);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(itemQuantitySpinner);

	}

	//Event Listener on Button[#addEditItemClicked].onAction
	ObservableList<String> itemNameAndQuantityList = FXCollections.observableArrayList();
		@FXML
		public void addItemToBundle(ActionEvent event) {
			
			String itemName = "";
			if(itemNameChoiceBox.getValue()!= null) {
				itemName = itemNameChoiceBox.getValue().toString();
			}
			Integer itemQuantity = itemQuantitySpinner.getValue();
			int indexOfItem = -1;

			if(itemNames.size()!= 0 && itemNames.toString().contains(itemName)) {
				//edit the quantity instead
				for(int i =0; i<itemNames.size(); i++) {
					if(itemNames.get(i) == itemName) {
						
						indexOfItem = i;
						break;
					}
				}
				if(itemQuantity == 0) {
					itemNameAndQuantityList.remove(indexOfItem);
					itemNames.remove(indexOfItem);
					itemQuantities.remove(indexOfItem);
				}else {
					itemQuantities.set(indexOfItem, itemQuantity);
					itemNameAndQuantityList.set(indexOfItem, itemQuantity + " " + itemName);
				}
					
			}else {
				//add new item
				if(itemQuantity != 0 && itemName != "") {
					itemNames.add(itemName);
					itemQuantities.add(itemQuantity);
					itemNameAndQuantityList.add(itemQuantity + " " + itemName);
				}
				itemNameChoiceBox.setValue(null);
				itemQuantitySpinner.getValueFactory().setValue(0);
			}
			itemsInBundleListView.setItems(itemNameAndQuantityList);
			ClimbSafeFxmlView.getInstance().refresh();
		}

	@FXML
	public void addEquipmentBundle(ActionEvent event) {
		String name = nameTextField.getText(); 
		int discount = getNumberFromField(discountTextField);
		
		if(name!="" && discount != -1) {
			try {
				if (successful(() -> ClimbSafeFeatureSet5Controller.addEquipmentBundle(name, discount, itemNames, itemQuantities)) ) {
					nameTextField.setText("");
					discountTextField.setText("");
					itemNameChoiceBox.setValue(null);
					itemQuantitySpinner.getValueFactory().setValue(0);
					ClimbSafeFxmlView.getInstance().refresh();
					itemsInBundleListView.setItems(null);
					returnMessageText.setText("Bundle added successfully");
				}	
			} catch (RuntimeException e) {
				ViewUtils.showError(e.getMessage());
			}
		}
		

	}
	
	/** Returns the number from the given text field if present, otherwise appends error string to the given message. */
	private int getNumberFromField(TextField field) {
		if(field.getText() != "") {
			return Integer.parseInt(field.getText());
		}else {
			return -1;
		}
	}
	
}
