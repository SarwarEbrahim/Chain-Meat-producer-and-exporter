package com.example.chainmeatproducerandexporter.ArshadAnjum;

import com.example.chainmeatproducerandexporter.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class InspectionHistoryController
{
    @javafx.fxml.FXML
    private TableColumn BatchIDTc;
    @javafx.fxml.FXML
    private TextField BatchIDTF;
    @javafx.fxml.FXML
    private TableColumn StatusTc;
    @javafx.fxml.FXML
    private TableColumn HygieneTc;
    @javafx.fxml.FXML
    private TableColumn odorTc;
    @javafx.fxml.FXML
    private ComboBox Combostatus;
    @javafx.fxml.FXML
    private TableColumn TempTc;
    @javafx.fxml.FXML
    private TableView InspectionHistoryTV;
    @javafx.fxml.FXML
    private TableColumn DateTc;
    @javafx.fxml.FXML
    private DatePicker DateDP;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @Deprecated
    public void BackButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void ApplyFilterButtonOnAction(ActionEvent actionEvent) {
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