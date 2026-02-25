package com.quantitymeasurement.app;

import java.util.Objects;

public class Weight {

    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    private double toGram() {
        return unit.convertToBase(value);
    }

    public Weight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        if (!Double.isFinite(this.value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        // Convert to base (Gram)
        double baseValue = this.toGram();

        // Convert to target unit
        double convertedValue = baseValue / targetUnit.getConversionFactor();

        // Round to 2 decimal places
        convertedValue = Math.round(convertedValue * 100.0) / 100.0;

        return new Weight(convertedValue, targetUnit);
    }
    
    public Weight add(Weight thatWeight) {

        if (thatWeight == null) {
            throw new IllegalArgumentException("Weight cannot be null");
        }

        if (!Double.isFinite(this.value) || !Double.isFinite(thatWeight.value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        // Convert both to base unit (inches)
        double thisGram = this.toGram();
        double otherGram = thatWeight.toGram();

        // Add
        double sumInches = thisGram + otherGram;

        // Convert back to THIS unit
        double result = sumInches / this.unit.getConversionFactor();

        // Round to 2 decimal places
        result = Math.round(result * 100.0) / 100.0;

        return new Weight(result, this.unit);
    }
    
    public Weight add(Weight weight2, WeightUnit unit) {
    	if(weight2 == null) {
    		throw new IllegalArgumentException("weight cannot be null");
    	} else if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
    	return this.add(weight2).convertTo(unit);
    }

    
    private static final double EPSILON = 0.01;

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Weight other = (Weight) obj;

        return Math.abs(this.toGram() - other.toGram()) < EPSILON;
    }
    
    public double getValue() {
		return value;
	}

	@Override
    public int hashCode() {
        return Objects.hash(toGram());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
    
    // Main method for standalone testing
    public static void main(String args[]) {

    	 // Same unit equality
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1.0, WeightUnit.KILOGRAM);
        System.out.println("Are Weights equal? " + w1.equals(w2));

        // Cross-unit equality (kg ↔ g)
        Weight w3 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w4 = new Weight(1000.0, WeightUnit.GRAM);
        System.out.println("Are Weights equal? " + w3.equals(w4));

        // Cross-unit equality (kg ↔ pound)
        Weight w5 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w6 = new Weight(2.20462, WeightUnit.POUND);
        System.out.println("Are Weights equal? " + w5.equals(w6));

        // Another example (gram ↔ pound)
        Weight w7 = new Weight(453.592, WeightUnit.GRAM);
        Weight w8 = new Weight(1.0, WeightUnit.POUND);
        System.out.println("Are Weights equal? " + w7.equals(w8));

    }
}
