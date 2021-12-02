package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.util.List;

import ca.mcgill.ecse.climbsafe.controller.*;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
  
  private List<TOAssignment> assignments; 
  

  public void initialize() {
	  //add if statement for when no data in system
	  assignments = ClimbSafeFeatureSet6Controller.getAssignments();

	  memberChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			memberChoiceBox.setItems(ViewUtils.getMembers());
			memberChoiceBox.setValue(null);
		});	
	  ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);
	  
	}
  @FXML
  public void viewClicked(ActionEvent event) {
	  
	  try {
		 assignments = ClimbSafeFeatureSet6Controller.getAssignments();
		 ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);
		 if(assignments.isEmpty()) {
			 String e = "Assignments not yet created.";
			 ViewUtils.showError(e);
			 ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);

		 }else if(memberChoiceBox.getValue() == null) {
			 String e = "Please choose member.";
			 ViewUtils.showError(e);
			 ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);
		 }
		 else {
			String refund;
			String startAndEndWeek;
			int totalCost;
			boolean found = false;
		 for(TOAssignment a : assignments) {
			if(a.getMemberEmail().equals(memberChoiceBox.getValue())) {
				found = true;
				tripStatusText.setText(a.getStatus());
				memberEmailText.setText(a.getMemberEmail());
				memberNameText.setText(a.getMemberName());
				refund =a.getRefundedPercentageAmount()+"%";
				refundText.setText(refund);
				startAndEndWeek = "From week "+a.getStartWeek()+" to week "+a.getEndWeek();
				startAndEndWeekText.setText(startAndEndWeek);
				if(a.getGuideEmail()==null) {
					guideEmailText.setText("Guide not required");
					guideNameText.setText("Guide not required");
				}else {
					guideEmailText.setText(a.getGuideEmail());
					guideNameText.setText(a.getGuideName());
				}
				
				totalCost = a.getTotalCostForEquipment()+a.getTotalCostForGuide();
				totalCostText.setText(String.valueOf(totalCost));
				if(a.getAuthorizationCode()==null) {
					codeText.setText("Payment not yet authorized.");
				}else {
					codeText.setText(a.getAuthorizationCode());

				}

			} 
		 }
		 if(found==false) {
			tripStatusText.setText("N/A");
			memberEmailText.setText("N/A");
			memberNameText.setText("N/A");
			refundText.setText("N/A");
			startAndEndWeekText.setText("N/A");
			guideEmailText.setText("N/A");
			guideNameText.setText("N/A");
			totalCostText.setText("N/A");
			codeText.setText("N/A");
		 }
		  ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);
		 
		 
		 } 
		
	  } catch (RuntimeException e) {
		  ViewUtils.showError(e.getMessage());
		  ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);

	  }	  
	  
  }
  
  
  


  

  

}