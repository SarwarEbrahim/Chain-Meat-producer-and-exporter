package com.example.chainmeatproducerandexporter.FarinAhmed;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.*;
import java.time.LocalDate;

public class DailyProductionCapacityController
{
    @javafx.fxml.FXML
    private DatePicker dateDatePicker;
    @javafx.fxml.FXML
    private TextField productnameTextField;
    @javafx.fxml.FXML
    private TableColumn<ProductionCapacity, Double> percentageusageColumn;
    @javafx.fxml.FXML
    private TableColumn<ProductionCapacity, String> productnameColumn;
    @javafx.fxml.FXML
    private TextField availablecapacityTextfield;
    @javafx.fxml.FXML
    private TableView<ProductionCapacity> productionTableView;
    @javafx.fxml.FXML
    private TableColumn<ProductionCapacity, Integer> capacitycolumn;
    @javafx.fxml.FXML
    private TextField percentageusageTextfield;
    @javafx.fxml.FXML
    private TableColumn<ProductionCapacity, String> datecoulmn;

    private ObservableList<ProductionCapacity> capacityList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        loadFromFile();
    }

    @javafx.fxml.FXML
    public void recordcapacityusageButton(ActionEvent actionEvent) {

        //
        if (productnameTextField.getText().isEmpty() ||
                availablecapacityTextfield.getText().isEmpty() ||
                percentageusageTextfield.getText().isEmpty() ||
                dateDatePicker.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
            return;
        }

        //
        int capacity;
        double percentage;

        try {
            capacity = Integer.parseInt(availablecapacityTextfield.getText());
            percentage = Double.parseDouble(percentageusageTextfield.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Capacity and Percentage must be numbers");
            alert.showAndWait();
            return;
        }

        //
        if (percentage < 0 || percentage > 100) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid Percentage");
            alert.setContentText("Percentage must be between 0 and 100");
            alert.showAndWait();
            return;
        }

        //
        LocalDate date = dateDatePicker.getValue();
        if (date.isAfter(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid Date");
            alert.setContentText("Date cannot be in future");
            alert.showAndWait();
            return;
        }

        //
        ProductionCapacity pc = new ProductionCapacity(
                productnameTextField.getText(),
                capacity,
                percentage,
                date.toString()
        );

        capacityList.add(pc);

        //
        productionTableView.setItems(capacityList);

        //
        saveToFile();

        //
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Done");
        alert.setContentText("Capacity Recorded Successfully!");
        alert.showAndWait();

        //
        productnameTextField.clear();
        availablecapacityTextfield.clear();
        percentageusageTextfield.clear();
        dateDatePicker.setValue(null);
    }

    //
    private void saveToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("capacity.bin"))) {
            output.writeInt(capacityList.size());
            for (ProductionCapacity pc : capacityList) {
                output.writeObject(pc);
            }
        } catch (IOException e) {
            System.out.println("File write error");
        }
    }

    //
    private void loadFromFile() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("capacity.bin"))) {
            int size = input.readInt();
            for (int i = 0; i < size; i++) {
                ProductionCapacity pc = (ProductionCapacity) input.readObject();
                capacityList.add(pc);
            }
            productionTableView.setItems(capacityList);
        } catch (IOException | ClassNotFoundException e) {
            // first run ignore
        }
    }
}