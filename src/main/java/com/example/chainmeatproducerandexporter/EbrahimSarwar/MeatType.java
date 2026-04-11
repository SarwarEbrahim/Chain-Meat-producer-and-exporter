package com.example.chainmeatproducerandexporter.EbrahimSarwar;

public class MeatType {
    public String meat;

    public MeatType(String meat) {
        this.meat = meat;
    }

    public String getMeat() {
        return meat;
    }

    public void setMeat(String meat) {
        this.meat = meat;
    }

    @Override
    public String toString() {
        return "MeatType{" +
                "meat='" + meat + '\'' +
                '}';
    }
}
