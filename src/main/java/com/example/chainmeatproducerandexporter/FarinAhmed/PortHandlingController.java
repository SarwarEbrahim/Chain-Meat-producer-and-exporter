package com.example.chainmeatproducerandexporter.FarinAhmed;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.*;
import java.util.ArrayList;

public class PortHandlingController
{
    @javafx.fxml.FXML
    private TextField chargeTextfield;
    @javafx.fxml.FXML
    private TextField nameTextField;
    @javafx.fxml.FXML
    private ComboBox<String> shipmentIdComboBox;
    @javafx.fxml.FXML
    private ComboBox<String> currencyComboBox;

    private ArrayList<PortHandling> portList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {

        shipmentIdComboBox.getItems().addAll("S001", "S002", "S003");
        currencyComboBox.getItems().addAll("USD", "EUR", "BDT");

        loadFromFile();
    }

    @javafx.fxml.FXML
    public void saveportdetailsButton(ActionEvent actionEvent) {

        // 1. Validation
        if (chargeTextfield.getText().isEmpty() ||
                nameTextField.getText().isEmpty() ||
                shipmentIdComboBox.getValue() == null ||
                currencyComboBox.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
            return;
        }

        // 2. Number validation
        double charge;
        try {
            charge = Double.parseDouble(chargeTextfield.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Charge must be a number");
            alert.showAndWait();
            return;
        }

        // 3. Create object
        PortHandling port = new PortHandling(
                shipmentIdComboBox.getValue(),
                nameTextField.getText(),
                charge,
                currencyComboBox.getValue()
        );

        portList.add(port);

        // 4. Save file
        saveToFile();

        // 5. Success alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Done");
        alert.setContentText("Port Details Saved Successfully!");
        alert.showAndWait();

        // 6. Clear fields
        chargeTextfield.clear();
        nameTextField.clear();
        shipmentIdComboBox.setValue(null);
        currencyComboBox.setValue(null);
    }

    // Save file
    private void saveToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("port.bin"))) {
            output.writeInt(portList.size());
            for (PortHandling p : portList) {
                output.writeObject(p);
            }
        } catch (IOException e) {
            System.out.println("File write error");
        }
    }

    // Load file
    private void loadFromFile() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("port.bin"))) {
            int size = input.readInt();
            for (int i = 0; i < size; i++) {
                PortHandling p = (PortHandling) input.readObject();
                portList.add(p);
            }
        } catch (IOException | ClassNotFoundException e) {
            // first run ignore
        }
    }
}