package ca.mcgill.ecse.climbsafe.view.controllers;

import java.util.List;

import ca.mcgill.ecse.climbsafe.controller.*;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
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

	@FXML private ImageView winterBackground;
	@FXML private ImageView fallBackground;
	@FXML private ImageView summerBackground;
	@FXML private ImageView springBackground;
	
	@FXML private Label t1;
	@FXML private Label t2;
	@FXML private Label t3;
	@FXML private Label t4;
	@FXML private Label t5;
	@FXML private Label t6;
	@FXML private Label t7;
	@FXML private Label t8;
	@FXML private Label t9;

	private String myDate = ClimbSafeController.getNMCDate().toString();
	private String month = myDate.split("-")[1];
	private String day = myDate.split("-")[2];

	/**
	 * Initializes the page. It changes the background depending on the date setup in NMC.
	 * @author Enzo  and Joey and Kara
	 */
	public void initialize() {
		Color fontColor = Color.BLACK;
		String hexCode = "#000000";
		if (Integer.parseInt(month) < 3) {
			winterBackground.setOpacity(1);
			summerBackground.setOpacity(0);
			fallBackground.setOpacity(0);
			springBackground.setOpacity(0);
			hexCode = "#2fc1ff";

		}else if (Integer.parseInt(month) == 3) {
			if (Integer.parseInt(day) < 20) {
				winterBackground.setOpacity(1);
				summerBackground.setOpacity(0);
				fallBackground.setOpacity(0);
				springBackground.setOpacity(0);
				hexCode = "#2fc1ff";
			}else {
				springBackground.setOpacity(1);
				winterBackground.setOpacity(0);
				summerBackground.setOpacity(0);
				fallBackground.setOpacity(0);
				hexCode = "#ce87bd";
			}
		}else if (Integer.parseInt(month)< 6 && Integer.parseInt(month) > 3) {
			springBackground.setOpacity(1);
			winterBackground.setOpacity(0);
			summerBackground.setOpacity(0);
			fallBackground.setOpacity(0);
			hexCode = "#ce87bd";
		}else if (Integer.parseInt(month) == 6) {
			if (Integer.parseInt(day) < 20) {
				springBackground.setOpacity(1);
				winterBackground.setOpacity(0);
				summerBackground.setOpacity(0);
				fallBackground.setOpacity(0);
				hexCode = "#ce87bd";
			}else {
				summerBackground.setOpacity(1);
				winterBackground.setOpacity(0);
				springBackground.setOpacity(0);
				fallBackground.setOpacity(0);
				hexCode = "#69bc4c";
			}
		}else if (Integer.parseInt(month) < 9 && Integer.parseInt(month) > 6) {
			summerBackground.setOpacity(1);
			winterBackground.setOpacity(0);
			springBackground.setOpacity(0);
			fallBackground.setOpacity(0);
			hexCode = "#69bc4c";
		}else if(Integer.parseInt(month) == 9) {
			if (Integer.parseInt(day) < 22) {
				summerBackground.setOpacity(1);
				winterBackground.setOpacity(0);
				springBackground.setOpacity(0);
				fallBackground.setOpacity(0);
				hexCode = "#69bc4c";
			}else {
				fallBackground.setOpacity(1);
				summerBackground.setOpacity(0);
				winterBackground.setOpacity(0);
				springBackground.setOpacity(0);
				hexCode = "#771102";
			}
		}else if (Integer.parseInt(month) < 12 && Integer.parseInt(month) > 9){
			fallBackground.setOpacity(1);
			summerBackground.setOpacity(0);
			winterBackground.setOpacity(0);
			springBackground.setOpacity(0);
			hexCode = "#771102";
		}else if(Integer.parseInt(month) == 12) {
			if (Integer.parseInt(day) < 21) {
				fallBackground.setOpacity(1);
				summerBackground.setOpacity(0);
				winterBackground.setOpacity(0);
				springBackground.setOpacity(0);
				hexCode = "#771102";
			}else {
				winterBackground.setOpacity(1);
				summerBackground.setOpacity(0);
				fallBackground.setOpacity(0);
				springBackground.setOpacity(0);
				hexCode = "#2fc1ff";
			}
		}
		fontColor = Color.web(hexCode);  
		t1.setTextFill(fontColor);
		t2.setTextFill(fontColor);
		t3.setTextFill(fontColor);
		t4.setTextFill(fontColor);
		t5.setTextFill(fontColor);
		t6.setTextFill(fontColor);
		t7.setTextFill(fontColor);
		t8.setTextFill(fontColor);
		t9.setTextFill(fontColor);
		viewButton.setStyle("-fx-text-fill: " + hexCode);
		assignments = ClimbSafeFeatureSet6Controller.getAssignments();

		memberChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			memberChoiceBox.setItems(ViewUtils.getMembers());
			memberChoiceBox.setValue(null);
		});	
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberChoiceBox);

	}
	  /**
	   * Shows individual member assignment of member chosen in choice box
	 	 * @author Kara
	 	 * @param event
	 	 */
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
							tripStatusText.setText(a.getStatus());
							codeText.setText("Payment not yet authorized.");
						}else if((a.getAuthorizationCode()!=null) && a.getStatus().equals("Assigned")){
							codeText.setText(a.getAuthorizationCode());
							tripStatusText.setText("Paid");
						}else {
							codeText.setText(a.getAuthorizationCode());
							tripStatusText.setText(a.getStatus());
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