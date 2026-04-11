package com.example.chainmeatproducerandexporter;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.*;
import java.util.ArrayList;

public class RecordPackigingController
{
    @javafx.fxml.FXML
    private ComboBox<String> packagingTypeComboBox;
    @javafx.fxml.FXML
    private TextField unitTextField;
    @javafx.fxml.FXML
    private TextField packageidTextField;
    @javafx.fxml.FXML
    private TextField weightTextField;

    private ArrayList<RecordPackigingDetails> packagingList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        packagingTypeComboBox.getItems().addAll("Box", "Plastic", "Vacuum");
        loadFromFile();
    }

    @javafx.fxml.FXML
    public void packagingButton(ActionEvent actionEvent) {

        // 1. Empty validation
        if (packageidTextField.getText().isEmpty() ||
                unitTextField.getText().isEmpty() ||
                weightTextField.getText().isEmpty() ||
                packagingTypeComboBox.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
            return;
        }

        // 2. Number validation
        int units;
        double weight;

        try {
            units = Integer.parseInt(unitTextField.getText());
            weight = Double.parseDouble(weightTextField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Units must be integer and weight must be number");
            alert.showAndWait();
            return;
        }

        // 3. Create object
        RecordPackigingDetails details = new RecordPackigingDetails();
        details.setPackageID(packageidTextField.getText());
        details.setPackagingType(packagingTypeComboBox.getValue());
        details.setNumberOfUnits(units);
        details.setWeightPerUnits(weight);

        packagingList.add(details);

        // 4. Save file
        saveToFile();

        // 5. Success alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Done");
        alert.setContentText("Packaging Details Saved Successfully!");
        alert.showAndWait();

        // 6. Clear fields
        packageidTextField.clear();
        unitTextField.clear();
        weightTextField.clear();
        packagingTypeComboBox.setValue(null);
    }

    // Save file
    private void saveToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("packaging.bin"))) {
            output.writeInt(packagingList.size());
            for (RecordPackigingDetails r : packagingList) {
                output.writeObject(r);
            }
        } catch (IOException e) {
            System.out.println("File write error");
        }
    }

    // Load file
    private void loadFromFile() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("packaging.bin"))) {
            int size = input.readInt();
            for (int i = 0; i < size; i++) {
                RecordPackigingDetails r = (RecordPackigingDetails) input.readObject();
                packagingList.add(r);
            }
        } catch (IOException | ClassNotFoundException e) {
            // first run ignore
        }
    }
}