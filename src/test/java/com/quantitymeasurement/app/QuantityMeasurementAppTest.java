package com.quantitymeasurement.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    /* =========================================================
       YARD TESTS
       ========================================================= */

    @Test
    void testEquality_YardToYard_SameValue() {
        Length l1 = new Length(1.0, Length.Unit.YARDS);
        Length l2 = new Length(1.0, Length.Unit.YARDS);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        Length l1 = new Length(1.0, Length.Unit.YARDS);
        Length l2 = new Length(2.0, Length.Unit.YARDS);
        assertFalse(l1.equals(l2));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        Length l1 = new Length(1.0, Length.Unit.YARDS);
        Length l2 = new Length(3.0, Length.Unit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        Length l1 = new Length(3.0, Length.Unit.FEET);
        Length l2 = new Length(1.0, Length.Unit.YARDS);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        Length l1 = new Length(1.0, Length.Unit.YARDS);
        Length l2 = new Length(36.0, Length.Unit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {
        Length l1 = new Length(36.0, Length.Unit.INCHES);
        Length l2 = new Length(1.0, Length.Unit.YARDS);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        Length l1 = new Length(1.0, Length.Unit.YARDS);
        Length l2 = new Length(2.0, Length.Unit.FEET);
        assertFalse(l1.equals(l2));
    }

    /* =========================================================
       CENTIMETER TESTS
       ========================================================= */

    @Test
    void testEquality_centimetersToInches_EquivalentValue() {
        Length l1 = new Length(1.0, Length.Unit.CENTIMETERS);
        Length l2 = new Length(0.393701, Length.Unit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_centimetersToFeet_NonEquivalentValue() {
        Length l1 = new Length(1.0, Length.Unit.CENTIMETERS);
        Length l2 = new Length(1.0, Length.Unit.FEET);
        assertFalse(l1.equals(l2));
    }

    /* =========================================================
       TRANSITIVE PROPERTY TEST
       ========================================================= */

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        Length yard = new Length(1.0, Length.Unit.YARDS);
        Length feet = new Length(3.0, Length.Unit.FEET);
        Length inches = new Length(36.0, Length.Unit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    /* =========================================================
       NULL UNIT TESTS
       ========================================================= */

    @Test
    void testEquality_YardWithNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1.0, null));
    }

    @Test
    void testEquality_CentimetersWithNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1.0, null));
    }

    /* =========================================================
       REFLEXIVE PROPERTY
       ========================================================= */

    @Test
    void testEquality_YardSameReference() {
        Length yard = new Length(1.0, Length.Unit.YARDS);
        assertTrue(yard.equals(yard));
    }

    @Test
    void testEquality_CentimetersSameReference() {
        Length cm = new Length(2.0, Length.Unit.CENTIMETERS);
        assertTrue(cm.equals(cm));
    }

    /* =========================================================
       NULL COMPARISON TESTS
       ========================================================= */

    @Test
    void testEquality_YardNullComparison() {
        Length yard = new Length(1.0, Length.Unit.YARDS);
        assertFalse(yard.equals(null));
    }

    @Test
    void testEquality_CentimetersNullComparison() {
        Length cm = new Length(1.0, Length.Unit.CENTIMETERS);
        assertFalse(cm.equals(null));
    }

    /* =========================================================
       COMPLEX MULTI-UNIT SCENARIO
       ========================================================= */

    @Test
    void testEquality_AllUnits_ComplexScenario() {
        Length yards = new Length(2.0, Length.Unit.YARDS);
        Length feet = new Length(6.0, Length.Unit.FEET);
        Length inches = new Length(72.0, Length.Unit.INCHES);

        assertTrue(yards.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yards.equals(inches));
    }
}
