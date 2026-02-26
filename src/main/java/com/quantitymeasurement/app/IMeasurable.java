package com.quantitymeasurement.app;

public interface IMeasurable {
	
	double getConversionFactor();

	default double convertToBaseUnit(double value) {
		return getConversionFactor()* value;
	};
	
	default double convertFromBaseUnit(double value) {
		return value / getConversionFactor();
	}
	
	String getUnitName();
}
