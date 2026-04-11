package com.example.chainmeatproducerandexporter.FarinAhmed;

public class AssignContainer {


    private String shipmentId;

    private   String containerType;

    public AssignContainer(String shipmentId, String containerType, Double capacityUsed, String status) {
        this.shipmentId = shipmentId;
        this.containerType = containerType;
        this.capacityUsed = capacityUsed;
        this.status = status;
    }

    private Double capacityUsed;

    private String status;

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public Double getCapacityUsed() {
        return capacityUsed;
    }

    public void setCapacityUsed(Double capacityUsed) {
        this.capacityUsed = capacityUsed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;






    }
}
