package com.quantitymeasurement.app;

@FunctionalInterface
interface SupportsArithmetic{
	
	boolean isSupported();
	
}

public interface IMeasurable {
	
	SupportsArithmetic supportsArithmetic = () -> true;
	
	double getConversionFactor();

	default double convertToBaseUnit(double value) {
		return getConversionFactor()* value;
	};
	
	default double convertFromBaseUnit(double value) {
		return value / getConversionFactor();
	}
	
	String getUnitName();
	
	default boolean SupportsArithmetic() {
		return supportsArithmetic.isSupported();
	}
	
	default void validateOperationSupport(String operation) {
		
	}
}
