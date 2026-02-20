package com.quantitymeasurement.app;

import java.util.Objects;

public class Length {

    private final double value;
    private final Unit unit;

    public Length(double value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public enum Unit {

        FEET(12),
        INCHES(1);

        private final double conversionFactorToInches;

        Unit(double conversionFactorToInches) {
            this.conversionFactorToInches = conversionFactorToInches;
        }

        public double convertToBase(double value) {
            return value * conversionFactorToInches;
        }
    }

    private double toInches() {
        return unit.convertToBase(value);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Length other = (Length) obj;

        return Double.compare(this.toInches(), other.toInches()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toInches());
    }
}
