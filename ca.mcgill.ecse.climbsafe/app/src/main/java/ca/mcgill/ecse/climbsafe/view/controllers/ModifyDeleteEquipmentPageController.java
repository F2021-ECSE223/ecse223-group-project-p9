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
import javafx.scene.control.TextField;
/**
 * Assigns behavior of the TextFields and Buttons in the ModifyDeleteEquipment view page
 * @author Enzo Benoit-Jeannin
 *
 */
public class ModifyDeleteEquipmentPageController {
	
//	@FXML private TextField oldNameModifyEquipment;
	@FXML private TextField newNameModifyEquipment;
	@FXML private TextField weightModifyEquipment;
	@FXML private TextField priceModifyEquipment;
	@FXML private Button modifyEquipmentButton;
	@FXML private ChoiceBox<String> deleteEquipmentName;
	@FXML private Button deleteEquipmentButton;
	@FXML private ChoiceBox<String> equipmentChoiceBox;
	
	public void initialize() {
		equipmentChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			equipmentChoiceBox.setItems(ViewUtils.getEquipments());
			equipmentChoiceBox.setValue(null);
		});
		deleteEquipmentName.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			deleteEquipmentName.setItems(ViewUtils.getEquipments());
			deleteEquipmentName.setValue(null);
		});
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(equipmentChoiceBox);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(deleteEquipmentName);
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
		int weight = Integer.parseInt(weightModifyEquipment.getText());
		int price = Integer.parseInt(priceModifyEquipment.getText());
		
		//try updating the equipment or catch the error
		try {
			if(successful(() -> ClimbSafeFeatureSet4Controller.updateEquipment(oldName, newName, weight, price))) {
				equipmentChoiceBox.setValue(null);
				newNameModifyEquipment.setText("");
				weightModifyEquipment.setText("");
				priceModifyEquipment.setText("");
				ClimbSafeFxmlView.getInstance().refresh();
			}
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
	}
	
	@FXML
	public void equipmentSearchClicked(ActionEvent event) {
		List<TOEquipment> e = ClimbSafeController.getTOEquipment();
		for (TOEquipment myTOEquipment : e) {
			if (myTOEquipment.getName() == equipmentChoiceBox.getValue()) {
				newNameModifyEquipment.setText(myTOEquipment.getName());
				weightModifyEquipment.setText(String.valueOf(myTOEquipment.getWeight()));
				priceModifyEquipment.setText(String.valueOf(myTOEquipment.getPricePerWeek()));
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
		try {
			if(successful(() -> ClimbSafeFeatureSet6Controller.deleteEquipment(name))) {
				deleteEquipmentName.setValue(null);
				ClimbSafeFxmlView.getInstance().refresh();
			}
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
	}
	
	
}