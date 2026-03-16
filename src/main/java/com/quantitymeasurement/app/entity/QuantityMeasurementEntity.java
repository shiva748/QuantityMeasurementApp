package com.quantitymeasurement.app.entity;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double value;
    private String unit;

    public QuantityMeasurementEntity(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}