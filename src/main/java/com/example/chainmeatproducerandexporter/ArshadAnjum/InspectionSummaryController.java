package com.example.chainmeatproducerandexporter.ArshadAnjum;

import com.example.chainmeatproducerandexporter.ArshadAnjumModel.MeatBatch;
import com.example.chainmeatproducerandexporter.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class InspectionSummaryController {

    @javafx.fxml.FXML
    private Label remarkslabel;
    @javafx.fxml.FXML
    private Label insbidlabel;
    @javafx.fxml.FXML
    private Label HygieneScorelabel;
    @javafx.fxml.FXML
    private Label OdorConditionlabel;
    @javafx.fxml.FXML
    private Label MeatTypeLabel;
    @javafx.fxml.FXML
    private Label instemplabel;

    private MeatBatch selectedBatch;
    private String temperature;
    private String hygieneScore;
    private String odorCondition;
    private String remarks;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void ApproveBatchButtonOnAction(ActionEvent actionEvent) {
        if (selectedBatch == null) {
            showAlert("Error", "No batch selected.");
            return;
        }

        String batchData = selectedBatch.getBatchId() + "," +
                selectedBatch.getMeatType() + "," +
                selectedBatch.getQuantity() + "," +
                selectedBatch.getProductionDate() + "," +
                selectedBatch.getExpiryDate();

        writeToFile("src/files/approved_batches.txt", batchData);
        saveInspectionRecord("APPROVED");
        removeFromPendingFile(selectedBatch.getBatchId());

        showAlert("Success", "Batch approved successfully.");
        goToDashboard(actionEvent);
    }

    @javafx.fxml.FXML
    public void RejectBatchButtonOnAction(ActionEvent actionEvent) {
        if (selectedBatch == null) {
            showAlert("Error", "No batch selected.");
            return;
        }

        String batchData = selectedBatch.getBatchId() + "," +
                selectedBatch.getMeatType() + "," +
                selectedBatch.getQuantity() + "," +
                selectedBatch.getProductionDate() + "," +
                selectedBatch.getExpiryDate() + "," +
                remarks;

        writeToFile("src/files/rejected_batches.txt", batchData);
        saveInspectionRecord("REJECTED");
        removeFromPendingFile(selectedBatch.getBatchId());

        showAlert("Success", "Batch rejected successfully.");
        goToDashboard(actionEvent);
    }

    @javafx.fxml.FXML
    public void BackOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("InspectionScreen.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());

            InspectionScreenController controller = fxmlLoader.getController();
            controller.setSelectedBatch(selectedBatch);

            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setTitle("Inspection Screen");
            nextStage.setScene(nextScene);
            nextStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void ReInspectionButtonOnAction(ActionEvent actionEvent) {
        if (selectedBatch == null) {
            showAlert("Error", "No batch selected.");
            return;
        }

        String batchData = selectedBatch.getBatchId() + "," +
                selectedBatch.getMeatType() + "," +
                selectedBatch.getQuantity() + "," +
                selectedBatch.getProductionDate() + "," +
                selectedBatch.getExpiryDate() + "," +
                remarks;

        writeToFile("src/files/reinspection_batches.txt", batchData);
        saveInspectionRecord("REINSPECTION");
        removeFromPendingFile(selectedBatch.getBatchId());

        showAlert("Success", "Batch marked for re-inspection.");
        goToDashboard(actionEvent);
    }

    @javafx.fxml.FXML
    public void ReturnToDashboardButtonOnAction(ActionEvent actionEvent) {
        goToDashboard(actionEvent);
    }

    public void setInspectionData(MeatBatch selectedBatch, String temperature, String hygieneScore, String odorCondition, String remarks) {
        this.selectedBatch = selectedBatch;
        this.temperature = temperature;
        this.hygieneScore = hygieneScore;
        this.odorCondition = odorCondition;
        this.remarks = remarks;

        insbidlabel.setText(selectedBatch.getBatchId());
        MeatTypeLabel.setText(selectedBatch.getMeatType());
        instemplabel.setText(temperature);
        HygieneScorelabel.setText(hygieneScore);
        OdorConditionlabel.setText(odorCondition);
        remarkslabel.setText(remarks);
    }

    private void saveInspectionRecord(String result) {
        String recordId = "IR" + System.currentTimeMillis();

        String record = recordId + "," +
                selectedBatch.getBatchId() + "," +
                "QI01" + "," +
                java.time.LocalDate.now() + "," +
                temperature + "," +
                hygieneScore + "," +
                odorCondition + "," +
                remarks + "," +
                result;

        writeToFile("src/files/inspection_records.txt", record);
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
                // file may not exist yet
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

    private void removeFromPendingFile(String batchId) {
        File inputFile = new File("src/files/pending_batches.txt");
        File tempFile = new File("src/files/temp.txt");

        try {
            FileReader fr = new FileReader(inputFile);
            FileWriter fw = new FileWriter(tempFile);

            StringBuilder content = new StringBuilder();
            int ch;

            while ((ch = fr.read()) != -1) {
                content.append((char) ch);
            }

            fr.close();

            String[] lines = content.toString().split("\n");

            for (String line : lines) {
                if (!line.trim().isEmpty() && !line.startsWith(batchId + ",")) {
                    fw.write(line + "\n");
                }
            }

            fw.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goToDashboard(ActionEvent actionEvent) {
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
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}