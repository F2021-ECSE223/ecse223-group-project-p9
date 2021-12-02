package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddEquipmentPageController {

	@FXML private TextField addEquipmentName;
	@FXML private Button addEquipmentButton;
	@FXML private Spinner<Integer> weightSpinner;
	@FXML private Spinner<Integer> priceSpinner;
	@FXML private Text returnMessageText;

	public void initialize() {
		weightSpinner.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			int minQuantity = 0;
			int maxQuantity = 99;
			int initQuantity = 0;
			SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minQuantity, maxQuantity, initQuantity);
			weightSpinner.setValueFactory(valueFactory);
		});
		priceSpinner.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			int minQuantity = 0;
			int maxQuantity = 99;
			int initQuantity = 0;
			SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minQuantity, maxQuantity, initQuantity);
			priceSpinner.setValueFactory(valueFactory);
		});
		returnMessageText.setText("");

		ClimbSafeFxmlView.getInstance().registerRefreshEvent(weightSpinner);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(priceSpinner);
	}
	
	/**
	 * Describes what pressing the addEquipment Button does (event listener)
	 * @author Enzo Benoit-Jeannin
	 * @param event
	 */
	@FXML
	public void addEquipmentClick(ActionEvent event) {
		String name = addEquipmentName.getText();
		int weight = weightSpinner.getValue();
		int price = priceSpinner.getValue();
		//try adding the new equipment or catch the error
		try {
			if(name!="" && weight != -1 && price != -1) {
				if(successful(() -> ClimbSafeFeatureSet4Controller.addEquipment(name, weight, price))) {
					addEquipmentName.setText("");
					weightSpinner.getValueFactory().setValue(0);
					priceSpinner.getValueFactory().setValue(0);
					ClimbSafeFxmlView.getInstance().refresh();
					returnMessageText.setText("Equipment added successfully");
				}
			}
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
	}

}