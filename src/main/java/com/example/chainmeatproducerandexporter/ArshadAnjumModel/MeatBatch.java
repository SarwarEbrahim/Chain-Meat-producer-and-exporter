package com.example.chainmeatproducerandexporter.ArshadAnjumModel;

import java.time.LocalDate;

public class MeatBatch {
    private String batchId;
    private String meatType;
    private int quantity;
    private LocalDate productionDate;
    private LocalDate expiryDate;
    private String qualityStatus;
    private String approvalDate;
    private String inspectorId;
    private String rejectionReason;
    private String reinspectionReason;

    public MeatBatch() {
    }

    public MeatBatch(String batchId, String meatType, int quantity, LocalDate productionDate,
                     LocalDate expiryDate, String qualityStatus) {
        this.batchId = batchId;
        this.meatType = meatType;
        this.quantity = quantity;
        this.productionDate = productionDate;
        this.expiryDate = expiryDate;
        this.qualityStatus = qualityStatus;
    }

    public MeatBatch(String batchId, String meatType, int quantity, String productionDate, String expiryDate) {
        this.batchId = batchId;
        this.meatType = meatType;
        this.quantity = quantity;
        this.productionDate = LocalDate.parse(productionDate);
        this.expiryDate = LocalDate.parse(expiryDate);

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getQualityStatus() {
        return qualityStatus;
    }

    public void setQualityStatus(String qualityStatus) {
        this.qualityStatus = qualityStatus;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(String inspectorId) {
        this.inspectorId = inspectorId;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getReinspectionReason() {
        return reinspectionReason;
    }

    public void setReinspectionReason(String reinspectionReason) {
        this.reinspectionReason = reinspectionReason;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public boolean isNearExpiry() {
        return !isExpired() && LocalDate.now().plusDays(3).isAfter(expiryDate);
    }

    @Override
    public String toString() {
        return batchId + "," + meatType + "," + quantity + "," + productionDate + "," + expiryDate + "," + qualityStatus;
    }
}