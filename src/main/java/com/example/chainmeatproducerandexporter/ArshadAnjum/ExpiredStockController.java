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

public class ExpiredStockController {

    @javafx.fxml.FXML
    private TableColumn<Inventory, String> MeatTypeTc;
    @javafx.fxml.FXML
    private TableView<Inventory> ExpiredExpiryTV;
    @javafx.fxml.FXML
    private TableColumn<Inventory, String> expirybatchidTc;
    @javafx.fxml.FXML
    private TableColumn<Inventory, LocalDate> ExpiryDateTc;
    @javafx.fxml.FXML
    private ComboBox<String> comboFilter;
    @javafx.fxml.FXML
    private TableColumn<Inventory, Integer> QuantityTc;
    @javafx.fxml.FXML
    private TableColumn<Inventory, String> estatusTc;

    private ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();
    private ObservableList<Inventory> filteredList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        expirybatchidTc.setCellValueFactory(new PropertyValueFactory<>("batchId"));
        MeatTypeTc.setCellValueFactory(new PropertyValueFactory<>("meatType"));
        QuantityTc.setCellValueFactory(new PropertyValueFactory<>("totalQuantity"));
        ExpiryDateTc.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        estatusTc.setCellValueFactory(new PropertyValueFactory<>("expiryStatus"));

        comboFilter.getItems().addAll("All", "Expired", "Near Expiry");
        comboFilter.setValue("All");

        loadInventoryData();
        applyFilter("All");
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

        } catch (IOException e) {
            showAlert("Error", "Cannot read inventory.txt");
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void ApplyButtonOnAction(ActionEvent actionEvent) {
        String selectedFilter = comboFilter.getValue();

        if (selectedFilter == null || selectedFilter.isEmpty()) {
            selectedFilter = "All";
        }

        applyFilter(selectedFilter);
    }

    private void applyFilter(String filterType) {
        filteredList.clear();

        for (Inventory inventory : inventoryList) {


            String status = getExpiryStatus(inventory.getExpiryDate());

            if (filterType.equals("All")) {
                if (status.equals("Expired") || status.equals("Near Expiry")) {
                    inventory.setExpiryStatus(status);
                    filteredList.add(inventory);
                }
            }
            else if (filterType.equals("Expired")) {
                if (status.equals("Expired")) {
                    inventory.setExpiryStatus(status);
                    filteredList.add(inventory);
                }
            }
            else if (filterType.equals("Near Expiry")) {
                if (status.equals("Near Expiry")) {
                    inventory.setExpiryStatus(status);
                    filteredList.add(inventory);
                }
            }
        }

        ExpiredExpiryTV.setItems(filteredList);
    }

    private String getExpiryStatus(LocalDate expiryDate) {

        LocalDate today = LocalDate.now();

        if (expiryDate.isBefore(today)) {
            return "Expired";
        }
        else if (!expiryDate.isAfter(today.plusDays(3))) {
            return "Near Expiry";
        }
        else {
            return "Fresh";
        }
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
    public void RemoveBatchButtonOnAction(ActionEvent actionEvent) {
        Inventory selectedInventory = ExpiredExpiryTV.getSelectionModel().getSelectedItem();

        if (selectedInventory == null) {
            showAlert("Error", "Please select a batch first.");
            return;
        }

        inventoryList.removeIf(item -> item.getBatchId().equals(selectedInventory.getBatchId()));

        saveAllInventoryData();
        applyFilter(comboFilter.getValue());

        showAlert("Success", "Batch removed successfully.");
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
                        inventory.getExpiryDate();

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