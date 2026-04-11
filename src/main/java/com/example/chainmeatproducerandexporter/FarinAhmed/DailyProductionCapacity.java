package com.example.chainmeatproducerandexporter.FarinAhmed;

public class DailyProductionCapacity {
    private String date;
    private String productName;
    private double availableCapacity;
    private double percentage;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(double availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public double getPercentage() {
        return percentage;
    }

    public DailyProductionCapacity(String date, String productName, double availableCapacity, double percentage) {
        this.date = date;
        this.productName = productName;
        this.availableCapacity = availableCapacity;
        this.percentage = percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;


    }
}
