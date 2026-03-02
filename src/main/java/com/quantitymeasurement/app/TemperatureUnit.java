package com.quantitymeasurement.app;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS(false),
    FAHRENHEIT(true);

    // ❌ Temperature does NOT support arithmetic
    private final SupportsArithmetic supportsArithmetic = () -> false;

    private final boolean isFahrenheit;

    TemperatureUnit(boolean isFahrenheit) {
        this.isFahrenheit = isFahrenheit;
    }

    /* =========================================================
       FUNCTIONAL INTERFACES (AS PER DESIGN)
       ========================================================= */

    private static final Function<Double, Double> FAHRENHEIT_TO_CELSIUS =
            (f) -> (f - 32) * 5 / 9;

    private static final Function<Double, Double> CELSIUS_TO_CELSIUS =
            (c) -> c;

    /* =========================================================
       BASIC METHODS
       ========================================================= */

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public double getConversionFactor() {
        return 1.0; // Not used for temperature
    }

    /* =========================================================
       CONVERT TO BASE (CELSIUS)
       ========================================================= */

    @Override
    public double convertToBaseUnit(double value) {
        return isFahrenheit
                ? FAHRENHEIT_TO_CELSIUS.apply(value)
                : CELSIUS_TO_CELSIUS.apply(value);
    }

    /* =========================================================
       CONVERT FROM BASE (CELSIUS)
       ========================================================= */

    @Override
    public double convertFromBaseUnit(double baseValue) {
        if (isFahrenheit) {
            return (baseValue * 9 / 5) + 32;
        }
        return baseValue;
    }

    /* =========================================================
       DIRECT CONVERSION METHOD
       ========================================================= */

    public double convertTo(double value, TemperatureUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = this.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

    /* =========================================================
       ARITHMETIC SUPPORT (UC14 CORE)
       ========================================================= */

    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException(
                "Temperature does not support " + operation + " operation."
        );
    }

    /* =========================================================
       TO STRING
       ========================================================= */

    @Override
    public String toString() {
        return name();
    }
}