package com.quantitymeasurement.app;

public class QuantityMeasurementApp {

    public static boolean compare(double v1, Length.Unit u1, double v2, Length.Unit u2) {

        Length l1 = new Length(v1, u1);
        Length l2 = new Length(v2, u2);

        return l1.equals(l2);
    }

    public static void main(String[] args) {

        System.out.println(compare(1, Length.Unit.FEET, 12, Length.Unit.INCHES));

        System.out.println(compare(2, Length.Unit.FEET, 24, Length.Unit.INCHES));

        System.out.println(compare(1, Length.Unit.FEET, 10, Length.Unit.INCHES));
    }
}
