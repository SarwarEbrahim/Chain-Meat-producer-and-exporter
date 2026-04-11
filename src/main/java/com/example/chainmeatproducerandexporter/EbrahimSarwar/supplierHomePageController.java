package com.example.chainmeatproducerandexporter.EbrahimSarwar;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class supplierHomePageController
{
    @javafx.fxml.FXML
    private VBox vBox;
    @javafx.fxml.FXML
    private Label personalInfoOutput;

    @javafx.fxml.FXML
    public void initialize() {
        vBox.setVisible(false);

    }


    @javafx.fxml.FXML
    public void viewPaymentStatusOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewApprovalStatusOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void ViewMeatTypesOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/chainmeatproducerandexporter/viewMeatTypes.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void menuOnAction(ActionEvent actionEvent) {
        vBox.setVisible(!vBox.isVisible());


    }


    @javafx.fxml.FXML
    public void viewUnitPriceOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void choosePaymentMethodOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void scheduledDeliveryOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void SelectedMeatTypesOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/chainmeatproducerandexporter/selectedMeatTypes.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
}
//selectedMeatTypes.fxml