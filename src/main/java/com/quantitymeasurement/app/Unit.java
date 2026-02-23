package com.quantitymeasurement.app;


//================= UC8 Standalone Unit ENUM =================

public enum Unit {

    FEET(12),               // 1 ft = 12 inches
    INCHES(1),              // Base unit
    YARDS(36),              // 1 yd = 36 inches
    CENTIMETERS(0.393701);  // 1 cm = 0.393701 inches

    final double conversionFactorToInches;

    Unit(double conversionFactorToInches) {
        this.conversionFactorToInches = conversionFactorToInches;
    }

    public double convertToBase(double value) {
        return value * conversionFactorToInches;
    }
}