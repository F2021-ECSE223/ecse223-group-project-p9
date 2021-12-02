package ca.mcgill.ecse.climbsafe.view.controllers;

import java.util.ArrayList;
import java.util.List;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet5Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
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

public class UpdateEquipmentBundleController {
	@FXML private ChoiceBox<String> equipmentBundleChoiceBox;
	
	@FXML private TextField newNameTextField;
	
	@FXML private ChoiceBox<String> itemInBundleChoiceBox;

	@FXML private ChoiceBox<String> itemNotInBundleChoiceBox;

//	@FXML private TextField priceTextField;
	
	@FXML private Button removeItemButton;
	@FXML private Button addItemButton;
	@FXML private Button updateButton;
	@FXML private Button deleteButton;
	@FXML private TextField discountTextField;
	
	
	private List<String> itemNames = new ArrayList<>();
	private List<Integer> itemQuantities = new ArrayList<>();
	//private EquipmentBundle b = null;
	
	/**
	 * @author SeJong Yoo
	 * @param event
	 */
	
	public void initialize() {
		
		equipmentBundleChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			equipmentBundleChoiceBox.setItems(ViewUtils.getBundles());
			equipmentBundleChoiceBox.setValue(null);
		});
		
		itemInBundleChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			//not correct
			itemInBundleChoiceBox.setItems(ViewUtils.getEquipments());
			itemInBundleChoiceBox.setValue(null);
		});
		
		itemNotInBundleChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			not correct
			itemNotInBundleChoiceBox.setItems(ViewUtils.getEquipments());
			itemNotInBundleChoiceBox.setValue(null);
		});
		
		
	
		discountTextField.setText("");
		newNameTextField.setText("");
		
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(equipmentBundleChoiceBox);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(itemInBundleChoiceBox);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(itemNotInBundleChoiceBox);
		
	}
	
	@FXML
	public void updateEquipmentBundle(ActionEvent event) {
		String newName = newNameTextField.getText();
		
		/*b = ClimbSafeController.getBundle(equipmentBundleChoiceBox.getValue());
		System.out.println("Clicked");
		System.out.println(b);
		if(b != null) {
			bundleNameTextField.setText(b.getName());
			//items in this list
			discountTextField.setText(String.valueOf(b.getDiscount()));
			//price controller isnt there....
//			priceTextField.setText(b.getPrice);
			itemsInBundleListView.setItems(ViewUtils.getBundleItemsAndQuantity(b.getName()));
			ClimbSafeFxmlView.getInstance().refresh();
		}*/
	}
	public void deleteEquipmentBundle(ActionEvent event) {
		String name = equipmentBundleChoiceBox.getValue();
		try {
			if(successful(() -> ClimbSafeFeatureSet6Controller.deleteEquipmentBundle(name))) {
				equipmentBundleChoiceBox.setValue(null);
				ClimbSafeFxmlView.getInstance().refresh();
			}
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
		
	}
	public void removeItem(ActionEvent event) {
	}
	public void addItem(ActionEvent event) {
	}
	
			

}
