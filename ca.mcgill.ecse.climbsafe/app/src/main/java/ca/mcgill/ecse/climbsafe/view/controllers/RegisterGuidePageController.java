package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterGuidePageController {
	
	@FXML private TextField name;
	@FXML private TextField email;
	@FXML private PasswordField password;
	@FXML private TextField emergencyContact; 
	@FXML private Button registerGuide;
	@FXML private Text returnMessageText;

	public void initialize() {
		
		name.setText("");
		email.setText("");
		password.setText("");
		emergencyContact.setText("");
		returnMessageText.setText("");
	}
		
	@FXML public void registerGuideClicked(ActionEvent event) {
		String Name = name.getText();
		String Email = email.getText();
		String Password = password.getText();
		String EmergencyContact = emergencyContact.getText();
		
	
	if(Name == "" || Email == "" || Password == "" || EmergencyContact == "") {
		ViewUtils.showError("Please fill out all of the field");
	} else {
		try {
			if(successful(() -> ClimbSafeFeatureSet3Controller.registerGuide(Email, Password, Name, EmergencyContact))) {
				name.setText("");
				email.setText("");
				password.setText("");
				emergencyContact.setText("");
				returnMessageText.setText("Guide updated successfully");
	}
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
	}
		
		
		
		
	}		
}