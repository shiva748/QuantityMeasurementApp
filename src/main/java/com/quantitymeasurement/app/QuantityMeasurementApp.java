package com.quantitymeasurement.app;

public class QuantityMeasurementApp {

    /*
     * Generic method to demonstrate equality between two Length objects
     */
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    /*
     * Convenience method to create Length objects and compare them
     */
    public static boolean demonstrateLengthComparison(
            double value1, Unit unit1,
            double value2, Unit unit2) {

        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);

        return length1.equals(length2);
    }

    /*
     * Demonstrate length conversion from one unit to another 
    */
    
    public static Length demonstrateLengthConversion(
            double value,
            Unit source,
            Unit target) {

        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }
        
        Length sourceLength = new Length(value, source);
        return sourceLength.convertTo(target);
    }
    
    /*
     * Demonstrate length conversion to target unit 
    */
    
    public static Length demonstrateLengthConversion(
            Length length,
            Unit target) {
    	if(length == null) {
    		throw new IllegalArgumentException("Length cannot be null");
    	}
    	
        if (target == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        return length.convertTo(target);
    }
    
    public static Length demonstrateLengthAddition(Length length1, Length length2) {

        if (length1 == null || length2 == null) {
            throw new IllegalArgumentException("Length cannot be null");
        }

        return length1.add(length2);
    }
    
    public static Length demonstrateLengthAddition(Length length1, Length length2,Unit unit) {
    	if(length1 == null || length2 == null) {
    		throw new IllegalArgumentException("Length's cannot be null");
    	}else if(unit == null) {
    		throw new IllegalArgumentException("Target unit cannot be null");
    	}
		return length1.add(length2,unit);
	}
    
    /*
     * Main method to demonstrate UC4 extended unit support
     */
    public static void main(String[] args) {

        System.out.println("Feet vs Inches:");
        System.out.println(demonstrateLengthComparison(
                1.0, Unit.FEET,
                12.0, Unit.INCHES)); // true


        System.out.println("Yards vs Feet:");
        System.out.println(demonstrateLengthComparison(
                1.0, Unit.YARDS,
                3.0, Unit.FEET)); // true


        System.out.println("Yards vs Inches:");
        System.out.println(demonstrateLengthComparison(
                1.0, Unit.YARDS,
                36.0, Unit.INCHES)); // true


        System.out.println("Centimeters vs Inches:");
        System.out.println(demonstrateLengthComparison(
                1.0, Unit.CENTIMETERS,
                0.393701, Unit.INCHES)); // true


        System.out.println("Complex Multi-Unit:");
        System.out.println(demonstrateLengthComparison(
                2.0, Unit.YARDS,
                72.0, Unit.INCHES)); // true


        System.out.println("Non Equal Example:");
        System.out.println(demonstrateLengthComparison(
                1.0, Unit.YARDS,
                2.0, Unit.FEET)); // false
        
     // method call to demonstrate UC5

     // convert(1.0, FEET, INCHES) → 12.0
     System.out.println("1 FEET to INCHES: " +
             demonstrateLengthConversion(1.0, Unit.FEET, Unit.INCHES));

     // convert(3.0, YARDS, FEET) → 9.0
     System.out.println("3 YARDS to FEET: " +
             demonstrateLengthConversion(3.0, Unit.YARDS, Unit.FEET));

     // convert(36.0, INCHES, YARDS) → 1.0
     System.out.println("36 INCHES to YARDS: " +
             demonstrateLengthConversion(36.0, Unit.INCHES, Unit.YARDS));

     // convert(1.0, CENTIMETERS, INCHES) → ~0.393701
     System.out.println("1 CM to INCHES: " +
             demonstrateLengthConversion(1.0, Unit.CENTIMETERS, Unit.INCHES));

     // convert(0.0, FEET, INCHES) → 0.0
     System.out.println("0 FEET to INCHES: " +
             demonstrateLengthConversion(0.0, Unit.FEET, Unit.INCHES));
     
  // ================= UC6 ADDITION DEMONSTRATION =================

     System.out.println("\n--- UC6 Addition Examples ---");

     // add(1.0 FEET, 2.0 FEET) → 3.0 FEET
     System.out.println("1 FEET + 2 FEET = " +
             demonstrateLengthAddition(
                     new Length(1.0, Unit.FEET),
                     new Length(2.0, Unit.FEET)
             ));

     // add(1.0 FEET, 12.0 INCHES) → 2.0 FEET
     System.out.println("1 FEET + 12 INCHES = " +
             demonstrateLengthAddition(
                     new Length(1.0, Unit.FEET),
                     new Length(12.0, Unit.INCHES)
             ));

     // add(12.0 INCHES, 1.0 FEET) → 24.0 INCHES
     System.out.println("12 INCHES + 1 FEET = " +
             demonstrateLengthAddition(
                     new Length(12.0, Unit.INCHES),
                     new Length(1.0, Unit.FEET)
             ));

     // add(1.0 YARDS, 3.0 FEET) → 2.0 YARDS
     System.out.println("1 YARD + 3 FEET = " +
             demonstrateLengthAddition(
                     new Length(1.0, Unit.YARDS),
                     new Length(3.0, Unit.FEET)
             ));

     // add(36.0 INCHES, 1.0 YARD) → 72.0 INCHES
     System.out.println("36 INCHES + 1 YARD = " +
             demonstrateLengthAddition(
                     new Length(36.0, Unit.INCHES),
                     new Length(1.0, Unit.YARDS)
             ));

     // add(2.54 CM, 1.0 INCH) → ~5.08 CM
     System.out.println("2.54 CM + 1 INCH = " +
             demonstrateLengthAddition(
                     new Length(2.54, Unit.CENTIMETERS),
                     new Length(1.0, Unit.INCHES)
             ));

     // add(5.0 FEET, 0.0 INCHES) → 5.0 FEET
     System.out.println("5 FEET + 0 INCHES = " +
             demonstrateLengthAddition(
                     new Length(5.0, Unit.FEET),
                     new Length(0.0, Unit.INCHES)
             ));

     // add(5.0 FEET, -2.0 FEET) → 3.0 FEET
     System.out.println("5 FEET + (-2 FEET) = " +
             demonstrateLengthAddition(
                     new Length(5.0, Unit.FEET),
                     new Length(-2.0, Unit.FEET)
             ));
     
  // ================= UC7 ADDITION (TARGET UNIT) =================

     System.out.println("\n--- UC7 Addition with Target Unit ---");

     // add(1 FEET, 12 INCHES, FEET) → 2 FEET
     System.out.println("1 FEET + 12 INCHES (FEET) = " +
             demonstrateLengthAddition(
                     new Length(1.0, Unit.FEET),
                     new Length(12.0, Unit.INCHES),
                     Unit.FEET
             ));

     // add(1 FEET, 12 INCHES, INCHES) → 24 INCHES
     System.out.println("1 FEET + 12 INCHES (INCHES) = " +
             demonstrateLengthAddition(
                     new Length(1.0, Unit.FEET),
                     new Length(12.0, Unit.INCHES),
                     Unit.INCHES
             ));

     // add(1 FEET, 12 INCHES, YARDS) → ~0.667 YARDS
     System.out.println("1 FEET + 12 INCHES (YARDS) = " +
             demonstrateLengthAddition(
                     new Length(1.0, Unit.FEET),
                     new Length(12.0, Unit.INCHES),
                     Unit.YARDS
             ));

     // add(1 YARD, 3 FEET, YARDS) → 2 YARDS
     System.out.println("1 YARD + 3 FEET (YARDS) = " +
             demonstrateLengthAddition(
                     new Length(1.0, Unit.YARDS),
                     new Length(3.0, Unit.FEET),
                     Unit.YARDS
             ));

     // add(36 INCHES, 1 YARD, FEET) → 6 FEET
     System.out.println("36 INCHES + 1 YARD (FEET) = " +
             demonstrateLengthAddition(
                     new Length(36.0, Unit.INCHES),
                     new Length(1.0, Unit.YARDS),
                     Unit.FEET
             ));

     // add(2.54 CM, 1 INCH, CM) → ~5.08 CM
     System.out.println("2.54 CM + 1 INCH (CM) = " +
             demonstrateLengthAddition(
                     new Length(2.54, Unit.CENTIMETERS),
                     new Length(1.0, Unit.INCHES),
                     Unit.CENTIMETERS
             ));

     // add(5 FEET, 0 INCHES, YARDS) → ~1.667 YARDS
     System.out.println("5 FEET + 0 INCHES (YARDS) = " +
             demonstrateLengthAddition(
                     new Length(5.0, Unit.FEET),
                     new Length(0.0, Unit.INCHES),
                     Unit.YARDS
             ));

     // add(5 FEET, -2 FEET, INCHES) → 36 INCHES
     System.out.println("5 FEET + (-2 FEET) (INCHES) = " +
             demonstrateLengthAddition(
                     new Length(5.0, Unit.FEET),
                     new Length(-2.0, Unit.FEET),
                     Unit.INCHES
             ));
    }
}
