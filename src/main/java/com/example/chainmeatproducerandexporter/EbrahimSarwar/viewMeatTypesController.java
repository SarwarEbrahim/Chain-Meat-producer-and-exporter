package com.example.chainmeatproducerandexporter.EbrahimSarwar;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class viewMeatTypesController
{
    @javafx.fxml.FXML
    private TableColumn<MeatTypes, String> meatTypeColumn;
    @javafx.fxml.FXML
    private TableView<MeatTypes> meatTypesTable;
    @javafx.fxml.FXML
    private Label output;

    @javafx.fxml.FXML
    public void initialize() {
        meatTypeColumn.setCellValueFactory(new PropertyValueFactory<MeatTypes, String>("meatType"));

    }

    @javafx.fxml.FXML
    public void homePageOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/chainmeatproducerandexporter/supplierHomePage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void buffaloOnClick(ActionEvent actionEvent) {
        output.setText("67kg");

    }

    @javafx.fxml.FXML
    public void camelOnClick(ActionEvent actionEvent) {
        output.setText("50kg");
    }

    @javafx.fxml.FXML
    public void chikenOnClick(ActionEvent actionEvent) {
        output.setText("100kg");
    }

    @javafx.fxml.FXML
    public void beefOnClick(ActionEvent actionEvent) {
        output.setText("95kg");
    }

    @javafx.fxml.FXML
    public void lambOnClick(ActionEvent actionEvent) {
        output.setText("30kg");
    }

    @javafx.fxml.FXML
    public void muttonOnClick(ActionEvent actionEvent) {
        output.setText("150kg");
    }

    @javafx.fxml.FXML
    public void duckOnClick(ActionEvent actionEvent) {
        output.setText("200kg");
    }
}