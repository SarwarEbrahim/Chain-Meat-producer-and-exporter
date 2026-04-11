package com.example.chainmeatproducerandexporter.FarinAhmed;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AssignContainerController
{
    @javafx.fxml.FXML
    private TableView<AssignContainer> containerTableView;
    @javafx.fxml.FXML
    private ComboBox<String> containertypeComboBox;
    @javafx.fxml.FXML
    private TextField shipmentIdTextField;
    @javafx.fxml.FXML
    private TextField capacityusedTextfield;
    AssignContainer assignContainer;

    @javafx.fxml.FXML
    public void initialize() {

    }

    @javafx.fxml.FXML
    public void assignContainerButton(ActionEvent actionEvent) {
    }
}
}