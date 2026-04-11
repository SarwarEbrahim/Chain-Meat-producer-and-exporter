package com.example.chainmeatproducerandexporter;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TrackBatchController
{
    @javafx.fxml.FXML
    private TextField materialTextField;
    @javafx.fxml.FXML
    private ComboBox<String> batchIdComboBox;
    @javafx.fxml.FXML
    private TableView<TrackBatchprocessingCost> processingTableView;
    @javafx.fxml.FXML
    private TextField LaborTextField;

    private ArrayList<TrackBatchprocessingCost> costList = new ArrayList<>();

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
        double materialCost;
        double laborCost;

        try {
            materialCost = Double.parseDouble(materialTextField.getText());
            laborCost = Double.parseDouble(LaborTextField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Cost must be numeric values");
            alert.showAndWait();
            return;
        }

        // 3. Date
        String date = LocalDate.now().toString();

        // 4. Calculate total
        double total = materialCost + laborCost;

        // 5. Create object
        TrackBatchprocessingCost cost = new TrackBatchprocessingCost(
                batchIdComboBox.getValue(),
                laborCost,
                materialCost,
                total,
                date
        );

        costList.add(cost);

        // 6. Show in TableView
        processingTableView.setItems(FXCollections.observableArrayList(costList));

        // 7. Save file
        saveToFile();

        // 8. Success alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Done");
        alert.setContentText("Batch Cost Recorded Successfully!");
        alert.showAndWait();

        // 9. Clear fields
        materialTextField.clear();
        LaborTextField.clear();
        batchIdComboBox.setValue(null);
    }

    // Save file
    private void saveToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("batchcost.bin"))) {
            output.writeInt(costList.size());
            for (TrackBatchprocessingCost c : costList) {
                output.writeObject(c);
            }
        } catch (IOException e) {
            System.out.println("File write error");
        }
    }

    // Load file
    private void loadFromFile() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("batchcost.bin"))) {
            int size = input.readInt();
            for (int i = 0; i < size; i++) {
                TrackBatchprocessingCost c = (TrackBatchprocessingCost) input.readObject();
                costList.add(c);
            }
        } catch (IOException | ClassNotFoundException e) {
            // first run ignore
        }
    }
}