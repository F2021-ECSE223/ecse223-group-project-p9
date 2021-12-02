package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.util.List;

import ca.mcgill.ecse.climbsafe.controller.*;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class ViewAllAssignmentsControllerPage {

  @FXML
  private Button initiateButton;
  @FXML
  private Button viewButton;
  @FXML
  private TableColumn<TOTableAssignment, String> memberNameColumn;
  @FXML
  private TableColumn<TOTableAssignment, String> memberEmailColumn;
  @FXML
  private TableColumn<TOTableAssignment, String> guideNameColumn;
  @FXML
  private TableColumn<TOTableAssignment, String> guideEmailColumn;
  @FXML
  private TableColumn<TOTableAssignment, String> codeColumn;
  @FXML
  private TableColumn<TOTableAssignment, String> startWeekColumn;
  @FXML
  private TableColumn<TOTableAssignment, String> endWeekColumn;
  @FXML
  private TableColumn<TOTableAssignment, String> tripStatusColumn;
  @FXML
  private TableColumn<TOTableAssignment, String> refundColumn;
  @FXML
  private TableColumn<TOTableAssignment, String> guideCostColumn;
  @FXML
  private TableColumn<TOTableAssignment, String> equipCostColumn;
  @FXML
  private TableView<TOTableAssignment> assignmentTable;
  @FXML
  private Text assignmentCompletionText;
  
  public void initialize() {
	  //add if statement for when no data in system
	  List<TOAssignment> assignments = ClimbSafeFeatureSet6Controller.getAssignments();
	  
	  if(!(assignments.isEmpty())) {
			 assignmentCompletionText.setText("Assignments initialized.");
	  }else {
			 assignmentCompletionText.setText("Please initialize assignments before viewing.");
	  }
	}

  @FXML
  public void viewClicked(ActionEvent event) {
    List<TOTableAssignment> assignments = ClimbSafeController.getTOTableAssignments();
    assignmentTable.getItems().clear();
    for(TOTableAssignment a: assignments) {
    	assignmentTable.getItems().add(a);
    }
  }

  // Event Listener on Button[#addRouteButton].onAction
  @FXML
  public void initiateClicked(ActionEvent event) {
	  List<TOAssignment> assignments = ClimbSafeFeatureSet6Controller.getAssignments();

		  //need to refresh members/guides in the case where they are updated before assignment
		  try {
			  if(assignments.isEmpty()) {
					 if(successful(() -> AssignmentController.initiateAssignment())) {
						 assignmentCompletionText.setText("Assignments initialized.");
					 }
			  }else {
				  String e = "Assignments already initialized, cannot initialize again.";
				  ViewUtils.showError(e);
			  }
				

			} catch (RuntimeException e) {
				ViewUtils.showError(e.getMessage());
			}
	  
  }

  
}