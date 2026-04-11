package com.example.chainmeatproducerandexporter.ArshadAnjum;

import com.example.chainmeatproducerandexporter.HelloApplication;
import com.example.chainmeatproducerandexporter.ArshadAnjumModel.MeatBatch;
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

public class ApprovedBatchesController {

    @javafx.fxml.FXML
    private TableView<MeatBatch> ApprovedBatchesTV;
    @javafx.fxml.FXML
    private TableColumn<MeatBatch, String> MeatTypeTC;
    @javafx.fxml.FXML
    private ComboBox<String> ComboLocation;
    @javafx.fxml.FXML
    private TableColumn<MeatBatch, Integer> QuantityTC;
    @javafx.fxml.FXML
    private TableColumn<MeatBatch, String> ExpiredDateTC;
    @javafx.fxml.FXML
    private TextField SearchBatchIDTF;
    @javafx.fxml.FXML
    private TableColumn<MeatBatch, String> idbatchTC;
    @javafx.fxml.FXML
    private TableColumn<MeatBatch, String> ApprovalDateTC;

    private ObservableList<MeatBatch> approvedBatchList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        idbatchTC.setCellValueFactory(new PropertyValueFactory<>("batchId"));
        MeatTypeTC.setCellValueFactory(new PropertyValueFactory<>("meatType"));
        QuantityTC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ApprovalDateTC.setCellValueFactory(new PropertyValueFactory<>("productionDate"));
        ExpiredDateTC.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));

        ComboLocation.getItems().addAll("Cold Storage A", "Cold Storage B", "Freezer Room 1");

        loadApprovedBatches();
    }

    private void loadApprovedBatches() {
        approvedBatchList.clear();

        try {
            FileReader fr = new FileReader("src/files/approved_batches.txt");

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

                        if (data.length < 5) {
                            System.out.println("Skipping bad data line: " + line);
                            continue;
                        }

                        String batchId = data[0].trim();
                        String meatType = data[1].trim();
                        int quantity = Integer.parseInt(data[2].trim());
                        String productionDate = data[3].trim();
                        String expiryDate = data[4].trim();

                        MeatBatch batch = new MeatBatch(batchId, meatType, quantity, productionDate, expiryDate);
                        approvedBatchList.add(batch);

                    } catch (Exception e) {
                        System.out.println("Skipping bad data line: " + line);
                    }
                }
            }

            ApprovedBatchesTV.setItems(approvedBatchList);

        } catch (IOException e) {
            showAlert("Error", "Cannot read approved_batches.txt");
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void AddtoInventoryButtonOnAction(ActionEvent actionEvent) {
        MeatBatch selectedBatch = ApprovedBatchesTV.getSelectionModel().getSelectedItem();
        String selectedLocation = ComboLocation.getValue();

        if (selectedBatch == null) {
            showAlert("Error", "Please select a batch first.");
            return;
        }

        if (selectedLocation == null || selectedLocation.isEmpty()) {
            showAlert("Error", "Please select a storage location.");
            return;
        }

        String inventoryId = "I" + System.currentTimeMillis();

        String inventoryData = inventoryId + "," +
                selectedBatch.getBatchId() + "," +
                selectedBatch.getMeatType() + "," +
                selectedBatch.getQuantity() + "," +
                selectedLocation + "," +
                selectedBatch.getExpiryDate();

        writeToFile("src/files/inventory.txt", inventoryData);

        showAlert("Success", "Batch added to inventory successfully.");
    }

    @javafx.fxml.FXML
    public void SearchButtonOnAction(ActionEvent actionEvent) {
        String searchId = SearchBatchIDTF.getText().trim();

        if (searchId.isEmpty()) {
            ApprovedBatchesTV.setItems(approvedBatchList);
            return;
        }

        ObservableList<MeatBatch> filteredList = FXCollections.observableArrayList();

        for (MeatBatch batch : approvedBatchList) {
            if (batch.getBatchId().equalsIgnoreCase(searchId)) {
                filteredList.add(batch);
            }
        }

        if (filteredList.isEmpty()) {
            showAlert("Search Result", "No batch found with this Batch ID.");
        }

        ApprovedBatchesTV.setItems(filteredList);
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

    private void writeToFile(String filePath, String data) {
        try {
            StringBuilder oldContent = new StringBuilder();

            try {
                FileReader fr = new FileReader(filePath);
                int ch;
                while ((ch = fr.read()) != -1) {
                    oldContent.append((char) ch);
                }
                fr.close();
            } catch (IOException e) {
                // file may not exist yet; that's okay
            }

            FileWriter fw = new FileWriter(filePath);

            if (!oldContent.toString().isEmpty()) {
                fw.write(oldContent.toString());

                if (!oldContent.toString().endsWith("\n")) {
                    fw.write("\n");
                }
            }

            fw.write(data);
            fw.write("\n");
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
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