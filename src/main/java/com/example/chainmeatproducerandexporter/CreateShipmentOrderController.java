package com.example.chainmeatproducerandexporter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.*;
import java.time.LocalDate;

public class CreateShipmentOrderController
{
    @javafx.fxml.FXML
    private ComboBox<String> exporttypeCombBox;
    @javafx.fxml.FXML
    private ComboBox<String> destinationcountryComboBox;
    @javafx.fxml.FXML
    private DatePicker dateDatePicker;
    @javafx.fxml.FXML
    private TableView<Shipment> shipmentTableView;
    @javafx.fxml.FXML
    private TextField shipmentidTextField;
    @javafx.fxml.FXML
    private TextField quantityTextField;

    private ObservableList<Shipment> shipmentList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        exporttypeCombBox.getItems().addAll("Air", "Sea", "Land");
        destinationcountryComboBox.getItems().addAll("USA", "UK", "Canada", "UAE");

        loadFromFile();
    }

    @javafx.fxml.FXML
    public void createshipmentButton(ActionEvent actionEvent) {

        //
        if (shipmentidTextField.getText().isEmpty() ||
                quantityTextField.getText().isEmpty() ||
                exporttypeCombBox.getValue() == null ||
                destinationcountryComboBox.getValue() == null ||
                dateDatePicker.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
            return;
        }

        //
        int quantity;
        try {
            quantity = Integer.parseInt(quantityTextField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Quantity must be a number");
            alert.showAndWait();
            return;
        }

        //
        LocalDate date = dateDatePicker.getValue();
        if (date.isBefore(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid Date");
            alert.setContentText("Shipment date cannot be in the past");
            alert.showAndWait();
            return;
        }

        //
        for (Shipment s : shipmentList) {
            if (s.getShipmentId().equals(shipmentidTextField.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Duplicate ID");
                alert.setContentText("Shipment ID already exists");
                alert.showAndWait();
                return;
            }
        }

        //
        Shipment shipment = new Shipment(
                shipmentidTextField.getText(),
                exporttypeCombBox.getValue(),
                destinationcountryComboBox.getValue(),
                quantity,
                date.toString()
        );

        shipmentList.add(shipment);

        //
        shipmentTableView.setItems(shipmentList);

        //
        saveToFile();

        //
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Success");
        success.setHeaderText("Done");
        success.setContentText("Shipment Created Successfully!");
        success.showAndWait();

        //
        shipmentidTextField.clear();
        quantityTextField.clear();
        exporttypeCombBox.setValue(null);
        destinationcountryComboBox.setValue(null);
        dateDatePicker.setValue(null);
    }

    //
    private void saveToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("shipment.bin"))) {
            output.writeInt(shipmentList.size());
            for (Shipment s : shipmentList) {
                output.writeObject(s);
            }
        } catch (IOException e) {
            System.out.println("File write error");
        }
    }

    //
    private void loadFromFile() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("shipment.bin"))) {
            int size = input.readInt();
            for (int i = 0; i < size; i++) {
                Shipment s = (Shipment) input.readObject();
                shipmentList.add(s);
            }
            shipmentTableView.setItems(shipmentList);
        } catch (IOException | ClassNotFoundException e) {
            // first run ignore
        }
    }
}