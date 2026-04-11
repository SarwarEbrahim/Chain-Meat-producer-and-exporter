package com.example.chainmeatproducerandexporter.ArshadAnjum;

import com.example.chainmeatproducerandexporter.ArshadAnjumModel.MeatBatch;
import com.example.chainmeatproducerandexporter.HelloApplication;
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
import java.io.IOException;

public class PendingBatchesController {

    @javafx.fxml.FXML
    private TableView<MeatBatch> PendingBatchesTV;

    @javafx.fxml.FXML
    private TableColumn<MeatBatch, String> BatchIDTC;

    @javafx.fxml.FXML
    private TableColumn<MeatBatch, String> ProdDateTF;

    @javafx.fxml.FXML
    private TableColumn<MeatBatch, String> MeatTypeTC;

    @javafx.fxml.FXML
    private TableColumn<MeatBatch, Integer> QuantityTC;

    @javafx.fxml.FXML
    private TextField SearchBatchIDTF;

    private ObservableList<MeatBatch> pendingBatchList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        BatchIDTC.setCellValueFactory(new PropertyValueFactory<>("batchId"));
        MeatTypeTC.setCellValueFactory(new PropertyValueFactory<>("meatType"));
        QuantityTC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ProdDateTF.setCellValueFactory(new PropertyValueFactory<>("productionDate"));

        loadPendingBatches();
    }

    private void loadPendingBatches() {
        pendingBatchList.clear();

        try {
            FileReader fr = new FileReader("src/files/pending_batches.txt");

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

                        String batchId = data[0].trim();
                        String meatType = data[1].trim();
                        int quantity = Integer.parseInt(data[2].trim());
                        String productionDate = data[3].trim();
                        String expiryDate = data[4].trim();

                        MeatBatch batch = new MeatBatch(
                                batchId,
                                meatType,
                                quantity,
                                productionDate,
                                expiryDate
                        );

                        pendingBatchList.add(batch);

                    } catch (Exception e) {
                        System.out.println("Skipping bad data line: " + line);
                    }
                }
            }

            PendingBatchesTV.setItems(pendingBatchList);

        } catch (IOException e) {
            showAlert("Error", "Cannot read pending_batches.txt");
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void InspectButtonOnAction(ActionEvent actionEvent) {

        MeatBatch selected = PendingBatchesTV.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("Error", "Select a batch first!");
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("InspectionScreen.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());

            InspectionScreenController controller = fxmlLoader.getController();
            controller.setSelectedBatch(selected);

            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setScene(nextScene);
            nextStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void SearchButtonOnAction(ActionEvent actionEvent) {

        String searchId = SearchBatchIDTF.getText().trim();

        if (searchId.isEmpty()) {
            PendingBatchesTV.setItems(pendingBatchList);
            return;
        }

        ObservableList<MeatBatch> filtered = FXCollections.observableArrayList();

        for (MeatBatch batch : pendingBatchList) {
            if (batch.getBatchId().equalsIgnoreCase(searchId)) {
                filtered.add(batch);
            }
        }

        if (filtered.isEmpty()) {
            showAlert("Search", "No batch found!");
        }

        PendingBatchesTV.setItems(filtered);
    }

    @javafx.fxml.FXML
    public void ReturnToDashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QualityInspectorDashboard.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setTitle("Quality Inspector Dashboard");
            nextStage.setScene(nextScene);
            nextStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}