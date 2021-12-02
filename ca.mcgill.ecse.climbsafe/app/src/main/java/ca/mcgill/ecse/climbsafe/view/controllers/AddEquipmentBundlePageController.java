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

public class AddEquipmentBundlePageController {
	@FXML private TextField nameTextField;
	@FXML private TextField discountTextField;
	@FXML private ChoiceBox<String> itemNameChoiceBox;
	@FXML private ChoiceBox<Integer> itemQuantityChoiceBox;


	@FXML private Button addEquipmentButton;
	@FXML private Button addItem;


	private List<String> itemNames = new ArrayList<>();
	private List<Integer> itemQuantities = new ArrayList<>();

	public void initialize() {
		nameTextField.setText("");
		discountTextField.setText("");
		itemNameChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			itemNameChoiceBox.setItems(ViewUtils.getEquipments());
			itemNameChoiceBox.setValue(null);
		});
		itemQuantityChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			itemQuantityChoiceBox.setItems(ViewUtils.getQuantity());
			itemQuantityChoiceBox.setValue(null);
		});

		ClimbSafeFxmlView.getInstance().registerRefreshEvent(itemNameChoiceBox);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(itemQuantityChoiceBox);

	}



	public void addItemToBundle(ActionEvent event) {
		String itemName = itemNameChoiceBox.getValue();
		Integer quantity = itemQuantityChoiceBox.getValue();
		itemNames.add(itemName);
		itemQuantities.add(quantity);
		itemNameChoiceBox.setValue(null);
		itemQuantityChoiceBox.setValue(null);
		ClimbSafeFxmlView.getInstance().refresh();
	}

	@FXML
	public void addEquipmentBundle(ActionEvent event) {
		String name = nameTextField.getText(); 
		int discount = Integer.parseInt(discountTextField.getText());
		try {
			if (successful(() -> ClimbSafeFeatureSet5Controller.addEquipmentBundle(name, discount, itemNames, itemQuantities)) ) {
				nameTextField.setText("");
				discountTextField.setText("");
				itemNameChoiceBox.setValue(null);
				itemQuantityChoiceBox.setValue(null);
				ClimbSafeFxmlView.getInstance().refresh();
			}	
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}

	}

	
}

