package com.quantitymeasurement.app;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (Math.abs(b) < EPSILON) {
                throw new ArithmeticException("Divisor can't be 0.");
            }
            return a / b;
        });

        private final DoubleBinaryOperator operation;

        ArithmeticOperation(DoubleBinaryOperator operation) {
            this.operation = operation;
        }

        public double compute(double a, double b) {
            return operation.applyAsDouble(a, b);
        }
    }

    private final double value;
    private final U unit;

    private static final double EPSILON = 1e-9;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value cannot be infinite.");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    // 🔥 Centralized operation method
    private Quantity<U> operate(Quantity<U> that, ArithmeticOperation op) {
        if (that == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        double resultBase = op.compute(this.toBase(), that.toBase());
        double result = resultBase / this.unit.getConversionFactor();

        return new Quantity<>(round(result), this.unit);
    }

    public Quantity<U> add(Quantity<U> that) {
        return operate(that, ArithmeticOperation.ADD);
    }

    public Quantity<U> subtract(Quantity<U> that) {
        return operate(that, ArithmeticOperation.SUBTRACT);
    }

    public double divide(Quantity<U> that) {
        if (that == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        return round(ArithmeticOperation.DIVIDE.compute(this.toBase(), that.toBase()));
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = this.toBase();
        double convertedValue = baseValue / targetUnit.getConversionFactor();

        return new Quantity<>(round(convertedValue), targetUnit);
    }

    public Quantity<U> add(Quantity<U> that, U targetUnit) {
        return this.add(that).convertTo(targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> that, U targetUnit) {
        return this.subtract(that).convertTo(targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> that)) return false;

        if (this.unit.getClass() != that.unit.getClass()) return false;

        return Math.abs(this.toBase() - that.toBase()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round(toBase()));
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}