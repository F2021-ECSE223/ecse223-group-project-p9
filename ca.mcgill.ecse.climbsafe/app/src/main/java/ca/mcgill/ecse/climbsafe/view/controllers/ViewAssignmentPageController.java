package ca.mcgill.ecse.climbsafe.view.controllers;

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

  public void initialize() {
	  memberChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			memberChoiceBox.setItems(ViewUtils.getMembers());
			memberChoiceBox.setValue(null);
		});	
	  ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);
	}
  @FXML
  public void viewClicked(ActionEvent event) {
	  try {
		 List<TOAssignment> assignments = ClimbSafeFeatureSet6Controller.getAssignments();
		 if(assignments.isEmpty()) {
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


  

  

}