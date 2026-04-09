package com.example.chainmeatproducerandexporter.ArshadAnjum;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

    @javafx.fxml.FXML
    public void BackButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void GenerateReportButtonOnAction(ActionEvent actionEvent) {
    }
}