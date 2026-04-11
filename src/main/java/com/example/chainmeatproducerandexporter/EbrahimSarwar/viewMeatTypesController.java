package com.example.chainmeatproducerandexporter.EbrahimSarwar;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class viewMeatTypesController



{


    @javafx.fxml.FXML
    private Label quantityOutput;

    @javafx.fxml.FXML
    public void initialize() {

    }

    @javafx.fxml.FXML
    public void selectButton1(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void selectButton3(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void quantityButton6(ActionEvent actionEvent) {
        quantityOutput.setText("50kg");
    }

    @javafx.fxml.FXML
    public void selectButton2(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void quantityButton5(ActionEvent actionEvent) {
        quantityOutput.setText("110kg");
    }

    @javafx.fxml.FXML
    public void selectButton5(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void selectButton4(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void quantityButton2(ActionEvent actionEvent) {
        quantityOutput.setText("200kg");
    }

    @javafx.fxml.FXML
    public void selectButton6(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void quantityButton1(ActionEvent actionEvent) {
        quantityOutput.setText("250kg");
    }

    @javafx.fxml.FXML
    public void quantityButton4(ActionEvent actionEvent) {
        quantityOutput.setText("160kg");
    }

    @javafx.fxml.FXML
    public void quantityButton3(ActionEvent actionEvent) {
        quantityOutput.setText("140kg");
    }

    @javafx.fxml.FXML
    public void BackHomeOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/chainmeatproducerandexporter/supplierHomePage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}