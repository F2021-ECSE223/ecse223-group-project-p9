package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.util.List;

import ca.mcgill.ecse.climbsafe.controller.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
  public void viewClicked(ActionEvent event) {
    List<TOTableAssignment> assignments = ClimbSafeController.getTOTableAssignments();
    for(TOTableAssignment a: assignments) {
    	assignmentTable.getItems().add(a);
    }
  }

  // Event Listener on Button[#addRouteButton].onAction
  @FXML
  public void initiateClicked(ActionEvent event) {
    // omitted
  }

  // Event Listener on Button[#addBusButton].onAction
  @FXML
  public void addBusClicked(ActionEvent event) {
    // omitted
  }
}