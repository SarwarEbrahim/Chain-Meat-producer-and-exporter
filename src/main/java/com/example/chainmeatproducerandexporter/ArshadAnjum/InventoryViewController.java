package com.example.chainmeatproducerandexporter.ArshadAnjum;

import com.example.chainmeatproducerandexporter.HelloApplication;
import com.example.chainmeatproducerandexporter.ArshadAnjumModel.Inventory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class InventoryViewController {

    @javafx.fxml.FXML
    private TableColumn<Inventory, String> MeatTypeTC;
    @javafx.fxml.FXML
    private TextField SearchMeatTypeTF;
    @javafx.fxml.FXML
    private TableColumn<Inventory, LocalDate> ExpiryDateTC;
    @javafx.fxml.FXML
    private TableView<Inventory> InventoryStockTV;
    @javafx.fxml.FXML
    private TableColumn<Inventory, Integer> QuantityTC;
    @javafx.fxml.FXML
    private TextField NewQuantityTF;
    @javafx.fxml.FXML
    private TableColumn<Inventory, String> LocationTC;

    private ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        MeatTypeTC.setCellValueFactory(new PropertyValueFactory<>("meatType"));
        ExpiryDateTC.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        QuantityTC.setCellValueFactory(new PropertyValueFactory<>("totalQuantity"));
        LocationTC.setCellValueFactory(new PropertyValueFactory<>("storageLocation"));

        loadInventoryData();
    }

    private void loadInventoryData() {
        inventoryList.clear();

        try {
            FileReader fr = new FileReader("src/files/inventory.txt");

            StringBuilder content = new StringBuilder();
            int ch;

            while ((ch = fr.read()) != -1) {
                content.append((char) ch);
            }

            fr.close();

            String[] lines = content.toString().split("\n");

            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    try {
                        String[] data = line.split(",");

                        if (data.length < 6) {
                            System.out.println("Skipping bad data line: " + line);
                            continue;
                        }

                        String inventoryId = data[0].trim();
                        String batchId = data[1].trim();
                        String meatType = data[2].trim();
                        int totalQuantity = Integer.parseInt(data[3].trim());
                        String storageLocation = data[4].trim();

                        LocalDate expiryDate = LocalDate.parse(data[5].trim());

                        Inventory inventory = new Inventory(
                                inventoryId,
                                batchId,
                                meatType,
                                totalQuantity,
                                storageLocation,
                                expiryDate
                        );

                        inventoryList.add(inventory);

                    } catch (Exception e) {
                        System.out.println("Skipping bad data line: " + line);
                    }
                }
            }

            InventoryStockTV.setItems(inventoryList);

        } catch (IOException e) {
            showAlert("Error", "Cannot read inventory.txt");
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void UpdateQuantityButtonOnAction(ActionEvent actionEvent) {
        Inventory selectedInventory = InventoryStockTV.getSelectionModel().getSelectedItem();

        if (selectedInventory == null) {
            showAlert("Error", "Please select an inventory item first.");
            return;
        }

        String newQuantityText = NewQuantityTF.getText().trim();

        if (newQuantityText.isEmpty()) {
            showAlert("Error", "Please enter a new quantity.");
            return;
        }

        try {
            int newQuantity = Integer.parseInt(newQuantityText);

            if (newQuantity < 0) {
                showAlert("Error", "Quantity cannot be negative.");
                return;
            }

            selectedInventory.setTotalQuantity(newQuantity);

            saveAllInventoryData(); // save updated data
            InventoryStockTV.refresh();

            showAlert("Success", "Quantity updated successfully.");

        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid number.");
        }
    }

    @javafx.fxml.FXML
    public void invSearchButtonOnAction(ActionEvent actionEvent) {
        String searchText = SearchMeatTypeTF.getText().trim();

        if (searchText.isEmpty()) {
            InventoryStockTV.setItems(inventoryList);
            return;
        }

        ObservableList<Inventory> filteredList = FXCollections.observableArrayList();

        for (Inventory inventory : inventoryList) {
            if (inventory.getMeatType().equalsIgnoreCase(searchText)) {
                filteredList.add(inventory);
            }
        }

        if (filteredList.isEmpty()) {
            showAlert("Search Result", "No inventory found for this meat type.");
        }

        InventoryStockTV.setItems(filteredList);
    }

    @javafx.fxml.FXML
    public void BackButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("InventoryManagerDashboard.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setTitle("Inventory Manager Dashboard");
            nextStage.setScene(nextScene);
            nextStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void ReturnToDashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("InventoryManagerDashboard.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setTitle("Inventory Manager Dashboard");
            nextStage.setScene(nextScene);
            nextStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveAllInventoryData() {
        FileWriter fw = null;

        try {
            fw = new FileWriter("src/files/inventory.txt");

            for (Inventory inventory : inventoryList) {
                String line = inventory.getInventoryId() + "," +
                        inventory.getBatchId() + "," +
                        inventory.getMeatType() + "," +
                        inventory.getTotalQuantity() + "," +
                        inventory.getStorageLocation() + "," +
                        inventory.getExpiryDate(); // LocalDate saved as YYYY-MM-DD

                fw.write(line + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}