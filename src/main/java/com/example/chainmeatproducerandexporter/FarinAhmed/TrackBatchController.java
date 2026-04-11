package com.example.chainmeatproducerandexporter.FarinAhmed;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.*;
import java.util.ArrayList;

public class TrackBatchController
{
    @javafx.fxml.FXML
    private TextField materialTextField;
    @javafx.fxml.FXML
    private ComboBox<String> batchIdComboBox;
    @javafx.fxml.FXML
    private TableView<TrackBatch> processingTableView;
    @javafx.fxml.FXML
    private TextField LaborTextField;

    private ArrayList<TrackBatch> trackList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {

        batchIdComboBox.getItems().addAll("B001", "B002", "B003");

        loadFromFile();
    }

    @javafx.fxml.FXML
    public void recordcostButton(ActionEvent actionEvent) {

        // 1. Validation
        if (materialTextField.getText().isEmpty() ||
                LaborTextField.getText().isEmpty() ||
                batchIdComboBox.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
            return;
        }

        // 2. Number validation
        double laborCost;
        double materialCost;

        try {
            laborCost = Double.parseDouble(LaborTextField.getText());
            materialCost = Double.parseDouble(materialTextField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Costs must be numbers");
            alert.showAndWait();
            return;
        }

        // 3. Create object
        TrackBatch track = new TrackBatch(
                batchIdComboBox.getValue(),
                materialCost,
                laborCost,
                materialCost + laborCost,
                "Recorded"
        );

        trackList.add(track);

        // 4. Show in TableView
        processingTableView.setItems(FXCollections.observableArrayList(trackList));

        // 5. Save file
        saveToFile();

        // 6. Success alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Done");
        alert.setContentText("Cost Recorded Successfully!");
        alert.showAndWait();

        // 7. Clear fields
        materialTextField.clear();
        LaborTextField.clear();
        batchIdComboBox.setValue(null);
    }

    // Save file
    private void saveToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("trackbatch.bin"))) {
            output.writeInt(trackList.size());
            for (TrackBatch t : trackList) {
                output.writeObject(t);
            }
        } catch (IOException e) {
            System.out.println("File write error");
        }
    }

    // Load file
    private void loadFromFile() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("trackbatch.bin"))) {
            int size = input.readInt();
            for (int i = 0; i < size; i++) {
                TrackBatch t = (TrackBatch) input.readObject();
                trackList.add(t);
            }
        } catch (IOException | ClassNotFoundException e) {
            // first run ignore
        }
    }
}