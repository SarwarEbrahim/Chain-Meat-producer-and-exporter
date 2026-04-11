
package com.example.chainmeatproducerandexporter.FarinAhmed;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.*;

public class AssignContainerController
{
    @javafx.fxml.FXML
    private TableView<AssignContainer> containerTableView;
    @javafx.fxml.FXML
    private ComboBox<String> containertypeComboBox;
    @javafx.fxml.FXML
    private TextField shipmentIdTextField;
    @javafx.fxml.FXML
    private TextField capacityusedTextfield;

    private ObservableList<AssignContainer> containerList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        containertypeComboBox.getItems().addAll("Small", "Medium", "Large");

        // load existing data from file
        loadFromFile();
    }

    @javafx.fxml.FXML
    public void assignContainerButton(ActionEvent actionEvent) {

        //
        if (shipmentIdTextField.getText().isEmpty() ||
                capacityusedTextfield.getText().isEmpty() ||
                containertypeComboBox.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
            return;
        }

        //
        int capacity;
        try {
            capacity = Integer.parseInt(capacityusedTextfield.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Capacity must be a number");
            alert.showAndWait();
            return;
        }

        //
        for (AssignContainer c : containerList) {
            if (c.getShipmentId().equals(shipmentIdTextField.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Duplicate ID");
                alert.setContentText("Shipment ID already exists");
                alert.showAndWait();
                return;
            }
        }

        //
        AssignContainer assignContainer = new AssignContainer(
                shipmentIdTextField.getText(),
                containertypeComboBox.getValue(),
                capacity
        );

        containerList.add(assignContainer);

        //
        saveToFile();

        //
        containerTableView.setItems(containerList);

        //
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Success");
        success.setHeaderText("Done");
        success.setContentText("Container Assigned Successfully!");
        success.showAndWait();

        //
        shipmentIdTextField.clear();
        capacityusedTextfield.clear();
        containertypeComboBox.setValue(null);
    }

    //
    private void saveToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("container.bin"))) {
            output.writeInt(containerList.size());
            for (AssignContainer c : containerList) {
                output.writeObject(c);
            }
        } catch (IOException e) {
            System.out.println("File write error");
        }
    }

    //
    private void loadFromFile() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("container.bin"))) {
            int size = input.readInt();
            for (int i = 0; i < size; i++) {
                AssignContainer c = (AssignContainer) input.readObject();
                containerList.add(c);
            }
            containerTableView.setItems(containerList);
        } catch (IOException | ClassNotFoundException e) {
            // first time run হলে file না থাকলে সমস্যা নাই
        }
    }
}