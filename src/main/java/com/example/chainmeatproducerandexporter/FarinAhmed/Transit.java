package com.example.chainmeatproducerandexporter.FarinAhmed;

public class Transit {



    private String ShipmentID;
    private String origin;
    private String destination;
    private String Mode;
    private String Status;


    public String getShipmentID() {
        return ShipmentID;
    }

    public void setShipmentID(String shipmentID) {
        ShipmentID = shipmentID;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }

    public String getStatus() {
        return Status;
    }

    public Transit(String shipmentID, String origin, String destination, String mode, String status) {
        ShipmentID = shipmentID;
        this.origin = origin;
        this.destination = destination;
        Mode = mode;
        Status = status;
    }

    public void setStatus(String status) {
        Status = status;



    }
}
