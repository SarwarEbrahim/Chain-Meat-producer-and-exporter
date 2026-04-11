package com.example.chainmeatproducerandexporter.FarinAhmed;

public class GenerateBatchSummary {



    private String batchId;
    private String productionDate;
    private String productName;
    private String productType;
    private String processingStage;
    private double packagingTotalCost;
    private String packagingType;
    private String status;


    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProcessingStage() {
        return processingStage;
    }

    public void setProcessingStage(String processingStage) {
        this.processingStage = processingStage;
    }

    public double getPackagingTotalCost() {
        return packagingTotalCost;
    }

    public void setPackagingTotalCost(double packagingTotalCost) {
        this.packagingTotalCost = packagingTotalCost;
    }

    public String getPackagingType() {
        return packagingType;
    }

    public void setPackagingType(String packagingType) {
        this.packagingType = packagingType;
    }

    public String getStatus() {
        return status;
    }

    public GenerateBatchSummary(String batchId, String productionDate, String productName, String productType, String processingStage, double packagingTotalCost, String packagingType, String status) {
        this.batchId = batchId;
        this.productionDate = productionDate;
        this.productName = productName;
        this.productType = productType;
        this.processingStage = processingStage;
        this.packagingTotalCost = packagingTotalCost;
        this.packagingType = packagingType;
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;


    }
}
