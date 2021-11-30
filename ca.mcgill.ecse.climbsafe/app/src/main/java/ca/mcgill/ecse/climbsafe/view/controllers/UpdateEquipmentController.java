package ca.mcgill.ecse.climbsafe.view.controllers;

import java.util.ArrayList;
import java.util.List;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet5Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class UpdateEquipmentController {
	
	@FXML private TextField oldNameModifyEquipmentBundle;
	@FXML private TextField newNameModifyEquipmentBundle;
	@FXML private TextField priceModifyEquipementBundle;
	@FXML private TextField deleteEquipmentBundleName;
	@FXML private Button modifyEquipmentBundleButton;
	@FXML private Button deleteEquipmentBundleButton;
	
	public void deleteEquipmentBundleClicked(ActionEvent event) {
		String name = newNameModifyEquipmentBundle.getText();
		
		try {
			if(successful(() -> ClimbSafeFeatureSet6Controller.deleteEquipment(name))) {
				deleteEquipmentBundleName.setText("");
				ClimbSafeFxmlView.getInstance().refresh();
			}
			
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
	}

}
