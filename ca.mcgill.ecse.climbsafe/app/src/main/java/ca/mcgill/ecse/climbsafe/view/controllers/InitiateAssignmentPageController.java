package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.*;
import ca.mcgill.ecse.climbsafe.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class InitiateAssignmentPageController {
  @FXML
  private Button yesButton;
  @FXML
  private Text completeText;


  @FXML
  public void yesClicked(ActionEvent event) {
	  try {
			if(successful(() -> AssignmentController.initiateAssignment())) {
				completeText.setText("Complete!");
				
			}

		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
  }


}