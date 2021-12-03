package ca.mcgill.ecse.climbsafe.view.controllers;

import static ca.mcgill.ecse.climbsafe.view.controllers.ViewUtils.successful;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.view.ClimbSafeFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class RegisterMemberPageController {

	@FXML private TextField nameTextField;
	@FXML private TextField emailTextField;
	@FXML private TextField emergencyContactTextField;
	@FXML private ChoiceBox<Integer> nrWeeksChoiceBox;
	@FXML private CheckBox guideRequiredCheckBox;
	@FXML private CheckBox hotelRequiredCheckBox;
	@FXML private PasswordField passwordTextField;

	@FXML private ChoiceBox<String> itemNameChoiceBox;
	@FXML private Spinner<Integer> itemQuantitySpinner;
	@FXML private Button addEditItemButton;
	@FXML private ListView<String> memberItemsListView;

	@FXML private Button registerMemberRegisterButton;
	@FXML private Text registerMessageLabel;
	@FXML private ImageView winterBackground;
	@FXML private ImageView fallBackground;
	@FXML private ImageView summerBackground;
	@FXML private ImageView springBackground;

	private String myDate = ClimbSafeController.getNMCDate().toString();
	private String month = myDate.split("-")[1];
	private String day = myDate.split("-")[2];

	private List<String> itemNames = new ArrayList<>();
	private List<String> itemNamesWithBundle = new ArrayList<>();
	private List<Integer> itemQuantities = new ArrayList<>();

	/**
	 * Initializes the page. It changes the background depending on the date setup in NMC.
	 * @author Enzo  and Joey 
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
		emailTextField.setText("");
		passwordTextField.setText("");
		nameTextField.setText("");
		emergencyContactTextField.setText("");
		guideRequiredCheckBox.setSelected(false);
		hotelRequiredCheckBox.setSelected(false);
		nrWeeksChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			nrWeeksChoiceBox.setItems(ViewUtils.getNrWeeks());
			nrWeeksChoiceBox.setValue(null);
		});
		itemNameChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			itemNameChoiceBox.setItems(ViewUtils.getItemNames());
			itemNameChoiceBox.setValue(null);
		});
		itemQuantitySpinner.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
			int minQuantity = 0;
			int maxQuantity = 99;
			int initQuantity = 0;
			SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minQuantity, maxQuantity, initQuantity);
			itemQuantitySpinner.setValueFactory(valueFactory);
		});
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(nrWeeksChoiceBox);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(itemNameChoiceBox);
		ClimbSafeFxmlView.getInstance().registerRefreshEvent(itemQuantitySpinner);
	}

	// Event Listener on Button[#registerMemberRegisterClicked].onAction
	@FXML
	public void registerMemberRegisterClicked(ActionEvent event) {
		String email = emailTextField.getText();
		String password = passwordTextField.getText();
		String name = nameTextField.getText();
		String emergencyContact = emergencyContactTextField.getText();
		int nrWeeks = getNumberFromField(nrWeeksChoiceBox);
		boolean guideRequired = guideRequiredCheckBox.isSelected();
		boolean hotelRequired = hotelRequiredCheckBox.isSelected();

		if(email == "" || password == "" || name == "" || emergencyContact == "" || nrWeeks == -1) {
			registerMessageLabel.setText("");
			ViewUtils.showError("Please fill out all of the field");
		}else {
			try {
				if(successful(() -> ClimbSafeFeatureSet2Controller.registerMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired, itemNames, itemQuantities))) {
					emailTextField.setText("");
					passwordTextField.setText("");
					nameTextField.setText("");
					emergencyContactTextField.setText("");
					nrWeeksChoiceBox.setValue(null);
					guideRequiredCheckBox.setSelected(false);
					hotelRequiredCheckBox.setSelected(false);
					itemNameChoiceBox.setValue(null);
					itemQuantitySpinner.setValueFactory(null);
					memberItemsListView.setItems(null);
					ClimbSafeFxmlView.getInstance().refresh();
					registerMessageLabel.setText("");
					registerMessageLabel.setText("Member registered successfully");
				}
			} catch (RuntimeException e) {
				ViewUtils.showError(e.getMessage());
			}
		}
	}

	//Event Listener on Button[#addEditItemClicked].onAction
	@FXML
	public void addEditItemClicked(ActionEvent event) {
		ObservableList<String> itemNameAndQuantityList = FXCollections.observableArrayList();
		String itemName = "";
		String wholeBundleName = null;
		if(itemNameChoiceBox.getValue()!= null) {
			itemName = itemNameChoiceBox.getValue().toString();
			wholeBundleName = itemName;
		}
		if(wholeBundleName != null && wholeBundleName.contains(":")) {
			String tempWordList[] = itemName.split(":");
			itemName = tempWordList[0];
		}
		int itemQuantity = itemQuantitySpinner.getValue();
		int indexOfItem = -1;

		if(itemNames.size()!= 0 && itemNames.toString().contains(itemName)) {
			//edit the quantity instead
			for(int i =0; i<itemNames.size(); i++) {
				if(itemNames.get(i) == itemName) {
					indexOfItem = i;
					break;
				}
			}
			if(itemQuantity == 0) {
				itemNamesWithBundle.remove(indexOfItem);
				itemNames.remove(indexOfItem);
				itemQuantities.remove(indexOfItem);
			}else {
				itemQuantities.set(indexOfItem, itemQuantity);
			}
		}else {
			//add new item
			if(itemQuantity != 0 && itemName != "") {
				itemNamesWithBundle.add(wholeBundleName);
				itemNames.add(itemName);
				itemQuantities.add(itemQuantity);
			}
			itemNameChoiceBox.setValue(null);
			itemQuantitySpinner.getValueFactory().setValue(0);
		}

		for(int i =0; i<itemNamesWithBundle.size(); i++) {

			itemNameAndQuantityList.add(itemQuantities.get(i) + " " + itemNamesWithBundle.get(i));
		}

		memberItemsListView.setItems(itemNameAndQuantityList);
		int tempNrWeeks = getNumberFromField(nrWeeksChoiceBox);
		ClimbSafeFxmlView.getInstance().refresh();
		if(tempNrWeeks!=-1) {
			nrWeeksChoiceBox.setValue(tempNrWeeks);
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
