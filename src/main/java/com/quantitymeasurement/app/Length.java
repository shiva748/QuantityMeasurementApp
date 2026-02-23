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

    private double toInches() {
        return unit.convertToBase(value);
    }

    public Length convertTo(Unit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        if (!Double.isFinite(this.value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        // Convert to base (inches)
        double baseValue = this.toInches();

        // Convert to target unit
        double convertedValue = baseValue / targetUnit.conversionFactorToInches;

        // Round to 2 decimal places
        convertedValue = Math.round(convertedValue * 100.0) / 100.0;

        return new Length(convertedValue, targetUnit);
    }
    
    public Length add(Length thatLength) {

        if (thatLength == null) {
            throw new IllegalArgumentException("Length cannot be null");
        }

        if (!Double.isFinite(this.value) || !Double.isFinite(thatLength.value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        // Convert both to base unit (inches)
        double thisInches = this.toInches();
        double otherInches = thatLength.toInches();

        // Add
        double sumInches = thisInches + otherInches;

        // Convert back to THIS unit
        double result = sumInches / this.unit.conversionFactorToInches;

        // Round to 2 decimal places
        result = Math.round(result * 100.0) / 100.0;

        return new Length(result, this.unit);
    }
    
    public Length add(Length length2, Unit unit) {
    	if(length2 == null) {
    		throw new IllegalArgumentException("Length cannot be null");
    	} else if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
    	return this.add(length2).convertTo(unit);
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
