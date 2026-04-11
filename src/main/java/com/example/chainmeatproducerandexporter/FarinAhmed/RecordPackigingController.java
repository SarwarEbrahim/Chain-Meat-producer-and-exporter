package com.example.chainmeatproducerandexporter.FarinAhmed;

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

    private ArrayList<Packaging> packagingList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {

        packagingTypeComboBox.getItems().addAll("Box", "Plastic", "Vacuum");

        loadFromFile();
    }

    @javafx.fxml.FXML
    public void packagingButton(ActionEvent actionEvent) {

        // 1. Validation
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
        int unit;
        double weight;

        try {
            unit = Integer.parseInt(unitTextField.getText());
            weight = Double.parseDouble(weightTextField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Unit and Weight must be numbers");
            alert.showAndWait();
            return;
        }

        // 3. Create object
        Packaging packaging = new Packaging(
                packageidTextField.getText(),
                packagingTypeComboBox.getValue(),
                unit,
                weight
        );

        packagingList.add(packaging);

        // 4. Save file
        saveToFile();

        // 5. Success alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Done");
        alert.setContentText("Packaging Recorded Successfully!");
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
            for (Packaging p : packagingList) {
                output.writeObject(p);
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
                Packaging p = (Packaging) input.readObject();
                packagingList.add(p);
            }
        } catch (IOException | ClassNotFoundException e) {
            // first run ignore
        }
    }
}

