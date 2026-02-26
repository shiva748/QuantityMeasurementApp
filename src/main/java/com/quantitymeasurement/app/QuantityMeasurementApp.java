package com.quantitymeasurement.app;

public class QuantityMeasurementApp {

	public static <U extends IMeasurable> void demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {

		System.out.println("Input: " + q1 + " equals " + q2 + " → Output: " + q1.equals(q2));
	}

	public static <U extends IMeasurable> void demonstrateConversion(Quantity<U> q, U targetUnit) {

		System.out.println("Input: " + q + " convertTo " + targetUnit + " → Output: " + q.convertTo(targetUnit));
	}

	public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {

		System.out.println("Input: " + q1 + " add " + q2 + " → Output: " + q1.add(q2, targetUnit));
	}
	
	public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> q1, Quantity<U> q2) {
		System.out.println("Input: " + q1 + " add " + q2 + " → Output: " + q1.add(q2));
	}
    
	public static void main(String[] args) {

	    /* =========================================================
	       LENGTH OPERATIONS (UC1–UC8)
	       ========================================================= */

	    System.out.println("----- LENGTH OPERATIONS -----");

	    Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
	    Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

	    // Equality
	    demonstrateEquality(l1, l2);

	    // Conversion
	    demonstrateConversion(l1, LengthUnit.INCHES);

	    // Addition with target unit
	    demonstrateAddition(l1, l2, LengthUnit.FEET);


	    /* =========================================================
	       WEIGHT OPERATIONS (UC9)
	       ========================================================= */

	    System.out.println("\n----- WEIGHT OPERATIONS -----");

	    Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
	    Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

	    // Equality
	    demonstrateEquality(w1, w2);

	    // Conversion
	    demonstrateConversion(w1, WeightUnit.GRAM);

	    // Addition with target unit
	    demonstrateAddition(w1, w2, WeightUnit.KILOGRAM);


	    /* =========================================================
	       ADDITION WITHOUT TARGET (DEFAULT BEHAVIOR)
	       ========================================================= */

	    System.out.println("\n----- ADDITION (DEFAULT UNIT) -----");

	    demonstrateAddition(l1, l2); // result in FEET
	    demonstrateAddition(w1, w2); // result in KILOGRAM


	    /* =========================================================
	       CROSS-CATEGORY SAFETY (IMPORTANT)
	       ========================================================= */

	    System.out.println("\n----- CROSS CATEGORY CHECK -----");

	    Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
	    Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

	    // ❌ This will NOT compile (TYPE SAFETY)
	    // demonstrateEquality(length, weight);

	    // Manual check using equals (should be false)
	    System.out.println("Input: " + length + " equals " + weight +
	            " → Output: " + length.equals(weight));
	}
}
