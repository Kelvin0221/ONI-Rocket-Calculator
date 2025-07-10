package com.rocktcalculator.onirocketcalculator;

public class CalculatorIteration {
    private final int rocketWeight;
    private final int destination;
    private final int loadWeight;
    private final double thrust;

    CalculatorIteration(int rocketWeight, int destination, int loadWeight, double thrust){
        this.rocketWeight = rocketWeight;
        this.destination = destination;
        this.loadWeight = loadWeight;
        this.thrust = thrust;
    }

    public int getRocketWeight() {
        return rocketWeight;
    }

    public FuelIndicator BeginIteration(){
        int ite = 0;
        boolean continueIteration = true;
        FuelIndicator prevIndicator = new FuelIndicator(0, rocketWeight, 0, true);

        do{
            ite+=1;
            int fuelWeight = (int) Math.ceil((destination + prevIndicator.getLargerWeight()) / thrust);
            int newTotalWeight = rocketWeight+(2* fuelWeight);

            if(prevIndicator.isInLoad() && (prevIndicator.getTotalWeight() != newTotalWeight)){
                prevIndicator.setFuelWeight(fuelWeight);
                prevIndicator.setIteration(prevIndicator.getIteration()+1);
                prevIndicator.setTotalWeight(newTotalWeight);
                prevIndicator.setPenaltyWeight(newTotalWeight);
                prevIndicator.setInLoad((2* fuelWeight) < loadWeight);
            }else{
                continueIteration = false;
            }
        }while(continueIteration);

        return prevIndicator;
    }

}
