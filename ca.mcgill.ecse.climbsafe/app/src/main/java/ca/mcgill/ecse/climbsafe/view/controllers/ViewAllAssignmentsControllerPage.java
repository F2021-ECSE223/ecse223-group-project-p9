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
import javafx.scene.control.cell.PropertyValueFactory;
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
  private TableColumn<TOTableAssignment, String> startToEndColumn;
  @FXML
  private TableColumn<TOTableAssignment, String> tripStatusColumn;
  @FXML
  private TableColumn<TOTableAssignment, String> refundColumn;
  @FXML
  private TableColumn<TOTableAssignment, String> equipCostColumn;
  @FXML
  private TableColumn<TOTableAssignment, String> guideCostColumn;
  @FXML
  private TableView<TOTableAssignment> assignmentTable;
  @FXML
  private Text assignmentCompletionText;
  
  public void initialize() {
	  memberEmailColumn.setCellValueFactory(new PropertyValueFactory<>("memberEmail"));
	  memberNameColumn.setCellValueFactory(new PropertyValueFactory<>("memberName"));
	  guideEmailColumn.setCellValueFactory(new PropertyValueFactory<>("guideEmail"));
	  guideNameColumn.setCellValueFactory(new PropertyValueFactory<>("guideName"));
	  codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
	  startToEndColumn.setCellValueFactory(new PropertyValueFactory<>("startToEnd"));
	  tripStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
	  refundColumn.setCellValueFactory(new PropertyValueFactory<>("refund"));
	  equipCostColumn.setCellValueFactory(new PropertyValueFactory<>("equipCost"));
	  guideCostColumn.setCellValueFactory(new PropertyValueFactory<>("guideCost"));




	  List<TOAssignment> assignments = ClimbSafeFeatureSet6Controller.getAssignments();
	  if(!(ClimbSafeFeatureSet6Controller.getAssignments().isEmpty())) {
		  if(assignments.size()==ClimbSafeController.getTOMembers().size()) {
			  assignmentCompletionText.setText("Assignments initialized.");
		  }else {
			assignmentCompletionText.setText("Assignments initialized (incomplete for some members).");
		  }
			 
	  }else {
			 assignmentCompletionText.setText("Please initiate assignments before viewing.");
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

  @FXML
  public void initiateClicked(ActionEvent event) {
	  List<TOAssignment> assignments = ClimbSafeFeatureSet6Controller.getAssignments();
		  try {
			  if(assignments.isEmpty()) {
					 if(successful(() -> AssignmentController.initiateAssignment())) {
						 assignmentCompletionText.setText("Assignments initialized.");
						 List<TOTableAssignment> assignmentss = ClimbSafeController.getTOTableAssignments();
						 assignmentTable.getItems().clear();
						 for(TOTableAssignment a: assignmentss) {
							 assignmentTable.getItems().add(a);
						 }
					 }else if(!ClimbSafeFeatureSet6Controller.getAssignments().isEmpty()) {
						assignmentCompletionText.setText("Assignments initialized (incomplete for some members).");
						List<TOTableAssignment> assignmentss = ClimbSafeController.getTOTableAssignments();
						assignmentTable.getItems().clear();
						for(TOTableAssignment a: assignmentss) {
							assignmentTable.getItems().add(a);
						}
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