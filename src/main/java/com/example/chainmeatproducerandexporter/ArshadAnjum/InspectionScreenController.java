package com.example.chainmeatproducerandexporter.ArshadAnjum;

import com.example.chainmeatproducerandexporter.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InspectionScreenController
{
    @javafx.fxml.FXML
    private Label ProductionDateLabel;
    @javafx.fxml.FXML
    private Label BatchIDLabel;
    @javafx.fxml.FXML
    private TextField TemperatureTF;
    @javafx.fxml.FXML
    private ComboBox comboOdorCondition;
    @javafx.fxml.FXML
    private TextArea RemarksTA;
    @javafx.fxml.FXML
    private Label QuantityLabel;
    @javafx.fxml.FXML
    private Label MeatTypeLabel;
    @javafx.fxml.FXML
    private TextField HygieneScoreTF;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void ReviewInspectionButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Inspectionsummary.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setTitle("Inspection Summary");
            nextStage.setScene(nextScene);
            nextStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public void BackButtonOnAction(ActionEvent actionEvent) {
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