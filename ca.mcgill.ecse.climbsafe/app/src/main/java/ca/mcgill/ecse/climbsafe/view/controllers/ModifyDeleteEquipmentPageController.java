package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.util.List;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.TOEquipment;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
/**
 * Assigns behavior of the TextFields and Buttons in the ModifyDeleteEquipment view page
 * @author Enzo Benoit-Jeannin
 *
 */
public class ModifyDeleteEquipmentPageController {
	
	@FXML private TextField newNameModifyEquipment;
	@FXML private Button modifyEquipmentButton;
	@FXML private ChoiceBox<String> deleteEquipmentName;
	@FXML private Button deleteEquipmentButton;
	@FXML private ChoiceBox<String> equipmentChoiceBox;
	@FXML private Spinner<Integer> weightSpinner;
	@FXML private Spinner<Integer> priceSpinner;
	@FXML private Text returnMessageText;
	@FXML private ImageView winterBackground;
	@FXML private ImageView fallBackground;
	@FXML private ImageView summerBackground;
	@FXML private ImageView springBackground;
	
	String myDate = ClimbSafeController.getNMCDate().toString();
	String month = myDate.split("-")[1];
	String day = myDate.split("-")[2];
	
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
		
		equipmentChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			equipmentChoiceBox.setItems(ViewUtils.getEquipments());
			equipmentChoiceBox.setValue(null);
		});
		deleteEquipmentName.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			deleteEquipmentName.setItems(ViewUtils.getEquipments());
			deleteEquipmentName.setValue(null);
		});
		weightSpinner.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			int minQuantity = 0;
			int maxQuantity = 9999999;
			int initQuantity = 0;
			SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minQuantity, maxQuantity, initQuantity);
			weightSpinner.setValueFactory(valueFactory);
		});
		priceSpinner.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			int minQuantity = 0;
			int maxQuantity = 9999999;
			int initQuantity = 0;
			SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minQuantity, maxQuantity, initQuantity);
			priceSpinner.setValueFactory(valueFactory);
		});
		returnMessageText.setText("");
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(equipmentChoiceBox);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(deleteEquipmentName);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(weightSpinner);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(priceSpinner);
	}
	
	/**
	 * Describes what pressing the modifyEquipment Button does (event listener)
	 * @author Enzo Benoit-Jeannin
	 * @param event
	 */
	@FXML
	public void modifyEquipmentClick(ActionEvent event) {
		String oldName = equipmentChoiceBox.getValue();
		String newName = newNameModifyEquipment.getText();
		int weight = weightSpinner.getValue();
		int price = priceSpinner.getValue();
		
		//try updating the equipment or catch the error
		if(oldName != null && newName != "" && weight != -1 && price != -1) {
			try {
				if(successful(() -> ClimbSafeFeatureSet4Controller.updateEquipment(oldName, newName, weight, price))) {
					equipmentChoiceBox.setValue(null);
					newNameModifyEquipment.setText("");
					weightSpinner.getValueFactory().setValue(0);
					priceSpinner.getValueFactory().setValue(0);
					ClimbSafeFxmlView.getInstance().refresh();
					returnMessageText.setText("Equipment updated successfully");
				}
			} catch (RuntimeException e) {
				ViewUtils.showError(e.getMessage());
			}
		}
	}
	
	@FXML
	public void equipmentSearchClicked(ActionEvent event) {
		List<TOEquipment> e = ClimbSafeController.getTOEquipment();
		for (TOEquipment myTOEquipment : e) {
			if (myTOEquipment.getName() == equipmentChoiceBox.getValue()) {
				newNameModifyEquipment.setText(myTOEquipment.getName());
				weightSpinner.getValueFactory().setValue(myTOEquipment.getWeight());
				priceSpinner.getValueFactory().setValue(myTOEquipment.getPricePerWeek());
			}
		}
	}
	
	/**
	 * Describes what pressing the deleteEquipment Button does (event listener)
	 * @author Enzo Benoit-Jeannin
	 * @param event
	 */
	@FXML
	public void deleteEquipmentClick(ActionEvent event) {
		String name = deleteEquipmentName.getValue();
		
		//try deleting the equipment or catch the error
		if(name != null) {
			try {
				if(successful(() -> ClimbSafeFeatureSet6Controller.deleteEquipment(name))) {
					deleteEquipmentName.setValue(null);
					ClimbSafeFxmlView.getInstance().refresh();
					returnMessageText.setText("Equipment deleted successfully");
				}
			} catch (RuntimeException e) {
				ViewUtils.showError(e.getMessage());
			}
		}
		
	}
	
	private int getNumberFromField(TextField field) {
		if(field.getText() != "") {
			return Integer.parseInt(field.getText());
		}else {
			return -1;
		}
	}
	
	
}