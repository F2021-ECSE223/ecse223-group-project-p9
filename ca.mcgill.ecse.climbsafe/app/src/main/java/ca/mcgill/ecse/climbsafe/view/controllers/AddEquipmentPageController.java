package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddEquipmentPageController {
	
	@FXML private TextField addEquipmentName;
	@FXML private TextField addEquipmentWeight;
	@FXML private TextField addEquipmentPrice;
	@FXML private Button addEquipmentButton;

	/**
	 * Describes what pressing the addEquipment Button does (event listener)
	 * @author Enzo Benoit-Jeannin
	 * @param event
	 */
	@FXML
	public void addEquipmentButtonClicked(ActionEvent event) {
		String name = addEquipmentName.getText();
		int weight = Integer.parseInt(addEquipmentWeight.getText());
		int price = Integer.parseInt(addEquipmentPrice.getText());
		//try adding the new equipment or catch the error
		try {
			if(successful(() -> ClimbSafeFeatureSet4Controller.addEquipment(name, weight, price))) {
				addEquipmentName.setText("");
				addEquipmentWeight.setText("");
				addEquipmentPrice.setText("");
			}
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
	}
	
}