package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import ca.mcgill.ecse.climbsafe.controller.*;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PayForMemberTripPageController {
  @FXML
  private TextField codeTextField;
  @FXML
  private Button payForTripButton;
  @FXML
  private Text completeText;
  @FXML
  private ChoiceBox<String> memberChoiceBox;

  public void initialize() {
	  memberChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			memberChoiceBox.setItems(ViewUtils.getMembers());
			memberChoiceBox.setValue(null);
		});	
	  ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);
	}

  @FXML
  public void payClicked(ActionEvent event) {
    String code = codeTextField.getText();
    String memberEmail = memberChoiceBox.getValue();   
    try {    	
		if(successful(() -> AssignmentController.payForTrip(memberEmail, code))) {
			codeTextField.setText("");
			completeText.setText("Payment authorized.");
			ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);

		}else {
			completeText.setText("Could not be completed.");
			ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);

		}
	} catch (RuntimeException e) {
		ViewUtils.showError(e.getMessage());
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);

	}
  }
  

  

}
