package com.example.chainmeatproducerandexporter.EbrahimSarwar;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SelectedMeatTypesController
{
    @javafx.fxml.FXML
    private TableView<MeatType> selectedMeatTable;
    @javafx.fxml.FXML
    private Label kgLabel;
    @javafx.fxml.FXML
    private Button confirmOnAction;
    @javafx.fxml.FXML
    private Button increaseQuantity;
    @javafx.fxml.FXML
    private TableColumn<MeatType, String> selectedMeatColumn;
    @javafx.fxml.FXML
    private Button decreaseQuantity;

    @javafx.fxml.FXML
    public void initialize() {
        selectedMeatColumn.setCellValueFactory(new PropertyValueFactory<MeatType, String>("meat"));
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

    @javafx.fxml.FXML
    public void loadMeatListOnAction(ActionEvent actionEvent) throws FileNotFoundException {
        ArrayList<MeatType> meatTypeList = new ArrayList<MeatType>();

        File f = new File("meatTypes.txt");
        Scanner s = new Scanner(f);
        String str;
        while (s.hasNextLine()) {
            str = s.nextLine();
            meatTypeList.add(new MeatType(str));
        }
        selectedMeatTable.getItems().clear();
        selectedMeatTable.getItems().addAll(meatTypeList);


    }


}