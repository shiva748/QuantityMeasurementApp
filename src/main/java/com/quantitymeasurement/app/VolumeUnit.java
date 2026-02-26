package com.quantitymeasurement.app;

public enum VolumeUnit implements IMeasurable {
	LITRE(1.0),			// base unit
	MILLILITRE(0.001),
	GALLON(3.78541);
	
	private final double conversionFactor;
	
	private VolumeUnit(double conversionFactor) {
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
