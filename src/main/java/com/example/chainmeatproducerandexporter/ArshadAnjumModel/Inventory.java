package com.example.chainmeatproducerandexporter.ArshadAnjumModel;

import java.time.LocalDate;

public class Inventory {
    private String inventoryId;
    private String batchId;
    private String meatType;
    private int totalQuantity;
    private String storageLocation;
    private LocalDate expiryDate;
    private String expiryStatus;

    public Inventory() {
    }

    public Inventory(String inventoryId, String batchId, String meatType, int totalQuantity,
                     String storageLocation, LocalDate expiryDate) {
        this.inventoryId = inventoryId;
        this.batchId = batchId;
        this.meatType = meatType;
        this.totalQuantity = totalQuantity;
        this.storageLocation = storageLocation;
        this.expiryDate = expiryDate;
    }

    public String getExpiryStatus() {
        return expiryStatus;
    }

    public void setExpiryStatus(String expiryStatus) {
        this.expiryStatus = expiryStatus;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getMeatType() {
        return meatType;
    }

    public void setMeatType(String meatType) {
        this.meatType = meatType;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public boolean isNearExpiry() {
        return !isExpired() && LocalDate.now().plusDays(3).isAfter(expiryDate);
    }

    @Override
    public String toString() {
        return inventoryId + "," + batchId + "," + meatType + "," + totalQuantity + "," +
                storageLocation + "," + expiryDate;
    }
}