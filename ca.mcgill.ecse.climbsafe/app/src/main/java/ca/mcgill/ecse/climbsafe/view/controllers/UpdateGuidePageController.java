package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.util.List;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import ca.mcgill.ecse.climbsafe.view.pages.;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;


public class UpdateGuidePageController {
	
	@FXML private TextField name;
	@FXML private ChoiceBox<String> guideChoice;
	@FXML private PasswordField password;
	@FXML private TextField emergencyContact;
	@FXML private CheckBox permissionCheck;
	
	@FXML private Button deleteGuide;
	@FXML private Button updateGuide;
	
	public void initialize() {
		name.setText("");
		password.setText("");
		emergencyContact.setText("");
		guideChoice.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, a -> {
			guideChoice.setItems(ViewUtils.getGuides());
		});
	}

}
