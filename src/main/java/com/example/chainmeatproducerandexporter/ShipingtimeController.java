package com.example.chainmeatproducerandexporter;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ShipingtimeController
{
    @javafx.fxml.FXML
    private TableView<ShippingSchedule> schuduleTableView;
    @javafx.fxml.FXML
    private TableColumn<ShippingSchedule, String> shipmenttotaltimeColumn;
    @javafx.fxml.FXML
    private TableColumn<ShippingSchedule, String> status2Column;
    @javafx.fxml.FXML
    private AnchorPane statusColumn;
    @javafx.fxml.FXML
    private ComboBox<String> timeComboBox;
    @javafx.fxml.FXML
    private DatePicker departureDatePicker;
    @javafx.fxml.FXML
    private TableColumn<ShippingSchedule, String> IdColumn;
    @javafx.fxml.FXML
    private TableColumn<ShippingSchedule, String> typeColumn;

    private ArrayList<ShippingSchedule> scheduleList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {

        timeComboBox.getItems().addAll("24h", "48h", "72h", "1 Week");

        loadFromFile();
    }

    @javafx.fxml.FXML
    public void scheduleButton(ActionEvent actionEvent) {

        // 1. Empty validation
        if (timeComboBox.getValue() == null ||
                departureDatePicker.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Please select time and departure date");
            alert.showAndWait();
            return;
        }

        // 2. Date validation
        LocalDate date = departureDatePicker.getValue();
        if (date.isBefore(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid Date");
            alert.setContentText("Departure date cannot be in the past");
            alert.showAndWait();
            return;
        }

        // 3. Create object
        ShippingSchedule schedule = new ShippingSchedule(
                "SH" + (scheduleList.size() + 1),
                timeComboBox.getValue(),
                date.toString(),
                "Scheduled"
        );

        scheduleList.add(schedule);

        // 4. Show in TableView
        schuduleTableView.setItems(FXCollections.observableArrayList(scheduleList));

        // 5. Save file
        saveToFile();

        // 6. Success alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Done");
        alert.setContentText("Shipment Scheduled Successfully!");
        alert.showAndWait();

        // 7. Clear fields
        timeComboBox.setValue(null);
        departureDatePicker.setValue(null);
    }

    // Save file
    private void saveToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("schedule.bin"))) {
            output.writeInt(scheduleList.size());
            for (ShippingSchedule s : scheduleList) {
                output.writeObject(s);
            }
        } catch (IOException e) {
            System.out.println("File write error");
        }
    }

    // Load file
    private void loadFromFile() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("schedule.bin"))) {
            int size = input.readInt();
            for (int i = 0; i < size; i++) {
                ShippingSchedule s = (ShippingSchedule) input.readObject();
                scheduleList.add(s);
            }
        } catch (IOException | ClassNotFoundException e) {
            // first run ignore
        }
    }
}