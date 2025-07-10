package com.rocktcalculator.onirocketcalculator;

public class FuelIndicator {
    private int iteration;
    private int totalWeight;
    private int penaltyWeight;
    private int fuelWeight;
    private boolean inLoad;

    FuelIndicator(int iteration, int totalWeight, int fuelWeight, boolean inLoad){
        this.iteration = iteration;
        this.totalWeight = totalWeight;
        setPenaltyWeight(totalWeight);
        this.fuelWeight = fuelWeight;
        this.inLoad = inLoad;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public int getFuelWeight() {
        return fuelWeight;
    }

    public void setFuelWeight(int fuelWeight) {
        this.fuelWeight = fuelWeight;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public void setPenaltyWeight(int totalWeight) {
        this.penaltyWeight = (int) Math.ceil(Math.pow(((double) totalWeight /300), 3.2));
    }

    public boolean isInLoad() {
        return inLoad;
    }

    public void setInLoad(boolean inLoad) {
        this.inLoad = inLoad;
    }

    public int getLargerWeight(){
        return Math.max(totalWeight, penaltyWeight);
    }

    public int getSumFuelWeight(){
        return fuelWeight*2;
    }

    @Override
    public String toString() {
        return "I: " + iteration + " TotalW: " + totalWeight + "FuelW: " + fuelWeight + " Penalty: " + penaltyWeight + " Flyable: " + inLoad;
    }
}
