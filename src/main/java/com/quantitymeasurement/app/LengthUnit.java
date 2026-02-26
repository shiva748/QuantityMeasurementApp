package com.quantitymeasurement.app;


//================= UC10 Converted length unit to use IMeasurable interface =================

public enum LengthUnit implements IMeasurable {

    FEET(12),               // 1 ft = 12 inches
    INCHES(1),              // Base unit
    YARDS(36),              // 1 yd = 36 inches
    CENTIMETERS(0.393701);  // 1 cm = 0.393701 inches

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }
    
    @Override
    public double getConversionFactor() {
    	return conversionFactor;
    }

	@Override
	public String getUnitName() {
		return name();
	}
}