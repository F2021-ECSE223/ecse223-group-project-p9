package ca.mcgill.ecse.climbsafe.view.controllers;
import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeController;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class StartFinishCancelTripController {

	@FXML private ChoiceBox<Integer> nrWeeksChoiceBox;
	@FXML private ChoiceBox<String> memberFinishChoiceBox;
	@FXML private ChoiceBox<String> memberCancelChoiceBox;
	@FXML private Button startTripButton;
	@FXML private Button finishMemberTripButton;
	@FXML private Button cancelMemberTripButton;
	@FXML private Text returnMessageText;
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

	private String myDate = ClimbSafeController.getNMCDate().toString();
	private String month = myDate.split("-")[1];
	private String day = myDate.split("-")[2];

	/**
	 * Initializes the page. It changes the background depending on the date setup in NMC.
	 * @author Enzo  and Joey 
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
		startTripButton.setStyle("-fx-text-fill: " + hexCode);
		finishMemberTripButton.setStyle("-fx-text-fill: " + hexCode);
		cancelMemberTripButton.setStyle("-fx-text-fill: " + hexCode);
		nrWeeksChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			nrWeeksChoiceBox.setItems(ViewUtils.getNrWeeks());
			nrWeeksChoiceBox.setValue(null);
		});
		memberFinishChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			memberFinishChoiceBox.setItems(ViewUtils.getMembers());
			memberFinishChoiceBox.setValue(null);
		});
		memberCancelChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			memberCancelChoiceBox.setItems(ViewUtils.getMembers());
			memberCancelChoiceBox.setValue(null);
		});
		returnMessageText.setText("");
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(nrWeeksChoiceBox);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberFinishChoiceBox);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberCancelChoiceBox);
	}
	/**
	 * Starts trips for specified week when button pressed 
	 * @param event
	 * @author Victor
	 */
	public void startTripsForWeek(ActionEvent event) {
		returnMessageText.setText("");
		int nrWeeks = getNumberFromField(nrWeeksChoiceBox);
		nrWeeksChoiceBox.setValue(null);
		try {
			if(successful(() ->AssignmentController.startTrips(nrWeeks))) {
				ClimbSafeFxmlView.getInstance().refresh();
				returnMessageText.setText("Trip started successfully");
			}
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}

	}
	/**
	 * Finishes member's trip selected in choice box
	 * @param event
	 * @author Victor
	 */
	public void finishMemberTrip(ActionEvent event) {
		returnMessageText.setText("");
		String email = memberFinishChoiceBox.getValue();
		memberFinishChoiceBox.setValue(null);
		if(email != null) {
			try {
				if(successful(() -> AssignmentController.finishTrip(email))) {
					memberFinishChoiceBox.setValue(null);
					ClimbSafeFxmlView.getInstance().refresh();
					returnMessageText.setText("Member finished trip successfully");
				}
			} catch (RuntimeException e) {
				ViewUtils.showError(e.getMessage());
			}
		}
	}
	/**
	 * Cancels member's trip when button pressed
	 * @param event
	 * @author Victor
	 */
	public void cancelMemberTrip(ActionEvent event) {
		returnMessageText.setText("");
		String email = memberCancelChoiceBox.getValue();
		memberCancelChoiceBox.setValue(null);
		if(email != null) {
			try {
				if(successful(() -> AssignmentController.cancelTrip(email))) {
					memberCancelChoiceBox.setValue(null);
					ClimbSafeFxmlView.getInstance().refresh();
					returnMessageText.setText("Member cancelled trip successfully");
				}
			} catch (RuntimeException e) {
				ViewUtils.showError(e.getMessage());
			}
		}
	}

	/** Returns the number from the given text field if present, otherwise appends error string to the given message. */
	private int getNumberFromField(ChoiceBox<Integer> field) {
		if(field.getValue() != null) {
			return field.getValue();
		}else {
			return -1;
		}
	}
}
