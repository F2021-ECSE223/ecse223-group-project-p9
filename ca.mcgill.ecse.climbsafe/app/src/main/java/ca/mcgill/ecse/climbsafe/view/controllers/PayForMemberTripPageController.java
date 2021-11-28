package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.*;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.view.controllers.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PayForMemberTripPageController {
  @FXML
  private TextField codeTextField;
  @FXML
  private Button payForTripButton;
  @FXML
  private TextField memberEmailTextField;
  @FXML
  private Text completeText;



  @FXML
  public void payClicked(ActionEvent event) {
    String code = codeTextField.getText();
    String memberEmail = memberEmailTextField.getText();
    try {    	
		if(successful(() -> AssignmentController.payForTrip(memberEmail, code))) {
			codeTextField.setText("");
			memberEmailTextField.setText("");
			completeText.setText("Payment authorized.");
		}	
	} catch (RuntimeException e) {
		ViewUtils.showError(e.getMessage());
	}
  }
  

  

}
