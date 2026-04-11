package com.example.chainmeatproducerandexporter.FarinAhmed;

public class Assignprocessingstage {



    private String batchID;
    private String processingStages;
    private double laborCost;
    private double materialCost;
    private double totalCost;
    private String date;

    public String getBatchID() {
        return batchID;
    }

    public void setBatchID(String batchID) {
        this.batchID = batchID;
    }

    public String getProcessingStages() {
        return processingStages;
    }

    public void setProcessingStages(String processingStages) {
        this.processingStages = processingStages;
    }

    public double getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(double laborCost) {
        this.laborCost = laborCost;
    }

    public double getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(double materialCost) {
        this.materialCost = materialCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getDate() {
        return date;
    }

    public Assignprocessingstage(String batchID, String processingStages, double laborCost, double materialCost, double totalCost, String date) {
        this.batchID = batchID;
        this.processingStages = processingStages;
        this.laborCost = laborCost;
        this.materialCost = materialCost;
        this.totalCost = totalCost;
        this.date = date;
    }

    public void setDate(String date) {
        this.date = date;


    }
}
