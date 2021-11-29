package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterGuidePageController {
	
	@FXML private TextField name;
	@FXML private TextField email;
	@FXML private PasswordField password;
	@FXML private TextField emergencyContact; 
	@FXML private Button registerGuide;

	public void registerGuideRegister(ActionEvent action) {
		
		String Name = name.getText();
		String Email = email.getText();
		String Password = password.getText();
		String EmergencyContact = emergencyContact.getText();
		
		
	}
	

}
