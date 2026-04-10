package com.example.chainmeatproducerandexporter.EbrahimSarwar;

import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SelectedMeatTypesController
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void loadMeatTypesOnAction(ActionEvent actionEvent) throws FileNotFoundException {
        File f = new File("requiredMeat.txt");
        Scanner s = new Scanner(f);
        
    }
}