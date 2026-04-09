package com.example.chainmeatproducerandexporter.ArshadAnjumAbir;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class InventoryReportController
{
    @javafx.fxml.FXML
    private TableColumn BatchIDTC;
    @javafx.fxml.FXML
    private DatePicker TdateDP;
    @javafx.fxml.FXML
    private TableColumn MeatTypeTC;
    @javafx.fxml.FXML
    private DatePicker FdateDP;
    @javafx.fxml.FXML
    private ComboBox comboReportType;
    @javafx.fxml.FXML
    private TableColumn StatusTC;
    @javafx.fxml.FXML
    private TableColumn QuantityTC;
    @javafx.fxml.FXML
    private TableView InventoryReportTV;
    @javafx.fxml.FXML
    private TableColumn DateTC;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void BackButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void SaveToFileOnAction(ActionEvent actionEvent) {
    }
}