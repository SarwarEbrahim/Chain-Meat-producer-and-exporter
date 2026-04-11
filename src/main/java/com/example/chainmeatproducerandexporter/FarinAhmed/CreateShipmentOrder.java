package com.example.chainmeatproducerandexporter.FarinAhmed;

public class CreateShipmentOrder {


    private String shipmentId;

    private String destinationCountry;

    private String exportType;

    private Integer totalQuantity;

    private String date;

    private String status;


    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getExportType() {
        return exportType;
    }

    public void setExportType(String exportType) {
        this.exportType = exportType;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public CreateShipmentOrder(String shipmentId, String destinationCountry, String exportType, Integer totalQuantity, String date, String status) {
        this.shipmentId = shipmentId;
        this.destinationCountry = destinationCountry;
        this.exportType = exportType;
        this.totalQuantity = totalQuantity;
        this.date = date;
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;


    }
}
