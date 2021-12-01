package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
	public void addEquipmentClick(ActionEvent event) {
		String name = addEquipmentName.getText();
		int weight = getNumberFromField(addEquipmentWeight);
		int price = getNumberFromField(addEquipmentPrice);
		//try adding the new equipment or catch the error
		try {
			if(name!="" && weight != -1 && price != -1) {
				if(successful(() -> ClimbSafeFeatureSet4Controller.addEquipment(name, weight, price))) {
					addEquipmentName.setText("");
					addEquipmentWeight.setText("");
					addEquipmentPrice.setText("");
					ClimbSafeFxmlView.getInstance().refresh();
				}
			}
		} catch (RuntimeException e) {
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