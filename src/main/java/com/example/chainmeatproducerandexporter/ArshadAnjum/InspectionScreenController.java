package com.example.chainmeatproducerandexporter.ArshadAnjum;

import com.example.chainmeatproducerandexporter.HelloApplication;
import com.example.chainmeatproducerandexporter.ArshadAnjumModel.MeatBatch;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class InspectionScreenController {

    @javafx.fxml.FXML
    private Label ProductionDateLabel;
    @javafx.fxml.FXML
    private Label BatchIDLabel;
    @javafx.fxml.FXML
    private TextField TemperatureTF;
    @javafx.fxml.FXML
    private ComboBox<String> comboOdorCondition;
    @javafx.fxml.FXML
    private TextArea RemarksTA;
    @javafx.fxml.FXML
    private Label QuantityLabel;
    @javafx.fxml.FXML
    private Label MeatTypeLabel;
    @javafx.fxml.FXML
    private TextField HygieneScoreTF;

    private MeatBatch selectedBatch;

    @javafx.fxml.FXML
    public void initialize() {
        comboOdorCondition.getItems().addAll("Normal", "Slightly Off", "Bad");
    }

    public void setSelectedBatch(MeatBatch selectedBatch) {
        this.selectedBatch = selectedBatch;

        if (selectedBatch != null) {
            BatchIDLabel.setText("Batch ID: "+selectedBatch.getBatchId());
            MeatTypeLabel.setText("Meat Type: "+ selectedBatch.getMeatType());
            QuantityLabel.setText("Quantity: "+ (String.valueOf(selectedBatch.getQuantity())));
            ProductionDateLabel.setText("Production Date: "+selectedBatch.getProductionDate().toString());
        }
    }

    @javafx.fxml.FXML
    public void ReviewInspectionButtonOnAction(ActionEvent actionEvent) {
        String temperature = TemperatureTF.getText().trim();
        String hygieneScore = HygieneScoreTF.getText().trim();
        String odorCondition = comboOdorCondition.getValue();
        String remarks = RemarksTA.getText().trim();

        if (selectedBatch == null) {
            showAlert("Error", "No batch data found.");
            return;
        }

        if (temperature.isEmpty() || hygieneScore.isEmpty() || odorCondition == null || odorCondition.isEmpty()) {
            showAlert("Validation Error", "Please fill in all required fields.");
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("InspectionSummary.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());

            InspectionSummaryController controller = fxmlLoader.getController();
            controller.setInspectionData(selectedBatch, temperature, hygieneScore, odorCondition, remarks);

            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setTitle("Inspection Summary");
            nextStage.setScene(nextScene);
            nextStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @javafx.fxml.FXML
    public void BackButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PendingBatches.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setTitle("Pending Batches");
            nextStage.setScene(nextScene);
            nextStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}