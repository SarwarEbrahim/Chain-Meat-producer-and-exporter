package com.example.chainmeatproducerandexporter.FarinAhmed;

public class Cost {

    private String ShipmentID;
    private double transportCost;
    private double handleCost;
    private double totalCost;

    public String getShipmentID() {
        return ShipmentID;
    }

    public void setShipmentID(String shipmentID) {
        ShipmentID = shipmentID;
    }

    public double getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(double transportCost) {
        this.transportCost = transportCost;
    }

    public double getHandleCost() {
        return handleCost;
    }

    public void setHandleCost(double handleCost) {
        this.handleCost = handleCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public Cost(String shipmentID, double transportCost, double handleCost, double totalCost) {
        ShipmentID = shipmentID;
        this.transportCost = transportCost;
        this.handleCost = handleCost;
        this.totalCost = totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;




    }
}
