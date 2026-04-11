package com.example.chainmeatproducerandexporter.FarinAhmed;

public class ShipmentInsurance {



    private String ShipmentID;
    private String insuranceCompany;
    private double amount;
    private String policyNumber;


    public String getShipmentID() {
        return ShipmentID;
    }

    public void setShipmentID(String shipmentID) {
        ShipmentID = shipmentID;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public ShipmentInsurance(String shipmentID, String insuranceCompany, double amount, String policyNumber) {
        ShipmentID = shipmentID;
        this.insuranceCompany = insuranceCompany;
        this.amount = amount;
        this.policyNumber = policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;


    }
}
