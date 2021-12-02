package ca.mcgill.ecse.climbsafe.view.controllers;
import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class StartFinishCancelTripController {

	@FXML private ChoiceBox<Integer> nrWeeksChoiceBox;
	@FXML private ChoiceBox<String> memberFinishChoiceBox;
	@FXML private ChoiceBox<String> memberCancelChoiceBox;
	@FXML private Button startTripButton;
	@FXML private Button finishMemberTripButton;
	@FXML private Button cancelMemberTripButton;

	public void initialize() {
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
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(nrWeeksChoiceBox);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberFinishChoiceBox);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(memberCancelChoiceBox);
	}

	public void startTripsForWeek(ActionEvent event) {
		int nrWeeks =  Integer.parseInt(nrWeeksChoiceBox.getValue().toString());
		try {
			if(successful(() ->AssignmentController.startTrips(nrWeeks))) {
				nrWeeksChoiceBox.setValue(null);
				ClimbSafeFxmlView.getInstance().refresh();
			}
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}

	}

	public void finishMemberTrip(ActionEvent event) {
		String email = memberFinishChoiceBox.getValue();
		try {
			if(successful(() -> AssignmentController.finishTrip(email))) {
				memberFinishChoiceBox.setValue(null);
				ClimbSafeFxmlView.getInstance().refresh();
			}
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}

	}

	public void cancelMemberTrip(ActionEvent event) {
		String email = memberCancelChoiceBox.getValue();
		try {
			if(successful(() -> AssignmentController.cancelTrip(email))) {
				memberCancelChoiceBox.setValue(null);
				ClimbSafeFxmlView.getInstance().refresh();
			}
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
	}
}
