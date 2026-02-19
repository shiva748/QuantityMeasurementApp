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
    }
}
