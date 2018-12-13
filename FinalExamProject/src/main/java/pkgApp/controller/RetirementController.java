package pkgApp.controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pkgApp.RetirementApp;
import pkgCore.Retirement;

public class RetirementController implements Initializable {

		
	private RetirementApp mainApp = null;
	
	@FXML
	private Label lblSaveEachMonth;
	
	@FXML
	private TextField txtYearsToWork;
	
	@FXML
	private TextField txtAnnualReturn;
	
	@FXML
	private Label lblSaveTotal;
	
	@FXML
	private TextField txtYearsRetired;
	
	@FXML
	private TextField txtAnnualReturnRetired;
	
	@FXML
	private TextField txtRequiredIncome;
	
	@FXML
	private TextField txtMonthlySSI;
	
	private HashMap<TextField, String> TextFieldRegEx = new HashMap<TextField, String>();

	public RetirementApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(RetirementApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TextFieldRegEx.put(txtYearsToWork, "\\d*?");
		TextFieldRegEx.put(txtAnnualReturn, "\\d*(\\.\\d*)?");
		TextFieldRegEx.put(txtYearsRetired,"\\d*?");
		TextFieldRegEx.put(txtAnnualReturnRetired,"\\d*(\\.\\d*)?");
		TextFieldRegEx.put(txtRequiredIncome,"\\d*?");
		TextFieldRegEx.put(txtMonthlySSI,"\\d*?");
		
		Iterator o = TextFieldRegEx.entrySet().iterator();
		while (o.hasNext()) {
			Map.Entry pair = (Map.Entry) o.next();
			TextField txtField = (TextField) pair.getKey();
			String strRegEx = (String) pair.getValue();

			txtField.focusedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
						Boolean newPropertyValue) {
					if (!newPropertyValue) {
						if (!txtField.getText().matches(strRegEx)) {
							txtField.setText("");
							txtField.requestFocus();
						}
					}
				}
			});
		}
	}
	
	@FXML
	public void btnClear(ActionEvent event) {
	
		txtYearsToWork.clear();
		txtAnnualReturn.clear();
		txtYearsRetired.clear();
		txtAnnualReturnRetired.clear();
		txtRequiredIncome.clear();
		txtMonthlySSI.clear();
		lblSaveEachMonth.setText("");
		lblSaveTotal.setText("");
	}
	
	@FXML
	public void btnCalculate(ActionEvent event) {
		DecimalFormat format = new DecimalFormat("#0.00");
		Retirement ret = new Retirement();

	    			int yearsToWork = Integer.parseInt(txtYearsToWork.getText());
	    			ret.setiYearsToWork(yearsToWork);
	    			double annualReturn = Double.parseDouble(txtAnnualReturn.getText());
	    			ret.setdAnnualReturnWorking(annualReturn);
	    			int yearsRetired = Integer.parseInt(txtYearsRetired.getText());
				    ret.setiYearsRetired(yearsRetired);
	    			double annualReturnRetired = Double.parseDouble(txtAnnualReturnRetired.getText());
	    			ret.setdAnnualReturnRetired(annualReturnRetired);
	    			double requiredIncome = Double.parseDouble(txtRequiredIncome.getText());
	    			ret.setdRequiredIncome(requiredIncome);
	    			double monthlySSI = Double.parseDouble(txtMonthlySSI.getText());
	    			ret.setdMonthlySSI(monthlySSI);


			lblSaveTotal.setText(String.valueOf(format.format((-1) * ret.TotalAmountSaved())));
			lblSaveEachMonth.setText(String.valueOf(format.format((-1) * ret.AmountToSave())));
		
		
	}
	
}
