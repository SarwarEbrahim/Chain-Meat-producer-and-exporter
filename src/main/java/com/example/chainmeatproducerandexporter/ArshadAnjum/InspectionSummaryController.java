package com.example.chainmeatproducerandexporter.ArshadAnjum;

import com.example.chainmeatproducerandexporter.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class InspectionSummaryController
{
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

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void ApproveBatchButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void RejectBatchButtonOnAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void BackOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void ReInspectionButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void ReturnToDashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QualityInspectorDashboard.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setTitle("QualityInspectorDashboard.fxml");
            nextStage.setScene(nextScene);
            nextStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}