package com.example.chainmeatproducerandexporter.EbrahimSarwar;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class viewMeatTypesController



{


    @javafx.fxml.FXML
    private Label quantityOutput;

    @javafx.fxml.FXML
    public void initialize() {

    }

    @javafx.fxml.FXML
    public void selectButton1(ActionEvent actionEvent) throws IOException {
        //chiken
        File f = new File("requiredMeat.txt");

        FileWriter fw = new FileWriter(f, true);
        fw.write("Chiken \n");
        fw.close();

    }

    @javafx.fxml.FXML
    public void selectButton3(ActionEvent actionEvent) throws IOException {
        //Mutton
        File f = new File("requiredMeat.txt");

        FileWriter fw = new FileWriter(f, true);
        fw.write("Mutton \n");
        fw.close();



    }

    @javafx.fxml.FXML
    public void quantityButton6(ActionEvent actionEvent) {
        quantityOutput.setText("50kg");
    }

    @javafx.fxml.FXML
    public void selectButton2(ActionEvent actionEvent) throws IOException {
        //Beef
        File f = new File("requiredMeat.txt");

        FileWriter fw = new FileWriter(f, true);
        fw.write("Beef \n");
        fw.close();
    }

    @javafx.fxml.FXML
    public void quantityButton5(ActionEvent actionEvent) {
        quantityOutput.setText("110kg");
    }

    @javafx.fxml.FXML
    public void selectButton5(ActionEvent actionEvent) throws IOException {
        //Camel
        File f = new File("requiredMeat.txt");

        FileWriter fw = new FileWriter(f, true);
        fw.write("Camel \n");
        fw.close();
    }

    @javafx.fxml.FXML
    public void selectButton4(ActionEvent actionEvent) throws IOException {
        //Duck
        File f = new File("requiredMeat.txt");

        FileWriter fw = new FileWriter(f, true);
        fw.write("Duck \n");
        fw.close();
    }

    @javafx.fxml.FXML
    public void quantityButton2(ActionEvent actionEvent) {
        quantityOutput.setText("200kg");
    }

    @javafx.fxml.FXML
    public void selectButton6(ActionEvent actionEvent) throws IOException {
        //Buffalo
        File f = new File("requiredMeat.txt");

        FileWriter fw = new FileWriter(f, true);
        fw.write("Buffalo \n");
        fw.close();
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
}