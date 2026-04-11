package com.example.chainmeatproducerandexporter.FarinAhmed;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.util.ArrayList;

public class ExportDocumentController
{
    @javafx.fxml.FXML
    private ComboBox<String> documenttypeComboBox;
    @javafx.fxml.FXML
    private AnchorPane unitsTextfield;
    @javafx.fxml.FXML
    private TextField unitTextField;
    @javafx.fxml.FXML
    private TextField shipmentidTextField;
    @javafx.fxml.FXML
    private TextField weightTextField;

    private String paymentStatus = ""; // Paid / Pending

    private ArrayList<ExportDocument> documentList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        documenttypeComboBox.getItems().addAll("Invoice", "Packing List", "Bill of Lading");

        loadFromFile();
    }

    @javafx.fxml.FXML
    public void addDocumentButton(ActionEvent actionEvent) {

        // ✅ 1. Validation
        if (shipmentidTextField.getText().isEmpty() ||
                unitTextField.getText().isEmpty() ||
                weightTextField.getText().isEmpty() ||
                documenttypeComboBox.getValue() == null ||
                paymentStatus.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Please fill all fields and select payment status");
            alert.showAndWait();
            return;
        }

        // ✅ 2. Number validation
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

        //
        ExportDocument doc = new ExportDocument(
                shipmentidTextField.getText(),
                documenttypeComboBox.getValue(),
                unit,
                weight,
                paymentStatus
        );

        documentList.add(doc);

        //
        saveToFile();

        //
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Done");
        alert.setContentText("Document Added Successfully!");
        alert.showAndWait();

        //
        shipmentidTextField.clear();
        unitTextField.clear();
        weightTextField.clear();
        documenttypeComboBox.setValue(null);
        paymentStatus = "";
    }

    //
    @javafx.fxml.FXML
    public void paidRadioButton(ActionEvent actionEvent) {
        paymentStatus = "Paid";
    }

    @javafx.fxml.FXML
    public void pendingRadioButton(ActionEvent actionEvent) {
        paymentStatus = "Pending";
    }

    //
    private void saveToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("exportdoc.bin"))) {
            output.writeInt(documentList.size());
            for (ExportDocument d : documentList) {
                output.writeObject(d);
            }
        } catch (IOException e) {
            System.out.println("File write error");
        }
    }

    //
    private void loadFromFile() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("exportdoc.bin"))) {
            int size = input.readInt();
            for (int i = 0; i < size; i++) {
                ExportDocument d = (ExportDocument) input.readObject();
                documentList.add(d);
            }
        } catch (IOException | ClassNotFoundException e) {
            // first run ignore
        }
    }
}