package com.quantitymeasurement.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuantityMeasurementAppTest {

	@Test
	void testIMeasurableInterface_LengthUnitImplementation() {
		IMeasurable unit = LengthUnit.FEET;
		assertEquals(12.0, unit.getConversionFactor());
		assertEquals("FEET", unit.getUnitName());
		assertEquals(12.0, unit.convertToBaseUnit(1.0));
	}

	@Test
	void testIMeasurableInterface_WeightUnitImplementation() {
		IMeasurable unit = WeightUnit.KILOGRAM;
		assertEquals(1000.0, unit.getConversionFactor());
		assertEquals("KILOGRAM", unit.getUnitName());
		assertEquals(1000.0, unit.convertToBaseUnit(1.0));
	}

	@Test
	void testIMeasurableInterface_ConsistentBehavior() {
		IMeasurable length = LengthUnit.INCHES;
		IMeasurable weight = WeightUnit.GRAM;

		assertEquals(1.0, length.getConversionFactor());
		assertEquals(1.0, weight.getConversionFactor());
	}

	@Test
	void testGenericQuantity_LengthOperations_Equality() {
		Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
		assertTrue(q1.equals(q2));
	}

	@Test
	void testGenericQuantity_WeightOperations_Equality() {
		Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);
		assertTrue(q1.equals(q2));
	}

	@Test
	void testCrossCategoryPrevention_LengthVsWeight() {
		Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertFalse(length.equals(weight));
	}

	@Test
	void testGenericQuantity_LengthOperations_Conversion() {
		Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);

		assertEquals(12.0, result.getValue());
		assertEquals(LengthUnit.INCHES, result.getUnit());
	}

	@Test
	void testGenericQuantity_WeightOperations_Conversion() {
		Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> result = q.convertTo(WeightUnit.GRAM);
		
		assertEquals(1000.0, result.getValue());
		assertEquals(WeightUnit.GRAM, result.getUnit());
	}

	@Test
	void testGenericQuantity_LengthOperations_Addition() {
		Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = q1.add(q2, LengthUnit.FEET);

		assertEquals(2.0, result.getValue());
		assertEquals(LengthUnit.FEET, result.getUnit());
	}

	@Test
	void testGenericQuantity_WeightOperations_Addition() {
		Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

		Quantity<WeightUnit> result = q1.add(q2, WeightUnit.KILOGRAM);

		assertEquals(2.0, result.getValue());
		assertEquals(WeightUnit.KILOGRAM, result.getUnit());
	}

	@Test
	void testGenericQuantity_ConstructorValidation_NullUnit() {
		assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
	}

	@Test
	void testGenericQuantity_ConstructorValidation_InvalidValue() {
		assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
	}

	@Test
	void testHashCode_GenericQuantity_Consistency() {
		Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
		assertEquals(q1, q2);
		assertEquals(q1.hashCode(), q2.hashCode());
	}

	@Test
	void testEquals_GenericQuantity_ContractPreservation() {
		Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);

		assertTrue(a.equals(b));
		assertTrue(b.equals(a));
	}

	@Test
	void testImmutability_GenericQuantity() {
		Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> q2 = q1.convertTo(LengthUnit.INCHES);

		assertNotSame(q1, q2);
	}

	@Test
	void testTypeWildcard_FlexibleSignatures() {
		Quantity<?> q1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<?> q2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertNotNull(q1);
		assertNotNull(q2);
	}

	@Test
	void testQuantityMeasurementApp_SimplifiedDemonstration_Equality() {
		Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

		assertDoesNotThrow(() -> QuantityMeasurementApp.demonstrateEquality(q1, q2));
	}

	@Test
	void testQuantityMeasurementApp_SimplifiedDemonstration_Conversion() {
		Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertDoesNotThrow(() -> QuantityMeasurementApp.demonstrateConversion(q, WeightUnit.GRAM));
	}

	@Test
	void testQuantityMeasurementApp_SimplifiedDemonstration_Addition() {
		Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

		assertDoesNotThrow(() -> QuantityMeasurementApp.demonstrateAddition(q1, q2, WeightUnit.KILOGRAM));
	}
	
	@Test
	void testVolumeEquality_LitreToLitre_Same() {
	    assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
	            .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
	}

	@Test
	void testVolumeEquality_LitreToMillilitre() {
	    assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
	            .equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
	}

	@Test
	void testVolumeEquality_MillilitreToLitre() {
	    assertTrue(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
	            .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
	}

	@Test
	void testVolumeEquality_GallonToLitre() {
	    assertTrue(new Quantity<>(1.0, VolumeUnit.GALLON)
	            .equals(new Quantity<>(3.78541, VolumeUnit.LITRE)));
	}

	@Test
	void testVolumeEquality_WithNull() {
	    assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE).equals(null));
	}

	@Test
	void testVolumeEquality_SameReference() {
	    Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
	    assertTrue(v.equals(v));
	}

	@Test
	void testVolumeEquality_NullUnit() {
	    assertThrows(IllegalArgumentException.class,
	            () -> new Quantity<>(1.0, null));
	}
	
	// === === conversion test === ===
	
	@Test
	void testVolumeConversion_LitreToMillilitre() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(1.0, VolumeUnit.LITRE)
	                    .convertTo(VolumeUnit.MILLILITRE);

	    assertEquals(1000.0, result.getValue());
	}

	@Test
	void testVolumeConversion_MillilitreToLitre() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
	                    .convertTo(VolumeUnit.LITRE);

	    assertEquals(1.0, result.getValue());
	}

	@Test
	void testVolumeConversion_GallonToLitre() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(1.0, VolumeUnit.GALLON)
	                    .convertTo(VolumeUnit.LITRE);

	    assertEquals(3.78541, result.getValue(), 0.01);
	}

	@Test
	void testVolumeConversion_LitreToGallon() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(3.78541, VolumeUnit.LITRE)
	                    .convertTo(VolumeUnit.GALLON);

	    assertEquals(1.0, result.getValue(), 0.01);
	}

	@Test
	void testVolumeConversion_SameUnit() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(5.0, VolumeUnit.LITRE)
	                    .convertTo(VolumeUnit.LITRE);

	    assertEquals(5.0, result.getValue());
	}
	
	// === === addition test === ===
	
	@Test
	void testVolumeAddition_SameUnit_Litre() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(1.0, VolumeUnit.LITRE)
	                    .add(new Quantity<>(2.0, VolumeUnit.LITRE));

	    assertEquals(3.0, result.getValue());
	}

	@Test
	void testVolumeAddition_CrossUnit_LitreMillilitre() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(1.0, VolumeUnit.LITRE)
	                    .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));

	    assertEquals(2.0, result.getValue());
	}

	@Test
	void testVolumeAddition_CrossUnit_GallonLitre() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(1.0, VolumeUnit.GALLON)
	                    .add(new Quantity<>(3.78541, VolumeUnit.LITRE));

	    assertEquals(2.0, result.getValue(), 0.01);
	}
	
	// === === target unit test === === 
	
	@Test
	void testVolumeAddition_Target_Litre() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(1.0, VolumeUnit.LITRE)
	                    .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.LITRE);

	    assertEquals(2.0, result.getValue());
	}

	@Test
	void testVolumeAddition_Target_Millilitre() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(1.0, VolumeUnit.LITRE)
	                    .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.MILLILITRE);

	    assertEquals(2000.0, result.getValue());
	}

	@Test
	void testVolumeAddition_Target_Gallon() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(3.78541, VolumeUnit.LITRE)
	                    .add(new Quantity<>(3.78541, VolumeUnit.LITRE), VolumeUnit.GALLON);

	    assertEquals(2.0, result.getValue(), 0.01);
	}
	
	// === === === edgecase === === === 
	
	@Test
	void testVolumeAddition_WithZero() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(5.0, VolumeUnit.LITRE)
	                    .add(new Quantity<>(0.0, VolumeUnit.MILLILITRE));

	    assertEquals(5.0, result.getValue());
	}

	@Test
	void testVolumeAddition_Negative() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(5.0, VolumeUnit.LITRE)
	                    .add(new Quantity<>(-2000.0, VolumeUnit.MILLILITRE));

	    assertEquals(3.0, result.getValue());
	}

	@Test
	void testVolumeAddition_LargeValues() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(1e6, VolumeUnit.LITRE)
	                    .add(new Quantity<>(1e6, VolumeUnit.LITRE));

	    assertEquals(2e6, result.getValue());
	}
	
	// === === Enum test === === 
	
	@Test
	void testVolumeUnit_LitreFactor() {
	    assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor());
	}

	@Test
	void testVolumeUnit_MillilitreFactor() {
	    assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor());
	}

	@Test
	void testVolumeUnit_GallonFactor() {
	    assertEquals(3.78541, VolumeUnit.GALLON.getConversionFactor());
	}
	/* =========================================================
	   SUBTRACTION TESTS
	   ========================================================= */

	@Test
	void testSubtraction_SameUnit_FeetMinusFeet() {
	    Quantity<LengthUnit> result =
	            new Quantity<>(10.0, LengthUnit.FEET)
	                    .subtract(new Quantity<>(5.0, LengthUnit.FEET));

	    assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
	}

	@Test
	void testSubtraction_SameUnit_LitreMinusLitre() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(10.0, VolumeUnit.LITRE)
	                    .subtract(new Quantity<>(3.0, VolumeUnit.LITRE));

	    assertEquals(new Quantity<>(7.0, VolumeUnit.LITRE), result);
	}

	@Test
	void testSubtraction_CrossUnit_FeetMinusInches() {
	    Quantity<LengthUnit> result =
	            new Quantity<>(10.0, LengthUnit.FEET)
	                    .subtract(new Quantity<>(6.0, LengthUnit.INCHES));

	    assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
	}

	@Test
	void testSubtraction_CrossUnit_InchesMinusFeet() {
	    Quantity<LengthUnit> result =
	            new Quantity<>(120.0, LengthUnit.INCHES)
	                    .subtract(new Quantity<>(5.0, LengthUnit.FEET));

	    assertEquals(new Quantity<>(60.0, LengthUnit.INCHES), result);
	}

	@Test
	void testSubtraction_ExplicitTargetUnit_Feet() {
	    Quantity<LengthUnit> result =
	            new Quantity<>(10.0, LengthUnit.FEET)
	                    .subtract(new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.FEET);

	    assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
	}

	@Test
	void testSubtraction_ExplicitTargetUnit_Inches() {
	    Quantity<LengthUnit> result =
	            new Quantity<>(10.0, LengthUnit.FEET)
	                    .subtract(new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.INCHES);

	    assertEquals(new Quantity<>(114.0, LengthUnit.INCHES), result);
	}

	@Test
	void testSubtraction_ExplicitTargetUnit_Millilitre() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(5.0, VolumeUnit.LITRE)
	                    .subtract(new Quantity<>(2.0, VolumeUnit.LITRE), VolumeUnit.MILLILITRE);

	    assertEquals(new Quantity<>(3000.0, VolumeUnit.MILLILITRE), result);
	}

	@Test
	void testSubtraction_ResultingInNegative() {
	    Quantity<LengthUnit> result =
	            new Quantity<>(5.0, LengthUnit.FEET)
	                    .subtract(new Quantity<>(10.0, LengthUnit.FEET));

	    assertEquals(new Quantity<>(-5.0, LengthUnit.FEET), result);
	}

	@Test
	void testSubtraction_ResultingInZero() {
	    Quantity<LengthUnit> result =
	            new Quantity<>(10.0, LengthUnit.FEET)
	                    .subtract(new Quantity<>(120.0, LengthUnit.INCHES));

	    assertEquals(new Quantity<>(0.0, LengthUnit.FEET), result);
	}

	@Test
	void testSubtraction_WithZeroOperand() {
	    Quantity<LengthUnit> result =
	            new Quantity<>(5.0, LengthUnit.FEET)
	                    .subtract(new Quantity<>(0.0, LengthUnit.INCHES));

	    assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
	}

	@Test
	void testSubtraction_WithNegativeValues() {
	    Quantity<LengthUnit> result =
	            new Quantity<>(5.0, LengthUnit.FEET)
	                    .subtract(new Quantity<>(-2.0, LengthUnit.FEET));

	    assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result);
	}

	@Test
	void testSubtraction_NonCommutative() {
	    Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
	    Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

	    assertNotEquals(a.subtract(b), b.subtract(a));
	}

	@Test
	void testSubtraction_WithLargeValues() {
	    Quantity<WeightUnit> result =
	            new Quantity<>(1e6, WeightUnit.KILOGRAM)
	                    .subtract(new Quantity<>(5e5, WeightUnit.KILOGRAM));

	    assertEquals(new Quantity<>(5e5, WeightUnit.KILOGRAM), result);
	}

	@Test
	void testSubtraction_WithSmallValues() {
	    Quantity<LengthUnit> result =
	            new Quantity<>(0.001, LengthUnit.FEET)
	                    .subtract(new Quantity<>(0.0005, LengthUnit.FEET));

	    assertEquals(new Quantity<>(0.0, LengthUnit.FEET), result);
	}

	@Test
	void testSubtraction_NullOperand() {
	    assertThrows(IllegalArgumentException.class,
	            () -> new Quantity<>(10.0, LengthUnit.FEET).subtract(null));
	}

	@Test
	void testSubtraction_NullTargetUnit() {
	    assertThrows(IllegalArgumentException.class,
	            () -> new Quantity<>(10.0, LengthUnit.FEET)
	                    .subtract(new Quantity<>(5.0, LengthUnit.FEET), null));
	}

	@Test
	void testSubtraction_AllMeasurementCategories() {
	    assertNotNull(new Quantity<>(10.0, LengthUnit.FEET)
	            .subtract(new Quantity<>(5.0, LengthUnit.FEET)));

	    assertNotNull(new Quantity<>(10.0, WeightUnit.KILOGRAM)
	            .subtract(new Quantity<>(5.0, WeightUnit.KILOGRAM)));

	    assertNotNull(new Quantity<>(5.0, VolumeUnit.LITRE)
	            .subtract(new Quantity<>(2.0, VolumeUnit.LITRE)));
	}

	@Test
	void testSubtraction_ChainedOperations() {
	    Quantity<LengthUnit> result =
	            new Quantity<>(10.0, LengthUnit.FEET)
	                    .subtract(new Quantity<>(2.0, LengthUnit.FEET))
	                    .subtract(new Quantity<>(1.0, LengthUnit.FEET));

	    assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result);
	}


	/* =========================================================
	   DIVISION TESTS
	   ========================================================= */

	@Test
	void testDivision_SameUnit_FeetDividedByFeet() {
	    double result = new Quantity<>(10.0, LengthUnit.FEET)
	            .divide(new Quantity<>(2.0, LengthUnit.FEET));

	    assertEquals(5.0, result);
	}

	@Test
	void testDivision_SameUnit_LitreDividedByLitre() {
	    double result = new Quantity<>(10.0, VolumeUnit.LITRE)
	            .divide(new Quantity<>(5.0, VolumeUnit.LITRE));

	    assertEquals(2.0, result);
	}

	@Test
	void testDivision_CrossUnit_FeetDividedByInches() {
	    double result = new Quantity<>(24.0, LengthUnit.INCHES)
	            .divide(new Quantity<>(2.0, LengthUnit.FEET));

	    assertEquals(1.0, result);
	}

	@Test
	void testDivision_CrossUnit_KilogramDividedByGram() {
	    double result = new Quantity<>(2.0, WeightUnit.KILOGRAM)
	            .divide(new Quantity<>(2000.0, WeightUnit.GRAM));

	    assertEquals(1.0, result);
	}

	@Test
	void testDivision_RatioLessThanOne() {
	    double result = new Quantity<>(5.0, LengthUnit.FEET)
	            .divide(new Quantity<>(10.0, LengthUnit.FEET));

	    assertEquals(0.5, result);
	}

	@Test
	void testDivision_RatioEqualToOne() {
	    double result = new Quantity<>(10.0, LengthUnit.FEET)
	            .divide(new Quantity<>(10.0, LengthUnit.FEET));

	    assertEquals(1.0, result);
	}

	@Test
	void testDivision_NonCommutative() {
	    Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
	    Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

	    assertNotEquals(a.divide(b), b.divide(a));
	}

	@Test
	void testDivision_ByZero() {
	    assertThrows(ArithmeticException.class,
	            () -> new Quantity<>(10.0, LengthUnit.FEET)
	                    .divide(new Quantity<>(0.0, LengthUnit.FEET)));
	}

	@Test
	void testDivision_WithLargeRatio() {
	    double result = new Quantity<>(1e6, WeightUnit.KILOGRAM)
	            .divide(new Quantity<>(1.0, WeightUnit.KILOGRAM));

	    assertEquals(1e6, result);
	}

	@Test
	void testDivision_WithSmallRatio() {
	    double result = new Quantity<>(1.0, WeightUnit.KILOGRAM)
	            .divide(new Quantity<>(1e6, WeightUnit.KILOGRAM));

	    assertEquals(0.0, result);
	}

	@Test
	void testDivision_NullOperand() {
	    assertThrows(IllegalArgumentException.class,
	            () -> new Quantity<>(10.0, LengthUnit.FEET).divide(null));
	}

	@Test
	void testDivision_AllMeasurementCategories() {
	    assertEquals(2.0,
	            new Quantity<>(10.0, LengthUnit.FEET)
	                    .divide(new Quantity<>(5.0, LengthUnit.FEET)));

	    assertEquals(2.0,
	            new Quantity<>(10.0, WeightUnit.KILOGRAM)
	                    .divide(new Quantity<>(5.0, WeightUnit.KILOGRAM)));

	    assertEquals(2.5,
	            new Quantity<>(5.0, VolumeUnit.LITRE)
	                    .divide(new Quantity<>(2.0, VolumeUnit.LITRE)));
	}


	/* =========================================================
	   INTEGRATION + PROPERTIES
	   ========================================================= */

	@Test
	void testSubtractionAndDivision_Integration() {
	    double result = new Quantity<>(10.0, LengthUnit.FEET)
	            .subtract(new Quantity<>(2.0, LengthUnit.FEET))
	            .divide(new Quantity<>(4.0, LengthUnit.FEET));

	    assertEquals(2.0, result);
	}

	@Test
	void testSubtractionAddition_Inverse() {
	    Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
	    Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

	    assertEquals(a, a.add(b).subtract(b));
	}

	@Test
	void testSubtraction_Immutability() {
	    Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
	    Quantity<LengthUnit> b = a.subtract(new Quantity<>(5.0, LengthUnit.FEET));

	    assertNotSame(a, b);
	}

	@Test
	void testDivision_Immutability() {
	    Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
	    double result = a.divide(new Quantity<>(2.0, LengthUnit.FEET));

	    assertEquals(10.0, a.getValue());
	    assertEquals(5.0, result);
	}
	
	/* =========================================================
    ADD / SUBTRACT / DIVIDE - CORE BEHAVIOR (ENUM DRIVEN)
    ========================================================= */

 @Test
 void testArithmetic_Add_Delegation() {
     Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
     Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

     assertEquals(15.0, q1.add(q2).getValue());
 }

 @Test
 void testArithmetic_Subtract_Delegation() {
     Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
     Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

     assertEquals(5.0, q1.subtract(q2).getValue());
 }

 @Test
 void testArithmetic_Divide_Delegation() {
     Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
     Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

     assertEquals(2.0, q1.divide(q2));
 }

 /* =========================================================
    VALIDATION TESTS
    ========================================================= */

 @Test
 void testValidation_NullOperand_Add() {
     Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);
     assertThrows(IllegalArgumentException.class, () -> q.add(null));
 }

 @Test
 void testValidation_NullOperand_Subtract() {
     Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);
     assertThrows(IllegalArgumentException.class, () -> q.subtract(null));
 }

 @Test
 void testValidation_NullOperand_Divide() {
     Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);
     assertThrows(IllegalArgumentException.class, () -> q.divide(null));
 }

 @Test
 void testValidation_DivideByZero() {
     Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
     Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.FEET);

     assertThrows(ArithmeticException.class, () -> q1.divide(q2));
 }

 @Test
 void testValidation_NullTargetUnit() {
     Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
     Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

     assertThrows(IllegalArgumentException.class, () -> q1.add(q2, null));
 }

 /* =========================================================
    ADDITION TESTS
    ========================================================= */

 @Test
 void testAddition_SameUnit() {
     Quantity<LengthUnit> result =
             new Quantity<>(10.0, LengthUnit.FEET)
                     .add(new Quantity<>(5.0, LengthUnit.FEET));

     assertEquals(15.0, result.getValue());
 }

 @Test
 void testAddition_CrossUnit() {
     Quantity<LengthUnit> result =
             new Quantity<>(1.0, LengthUnit.FEET)
                     .add(new Quantity<>(12.0, LengthUnit.INCHES));

     assertEquals(2.0, result.getValue());
 }

 @Test
 void testAddition_WithTargetUnit() {
     Quantity<LengthUnit> result =
             new Quantity<>(1.0, LengthUnit.FEET)
                     .add(new Quantity<>(12.0, LengthUnit.INCHES), LengthUnit.INCHES);

     assertEquals(24.0, result.getValue());
 }

 @Test
 void testAddition_WithZero() {
     Quantity<LengthUnit> result =
             new Quantity<>(5.0, LengthUnit.FEET)
                     .add(new Quantity<>(0.0, LengthUnit.INCHES));

     assertEquals(5.0, result.getValue());
 }

 @Test
 void testAddition_NegativeValues() {
     Quantity<LengthUnit> result =
             new Quantity<>(5.0, LengthUnit.FEET)
                     .add(new Quantity<>(-2.0, LengthUnit.FEET));

     assertEquals(3.0, result.getValue());
 }

 @Test
 void testAddition_LargeValues() {
     Quantity<WeightUnit> result =
             new Quantity<>(1e6, WeightUnit.KILOGRAM)
                     .add(new Quantity<>(1e6, WeightUnit.KILOGRAM));

     assertEquals(2e6, result.getValue());
 }

 /* =========================================================
    SUBTRACTION TESTS
    ========================================================= */

 @Test
 void testSubtraction_SameUnit() {
     Quantity<LengthUnit> result =
             new Quantity<>(10.0, LengthUnit.FEET)
                     .subtract(new Quantity<>(5.0, LengthUnit.FEET));

     assertEquals(5.0, result.getValue());
 }

 @Test
 void testSubtraction_CrossUnit() {
     Quantity<LengthUnit> result =
             new Quantity<>(10.0, LengthUnit.FEET)
                     .subtract(new Quantity<>(6.0, LengthUnit.INCHES));

     assertEquals(9.5, result.getValue());
 }

 @Test
 void testSubtraction_WithTargetUnit() {
     Quantity<LengthUnit> result =
             new Quantity<>(10.0, LengthUnit.FEET)
                     .subtract(new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.INCHES);

     assertEquals(114.0, result.getValue());
 }

 @Test
 void testSubtraction_ResultNegative() {
     Quantity<LengthUnit> result =
             new Quantity<>(5.0, LengthUnit.FEET)
                     .subtract(new Quantity<>(10.0, LengthUnit.FEET));

     assertEquals(-5.0, result.getValue());
 }

 @Test
 void testSubtraction_ResultZero() {
     Quantity<LengthUnit> result =
             new Quantity<>(10.0, LengthUnit.FEET)
                     .subtract(new Quantity<>(120.0, LengthUnit.INCHES));

     assertEquals(0.0, result.getValue());
 }

 @Test
 void testSubtraction_WithNegativeOperand() {
     Quantity<LengthUnit> result =
             new Quantity<>(5.0, LengthUnit.FEET)
                     .subtract(new Quantity<>(-2.0, LengthUnit.FEET));

     assertEquals(7.0, result.getValue());
 }

 /* =========================================================
    DIVISION TESTS
    ========================================================= */

 @Test
 void testDivision_SameUnit() {
     assertEquals(5.0,
             new Quantity<>(10.0, LengthUnit.FEET)
                     .divide(new Quantity<>(2.0, LengthUnit.FEET)));
 }

 @Test
 void testDivision_CrossUnit() {
     assertEquals(1.0,
             new Quantity<>(24.0, LengthUnit.INCHES)
                     .divide(new Quantity<>(2.0, LengthUnit.FEET)));
 }

 @Test
 void testDivision_LargeRatio() {
     assertEquals(1e6,
             new Quantity<>(1e6, WeightUnit.KILOGRAM)
                     .divide(new Quantity<>(1.0, WeightUnit.KILOGRAM)));
 }

 /* =========================================================
    IMMUTABILITY TESTS
    ========================================================= */

 @Test
 void testImmutability_Add() {
     Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
     Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

     q1.add(q2);

     assertEquals(10.0, q1.getValue());
 }

 @Test
 void testImmutability_Subtract() {
     Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
     Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

     q1.subtract(q2);

     assertEquals(10.0, q1.getValue());
 }

 /* =========================================================
    ROUNDING TESTS
    ========================================================= */

 @Test
 void testRounding_Addition() {
     Quantity<LengthUnit> result =
             new Quantity<>(1.234, LengthUnit.FEET)
                     .add(new Quantity<>(1.234, LengthUnit.FEET));

     assertEquals(2.47, result.getValue());
 }

 @Test
 void testRounding_Subtraction() {
     Quantity<LengthUnit> result =
             new Quantity<>(1.235, LengthUnit.FEET)
                     .subtract(new Quantity<>(0.234, LengthUnit.FEET));

     assertEquals(1.0, result.getValue());
 }

 /* =========================================================
    CHAIN OPERATIONS
    ========================================================= */

 @Test
 void testChainedOperations() {
     Quantity<LengthUnit> result =
             new Quantity<>(10.0, LengthUnit.FEET)
                     .subtract(new Quantity<>(2.0, LengthUnit.FEET))
                     .subtract(new Quantity<>(1.0, LengthUnit.FEET));

     assertEquals(7.0, result.getValue());
 }

 @Test
 void testAddSubtractInverse() {
     Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
     Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);

     Quantity<LengthUnit> result = a.add(b).subtract(b);

     assertTrue(a.equals(result));
 }

 /* =========================================================
    MULTI CATEGORY TEST
    ========================================================= */

 @Test
 void testOperations_AllCategories() {
     // Length
     assertEquals(2.0,
             new Quantity<>(1.0, LengthUnit.FEET)
                     .add(new Quantity<>(12.0, LengthUnit.INCHES)).getValue());

     // Weight
     assertEquals(2.0,
             new Quantity<>(1.0, WeightUnit.KILOGRAM)
                     .add(new Quantity<>(1000.0, WeightUnit.GRAM)).getValue());

     // Volume
     assertEquals(2.0,
             new Quantity<>(1.0, VolumeUnit.LITRE)
                     .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)).getValue());
 }
}
