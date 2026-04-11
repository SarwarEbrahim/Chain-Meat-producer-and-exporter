package com.example.chainmeatproducerandexporter.FarinAhmed;

import java.io.Serializable;

public class Transit implements Serializable {

    private String shipmentId;
    private String route;
    private String mode;
    private String origin;
    private String destination;
    private String status;

    public Transit(String shipmentId, String route, String mode,
                   String origin, String destination, String status) {
        this.shipmentId = shipmentId;
        this.route = route;
        this.mode = mode;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
    }

    public String getShipmentId() { return shipmentId; }
    public String getRoute() { return route; }
    public String getMode() { return mode; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getStatus() { return status; }
}
