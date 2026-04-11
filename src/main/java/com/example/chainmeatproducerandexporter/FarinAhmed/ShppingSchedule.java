package com.example.chainmeatproducerandexporter.FarinAhmed;

public class ShppingSchedule {

    private String ShipmentID;
    private String destinationCountry;
    private String exportType;
    private int totalQuantity;
    private String Status;


    public String getShipmentID() {
        return ShipmentID;
    }

    public void setShipmentID(String shipmentID) {
        ShipmentID = shipmentID;
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

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getStatus() {
        return Status;
    }

    public ShppingSchedule(String shipmentID, String destinationCountry, String exportType, int totalQuantity, String status) {
        ShipmentID = shipmentID;
        this.destinationCountry = destinationCountry;
        this.exportType = exportType;
        this.totalQuantity = totalQuantity;
        Status = status;
    }

    public void setStatus(String status) {
        Status = status;


    }
}
