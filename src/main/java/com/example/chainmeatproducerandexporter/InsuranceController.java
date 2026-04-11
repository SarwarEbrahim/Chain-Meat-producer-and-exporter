package com.example.chainmeatproducerandexporter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.*;

public class InsuranceController
{
    @javafx.fxml.FXML
    private TextField amountTextField;
    @javafx.fxml.FXML
    private TextField companyTextField;
    @javafx.fxml.FXML
    private ComboBox<String> sihipmentIdComboBox;
    @javafx.fxml.FXML
    private TableView<Insurance> insuranceTableView;
    @javafx.fxml.FXML
    private TableColumn<Insurance, String> NumberColumn;
    @javafx.fxml.FXML
    private TableColumn<Insurance, String> statusColumn;
    @javafx.fxml.FXML
    private TableColumn<Insurance, String> InsuranceColumn;
    @javafx.fxml.FXML
    private TableColumn<Insurance, Double> AmountColumn;

    private ObservableList<Insurance> insuranceList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {

        // sample shipment IDs (you can connect real data later)
        sihipmentIdComboBox.getItems().addAll("S001", "S002", "S003");

        loadFromFile();
    }

    @javafx.fxml.FXML
    public void saveinsuranceButton(ActionEvent actionEvent) {

        // ✅ 1. Validation
        if (amountTextField.getText().isEmpty() ||
                companyTextField.getText().isEmpty() ||
                sihipmentIdComboBox.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
            return;
        }

        // ✅ 2. Amount validation
        double amount;
        try {
            amount = Double.parseDouble(amountTextField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Amount must be a number");
            alert.showAndWait();
            return;
        }

        // ✅ 3. Default status
        String status = "Active";

        // ✅ 4. Create object
        Insurance insurance = new Insurance(
                sihipmentIdComboBox.getValue(),
                companyTextField.getText(),
                amount,
                status
        );

        insuranceList.add(insurance);

        // ✅ 5. Show in TableView
        insuranceTableView.setItems(insuranceList);

        // ✅ 6. Save file
        saveToFile();

        // ✅ 7. Success alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Done");
        alert.setContentText("Insurance Saved Successfully!");
        alert.showAndWait();

        // ✅ 8. Clear fields
        amountTextField.clear();
        companyTextField.clear();
        sihipmentIdComboBox.setValue(null);
    }

    // ✅ Save method
    private void saveToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("insurance.bin"))) {
            output.writeInt(insuranceList.size());
            for (Insurance i : insuranceList) {
                output.writeObject(i);
            }
        } catch (IOException e) {
            System.out.println("File write error");
        }
    }

    // ✅ Load method
    private void loadFromFile() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("insurance.bin"))) {
            int size = input.readInt();
            for (int i = 0; i < size; i++) {
                Insurance i = (Insurance) input.readObject();
                insuranceList.add(i);
            }
            insuranceTableView.setItems(insuranceList);
        } catch (IOException | ClassNotFoundException e) {
            // first run ignore
        }
    }
}
