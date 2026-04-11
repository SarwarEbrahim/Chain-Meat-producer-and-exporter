package com.example.chainmeatproducerandexporter.FarinAhmed;

public class ExprtDocument {


    private String shipmentId;

    private String documentType;

    private Integer numberOfUnits;

    private  Double weight;

    private String paymentStatus;


    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Integer getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(Integer numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public ExprtDocument(String shipmentId, String documentType, Integer numberOfUnits, Double weight, String paymentStatus) {
        this.shipmentId = shipmentId;
        this.documentType = documentType;
        this.numberOfUnits = numberOfUnits;
        this.weight = weight;
        this.paymentStatus = paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;


    }
}
