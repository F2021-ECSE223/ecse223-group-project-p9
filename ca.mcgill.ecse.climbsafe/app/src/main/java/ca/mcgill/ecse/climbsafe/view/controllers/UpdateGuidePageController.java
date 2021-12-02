package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.util.List;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.controller.TOGuide;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class UpdateGuidePageController {

	@FXML private TextField nameTextField;
	@FXML private ChoiceBox<String> guideChoiceBox;
	@FXML private PasswordField passwordField;
	@FXML private TextField emergencyContactTextField;
	@FXML private CheckBox permissionCheckBox;
	@FXML private Button deleteGuideButton;
	@FXML private Button updateGuideButton;
	@FXML private Button guideSearchButton;
	@FXML private Text returnMessageText;

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
		returnMessageText.setText("");
	}

	@FXML
	public void guideSearchClicked(ActionEvent event) {
		List<TOGuide> g = ClimbSafeController.getTOGuides();
		for (TOGuide myTOGuide : g) {
			if (myTOGuide.getEmail() == guideChoiceBox.getValue()) {
				nameTextField.setText(myTOGuide.getName());
				passwordField.setText(myTOGuide.getPassword());
				emergencyContactTextField.setText(myTOGuide.getEmergencyContact());
				ClimbSafeFxmlView.getInstance().refresh();
				guideChoiceBox.setValue(myTOGuide.getEmail());
			}
		}
	}


	@FXML
	public void updateGuideClicked(ActionEvent event) {
		String name = nameTextField.getText();
		String email = guideChoiceBox.getValue();
		String password = passwordField.getText();
		String emergencyContact = emergencyContactTextField.getText();
		if(name != "" && email != null && password != "" && emergencyContact != "") {
			try {
				if(successful(() -> ClimbSafeFeatureSet3Controller.updateGuide(email, password, name, emergencyContact))) {
					guideChoiceBox.setValue(null);
					passwordField.setText("");
					nameTextField.setText("");
					emergencyContactTextField.setText("");
					permissionCheckBox.setSelected(false);
					ClimbSafeFxmlView.getInstance().refresh();
					returnMessageText.setText("Guide updated successfully");
				}
			}catch (RuntimeException e) {
				ViewUtils.showError(e.getMessage());
			}
		}
	}

	@FXML
	public void deleteGuideClicked(ActionEvent event) {
		String email = guideChoiceBox.getValue();
		boolean confirmDelete = permissionCheckBox.isSelected();
		if(confirmDelete && email != null) {
			try {
				if(successful(() -> ClimbSafeFeatureSet1Controller.deleteGuide(email))) {
					guideChoiceBox.setValue(null);
					passwordField.setText("");
					nameTextField.setText("");
					emergencyContactTextField.setText("");
					permissionCheckBox.setSelected(false);
					ClimbSafeFxmlView.getInstance().refresh();
					returnMessageText.setText("Guide deleted successfully");
				}
			} catch (RuntimeException e) {
				ViewUtils.showError(e.getMessage());
			}
			ClimbSafeFxmlView.getInstance().refresh();
		}
	}

}













