package com.example.chainmeatproducerandexporter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.*;

public class CostController
{
    @javafx.fxml.FXML
    private TextField handleTextField;
    @javafx.fxml.FXML
    private TableView<Cost> CostTableView;
    @javafx.fxml.FXML
    private TableColumn<Cost, Integer> totalColumn;
    @javafx.fxml.FXML
    private Label transportTextfield;
    @javafx.fxml.FXML
    private ComboBox<String> selectComboBox;
    @javafx.fxml.FXML
    private TableColumn<Cost, Integer> handleColumn;
    @javafx.fxml.FXML
    private TableColumn<Cost, String> idColumn;
    @javafx.fxml.FXML
    private TableColumn<Cost, Integer> transportColumn;

    private ObservableList<Cost> costList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {

        // ComboBox values
        selectComboBox.getItems().addAll("Truck", "Ship", "Air");

        // Load file data
        loadFromFile();
    }

    @javafx.fxml.FXML
    public void TotalCostButton(ActionEvent actionEvent) {

        //
        if (handleTextField.getText().isEmpty() || selectComboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
            return;
        }

        //
        int handleCost;
        try {
            handleCost = Integer.parseInt(handleTextField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Handling cost must be a number");
            alert.showAndWait();
            return;
        }

        //
        int transportCost = 0;
        String type = selectComboBox.getValue();

        if (type.equals("Truck")) transportCost = 500;
        else if (type.equals("Ship")) transportCost = 1000;
        else if (type.equals("Air")) transportCost = 2000;

        // show transport cost in label
        transportTextfield.setText(String.valueOf(transportCost));

        //
        int total = handleCost + transportCost;

        //

        Cost cost = new Cost("C" + (costList.size()+1), handleCost, transportCost, total);
        costList.add(cost);

        // ✅ 6. Show in TableView
        CostTableView.setItems(costList);

        // ✅ 7. Save file
        saveToFile();

        // ✅ 8. Success alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Done");
        alert.setContentText("Cost Calculated Successfully!");
        alert.showAndWait();

        // ✅ 9. Clear fields
        handleTextField.clear();
        selectComboBox.setValue(null);
    }

    // ✅ Save method
    private void saveToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("cost.bin"))) {
            output.writeInt(costList.size());
            for (Cost c : costList) {
                output.writeObject(c);
            }
        } catch (IOException e) {
            System.out.println("File write error");
        }
    }

    // ✅ Load method
    private void loadFromFile() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("cost.bin"))) {
            int size = input.readInt();
            for (int i = 0; i < size; i++) {
                Cost c = (Cost) input.readObject();
                costList.add(c);
            }
            CostTableView.setItems(costList);
        } catch (IOException | ClassNotFoundException e) {
            // first run হলে ignore
        }
    }
}