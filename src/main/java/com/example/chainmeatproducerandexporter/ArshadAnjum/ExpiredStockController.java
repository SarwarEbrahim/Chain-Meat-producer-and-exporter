package com.example.chainmeatproducerandexporter.ArshadAnjum;

import com.example.chainmeatproducerandexporter.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ExpiredStockController
{
    @javafx.fxml.FXML
    private TableColumn MeatTypeTc;
    @javafx.fxml.FXML
    private TableView ExpiredExpiryTV;
    @javafx.fxml.FXML
    private TableColumn expirybatchidTc;
    @javafx.fxml.FXML
    private TableColumn ExpiryDateTc;
    @javafx.fxml.FXML
    private ComboBox comboFilter;
    @javafx.fxml.FXML
    private TableColumn QuantityTc;
    @javafx.fxml.FXML
    private TableColumn estatusTc;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void ApplyButtonOnAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void BackButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void RemoveBatchButtonOnAction(ActionEvent actionEvent) {
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
}