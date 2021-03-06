package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.io.IOException;
import java.sql.Date;
import java.time.*;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SetupNMCController {

	@FXML private Spinner<Integer> nrWeeksSpinner;
	@FXML private Spinner<Integer> weeklyPriceSpinner;
	@FXML private DatePicker dateBox;
	@FXML private Button next;
	@FXML private Text returnMessageText;
	@FXML private ImageView winterBackground;
	@FXML private ImageView fallBackground;
	@FXML private ImageView summerBackground;
	@FXML private ImageView springBackground;
	
	@FXML private Label t1;
	@FXML private Label t2;
	@FXML private Label t3;

	private String myDate = ClimbSafeController.getNMCDate().toString();
	private String month = myDate.split("-")[1];
	private String day = myDate.split("-")[2];

	/**
	 * Initializes the page. It changes the background depending on the date setup in NMC.
	 * @author Enzo and Joey 
	 */
	@FXML
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
		next.setStyle("-fx-text-fill: " + hexCode);

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
	  /**
	   * Sets up NMC info when button is pressed
	 	 * @author Victor and Enzo and Joey
	 	 */
	@FXML
	public void SetupNMCInfo(ActionEvent event) throws IOException {
		//assuming both inputs (numWeeks and weeklyPriceOfGuide) are correct
		int nrWeeks =  nrWeeksSpinner.getValue();
		int priceOfGuidePerWeek = weeklyPriceSpinner.getValue();
		if(nrWeeks != 0 &&  priceOfGuidePerWeek != 0 && dateBox.getValue() != null) {
			LocalDate d = dateBox.getValue();
			Date date = Date.valueOf(d);
			try {
				if(successful(() -> ClimbSafeFeatureSet1Controller.setup(date,  nrWeeks, priceOfGuidePerWeek))) {
					dateBox.setValue(null);
					nrWeeksSpinner.getValueFactory().setValue(0);
					weeklyPriceSpinner.getValueFactory().setValue(0);
					ClimbSafeFxmlView.getInstance().refresh();
					returnMessageText.setText("NMC info setup/updated successfully");
					ClimbSafeFxmlView.getInstance().stylingSet();
				}
				myDate = ClimbSafeController.getNMCDate().toString();
				month = myDate.split("-")[1];
				day = myDate.split("-")[2];
				if (Integer.parseInt(month) < 3) {
					winterBackground.setOpacity(1);
					summerBackground.setOpacity(0);
					fallBackground.setOpacity(0);
					springBackground.setOpacity(0);

				}else if (Integer.parseInt(month) == 3) {
					if (Integer.parseInt(day) < 20) {
						winterBackground.setOpacity(1);
						summerBackground.setOpacity(0);
						fallBackground.setOpacity(0);
						springBackground.setOpacity(0);
					}else {
						springBackground.setOpacity(1);
						winterBackground.setOpacity(0);
						summerBackground.setOpacity(0);
						fallBackground.setOpacity(0);
					}
				}else if (Integer.parseInt(month)< 6 && Integer.parseInt(month) > 3) {
					springBackground.setOpacity(1);
					winterBackground.setOpacity(0);
					summerBackground.setOpacity(0);
					fallBackground.setOpacity(0);
				}else if (Integer.parseInt(month) == 6) {
					if (Integer.parseInt(day) < 20) {
						springBackground.setOpacity(1);
						winterBackground.setOpacity(0);
						summerBackground.setOpacity(0);
						fallBackground.setOpacity(0);
					}else {
						summerBackground.setOpacity(1);
						winterBackground.setOpacity(0);
						springBackground.setOpacity(0);
						fallBackground.setOpacity(0);
					}
				}else if (Integer.parseInt(month) < 9 && Integer.parseInt(month) > 6) {
					summerBackground.setOpacity(1);
					winterBackground.setOpacity(0);
					springBackground.setOpacity(0);
					fallBackground.setOpacity(0);
				}else if(Integer.parseInt(month) == 9) {
					if (Integer.parseInt(day) < 22) {
						summerBackground.setOpacity(1);
						winterBackground.setOpacity(0);
						springBackground.setOpacity(0);
						fallBackground.setOpacity(0);
					}else {
						fallBackground.setOpacity(1);
						summerBackground.setOpacity(0);
						winterBackground.setOpacity(0);
						springBackground.setOpacity(0);
					}
				}else if (Integer.parseInt(month) < 12 && Integer.parseInt(month) > 9){
					fallBackground.setOpacity(1);
					summerBackground.setOpacity(0);
					winterBackground.setOpacity(0);
					springBackground.setOpacity(0);
				}else if(Integer.parseInt(month) == 12) {
					if (Integer.parseInt(day) < 21) {
						fallBackground.setOpacity(1);
						summerBackground.setOpacity(0);
						winterBackground.setOpacity(0);
						springBackground.setOpacity(0);
					}else {
						winterBackground.setOpacity(1);
						summerBackground.setOpacity(0);
						fallBackground.setOpacity(0);
						springBackground.setOpacity(0);
					}
				}
			} catch (RuntimeException e) {
				ViewUtils.showError(e.getMessage());
			}
		}

	}

}

