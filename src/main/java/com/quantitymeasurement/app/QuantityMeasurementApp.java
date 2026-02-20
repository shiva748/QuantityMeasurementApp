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
            double value1, Length.Unit unit1,
            double value2, Length.Unit unit2) {

        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);

        return length1.equals(length2);
    }

    /*
     * Demonstrate length conversion from one unit to another 
    */
    
    public static Length demonstrateLengthConversion(
            double value,
            Length.Unit source,
            Length.Unit target) {

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
            Length.Unit target) {
    	if(length == null) {
    		throw new IllegalArgumentException("Length cannot be null");
    	}
    	
        if (target == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        return length.convertTo(target);
    }
    
    /*
     * Main method to demonstrate UC4 extended unit support
     */
    public static void main(String[] args) {

        System.out.println("Feet vs Inches:");
        System.out.println(demonstrateLengthComparison(
                1.0, Length.Unit.FEET,
                12.0, Length.Unit.INCHES)); // true


        System.out.println("Yards vs Feet:");
        System.out.println(demonstrateLengthComparison(
                1.0, Length.Unit.YARDS,
                3.0, Length.Unit.FEET)); // true


        System.out.println("Yards vs Inches:");
        System.out.println(demonstrateLengthComparison(
                1.0, Length.Unit.YARDS,
                36.0, Length.Unit.INCHES)); // true


        System.out.println("Centimeters vs Inches:");
        System.out.println(demonstrateLengthComparison(
                1.0, Length.Unit.CENTIMETERS,
                0.393701, Length.Unit.INCHES)); // true


        System.out.println("Complex Multi-Unit:");
        System.out.println(demonstrateLengthComparison(
                2.0, Length.Unit.YARDS,
                72.0, Length.Unit.INCHES)); // true


        System.out.println("Non Equal Example:");
        System.out.println(demonstrateLengthComparison(
                1.0, Length.Unit.YARDS,
                2.0, Length.Unit.FEET)); // false
        
     // method call to demonstrate UC5

     // convert(1.0, FEET, INCHES) → 12.0
     System.out.println("1 FEET to INCHES: " +
             demonstrateLengthConversion(1.0, Length.Unit.FEET, Length.Unit.INCHES));

     // convert(3.0, YARDS, FEET) → 9.0
     System.out.println("3 YARDS to FEET: " +
             demonstrateLengthConversion(3.0, Length.Unit.YARDS, Length.Unit.FEET));

     // convert(36.0, INCHES, YARDS) → 1.0
     System.out.println("36 INCHES to YARDS: " +
             demonstrateLengthConversion(36.0, Length.Unit.INCHES, Length.Unit.YARDS));

     // convert(1.0, CENTIMETERS, INCHES) → ~0.393701
     System.out.println("1 CM to INCHES: " +
             demonstrateLengthConversion(1.0, Length.Unit.CENTIMETERS, Length.Unit.INCHES));

     // convert(0.0, FEET, INCHES) → 0.0
     System.out.println("0 FEET to INCHES: " +
             demonstrateLengthConversion(0.0, Length.Unit.FEET, Length.Unit.INCHES));
    }
}
