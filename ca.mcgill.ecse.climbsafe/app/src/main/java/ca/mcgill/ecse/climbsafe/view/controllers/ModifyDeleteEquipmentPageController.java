package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
/**
 * Assigns behavior of the TextFields and Buttons in the ModifyDeleteEquipment view page
 * @author Enzo Benoit-Jeannin
 *
 */
public class ModifyDeleteEquipmentPageController {
	
	@FXML private TextField oldNameModifyEquipment;
	@FXML private TextField newNameModifyEquipment;
	@FXML private TextField weightModifyEquipment;
	@FXML private TextField priceModifyEquipment;
	@FXML private Button modifyEquipmentButton;
	@FXML private TextField deleteEquipmentName;
	@FXML private Button deleteEquipmentButton;
	
	/**
	 * Describes what pressing the modifyEquipment Button does (event listener)
	 * @author Enzo Benoit-Jeannin
	 * @param event
	 */
	@FXML
	public void modifyEquipmentClick(ActionEvent event) {
		String oldName = oldNameModifyEquipment.getText();
		String newName = newNameModifyEquipment.getText();
		int weight = Integer.parseInt(weightModifyEquipment.getText());
		int price = Integer.parseInt(priceModifyEquipment.getText());
		
		//try updating the equipment or catch the error
		try {
			if(successful(() -> ClimbSafeFeatureSet4Controller.updateEquipment(oldName, newName, weight, price))) {
				oldNameModifyEquipment.setText("");
				newNameModifyEquipment.setText("");
				weightModifyEquipment.setText("");
				priceModifyEquipment.setText("");
			}
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
	}
	
	/**
	 * Describes what pressing the deleteEquipment Button does (event listener)
	 * @author Enzo Benoit-Jeannin
	 * @param event
	 */
	@FXML
	public void deleteEquipmentClick(ActionEvent event) {
		String name = newNameModifyEquipment.getText();
		
		//try deleting the equipment or catch the error
		try {
			if(successful(() -> ClimbSafeFeatureSet6Controller.deleteEquipment(name))) {
				deleteEquipmentName.setText("");
			}
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
	}
	
	
}