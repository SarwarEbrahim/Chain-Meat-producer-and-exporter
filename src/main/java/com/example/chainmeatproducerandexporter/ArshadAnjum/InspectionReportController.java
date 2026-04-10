package com.example.chainmeatproducerandexporter.ArshadAnjum;

import com.example.chainmeatproducerandexporter.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class InspectionReportController
{
    @javafx.fxml.FXML
    private TableView InspectionReportTV;
    @javafx.fxml.FXML
    private TableColumn BatchIDTc;
    @javafx.fxml.FXML
    private TableColumn StatusTc;
    @javafx.fxml.FXML
    private TableColumn InspectorTc;
    @javafx.fxml.FXML
    private DatePicker ToDateDP;
    @javafx.fxml.FXML
    private ComboBox ComboBatchType;
    @javafx.fxml.FXML
    private DatePicker FromDateDP;
    @javafx.fxml.FXML
    private TableColumn IMeatTypeTc;
    @javafx.fxml.FXML
    private TableColumn DateTc;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void SaveToFileButtonOnAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void BackButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void GenerateReportButtonOnAction(ActionEvent actionEvent) {
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