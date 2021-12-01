package ca.mcgill.ecse.climbsafe.view.controllers;

import java.util.ArrayList;
import java.util.List;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;
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

public class UpdateEquipmentController {
	@FXML private TextField addEquipmentBundleName;
	@FXML private TextField addEquipmentBundlePrice;
	@FXML private TextField discountTextField;
	@FXML private ChoiceBox<Integer> itemNameChoiceBox;
	@FXML private ChoiceBox<String> equipmentBundleChoiceBox;
	@FXML private Button addEquipmentButton;
	@FXML private Spinner<Integer> itemQuantitySpinner;
	@FXML private Button addEditItemButton;
	@FXML private ListView<String> equipmentBundleListView;
	@FXML private Button equipmentBundleSearchButton;
	@FXML private Button updateEquipmentBundleButton;
	@FXML private Button deleteEquipmentBundleButton;
	
	@FXML private TextField oldNameModifyEquipmentBundle;
	@FXML private TextField newNameModifyEquipmentBundle;
	@FXML private TextField priceModifyEquipmentBundle;
	@FXML private TextField deleteEquipmentBundleName;
	
	
	private List<String> itemNames = new ArrayList<>();
	private List<Integer> itemQuantities = new ArrayList<>();
	private EquipmentBundle e = null;
	
	/**
	 * @author SeJong Yoo
	 * @param event
	 */
	
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
	public void equipmentBundleSearchClicked(ActionEvent event) {
		e = ViewUtils.getBundles(equipmentBundleChoiceBox.getValue());
		addEquipmentBundleName.setText(e.getName());
		equipmentBundleListView.setItems(ViewUtils.getEquipmentBundleItems(e));
		ClimbSafeFxmlView.getInstance().refresh();
		itemNames = ViewUtils.getEquipmentBundleItemsName(e);
		itemQuantities = ViewUtils.getEquipmentBundleItemsQuantity(e);
	}
	
	@FXML
	public void modifyEquipmentBundleClicked(ActionEvent event) {
		String oldName = oldNameModifyEquipmentBundle.getText();
		String newName = newNameModifyEquipmentBundle.getText();
		int price = Integer.parseInt(priceModifyEquipmentBundle.getText());
		
		try {
			if(successful(() -> ClimbSafeFeatureSet5Controller.updateEquipmentBundle(oldName, newName, price, itemNames, itemQuantities))) {
				equipmentBundleChoiceBox.setValue(null);
				oldNameModifyEquipmentBundle.setText("");
				newNameModifyEquipmentBundle.setText("");
				discountTextField.setText("");
				priceModifyEquipmentBundle.setText("");
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
	public void deleteEquipmentBundleClicked(ActionEvent event) {
		String newName = newNameModifyEquipmentBundle.getText();
		
		try {
			if(successful(() -> ClimbSafeFeatureSet6Controller.deleteEquipmentBundle(name))) {
				deleteEquipmentBundleName.setText("");
				ClimbSafeFxmlView.getInstance().refresh();
			}
			
		} catch (RuntimeException e)  {
			ViewUtils.showError(newName);
		}
	}
			

}
