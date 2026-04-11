package com.example.chainmeatproducerandexporter.ArshadAnjum;

import com.example.chainmeatproducerandexporter.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class InventoryManagerDashboardController
{

    @javafx.fxml.FXML
    private BorderPane DashboardBorderpane;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void ViewInventoryButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("InventoryView.fxml"));
            DashboardBorderpane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @javafx.fxml.FXML
    public void ApprovedBatchesButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ApprovedBatches.fxml"));
            DashboardBorderpane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @javafx.fxml.FXML
    public void LogoutButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    HelloApplication.class.getResource("loginPage.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());

            Stage nextStage = (Stage) ((Node) actionEvent.getSource())
                    .getScene().getWindow();

            nextStage.setTitle("Login Page");
            nextStage.setScene(nextScene);
            nextStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void GenerateReportButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("InventoryReport.fxml"));
            DashboardBorderpane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @javafx.fxml.FXML
    public void ExpiredNearExpiryButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ExpiredStock.fxml"));
            DashboardBorderpane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}