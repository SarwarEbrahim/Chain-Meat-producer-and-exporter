package com.example.chainmeatproducerandexporter.EbrahimSarwar;

public class MeatTypes {
    private String meatType;

    public MeatTypes(String meatType) {
        this.meatType = "Chiken";
    }

    public String getMeatType() {
        return meatType;
    }

    public void setMeatType(String meatType) {
        this.meatType = meatType;
    }

    @Override
    public String toString() {
        return "meatTypes{" +
                "meatType='" + meatType + '\'' +
                '}';
    }
}
