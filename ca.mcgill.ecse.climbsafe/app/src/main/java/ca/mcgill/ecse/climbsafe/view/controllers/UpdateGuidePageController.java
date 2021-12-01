package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.util.List;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
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
	private Guide g = null;
	@FXML private Button deleteGuide;
	@FXML private Button updateGuide;
	
	public void initialize() {
		name.setText("");
		password.setText("");
		emergencyContact.setText("");
		guideChoice.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, a -> {
			guideChoice.setItems(ViewUtils.getGuides());
			guideChoice.setValue(null);
		});
		
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(guideChoice);
	}
	
	@FXML
	public void guideSearch(ActionEvent event) {
		name.setText(g.getName());
		password.setText(g.getPassword());
		emergencyContact.setText(g.getEmergencyContact());
		ClimbSafeFxmlView.getInstance().refresh();
		g = ClimbSafeController.getGuide(guideChoice.getValue());
		guideChoice.setValue(g.getEmail());

	}
	
	
	@FXML
	public void updateGuide(ActionEvent event) {
		String Name = name.getText();
		String Email = guideChoice.getValue();
		String Password = password.getText();
		String EmergencyContact = emergencyContact.getText();
		
		try {
			if(successful(() -> ClimbSafeFeatureSet3Controller.updateGuide(Name, Email, Password, EmergencyContact))) {
				guideChoice.setValue(null);
				password.setText("");
				name.setText("");
				emergencyContact.setText("");
				ClimbSafeFxmlView.getInstance().refresh();

	}
		}
		catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
	}
	
	@FXML
	public void deleteGuide(ActionEvent event) {
		String email = guideChoice.getValue();
		try {
			if(successful(() -> ClimbSafeFeatureSet1Controller.deleteMember(email))) {
				guideChoice.setValue(null);
				password.setText("");
				name.setText("");
				emergencyContact.setText("");
				ClimbSafeFxmlView.getInstance().refresh();
			}
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
		ClimbSafeFxmlView.getInstance().refresh();
	}

}













