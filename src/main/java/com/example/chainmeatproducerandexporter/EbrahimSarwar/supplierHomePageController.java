package com.example.chainmeatproducerandexporter.EbrahimSarwar;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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
    public void ViewMeatTypesOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void menuOnAction(ActionEvent actionEvent) {
        vBox.setVisible(true);
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
    public void SelectedMeatTypesOnAction(ActionEvent actionEvent) {
    }
}
