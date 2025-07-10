package com.rocktcalculator.onirocketcalculator;

public class FuelType {
    private String fuelName;
    private int fuelEfficiency;

    FuelType(String fuelName, int fuelEfficiency){
        this.fuelName = fuelName;
        this.fuelEfficiency = fuelEfficiency;
    }

    public String getFuelName() {
        return fuelName;
    }

    public int getFuelEfficiency() {
        return fuelEfficiency;
    }

    @Override
    public String toString() {
        return fuelName;
    }
}
