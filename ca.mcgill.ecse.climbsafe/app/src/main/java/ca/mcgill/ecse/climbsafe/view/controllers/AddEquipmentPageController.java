package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.util.List;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller;
import ca.mcgill.ecse.climbsafe.controller.TOEquipment;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class AddEquipmentPageController {

	@FXML private TextField addEquipmentName;
	@FXML private Button addEquipmentButton;
	@FXML private Button refreshEquipments;
	@FXML private Spinner<Integer> weightSpinner;
	@FXML private Spinner<Integer> priceSpinner;
	@FXML private Text returnMessageText;
	@FXML private TableColumn<TOEquipment, String> equipmentNames;
	@FXML private TableView<TOEquipment> showEquipment;
	@FXML private ImageView winterBackground;
	@FXML private ImageView fallBackground;
	@FXML private ImageView summerBackground;
	@FXML private ImageView springBackground;

	private String myDate = ClimbSafeController.getNMCDate().toString();
	private String month = myDate.split("-")[1];
	private String day = myDate.split("-")[2];

	/**
	 * Initializes the page originally
	 * @author Joey Koay
	 */
	public void initialize() {		
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

		equipmentNames.setCellValueFactory(new PropertyValueFactory<>("name"));
		List<TOEquipment> equipments = ClimbSafeController.getTOEquipment();
		showEquipment.getItems().clear();
		for(TOEquipment e: equipments) {
			showEquipment.getItems().add(e);
		}

		weightSpinner.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			int minQuantity = 0;
			int maxQuantity = 9999999;
			int initQuantity = 0;
			SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minQuantity, maxQuantity, initQuantity);
			weightSpinner.setValueFactory(valueFactory);
		});
		priceSpinner.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			int minQuantity = 0;
			int maxQuantity = 9999999;
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
			List<TOEquipment> equipments = ClimbSafeController.getTOEquipment();
			showEquipment.getItems().clear();
			for(TOEquipment e: equipments) {
				showEquipment.getItems().add(e);
			}
		} catch (RuntimeException e) {
			ViewUtils.showError(e.getMessage());
		}
	}

	/**
	 * Refreshes the table showing all equipments in the application
	 * @param event
	 * @author Enzo Benoit-Jeannin
	 */
	@FXML
	public void refreshClick(ActionEvent event) {
		List<TOEquipment> equipments = ClimbSafeController.getTOEquipment();
		showEquipment.getItems().clear();
		for(TOEquipment e: equipments) {
			showEquipment.getItems().add(e);
		}
	}
}