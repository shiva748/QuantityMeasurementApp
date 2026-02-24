package com.quantitymeasurement.app;


//================= UC8 Standalone Unit ENUM =================

public enum LengthUnit {

    FEET(12),               // 1 ft = 12 inches
    INCHES(1),              // Base unit
    YARDS(36),              // 1 yd = 36 inches
    CENTIMETERS(0.393701);  // 1 cm = 0.393701 inches

    private final double conversionFactorToInches;

    LengthUnit(double conversionFactorToInches) {
        this.conversionFactorToInches = conversionFactorToInches;
    }

    public double convertToBase(double value) {
        return value * conversionFactorToInches;
    }
    
    public double getConversionFactor() {
    	return conversionFactorToInches;
    }
}