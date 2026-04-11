package com.example.chainmeatproducerandexporter.FarinAhmed;

public class PortHandling {

    private String shipmentId;

    private String portName;

    private String currency;

    private Double handlingCharge;


    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getHandlingCharge() {
        return handlingCharge;
    }

    public PortHandling(String shipmentId, String portName, String currency, Double handlingCharge) {
        this.shipmentId = shipmentId;
        this.portName = portName;
        this.currency = currency;
        this.handlingCharge = handlingCharge;
    }

    public void setHandlingCharge(Double handlingCharge) {
        this.handlingCharge = handlingCharge;



    }
}
