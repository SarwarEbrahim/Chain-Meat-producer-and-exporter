package com.example.chainmeatproducerandexporter.FarinAhmed;

public class BatchtoStorage {
    private String batchID;
    private String productName;
    private String productType;
    private int batchQuantity;
    private String ProductionDate;

    public BatchtoStorage(String batchID, String productName, String productType, int batchQuantity, String productionDate) {
        this.batchID = batchID;
        this.productName = productName;
        this.productType = productType;
        this.batchQuantity = batchQuantity;
        ProductionDate = productionDate;
    }

    public String getBatchID() {
        return batchID;
    }

    public void setBatchID(String batchID) {
        this.batchID = batchID;
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

    public int getBatchQuantity() {
        return batchQuantity;
    }

    public void setBatchQuantity(int batchQuantity) {
        this.batchQuantity = batchQuantity;
    }

    public String getProductionDate() {
        return ProductionDate;
    }

    public void setProductionDate(String productionDate) {
        ProductionDate = productionDate;
    }
}
