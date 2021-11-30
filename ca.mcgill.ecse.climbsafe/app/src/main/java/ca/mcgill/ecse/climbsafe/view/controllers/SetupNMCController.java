package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.sql.Date;
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

public class SetupNMCController {

	@FXML private TextField numWeeksTextField;
	@FXML private TextField weeklyPriceOfGuideTextField;
	@FXML private DatePicker dateBox;
	@FXML private Button next;
	
	@FXML
	public void setUpNMCInfo(ActionEvent event) {
		//assuming both inputs (numWeeks and weeklyPriceOfGuide) are correct
/*
		String numWeeks = numWeeksTextField.getText();
		String weeklyPriceOfGuide = weeklyPriceOfGuideTextField.getText();
		Date date = dateBox.getDate();//this is a guess idk how DatePicker returns a date
		int nrWeeks = Integer.parseInt(numWeeks);
		int priceOfGuidePerWeek = Integer.parseInt(weeklyPriceOfGuide);
		try {
			if(successful(() -> ClimbSafeFeatureSet1Controller.setup(date,  nrWeeks, priceOfGuidePerWeek))) {
				numWeeksTextField.setText("");
				weeklyPriceOfGuideTextField.setText("");
				//somehow set dateBox to initial state or something
			}
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
		
*/
	}
}
 
