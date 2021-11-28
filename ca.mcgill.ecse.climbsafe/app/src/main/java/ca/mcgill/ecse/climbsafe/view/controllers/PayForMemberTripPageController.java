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
  private Button exitButton;
  @FXML
  private Text memberEmailText;
  @FXML
  private Text completeText;



  @FXML
  public void payClicked(ActionEvent event) {
    String code = codeTextField.getText();
    try {
		if(successful(() -> /*email from assignment that was clicked(from view assignments?).payForTrip(code);*/)) {

			codeTextField.setText("");
    	
		}
	
	} catch (RuntimeException e) {
		ViewUtils.showError(e.getMessage());
	}
  }
  
  @FXML
  public void exitClicked(ActionEvent event) {
    // close window
  }

  

}
