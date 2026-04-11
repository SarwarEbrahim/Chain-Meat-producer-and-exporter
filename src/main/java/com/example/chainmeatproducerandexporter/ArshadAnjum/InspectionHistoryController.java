package com.example.chainmeatproducerandexporter.ArshadAnjum;

import com.example.chainmeatproducerandexporter.ArshadAnjumModel.InspectionRecord;
import com.example.chainmeatproducerandexporter.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class InspectionHistoryController {

    @javafx.fxml.FXML
    private TableColumn<InspectionRecord, String> BatchIDTc;
    @javafx.fxml.FXML
    private TextField BatchIDTF;
    @javafx.fxml.FXML
    private TableColumn<InspectionRecord, String> StatusTc;
    @javafx.fxml.FXML
    private TableColumn<InspectionRecord, String> HygieneTc;
    @javafx.fxml.FXML
    private TableColumn<InspectionRecord, String> odorTc;
    @javafx.fxml.FXML
    private ComboBox<String> Combostatus;
    @javafx.fxml.FXML
    private TableColumn<InspectionRecord, String> TempTc;
    @javafx.fxml.FXML
    private TableView<InspectionRecord> InspectionHistoryTV;
    @javafx.fxml.FXML
    private TableColumn<InspectionRecord, LocalDate> DateTc;
    @javafx.fxml.FXML
    private DatePicker DateDP;

    private ObservableList<InspectionRecord> inspectionList = FXCollections.observableArrayList();
    private ObservableList<InspectionRecord> filteredList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        BatchIDTc.setCellValueFactory(new PropertyValueFactory<>("batchId"));
        TempTc.setCellValueFactory(new PropertyValueFactory<>("temperature"));
        HygieneTc.setCellValueFactory(new PropertyValueFactory<>("hygieneScore"));
        odorTc.setCellValueFactory(new PropertyValueFactory<>("odorCondition"));
        StatusTc.setCellValueFactory(new PropertyValueFactory<>("inspectionResult"));
        DateTc.setCellValueFactory(new PropertyValueFactory<>("inspectionDate"));

        Combostatus.getItems().addAll("All", "APPROVED", "REJECTED", "REINSPECTION");
        Combostatus.setValue("All");

        loadInspectionHistory();
    }

    private void loadInspectionHistory() {
        inspectionList.clear();

        try {
            FileReader fr = new FileReader("src/files/inspection_records.txt");

            StringBuilder content = new StringBuilder();
            int ch;

            while ((ch = fr.read()) != -1) {
                content.append((char) ch);
            }

            fr.close();

            String[] lines = content.toString().split("\n");

            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    try {
                        String[] data = line.split(",");

                        if (data.length < 9) {
                            System.out.println("Skipping bad data line: " + line);
                            continue;
                        }

                        String batchId = data[1].trim();
                        String temperature = data[4].trim();
                        String hygieneScore = data[5].trim();
                        String odorCondition = data[6].trim();
                        String inspectionResult = data[8].trim();

                        LocalDate inspectionDate = LocalDate.parse(data[3].trim());

                        InspectionRecord record = new InspectionRecord(
                                batchId,
                                temperature,
                                hygieneScore,
                                odorCondition,
                                inspectionResult,
                                inspectionDate
                        );

                        inspectionList.add(record);

                    } catch (Exception e) {
                        System.out.println("Skipping bad data line: " + line);
                    }
                }
            }

            InspectionHistoryTV.setItems(inspectionList);

        } catch (IOException e) {
            showAlert("Error", "Cannot read inspection_records.txt");
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void ApplyFilterButtonOnAction(ActionEvent actionEvent) {

        String batchIdSearch = BatchIDTF.getText().trim();
        String selectedStatus = Combostatus.getValue();
        LocalDate selectedDate = DateDP.getValue();

        filteredList.clear();

        for (InspectionRecord record : inspectionList) {

            boolean matchesBatch = true;
            boolean matchesStatus = true;
            boolean matchesDate = true;

            if (!batchIdSearch.isEmpty()) {
                matchesBatch = record.getBatchId().equalsIgnoreCase(batchIdSearch);
            }

            if (selectedStatus != null && !selectedStatus.equals("All")) {
                matchesStatus = record.getInspectionResult().equalsIgnoreCase(selectedStatus);
            }

            if (selectedDate != null) {
                matchesDate = selectedDate.equals(record.getInspectionDate());
            }

            if (matchesBatch && matchesStatus && matchesDate) {
                filteredList.add(record);
            }
        }

        InspectionHistoryTV.setItems(filteredList);

        if (filteredList.isEmpty()) {
            showAlert("Filter Result", "No inspection records found.");
        }
    }

    @javafx.fxml.FXML
    public void BackButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QualityInspectorDashboard.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());
            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setTitle("Quality Inspector Dashboard");
            nextStage.setScene(nextScene);
            nextStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void ReturnToDashboardButtonOnAction(ActionEvent actionEvent) {
        BackButtonOnAction(actionEvent);
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}