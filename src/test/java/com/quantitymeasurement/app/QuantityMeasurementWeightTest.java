package com.quantitymeasurement.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class QuantityMeasurementWeightTest {
	/* =========================================================
	 UC9: WEIGHT EQUALITY TESTS
	 ========================================================= */

	@Test
	void testEquality_KilogramToKilogram_SameValue() {
	    Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
	    Weight w2 = new Weight(1.0, WeightUnit.KILOGRAM);
	    assertTrue(w1.equals(w2));
	}

	@Test
	void testEquality_KilogramToKilogram_DifferentValue() {
	    Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
	    Weight w2 = new Weight(2.0, WeightUnit.KILOGRAM);
	    assertFalse(w1.equals(w2));
	}

	@Test
	void testEquality_KilogramToGram_EquivalentValue() {
	    assertTrue(new Weight(1.0, WeightUnit.KILOGRAM)
	            .equals(new Weight(1000.0, WeightUnit.GRAM)));
	}

	@Test
	void testEquality_GramToKilogram_EquivalentValue() {
	    assertTrue(new Weight(1000.0, WeightUnit.GRAM)
	            .equals(new Weight(1.0, WeightUnit.KILOGRAM)));
	}

	@Test
	void testEquality_WeightVsLength_Incompatible() {
	    Weight w = new Weight(1.0, WeightUnit.KILOGRAM);
	    Length l = new Length(1.0, LengthUnit.FEET);

	    assertFalse(w.equals(l));
	}

	@Test
	void testEquality_NullComparison() {
	    Weight w = new Weight(1.0, WeightUnit.KILOGRAM);
	    assertFalse(w.equals(null));
	}

	@Test
	void testEquality_SameReference() {
	    Weight w = new Weight(1.0, WeightUnit.KILOGRAM);
	    assertTrue(w.equals(w));
	}

	@Test
	void testEquality_NullUnit() {
	    assertThrows(IllegalArgumentException.class,
	            () -> new Weight(1.0, null));
	}

	@Test
	void testEquality_TransitiveProperty() {
	    Weight a = new Weight(1.0, WeightUnit.KILOGRAM);
	    Weight b = new Weight(1000.0, WeightUnit.GRAM);
	    Weight c = new Weight(2.20462, WeightUnit.POUND);

	    assertTrue(a.equals(b));
	    assertTrue(b.equals(c));
	    assertTrue(a.equals(c));
	}

	@Test
	void testEquality_ZeroValue() {
	    assertTrue(new Weight(0.0, WeightUnit.KILOGRAM)
	            .equals(new Weight(0.0, WeightUnit.GRAM)));
	}

	@Test
	void testEquality_NegativeWeight() {
	    assertTrue(new Weight(-1.0, WeightUnit.KILOGRAM)
	            .equals(new Weight(-1000.0, WeightUnit.GRAM)));
	}

	@Test
	void testEquality_LargeWeightValue() {
	    assertTrue(new Weight(1000000.0, WeightUnit.GRAM)
	            .equals(new Weight(1000.0, WeightUnit.KILOGRAM)));
	}

	@Test
	void testEquality_SmallWeightValue() {
	    assertTrue(new Weight(0.001, WeightUnit.KILOGRAM)
	            .equals(new Weight(1.0, WeightUnit.GRAM)));
	}

	/* =========================================================
	 UC9: CONVERSION TESTS
	 ========================================================= */

	@Test
	void testConversion_PoundToKilogram() {
	    Weight result = new Weight(2.20462, WeightUnit.POUND)
	            .convertTo(WeightUnit.KILOGRAM);

	    assertTrue(Math.abs(result.getValue() - 1.0) < 0.01);
	}

	@Test
	void testConversion_KilogramToPound() {
	    Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
	            .convertTo(WeightUnit.POUND);

	    assertTrue(Math.abs(result.getValue() - 2.20462) < 0.01);
	}

	@Test
	void testConversion_SameUnit() {
	    Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
	            .convertTo(WeightUnit.KILOGRAM);

	    assertEquals(new Weight(5.0, WeightUnit.KILOGRAM), result);
	}

	@Test
	void testConversion_ZeroValue() {
	    Weight result = new Weight(0.0, WeightUnit.KILOGRAM)
	            .convertTo(WeightUnit.GRAM);

	    assertEquals(new Weight(0.0, WeightUnit.GRAM), result);
	}

	@Test
	void testConversion_NegativeValue() {
	    Weight result = new Weight(-1.0, WeightUnit.KILOGRAM)
	            .convertTo(WeightUnit.GRAM);

	    assertEquals(new Weight(-1000.0, WeightUnit.GRAM), result);
	}

	@Test
	void testConversion_RoundTrip() {
	    Weight original = new Weight(1.5, WeightUnit.KILOGRAM);

	    Weight converted = original.convertTo(WeightUnit.GRAM)
	            .convertTo(WeightUnit.KILOGRAM);

	    assertTrue(Math.abs(original.getValue() - converted.getValue()) < 0.01);
	}

	/* =========================================================
	 UC9: ADDITION TESTS
	 ========================================================= */

	@Test
	void testAddition_SameUnit_KilogramPlusKilogram() {
	    Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
	            .add(new Weight(2.0, WeightUnit.KILOGRAM));

	    assertEquals(new Weight(3.0, WeightUnit.KILOGRAM), result);
	}

	@Test
	void testAddition_CrossUnit_KilogramPlusGram() {
	    Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
	            .add(new Weight(1000.0, WeightUnit.GRAM));

	    assertEquals(new Weight(2.0, WeightUnit.KILOGRAM), result);
	}

	@Test
	void testAddition_CrossUnit_PoundPlusKilogram() {
	    Weight result = new Weight(2.20462, WeightUnit.POUND)
	            .add(new Weight(1.0, WeightUnit.KILOGRAM));

	    assertTrue(Math.abs(result.getValue() - 4.40924) < 0.01);
	}

	@Test
	void testAddition_ExplicitTargetUnit_Kilogram() {
	    Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
	            .add(new Weight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);

	    assertEquals(new Weight(2000.0, WeightUnit.GRAM), result);
	}

	@Test
	void testAddition_Commutativity() {
	    Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
	    Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

	    assertEquals(w1.add(w2), w2.add(w1));
	}

	@Test
	void testAddition_WithZero() {
	    Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
	            .add(new Weight(0.0, WeightUnit.GRAM));

	    assertEquals(new Weight(5.0, WeightUnit.KILOGRAM), result);
	}

	@Test
	void testAddition_NegativeValues() {
	    Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
	            .add(new Weight(-2000.0, WeightUnit.GRAM));

	    assertEquals(new Weight(3.0, WeightUnit.KILOGRAM), result);
	}

	@Test
	void testAddition_LargeValues() {
	    Weight result = new Weight(1e6, WeightUnit.KILOGRAM)
	            .add(new Weight(1e6, WeightUnit.KILOGRAM));

	    assertEquals(new Weight(2e6, WeightUnit.KILOGRAM), result);
	}
}
