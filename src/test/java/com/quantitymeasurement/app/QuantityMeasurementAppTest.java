package com.quantitymeasurement.app;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static com.quantitymeasurement.app.QuantityMeasurementApp.Feet;
public class QuantityMeasurementAppTest {
	 QuantityMeasurementApp qm = new QuantityMeasurementApp();

	    // 1. Same values should be equal
	    @Test
	    void givenSameFeetValue_shouldReturnTrue() {
	        QuantityMeasurementApp.Feet f1 = new Feet(1.0);
	        QuantityMeasurementApp.Feet f2 = new Feet(1.0);

	        assertTrue(f1.equals(f2));
	    }

	    // 2. Different values should not be equal
	    @Test
	    void givenDifferentFeetValues_shouldReturnFalse() {
	        QuantityMeasurementApp.Feet f1 = new Feet(1.0);
	        QuantityMeasurementApp.Feet f2 = new Feet(2.0);

	        assertFalse(f1.equals(f2));
	    }

	    // 3. Comparing same reference should return true
	    @Test
	    void givenSameReference_shouldReturnTrue() {
	        QuantityMeasurementApp.Feet f1 = new Feet(1.0);

	        assertTrue(f1.equals(f1));
	    }

	    // 4. Comparing with null should return false
	    @Test
	    void givenFeetAndNull_shouldReturnFalse() {
	        QuantityMeasurementApp.Feet f1 = new Feet(1.0);

	        assertFalse(f1.equals(null));
	    }

	    // 5. Comparing with different object type should return false
	    @Test
	    void givenFeetAndDifferentType_shouldReturnFalse() {
	        QuantityMeasurementApp.Feet f1 = new Feet(1.0);

	        assertFalse(f1.equals("1.0"));
	    }

	    // 6. Same value but different object instances
	    @Test
	    void givenTwoDifferentObjectsWithSameValue_shouldReturnTrue() {
	        QuantityMeasurementApp.Feet f1 = new Feet(5.0);
	        QuantityMeasurementApp.Feet f2 = new Feet(5.0);

	        assertTrue(f1.equals(f2));
	    }

	    // 7. Decimal value equality check
	    @Test
	    void givenDecimalValues_shouldReturnTrue() {
	        QuantityMeasurementApp.Feet f1 = new Feet(2.5);
	        QuantityMeasurementApp.Feet f2 = new Feet(2.5);

	        assertTrue(f1.equals(f2));
	    }

	    // 8. Negative value equality check
	    @Test
	    void givenNegativeValues_shouldReturnTrue() {
	        QuantityMeasurementApp.Feet f1 = new Feet(-3.0);
	        QuantityMeasurementApp.Feet f2 = new Feet(-3.0);

	        assertTrue(f1.equals(f2));
	    }

	    // 9. Zero value equality
	    @Test
	    void givenZeroValues_shouldReturnTrue() {
	        QuantityMeasurementApp.Feet f1 = new Feet(0.0);
	        QuantityMeasurementApp.Feet f2 = new Feet(0.0);

	        assertTrue(f1.equals(f2));
	    }
}
