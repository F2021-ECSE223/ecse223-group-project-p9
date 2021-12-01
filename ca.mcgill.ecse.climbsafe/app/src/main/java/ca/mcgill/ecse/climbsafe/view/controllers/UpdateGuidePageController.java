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
import javafx.scene.control.PasswordField;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;


public class UpdateGuidePageController {
	
	@FXML private TextField nameTextField;
	@FXML private ChoiceBox<String> guideChoiceBox;
	@FXML private PasswordField passwordField;
	@FXML private TextField emergencyContactTextField;
	@FXML private CheckBox permissionCheckBox;
	private Guide g = null;
	@FXML private Button deleteGuideButton;
	@FXML private Button updateGuideButton;
	@FXML private Button guideSearchButton;
	
	public void initialize() {
		nameTextField.setText("");
		passwordField.setText("");
		emergencyContactTextField.setText("");
		permissionCheckBox.setSelected(false);
		guideChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, a -> {
			guideChoiceBox.setItems(ViewUtils.getGuides());
			guideChoiceBox.setValue(null);
		});
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(guideChoiceBox);
		
//		ClimbSafeFxmlView.getInstance().registerRefreshEvent(guideChoiceBox);
//		
//		passwordField.setText("");
//		nameTextField.setText("");
//		emergencyContactTextField.setText("");
//		guideChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, a -> {
//			guideChoiceBox.setItems(ViewUtils.getGuides());
//			guideChoiceBox.setValue(null);
//		});
//		ClimbSafeFxmlView.getInstance().registerRefreshEvent(guideChoiceBox);
	}
	
	@FXML
	public void guideSearchClicked(ActionEvent event) {
		g = ClimbSafeController.getGuide(guideChoiceBox.getValue());
		if(g != null) {
			nameTextField.setText(g.getName());
			passwordField.setText(g.getPassword());
			emergencyContactTextField.setText(g.getEmergencyContact());
			ClimbSafeFxmlView.getInstance().refresh();
			guideChoiceBox.setValue(g.getEmail());
		}
	}
	
	
	@FXML
	public void updateGuideClicked(ActionEvent event) {
		String name = nameTextField.getText();
		String email = guideChoiceBox.getValue();
		String password = passwordField.getText();
		String emergencyContact = emergencyContactTextField.getText();
		
		try {
			if(successful(() -> ClimbSafeFeatureSet3Controller.updateGuide(email, password, name, emergencyContact))) {
				guideChoiceBox.setValue(null);
				passwordField.setText("");
				nameTextField.setText("");
				emergencyContactTextField.setText("");
				ClimbSafeFxmlView.getInstance().refresh();
	}
		}
		catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
	}
	
	@FXML
	public void deleteGuideClicked(ActionEvent event) {
		String email = guideChoiceBox.getValue();
		boolean confirmDelete = permissionCheckBox.isSelected();
		if(confirmDelete) {
			try {
				if(successful(() -> ClimbSafeFeatureSet1Controller.deleteMember(email))) {
					guideChoiceBox.setValue(null);
					passwordField.setText("");
					nameTextField.setText("");
					emergencyContactTextField.setText("");
					permissionCheckBox.setSelected(false);
					ClimbSafeFxmlView.getInstance().refresh();
				}
			} catch (RuntimeException e) {
				ViewUtils.showError(e.getMessage());
			}
			ClimbSafeFxmlView.getInstance().refresh();
		}
	}

}













