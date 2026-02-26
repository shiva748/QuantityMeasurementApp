package com.quantitymeasurement.app;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {
	private final double value;
	
	private final U unit;
	
	
	public double getValue() {
		return value;
	}

	public U getUnit() {
		return unit;
	}

	public Quantity(double value, U unit) {
		if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }else if(!Double.isFinite(value)) {
        	throw new IllegalArgumentException("value cannot be Infinite.");
        }
        this.value = value;
        this.unit = unit;
	}
	
	private double toBase() {
		return unit.convertToBaseUnit(value);
	}
	
	private static final double EPSILON = 1e-9;
	
	public Quantity<U> convertTo(U targetUnit){
		if(targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}else if(!sameDomain(this.unit, targetUnit)) {
			throw new IllegalArgumentException("Invalid target unit");
		}
		// convert to base unit
		double baseValue = this.toBase();
		// convert to target unit
		double convertedValue = baseValue / targetUnit.getConversionFactor();
		System.out.print(convertedValue);
		return new Quantity<U>(round(convertedValue), targetUnit);
	}
	
	public Quantity<U> add(Quantity<U> thatQuantity){
		if(thatQuantity == null) {
			throw new IllegalArgumentException("Quantity can not be null");
		}else if(!sameDomain(this.unit, thatQuantity.unit)) {
			throw new IllegalArgumentException("Quantity's are not of same domain.");
		}
		
		double thisBase = (toBase() + thatQuantity.toBase())/this.unit.getConversionFactor();
		
		return new Quantity<>(round(thisBase), this.unit);
	}
	
	private boolean sameDomain(U unit1, U unit2) {
		return unit1.getClass() == unit2.getClass();
	}
	
	private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
	
	public Quantity<U> add(Quantity<U> thatQuantity, U targetUnit){
		if(thatQuantity == null || unit == null) {
			throw new IllegalArgumentException("Quantity and Targetunit can not be null");
		}
		return this.add(thatQuantity).convertTo(targetUnit);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Quantity<?>)) {
			return false; 
		}
		Quantity<?> that = (Quantity<?>) obj;
		if(this.unit.getClass() != that.unit.getClass()) {
			return false;
		}
		return Double.compare( this.unit.convertToBaseUnit(value), that.unit.convertToBaseUnit(that.value)) < EPSILON;
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(toBase());
    }
	
	@Override
    public String toString() {
        return value + " " + unit;
    }
	
}
