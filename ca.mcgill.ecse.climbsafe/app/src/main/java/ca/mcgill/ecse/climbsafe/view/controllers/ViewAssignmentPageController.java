package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.*;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ViewAssignmentPageController {
  @FXML
  private Button viewButton;
  @FXML
  private Text memberEmailText;
  @FXML
  private Text memberNameText;
  @FXML
  private Text guideEmailText;
  @FXML
  private Text guideNameText;
  @FXML
  private Text hotelNameText;
  @FXML
  private Text startAndEndWeekText;
  @FXML
  private Text refundText;
  @FXML
  private Text tripStatusText;
  @FXML
  private Text totalCostText;
  @FXML
  private Text codeText;
  @FXML
  private ChoiceBox<String> memberChoiceBox;
  @FXML
  private Button InitiateAssignmentsButton;
  @FXML
  private Text assignmentCompletionText;
  private List<TOAssignment> assignments; 
  

  public void initialize() {
	  //add if statement for when no data in system
	  memberChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			memberChoiceBox.setItems(ViewUtils.getMembers());
			memberChoiceBox.setValue(null);
		});	
	  ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);
	  if(!(assignments==null)) {
			 assignmentCompletionText.setText("Assignments initialized.");
	  }else {
			 assignmentCompletionText.setText("Please initialize assignments before viewing.");
	  }
	}
  @FXML
  public void viewClicked(ActionEvent event) {
	  try {
		  ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);
		 if(assignments==null) {
			 String e = "Assignments not yet created.";
			 ViewUtils.showError(e);
			 ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);

		 }else if(memberChoiceBox.getValue().equals(null)) {
			 String e = "Please choose member.";
			 ViewUtils.showError(e);
			 ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);
		 }
		 else {
			String refund;
			String startAndEndWeek;
			int totalCost;
		 for(TOAssignment a : assignments) {
			if(a.getMemberEmail().equals(memberChoiceBox.getValue())) {
				tripStatusText.setText(a.getStatus());
				memberEmailText.setText(a.getMemberEmail());
				memberNameText.setText(a.getMemberName());
				refund =a.getRefundedPercentageAmount()+"%";
				refundText.setText(refund);
				startAndEndWeek = "From week "+a.getStartWeek()+" to week "+a.getEndWeek();
				startAndEndWeekText.setText(startAndEndWeek);
				if(a.getGuideEmail().equals(null)) {
					guideEmailText.setText("Guide not required");
					guideNameText.setText("Guide not required");
				}else {
					guideEmailText.setText(a.getGuideEmail());
					guideNameText.setText(a.getGuideName());
				}
				if(a.getHotelName().equals(null)) {
					hotelNameText.setText("Hotel not required");
				}else {
					hotelNameText.setText(a.getHotelName());

				}
				totalCost = a.getTotalCostForEquipment()+a.getTotalCostForGuide();
				totalCostText.setText(String.valueOf(totalCost));
				if(a.getAuthorizationCode().equals(null)) {
					codeText.setText("Payment not yet authorized.");
				}else {
					codeText.setText(a.getAuthorizationCode());

				}

			} 
		 }
		  ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);
		 
		 
		 } 
		
	  } catch (RuntimeException e) {
		  ViewUtils.showError(e.getMessage());
		  ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);

	  }	  
	  
  }
  
  @FXML
  public void initiateAssignmentClicked(ActionEvent event) {
	  //need to refresh members/guides in the case where they are updated before assignment
	  try {
		  if(assignments==null) {
				 if(successful(() -> AssignmentController.initiateAssignment())) {
					 assignmentCompletionText.setText("Assignments initialized.");
					 assignments = ClimbSafeFeatureSet6Controller.getAssignments();
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