package ca.mcgill.ecse.climbsafe.view.controllers;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.*;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ViewAssignmentPageController {
  @FXML
  private TextField memberEmailsTextField;
  @FXML
  private Button viewButton;
  @FXML
  private Button authorizePaymentButton;
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
  public void viewClicked(ActionEvent event) {
	  try { 
		 if(validMember(memberEmailsTextField.getText())==-1) {
			String e = "Member with email address "+ memberEmailsTextField.getText() +" does not exist";
			ViewUtils.showError(e);
		 }

		 List<TOAssignment> assignments = ClimbSafeFeatureSet6Controller.getAssignments();
		 if(assignments.isEmpty()) {
			 String e = "Assignments not yet created.";
			 ViewUtils.showError(e);
		 }
		 String refund;
		 String startAndEndWeek;
		 int totalCost;
		 for(TOAssignment a : assignments) {
			if(a.getMemberEmail().equals(memberEmailsTextField.getText())) {
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
		
	  } catch (RuntimeException e) {
		  ViewUtils.showError(e.getMessage());
	  }	  
	  
  }
  @FXML
  public void authorizePaymentClicked(ActionEvent event) {
	  //open payformembertrip page
  }

  
	/**
	 * checking if the member exists
	 * 
	 * @author Joey Koay
	 * @param memberList - a list of all of the existing members
	 * @param email
	 * @return the index of the member in the list, if it does not exist, its -1
	 */
	private static int validMember(String email) {
		ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
		List<Member> memberList = climbSafe.getMembers();
		int validMember = -1;
		for(int i=0; i<memberList.size(); i++) {
			if(memberList.get(i).getEmail().equals(email)) {
				validMember = i;
				break;
			}
		}
		return validMember;
	}
  

}