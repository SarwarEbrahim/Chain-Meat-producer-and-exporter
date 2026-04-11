package com.example.chainmeatproducerandexporter.ArshadAnjum;

import com.example.chainmeatproducerandexporter.HelloApplication;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class InspectionReportController
{
    @javafx.fxml.FXML
    private TableView<InspectionReportRow> InspectionReportTV;
    @javafx.fxml.FXML
    private TableColumn<InspectionReportRow, String> BatchIDTc;
    @javafx.fxml.FXML
    private TableColumn<InspectionReportRow, String> StatusTc;
    @javafx.fxml.FXML
    private TableColumn<InspectionReportRow, String> InspectorTc;
    @javafx.fxml.FXML
    private DatePicker ToDateDP;
    @javafx.fxml.FXML
    private ComboBox<String> ComboBatchType;
    @javafx.fxml.FXML
    private DatePicker FromDateDP;
    @javafx.fxml.FXML
    private TableColumn<InspectionReportRow, String> IMeatTypeTc;
    @javafx.fxml.FXML
    private TableColumn<InspectionReportRow, String> DateTc;

    private ObservableList<InspectionReportRow> allReportRows = FXCollections.observableArrayList();
    private ObservableList<InspectionReportRow> filteredReportRows = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        BatchIDTc.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getBatchId()));

        StatusTc.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getStatus()));

        InspectorTc.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getInspectorId()));

        IMeatTypeTc.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getMeatType()));

        DateTc.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getInspectionDate()));

        ComboBatchType.getItems().addAll("All", "Beef", "Chicken", "Mutton");
        ComboBatchType.setValue("All");

        loadInspectionReportData();
        InspectionReportTV.setItems(allReportRows);
    }

    private void loadInspectionReportData() {
        allReportRows.clear();

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

                        // expected format:
                        // recordId,batchId,inspectorId,date,temperature,hygieneScore,odorCondition,remarks,result
                        // OR older custom format:
                        // batchId,meatType,temperature,hygieneScore,odorCondition,remarks,result

                        if (data.length >= 9) {
                            String batchId = data[1].trim();
                            String inspectorId = data[2].trim();
                            String inspectionDate = data[3].trim();
                            String status = data[8].trim();

                            // meat type is not inside this 9-field format
                            // so we fetch it from batch files
                            String meatType = findMeatTypeByBatchId(batchId);

                            InspectionReportRow row = new InspectionReportRow(
                                    batchId,
                                    meatType,
                                    inspectorId,
                                    status,
                                    inspectionDate
                            );

                            allReportRows.add(row);
                        }
                        else if (data.length >= 7) {
                            String batchId = data[0].trim();
                            String meatType = data[1].trim();
                            String inspectorId = "QI01";
                            String inspectionDate = "N/A";
                            String status = data[6].trim();

                            InspectionReportRow row = new InspectionReportRow(
                                    batchId,
                                    meatType,
                                    inspectorId,
                                    status,
                                    inspectionDate
                            );

                            allReportRows.add(row);
                        }
                        else {
                            System.out.println("Skipping bad data line: " + line);
                        }

                    } catch (Exception e) {
                        System.out.println("Skipping bad data line: " + line);
                    }
                }
            }

        } catch (IOException e) {
            showAlert("Error", "Cannot read inspection_records.txt");
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void SaveToFileButtonOnAction(ActionEvent actionEvent) {
        ObservableList<InspectionReportRow> dataToSave = InspectionReportTV.getItems();

        if (dataToSave == null || dataToSave.isEmpty()) {
            showAlert("Error", "No report data to save.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Inspection Report");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        fileChooser.setInitialFileName("inspection_report.txt");

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file == null) {
            return;
        }

        try {
            FileWriter fw = new FileWriter(file);

            fw.write("Batch ID,Meat Type,Inspector ID,Status,Inspection Date\n");

            for (InspectionReportRow row : dataToSave) {
                String line = row.getBatchId() + "," +
                        row.getMeatType() + "," +
                        row.getInspectorId() + "," +
                        row.getStatus() + "," +
                        row.getInspectionDate();

                fw.write(line + "\n");
            }

            fw.close();
            showAlert("Success", "Report saved successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save report.");
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
    public void GenerateReportButtonOnAction(ActionEvent actionEvent) {
        LocalDate fromDate = FromDateDP.getValue();
        LocalDate toDate = ToDateDP.getValue();
        String selectedMeatType = ComboBatchType.getValue();

        filteredReportRows.clear();

        for (InspectionReportRow row : allReportRows) {
            boolean matchesDate = true;
            boolean matchesType = true;

            if (fromDate != null && !row.getInspectionDate().equals("N/A")) {
                try {
                    LocalDate rowDate = LocalDate.parse(row.getInspectionDate());
                    if (rowDate.isBefore(fromDate)) {
                        matchesDate = false;
                    }
                } catch (Exception e) {
                    matchesDate = false;
                }
            }

            if (toDate != null && !row.getInspectionDate().equals("N/A")) {
                try {
                    LocalDate rowDate = LocalDate.parse(row.getInspectionDate());
                    if (rowDate.isAfter(toDate)) {
                        matchesDate = false;
                    }
                } catch (Exception e) {
                    matchesDate = false;
                }
            }

            if (selectedMeatType != null && !selectedMeatType.equals("All")) {
                if (!row.getMeatType().equalsIgnoreCase(selectedMeatType)) {
                    matchesType = false;
                }
            }

            if (matchesDate && matchesType) {
                filteredReportRows.add(row);
            }
        }

        InspectionReportTV.setItems(filteredReportRows);

        if (filteredReportRows.isEmpty()) {
            showAlert("Report", "No records found for selected criteria.");
        }
    }

    @javafx.fxml.FXML
    public void ReturnToDashboardButtonOnAction(ActionEvent actionEvent) {
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

    private String findMeatTypeByBatchId(String batchId) {
        String[] files = {
                "src/files/pending_batches.txt",
                "src/files/approved_batches.txt",
                "src/files/rejected_batches.txt",
                "src/files/reinspection_batches.txt"
        };

        for (String filePath : files) {
            try {
                FileReader fr = new FileReader(filePath);

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
                            if (data.length >= 2) {
                                String currentBatchId = data[0].trim();
                                String meatType = data[1].trim();

                                if (currentBatchId.equalsIgnoreCase(batchId)) {
                                    return meatType;
                                }
                            }
                        } catch (Exception e) {
                            // skip broken line
                        }
                    }
                }

            } catch (IOException e) {
                // file may be empty or missing, continue
            }
        }

        return "Unknown";
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static class InspectionReportRow {
        private String batchId;
        private String meatType;
        private String inspectorId;
        private String status;
        private String inspectionDate;

        public InspectionReportRow(String batchId, String meatType, String inspectorId, String status, String inspectionDate) {
            this.batchId = batchId;
            this.meatType = meatType;
            this.inspectorId = inspectorId;
            this.status = status;
            this.inspectionDate = inspectionDate;
        }

        public String getBatchId() {
            return batchId;
        }

        public String getMeatType() {
            return meatType;
        }

        public String getInspectorId() {
            return inspectorId;
        }

        public String getStatus() {
            return status;
        }

        public String getInspectionDate() {
            return inspectionDate;
        }
    }
}