package com.example.chainmeatproducerandexporter.FarinAhmed;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.*;

public class generateController
{
    @javafx.fxml.FXML
    private TableColumn<Summary, String> idcoulmn;
    @javafx.fxml.FXML
    private TableColumn<Summary, Integer> costColumn;
    @javafx.fxml.FXML
    private TableColumn<Summary, String> productionnameColumn;
    @javafx.fxml.FXML
    private TableColumn<Summary, String> statusColumn;
    @javafx.fxml.FXML
    private TextField batchIDTextField;
    @javafx.fxml.FXML
    private TableColumn<Summary, String> typecolumn;
    @javafx.fxml.FXML
    private TableColumn<Summary, String> packagingColumn;
    @javafx.fxml.FXML
    private AnchorPane batchId;
    @javafx.fxml.FXML
    private TableView<Summary> summaryTableView;
    @javafx.fxml.FXML
    private TableColumn<Summary, String> productColumn;
    @javafx.fxml.FXML
    private TableColumn<Summary, String> processingColumn;

    private ObservableList<Summary> summaryList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        loadFromFile();
    }

    @javafx.fxml.FXML
    public void searchButton(ActionEvent actionEvent) {

        //
        if (batchIDTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Empty Field");
            alert.setContentText("Please enter Batch ID");
            alert.showAndWait();
            return;
        }

        //
        for (Summary s : summaryList) {
            if (s.getBatchID().equals(batchIDTextField.getText())) {
                summaryTableView.setItems(FXCollections.observableArrayList(s));
                return;
            }
        }

        //
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Not Found");
        alert.setHeaderText(null);
        alert.setContentText("No batch found with this ID");
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void generatesummaryButton(ActionEvent actionEvent) {

        //
        if (batchIDTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Empty Field");
            alert.setContentText("Enter Batch ID first");
            alert.showAndWait();
            return;
        }

        //
        Summary summary = new Summary(
                batchIDTextField.getText(),
                "Meat Product",
                "Beef",
                "Processing Done",
                "Packed",
                5000,
                "Completed",
                "Frozen Meat"
        );

        summaryList.add(summary);

        //
        summaryTableView.setItems(summaryList);

        //
        saveToFile();

        //
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Done");
        alert.setContentText("Summary Generated Successfully!");
        alert.showAndWait();
    }

    //
    private void saveToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("summary.bin"))) {
            output.writeInt(summaryList.size());
            for (Summary s : summaryList) {
                output.writeObject(s);
            }
        } catch (IOException e) {
            System.out.println("File write error");
        }
    }

    //
    private void loadFromFile() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("summary.bin"))) {
            int size = input.readInt();
            for (int i = 0; i < size; i++) {
                Summary s = (Summary) input.readObject();
                summaryList.add(s);
            }
            summaryTableView.setItems(summaryList);
        } catch (IOException | ClassNotFoundException e) {
            // first run ignore
        }
    }
}