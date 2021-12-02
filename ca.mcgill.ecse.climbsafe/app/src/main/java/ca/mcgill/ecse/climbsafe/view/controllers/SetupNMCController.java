package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.sql.Date;
import java.time.*;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.text.Text;
import javafx.scene.control.ChoiceBox;

public class SetupNMCController {

	@FXML private Spinner<Integer> nrWeeksSpinner;
	@FXML private Spinner<Integer> weeklyPriceSpinner;
	@FXML private DatePicker dateBox;
	@FXML private Button next;
	@FXML private Text returnMessageText;
	
	@FXML
	public void initialize() {
		nrWeeksSpinner.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			int minQuantity = 0;
			int maxQuantity = 52;
			int initQuantity = 0;
			SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minQuantity, maxQuantity, initQuantity);
			nrWeeksSpinner.setValueFactory(valueFactory);
		});
		weeklyPriceSpinner.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			int minQuantity = 0;
			int maxQuantity = 9999;
			int initQuantity = 0;
			SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minQuantity, maxQuantity, initQuantity);
			weeklyPriceSpinner.setValueFactory(valueFactory);
		});
	  // set dateBox to be not editable so that the user must choose from the calendar
	  dateBox.setEditable(false);
	  returnMessageText.setText("");
	  ClimbSafeFxmlView.getInstance().registerRefreshEvent(nrWeeksSpinner);
	  ClimbSafeFxmlView.getInstance().registerRefreshEvent(weeklyPriceSpinner);
	}
	
	@FXML
	public void SetupNMCInfo(ActionEvent event) {
		//assuming both inputs (numWeeks and weeklyPriceOfGuide) are correct
		int nrWeeks =  nrWeeksSpinner.getValue();
		int priceOfGuidePerWeek = weeklyPriceSpinner.getValue();
		if(nrWeeks != 0 &&  priceOfGuidePerWeek != 0 && dateBox.getValue() != null) {
			LocalDate d = dateBox.getValue();
			Date date = Date.valueOf(d);
			try {
				if(successful(() -> ClimbSafeFeatureSet1Controller.setup(date,  nrWeeks, priceOfGuidePerWeek))) {
					nrWeeksSpinner.getValueFactory().setValue(0);
					weeklyPriceSpinner.getValueFactory().setValue(0);
					ClimbSafeFxmlView.getInstance().refresh();
					returnMessageText.setText("NMC info setup/updated successfully");
				}
			} catch (RuntimeException e) {
				ViewUtils.showError(e.getMessage());
			}
		}
		
	}
	
}
 
