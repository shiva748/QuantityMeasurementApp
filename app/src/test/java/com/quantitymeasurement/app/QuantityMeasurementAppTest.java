package com.quantitymeasurement.app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import static com.quantitymeasurement.app.QuantityMeasurementApp.Feet;
import static com.quantitymeasurement.app.QuantityMeasurementApp.Inches;

public class QuantityMeasurementAppTest {

    QuantityMeasurementApp qm = new QuantityMeasurementApp();

    /* =========================
       UC1 – FEET TEST CASES
       ========================= */

    @Test
    void givenSameFeetValue_shouldReturnTrue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);
        assertTrue(f1.equals(f2));
    }

    @Test
    void givenDifferentFeetValues_shouldReturnFalse() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(2.0);
        assertFalse(f1.equals(f2));
    }

    @Test
    void givenSameReference_shouldReturnTrue() {
        Feet f1 = new Feet(1.0);
        assertTrue(f1.equals(f1));
    }

    @Test
    void givenFeetAndNull_shouldReturnFalse() {
        Feet f1 = new Feet(1.0);
        assertFalse(f1.equals(null));
    }

    @Test
    void givenFeetAndDifferentType_shouldReturnFalse() {
        Feet f1 = new Feet(1.0);
        assertFalse(f1.equals("1.0"));
    }

    @Test
    void givenTwoDifferentObjectsWithSameValue_shouldReturnTrue() {
        Feet f1 = new Feet(5.0);
        Feet f2 = new Feet(5.0);
        assertTrue(f1.equals(f2));
    }

    @Test
    void givenDecimalFeetValues_shouldReturnTrue() {
        Feet f1 = new Feet(2.5);
        Feet f2 = new Feet(2.5);
        assertTrue(f1.equals(f2));
    }

    @Test
    void givenNegativeFeetValues_shouldReturnTrue() {
        Feet f1 = new Feet(-3.0);
        Feet f2 = new Feet(-3.0);
        assertTrue(f1.equals(f2));
    }

    @Test
    void givenZeroFeetValues_shouldReturnTrue() {
        Feet f1 = new Feet(0.0);
        Feet f2 = new Feet(0.0);
        assertTrue(f1.equals(f2));
    }

    /* =========================
       UC2 – INCHES TEST CASES
       ========================= */

    @Test
    void givenSameInchValue_shouldReturnTrue() {
        Inches i1 = new Inches(5.0);
        Inches i2 = new Inches(5.0);
        assertTrue(i1.equals(i2));
    }

    @Test
    void givenDifferentInchValues_shouldReturnFalse() {
        Inches i1 = new Inches(5.0);
        Inches i2 = new Inches(7.0);
        assertFalse(i1.equals(i2));
    }

    @Test
    void givenSameInchReference_shouldReturnTrue() {
        Inches i1 = new Inches(10.0);
        assertTrue(i1.equals(i1));
    }

    @Test
    void givenInchAndNull_shouldReturnFalse() {
        Inches i1 = new Inches(10.0);
        assertFalse(i1.equals(null));
    }

    @Test
    void givenInchAndDifferentType_shouldReturnFalse() {
        Inches i1 = new Inches(10.0);
        assertFalse(i1.equals(10.0));
    }

    @Test
    void givenTwoDifferentInchObjectsWithSameValue_shouldReturnTrue() {
        Inches i1 = new Inches(8.0);
        Inches i2 = new Inches(8.0);
        assertTrue(i1.equals(i2));
    }

    @Test
    void givenDecimalInchValues_shouldReturnTrue() {
        Inches i1 = new Inches(2.25);
        Inches i2 = new Inches(2.25);
        assertTrue(i1.equals(i2));
    }

    @Test
    void givenNegativeInchValues_shouldReturnTrue() {
        Inches i1 = new Inches(-4.0);
        Inches i2 = new Inches(-4.0);
        assertTrue(i1.equals(i2));
    }

    @Test
    void givenZeroInchValues_shouldReturnTrue() {
        Inches i1 = new Inches(0.0);
        Inches i2 = new Inches(0.0);
        assertTrue(i1.equals(i2));
    }
}
