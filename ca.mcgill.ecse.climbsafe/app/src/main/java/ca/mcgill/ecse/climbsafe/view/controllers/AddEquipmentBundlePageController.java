package ca.mcgill.ecse.climbsafe.view.controllers;

import java.util.ArrayList;
import java.util.List;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet5Controller;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class AddEquipmentBundlePageController {
	@FXML private TextField addEquipmentBundleName;
	@FXML private TextField addEquipmentBundlePrice;
	@FXML private ChoiceBox<Integer> itemNameChoiceBox;

	@FXML private Button addEquipmentButton;
	@FXML private Spinner<Integer> itemQuantitySpinner;
	@FXML private Button addEditItemButton;
	@FXML private ListView<String> equipmentBundleListView;
	
	/**
	 * @author SeJong Yoo
	 * @param event
	 */
	
	private List<String> itemNames = new ArrayList<>();
	private List<Integer> itemQuantities = new ArrayList<>();
	
	public void initialize() {
		addEquipmentBundleName.setText("");
		itemNameChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			itemNameChoiceBox.setItems(null);
			itemNameChoiceBox.setValue(null);
		});
		itemQuantitySpinner.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			int initQuantity = 0;
			int minQuantity = 0;
			int maxQuantity = 99;
			SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(initQuantity, minQuantity, maxQuantity);
			itemQuantitySpinner.setValueFactory(valueFactory);
		});
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(itemNameChoiceBox);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(itemQuantitySpinner);
		
	}
	
	@FXML
	public void addEquipmentBundleApplyClicked(ActionEvent event) {
		String name = addEquipmentBundleName.getText();
		// equipment bundle is selected from the itemNameChoiceBox 
		int price = Integer.parseInt(addEquipmentBundlePrice.getText());
		
		try {
			if (successful(() -> ClimbSafeFeatureSet5Controller.addEquipmentBundle(name, price, itemNames, itemQuantities)) ) {
				addEquipmentBundleName.setText("");
				addEquipmentBundlePrice.setText("");
				itemNameChoiceBox.setValue(null);
				itemQuantitySpinner.setValueFactory(null);
				equipmentBundleListView.setItems(null);
				ClimbSafeFxmlView.getInstance().refresh();
				
			}	
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
		
	}
	
	@FXML
	public void addEditItemClicked(ActionEvent event) {
		
		
	}
	
	

}

