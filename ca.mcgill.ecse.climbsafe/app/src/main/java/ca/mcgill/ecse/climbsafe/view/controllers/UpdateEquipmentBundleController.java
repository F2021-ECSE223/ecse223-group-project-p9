package ca.mcgill.ecse.climbsafe.view.controllers;

import java.util.ArrayList;
import java.util.List;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet5Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.TOBundle;
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
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class UpdateEquipmentBundleController {
	@FXML private ChoiceBox<String> equipmentBundleChoiceBox;
	@FXML private Button bundleSearchButton;
	
	@FXML private TextField bundleNameTextField;
	
	@FXML private ChoiceBox<String> itemChoiceBox;
	@FXML private Spinner<Integer> itemQuantitySpinner;
	@FXML private Button addEditButton;
	
	@FXML private ListView<String> itemsInBundleListView;
	
	@FXML private TextField discountTextField;
	
	@FXML private Button modifyButton;
	@FXML private Button deleteButton;
	@FXML private Text returnMessageText;
	@FXML private ImageView winterBackground;
	@FXML private ImageView fallBackground;
	@FXML private ImageView summerBackground;
	@FXML private ImageView springBackground;
	
	private String myDate = ClimbSafeController.getNMCDate().toString();
	private String month = myDate.split("-")[1];
	private String day = myDate.split("-")[2];
	
	private List<String> itemNames = new ArrayList<>();
	private List<Integer> itemQuantities = new ArrayList<>();
	private TOBundle b = null;
	
	/**	
	 * Initializes the page. It changes the background depending on the date setup in NMC.
	 * @author Enzo, Joey and SeJong Yoo
	 * @param event
	 */
	
	public void initialize() {
		if (Integer.parseInt(month) < 3) {
			winterBackground.setOpacity(1);
			summerBackground.setOpacity(0);
			fallBackground.setOpacity(0);
			springBackground.setOpacity(0);
			
		}else if (Integer.parseInt(month) == 3) {
			if (Integer.parseInt(day) < 20) {
				winterBackground.setOpacity(1);
				summerBackground.setOpacity(0);
				fallBackground.setOpacity(0);
				springBackground.setOpacity(0);
			}else {
				springBackground.setOpacity(1);
				winterBackground.setOpacity(0);
				summerBackground.setOpacity(0);
				fallBackground.setOpacity(0);
			}
		}else if (Integer.parseInt(month)< 6 && Integer.parseInt(month) > 3) {
			springBackground.setOpacity(1);
			winterBackground.setOpacity(0);
			summerBackground.setOpacity(0);
			fallBackground.setOpacity(0);
		}else if (Integer.parseInt(month) == 6) {
			if (Integer.parseInt(day) < 20) {
				springBackground.setOpacity(1);
				winterBackground.setOpacity(0);
				summerBackground.setOpacity(0);
				fallBackground.setOpacity(0);
			}else {
				summerBackground.setOpacity(1);
				winterBackground.setOpacity(0);
				springBackground.setOpacity(0);
				fallBackground.setOpacity(0);
			}
		}else if (Integer.parseInt(month) < 9 && Integer.parseInt(month) > 6) {
			summerBackground.setOpacity(1);
			winterBackground.setOpacity(0);
			springBackground.setOpacity(0);
			fallBackground.setOpacity(0);
		}else if(Integer.parseInt(month) == 9) {
			if (Integer.parseInt(day) < 22) {
				summerBackground.setOpacity(1);
				winterBackground.setOpacity(0);
				springBackground.setOpacity(0);
				fallBackground.setOpacity(0);
			}else {
				fallBackground.setOpacity(1);
				summerBackground.setOpacity(0);
				winterBackground.setOpacity(0);
				springBackground.setOpacity(0);
			}
		}else if (Integer.parseInt(month) < 12 && Integer.parseInt(month) > 9){
			fallBackground.setOpacity(1);
			summerBackground.setOpacity(0);
			winterBackground.setOpacity(0);
			springBackground.setOpacity(0);
		}else if(Integer.parseInt(month) == 12) {
			if (Integer.parseInt(day) < 21) {
				fallBackground.setOpacity(1);
				summerBackground.setOpacity(0);
				winterBackground.setOpacity(0);
				springBackground.setOpacity(0);
			}else {
				winterBackground.setOpacity(1);
				summerBackground.setOpacity(0);
				fallBackground.setOpacity(0);
				springBackground.setOpacity(0);
			}
		}
		equipmentBundleChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			equipmentBundleChoiceBox.setItems(ViewUtils.getBundles());
			equipmentBundleChoiceBox.setValue(null);
		});
		
		itemChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			itemChoiceBox.setItems(ViewUtils.getEquipments());
			itemChoiceBox.setValue(null);
		});
		
		bundleNameTextField.setText("");
		
		itemQuantitySpinner.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			int minQuantity = 0;
			int maxQuantity = 99;
			int initQuantity = 0;
			SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minQuantity, maxQuantity, initQuantity);
			itemQuantitySpinner.setValueFactory(valueFactory);
		});
		discountTextField.setText("");
		returnMessageText.setText("");
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(equipmentBundleChoiceBox);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(itemChoiceBox);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(itemQuantitySpinner);
		
	}
	
	@FXML
	public void equipmentBundleSearchClicked(ActionEvent event) {
		if(equipmentBundleChoiceBox.getValue() != null) {
			List<TOBundle> myBundles = ClimbSafeController.getTOBundle();
			for(TOBundle bundle: myBundles) {
				if (bundle.getName().equals(equipmentBundleChoiceBox.getValue().split(":")[0])) {
					b = bundle;
				}
			}
		}
		if(b != null) {
			itemNames = ViewUtils.getBundleItems(b.getName());
			itemQuantities = ViewUtils.getBundleQuantity(b.getName());
			bundleNameTextField.setText(b.getName());
			discountTextField.setText(String.valueOf(b.getDiscount()));
			itemsInBundleListView.setItems(ViewUtils.getBundleItemsAndQuantity(b.getName()));
			ClimbSafeFxmlView.getInstance().refresh();
			equipmentBundleChoiceBox.setValue(b.getName());
		}
	}
	
	//Event Listener on Button[#addEditItemClicked].onAction
		@FXML
		public void addEditItemClicked(ActionEvent event) {
			ObservableList<String> itemaNameAndQuantityList = FXCollections.observableArrayList();
			String itemName = "";
			if(itemChoiceBox.getValue() != null) {
				itemName = itemChoiceBox.getValue().toString();
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
				itemChoiceBox.setValue(null);
				itemQuantitySpinner.getValueFactory().setValue(0);
			}

			for(int i =0; i<itemNames.size(); i++) {
				itemaNameAndQuantityList.add(itemQuantities.get(i) + " " + itemNames.get(i));
			}

			itemsInBundleListView.setItems(itemaNameAndQuantityList);
			ClimbSafeFxmlView.getInstance().refresh();
			if(b!= null) {
				equipmentBundleChoiceBox.setValue(b.getName());
			}
		}
	
	@FXML
	public void modifyEquipmentBundleClicked(ActionEvent event) {
		String oldName = equipmentBundleChoiceBox.getValue();
		String newName = bundleNameTextField.getText();
		int discount = getNumberFromField(discountTextField);
		if(oldName != "" && newName != "" && discount != -1) {
			try {
				if(successful(() -> ClimbSafeFeatureSet5Controller.updateEquipmentBundle(oldName, newName, discount, itemNames, itemQuantities))) {
					equipmentBundleChoiceBox.setValue(null);
					bundleNameTextField.setText("");
					itemChoiceBox.setValue(null);
					itemQuantitySpinner.setValueFactory(null);
					itemsInBundleListView.setItems(null);
					discountTextField.setText("");
					ClimbSafeFxmlView.getInstance().refresh();
					returnMessageText.setText("Equipment Bundle updated successfully");
				}
			} catch (RuntimeException e) {
				ViewUtils.showError(e.getMessage());
			}
		}
		
	}
	
	@FXML
	public void deleteEquipmentBundleClicked(ActionEvent event) {
		String name = equipmentBundleChoiceBox.getValue().split(":")[0];
		
		try {
			if(successful(() -> ClimbSafeFeatureSet6Controller.deleteEquipmentBundle(name))) {
				equipmentBundleChoiceBox.setValue(null);
				bundleNameTextField.setText("");
				itemChoiceBox.setValue(null);
				itemQuantitySpinner.setValueFactory(null);
				itemsInBundleListView.setItems(null);
				discountTextField.setText("");
				ClimbSafeFxmlView.getInstance().refresh();
				returnMessageText.setText("Equipment deleted successfully");
			}
			
		} catch (RuntimeException e)  {
			ViewUtils.showError(e.getMessage());
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
