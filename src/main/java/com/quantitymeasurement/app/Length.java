package com.quantitymeasurement.app;

import java.util.Objects;

public class Length {

    private final double value;
    private final Unit unit;

    public Length(double value, Unit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public enum Unit {

        FEET(12),               // 1 ft = 12 inches
        INCHES(1),              // Base unit
        YARDS(36),              // 1 yd = 36 inches
        CENTIMETERS(0.393701);  // 1 cm = 0.393701 inches

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

    @Override
    public String toString() {
        return value + " " + unit;
    }
    
    // Main method for standalone testing
    public static void main(String args[]) {

    	Length length1 = new Length(1.0, Unit.FEET);
    	Length length2 = new Length(12.0, Unit.INCHES);
    	System.out.println("Are Length equal? "+ length1.equals(length2));

    	Length length3 = new Length(1.0, Unit.YARDS);
    	Length length4 = new Length(36.0, Unit.INCHES);
    	System.out.println("Are Length equal? "+ length3.equals(length4));
    	
    	Length length5 = new Length(100.0, Unit.CENTIMETERS);
    	Length length6 = new Length(39.3701, Unit.INCHES);
    	System.out.println("Are Length equal? "+ length5.equals(length6));

    }
}
