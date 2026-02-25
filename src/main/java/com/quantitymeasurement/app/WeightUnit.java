package com.quantitymeasurement.app;


//================= UC9 WeightUnit ENUM =================

public enum WeightUnit {

    MILLIGRAM(0.001),
    GRAM(1.0),              // Base unit
    KILOGRAM(1000.0),
    POUND(453.592),
    TONNE(1000000);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double convertToBase(double value) {
        return value * conversionFactor;
    }
    
    public double getConversionFactor() {
    	return conversionFactor;
    }
}