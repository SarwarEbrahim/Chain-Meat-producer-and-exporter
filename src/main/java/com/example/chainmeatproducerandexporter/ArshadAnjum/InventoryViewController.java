package com.example.chainmeatproducerandexporter.ArshadAnjum;

import com.example.chainmeatproducerandexporter.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InventoryViewController
{
    @javafx.fxml.FXML
    private TableColumn MeatTypeTC;
    @javafx.fxml.FXML
    private TextField SearchMeatTypeTF;
    @javafx.fxml.FXML
    private TableColumn ExpiryDateTC;
    @javafx.fxml.FXML
    private TableView InventoryStockTV;
    @javafx.fxml.FXML
    private TableColumn QuantityTC;
    @javafx.fxml.FXML
    private TextField NewQuantityTF;
    @javafx.fxml.FXML
    private TableColumn LocationTC;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void UpdateQuantityButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void invSearchButtonOnAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void BackButtonOnAction(ActionEvent actionEvent) {
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