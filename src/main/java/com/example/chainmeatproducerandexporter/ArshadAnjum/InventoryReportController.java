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

public class InventoryReportController {

    @javafx.fxml.FXML
    private TableColumn<Inventory, String> BatchIDTC;
    @javafx.fxml.FXML
    private DatePicker TdateDP;
    @javafx.fxml.FXML
    private TableColumn<Inventory, String> MeatTypeTC;
    @javafx.fxml.FXML
    private DatePicker FdateDP;
    @javafx.fxml.FXML
    private ComboBox<String> comboReportType;
    @javafx.fxml.FXML
    private TableColumn<Inventory, String> StatusTC;
    @javafx.fxml.FXML
    private TableColumn<Inventory, Integer> QuantityTC;
    @javafx.fxml.FXML
    private TableView<Inventory> InventoryReportTV;
    @javafx.fxml.FXML
    private TableColumn<Inventory, LocalDate> DateTC;

    private ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();
    private ObservableList<Inventory> reportList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        BatchIDTC.setCellValueFactory(new PropertyValueFactory<>("batchId"));
        MeatTypeTC.setCellValueFactory(new PropertyValueFactory<>("meatType"));
        QuantityTC.setCellValueFactory(new PropertyValueFactory<>("totalQuantity"));
        DateTC.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        StatusTC.setCellValueFactory(new PropertyValueFactory<>("expiryStatus"));

        comboReportType.getItems().addAll("All", "Expired", "Near Expiry", "Fresh");
        comboReportType.setValue("All");

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

                        inventory.setExpiryStatus(getExpiryStatus(expiryDate));

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
    public void GenerateReportButtonOnAction(ActionEvent actionEvent) {
        LocalDate fromDate = FdateDP.getValue();
        LocalDate toDate = TdateDP.getValue();
        String reportType = comboReportType.getValue();

        reportList.clear();

        for (Inventory inventory : inventoryList) {

            LocalDate expiryDate = inventory.getExpiryDate();
            String status = getExpiryStatus(expiryDate);

            boolean matchesDate = true;
            boolean matchesType = true;

            if (fromDate != null && expiryDate.isBefore(fromDate)) {
                matchesDate = false;
            }

            if (toDate != null && expiryDate.isAfter(toDate)) {
                matchesDate = false;
            }

            if (reportType != null && !reportType.equals("All")) {
                if (!status.equals(reportType)) {
                    matchesType = false;
                }
            }

            if (matchesDate && matchesType) {
                inventory.setExpiryStatus(status);
                reportList.add(inventory);
            }
        }

        InventoryReportTV.setItems(reportList);

        if (reportList.isEmpty()) {
            showAlert("Report", "No records found for selected criteria.");
        }
    }

    @javafx.fxml.FXML
    public void SaveToFileOnAction(ActionEvent actionEvent) {

            if (reportList.isEmpty()) {
                showAlert("Error", "No report data to save.");
                return;
            }

            javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
            fileChooser.setTitle("Save Inventory Report");

            fileChooser.getExtensionFilters().add(
                    new javafx.stage.FileChooser.ExtensionFilter("Text Files", "*.txt")
            );

            fileChooser.setInitialFileName("inventory_report.txt");

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            java.io.File file = fileChooser.showSaveDialog(stage);

            if (file == null) {
                return; // user cancelled
            }

            try {
                FileWriter fw = new FileWriter(file);

                // header
                fw.write("Batch ID,Meat Type,Quantity,Expiry Date,Status\n");

                // data
                for (Inventory inventory : reportList) {
                    String line = inventory.getBatchId() + "," +
                            inventory.getMeatType() + "," +
                            inventory.getTotalQuantity() + "," +
                            inventory.getExpiryDate() + "," +
                            inventory.getExpiryStatus();

                    fw.write(line + "\n");
                }

                fw.close();

                showAlert("Success", "Report saved successfully!");

            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "Failed to save file.");
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
    public void ReturnToDashboardButtonOnAction(ActionEvent actionEvent) {
        BackButtonOnAction(actionEvent); // reuse code
    }

    private String getExpiryStatus(LocalDate expiryDate) {
        LocalDate today = LocalDate.now();

        if (expiryDate.isBefore(today)) {
            return "Expired";
        } else if (!expiryDate.isAfter(today.plusDays(3))) {
            return "Near Expiry";
        } else {
            return "Fresh";
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