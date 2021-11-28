import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import ca.mcgill.ecse.climbsafe.controller.*;
import ca.mcgill.ecse.climbsafe.model.*;

public class PayForMemberTripPageController {
  @FXML
  private TextField memberEmailTextField;
  @FXML
  private TextField codeTextField;
  @FXML
  private Button payForTripButton;


  // Event Listener on Button[#addDriverButton].onAction
  @FXML
  public void payClicked(ActionEvent event) {
    String memberEmail = memberEmailTextField.getText();
    String code = codeTextField.getText();
    try {
    	
	
	} catch (RuntimeException e) {
		ViewUtils.showError(e.getMessage());
	}
  }

}