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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
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
	@FXML private ImageView winterBackground;
	@FXML private ImageView fallBackground;
	@FXML private ImageView summerBackground;
	@FXML private ImageView springBackground;
	@FXML private Label t1;
	@FXML private Label t2;
	@FXML private Label t3;
	@FXML private Label t4;
	@FXML private Label t5;
	@FXML private Label t6;

	private String myDate = ClimbSafeController.getNMCDate().toString();
	private String month = myDate.split("-")[1];
	private String day = myDate.split("-")[2];

	/**
	 * Initializes the page. It changes the background depending on the date setup in NMC.
	 * @author Enzo  and Joey 
	 */
	public void initialize() {
		Color fontColor = Color.BLACK;
		String hexCode = "#000000";
		if (Integer.parseInt(month) < 3) {
			winterBackground.setOpacity(1);
			summerBackground.setOpacity(0);
			fallBackground.setOpacity(0);
			springBackground.setOpacity(0);
			hexCode = "#2fc1ff";

		}else if (Integer.parseInt(month) == 3) {
			if (Integer.parseInt(day) < 20) {
				winterBackground.setOpacity(1);
				summerBackground.setOpacity(0);
				fallBackground.setOpacity(0);
				springBackground.setOpacity(0);
				hexCode = "#2fc1ff";
			}else {
				springBackground.setOpacity(1);
				winterBackground.setOpacity(0);
				summerBackground.setOpacity(0);
				fallBackground.setOpacity(0);
				hexCode = "#ce87bd";
			}
		}else if (Integer.parseInt(month)< 6 && Integer.parseInt(month) > 3) {
			springBackground.setOpacity(1);
			winterBackground.setOpacity(0);
			summerBackground.setOpacity(0);
			fallBackground.setOpacity(0);
			hexCode = "#ce87bd";
		}else if (Integer.parseInt(month) == 6) {
			if (Integer.parseInt(day) < 20) {
				springBackground.setOpacity(1);
				winterBackground.setOpacity(0);
				summerBackground.setOpacity(0);
				fallBackground.setOpacity(0);
				hexCode = "#ce87bd";
			}else {
				summerBackground.setOpacity(1);
				winterBackground.setOpacity(0);
				springBackground.setOpacity(0);
				fallBackground.setOpacity(0);
				hexCode = "#69bc4c";
			}
		}else if (Integer.parseInt(month) < 9 && Integer.parseInt(month) > 6) {
			summerBackground.setOpacity(1);
			winterBackground.setOpacity(0);
			springBackground.setOpacity(0);
			fallBackground.setOpacity(0);
			hexCode = "#69bc4c";
		}else if(Integer.parseInt(month) == 9) {
			if (Integer.parseInt(day) < 22) {
				summerBackground.setOpacity(1);
				winterBackground.setOpacity(0);
				springBackground.setOpacity(0);
				fallBackground.setOpacity(0);
				hexCode = "#69bc4c";
			}else {
				fallBackground.setOpacity(1);
				summerBackground.setOpacity(0);
				winterBackground.setOpacity(0);
				springBackground.setOpacity(0);
				hexCode = "#771102";
			}
		}else if (Integer.parseInt(month) < 12 && Integer.parseInt(month) > 9){
			fallBackground.setOpacity(1);
			summerBackground.setOpacity(0);
			winterBackground.setOpacity(0);
			springBackground.setOpacity(0);
			hexCode = "#771102";
		}else if(Integer.parseInt(month) == 12) {
			if (Integer.parseInt(day) < 21) {
				fallBackground.setOpacity(1);
				summerBackground.setOpacity(0);
				winterBackground.setOpacity(0);
				springBackground.setOpacity(0);
				hexCode = "#771102";
			}else {
				winterBackground.setOpacity(1);
				summerBackground.setOpacity(0);
				fallBackground.setOpacity(0);
				springBackground.setOpacity(0);
				hexCode = "#2fc1ff";
			}
		}
		fontColor = Color.web(hexCode);
		t1.setTextFill(fontColor);
		t2.setTextFill(fontColor);
		t3.setTextFill(fontColor);
		t4.setTextFill(fontColor);
		t5.setTextFill(fontColor);
		t6.setTextFill(fontColor);
		deleteGuideButton.setStyle("-fx-text-fill: " + hexCode);
		updateGuideButton.setStyle("-fx-text-fill: " + hexCode);
		guideSearchButton.setStyle("-fx-text-fill: " + hexCode);
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
	/**
	 * Enters guide info into test fields when button pressed
	 * @param event
	 * @author Joey
	 */
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

	/**
	 * Guide updated when button pressed
	 * @param event
	 * @author Joey
	 */
	@FXML
	public void updateGuideClicked(ActionEvent event) {
		String name = nameTextField.getText();
		String email = guideChoiceBox.getValue();
		String password = passwordField.getText();
		String emergencyContact = emergencyContactTextField.getText();
		if(email != null) {
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
	/**
	 * Deletes guide when button pressed
	 * @param event
	 * @author Joey
	 */
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













