package ca.mcgill.ecse.climbsafe.view.controllers;
import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.sql.Date;
import java.util.List;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import ca.mcgill.ecse.climbsafe.view.pages.;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class StartFinishCancelTripController {

	@FXML private TextField numWeekTextField;
	@FXML private TextField memberEmailFinishTripTextField;
	@FXML private TextField memberEmailCancelTripTextField;
	@FXML private Button startTrip;
	@FXML private Button finishMemberTrip;
	@FXML private Button cancelMemberTrip;

	public void startTripsForWeek(ActionEvent event) {
		int week = Integer.parseInt(numWeekTextField.getText());
		AssignmentController.startTrips(week);
	}
	
	public void finishMemberTrip(ActionEvent event) {
		String email = memberEmailTextField.getText();
		AssignmentController.finishTrip(email);
	}
	
	public void cancelMemberTrip(ActionEvent event) {
		String email = memberEmailTextField.getText();
		AssignmentController.cancelTrip(email);
	}
}

