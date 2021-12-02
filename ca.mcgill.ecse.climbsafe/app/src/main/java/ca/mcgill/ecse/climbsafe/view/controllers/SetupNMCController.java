package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.sql.Date;
import java.time.*;
import java.util.List;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import ca.mcgill.ecse.climbsafe.view.pages.;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;

public class SetupNMCController {

	@FXML private ChoiceBox<Integer> numWeeksChoiceBox;
	@FXML private ChoiceBox<Integer> weeklyPriceGuideChoiceBox;
	@FXML private DatePicker dateBox;
	@FXML private Button next;
	
	@FXML
	public void initialize() {
		numWeeksChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			numWeeksChoiceBox.setItems(ViewUtils.getNrWeeks());
			numWeeksChoiceBox.setValue(null);
		});
		weeklyPriceGuideChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			weeklyPriceGuideChoiceBox.setItems(ViewUtils.getWeeklyPriceGuide());
			weeklyPriceGuideChoiceBox.setValue(null);
		});
	  // set dateBox to be not editable so that the user must choose from the calendar
	  dateBox.setEditable(false);

	  ClimbSafeFxmlView.getInstance().registerRefreshEvent(numWeeksChoiceBox);
	  ClimbSafeFxmlView.getInstance().registerRefreshEvent(weeklyPriceGuideChoiceBox);
	}
	
	@FXML
	public void SetupNMCInfo(ActionEvent event) {
		//assuming both inputs (numWeeks and weeklyPriceOfGuide) are correct
		int nrWeeks =  getNumberFromField(numWeeksChoiceBox);
		int priceOfGuidePerWeek = getNumberFromField(weeklyPriceGuideChoiceBox);
		if(nrWeeks != -1 &&  priceOfGuidePerWeek != -1 && dateBox.getValue() != null) {
			LocalDate d = dateBox.getValue();
			Date date = Date.valueOf(d);
			try {
				if(successful(() -> ClimbSafeFeatureSet1Controller.setup(date,  nrWeeks, priceOfGuidePerWeek))) {
					numWeeksChoiceBox.setValue(null);
					weeklyPriceGuideChoiceBox.setValue(null);
					ClimbSafeFxmlView.getInstance().refresh();
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
	
	/** Returns the number from the given text field if present, otherwise appends error string to the given message. */
	private int getDateFromField(ChoiceBox<Integer> field) {
		if(field.getValue() != null) {
			return field.getValue();
		}else {
			return -1;
		}
	}
}
 
