package com.example.chainmeatproducerandexporter.ArshadAnjumModel;

import java.time.LocalDate;

public class InspectionRecord {
    private String recordId;
    private String batchId;
    private String inspectorId;
    private LocalDate inspectionDate;
    private double temperature;
    private int hygieneScore;
    private String odorCondition;
    private String remarks;
    private String inspectionResult;

    public InspectionRecord() {
    }

    public InspectionRecord(String recordId, String batchId, String inspectorId, LocalDate inspectionDate,
                            double temperature, int hygieneScore, String odorCondition,
                            String remarks, String inspectionResult) {
        this.recordId = recordId;
        this.batchId = batchId;
        this.inspectorId = inspectorId;
        this.inspectionDate = inspectionDate;
        this.temperature = temperature;
        this.hygieneScore = hygieneScore;
        this.odorCondition = odorCondition;
        this.remarks = remarks;
        this.inspectionResult = inspectionResult;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(String inspectorId) {
        this.inspectorId = inspectorId;
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHygieneScore() {
        return hygieneScore;
    }

    public void setHygieneScore(int hygieneScore) {
        this.hygieneScore = hygieneScore;
    }

    public String getOdorCondition() {
        return odorCondition;
    }

    public void setOdorCondition(String odorCondition) {
        this.odorCondition = odorCondition;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getInspectionResult() {
        return inspectionResult;
    }

    public void setInspectionResult(String inspectionResult) {
        this.inspectionResult = inspectionResult;
    }

    @Override
    public String toString() {
        return recordId + "," + batchId + "," + inspectorId + "," + inspectionDate + "," +
                temperature + "," + hygieneScore + "," + odorCondition + "," + remarks + "," + inspectionResult;
    }
}