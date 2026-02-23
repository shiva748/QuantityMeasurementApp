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
    
    /* =========================================================
    UC5: CONVERSION TESTS
    ========================================================= */

 @Test
 void testConversion_FeetToInches() {
     Length result = QuantityMeasurementApp
             .demonstrateLengthConversion(1.0, Length.Unit.FEET, Length.Unit.INCHES);

     assertEquals(new Length(12.0, Length.Unit.INCHES), result);
 }

 @Test
 void testConversion_InchesToFeet() {
     Length result = QuantityMeasurementApp
             .demonstrateLengthConversion(24.0, Length.Unit.INCHES, Length.Unit.FEET);

     assertEquals(new Length(2.0, Length.Unit.FEET), result);
 }

 @Test
 void testConversion_YardsToInches() {
     Length result = QuantityMeasurementApp
             .demonstrateLengthConversion(1.0, Length.Unit.YARDS, Length.Unit.INCHES);

     assertEquals(new Length(36.0, Length.Unit.INCHES), result);
 }

 @Test
 void testConversion_InchesToYards() {
     Length result = QuantityMeasurementApp
             .demonstrateLengthConversion(72.0, Length.Unit.INCHES, Length.Unit.YARDS);

     assertEquals(new Length(2.0, Length.Unit.YARDS), result);
 }

 @Test
 void testConversion_CentimetersToInches() {
     Length result = QuantityMeasurementApp
             .demonstrateLengthConversion(2.54, Length.Unit.CENTIMETERS, Length.Unit.INCHES);

     assertEquals(new Length(1.0, Length.Unit.INCHES), result);
 }

 @Test
 void testConversion_FeetToYards() {
     Length result = QuantityMeasurementApp
             .demonstrateLengthConversion(6.0, Length.Unit.FEET, Length.Unit.YARDS);

     assertEquals(new Length(2.0, Length.Unit.YARDS), result);
 }
 
 /* =========================================================
 UC5: Edge Case Tests
 ========================================================= */
 
 @Test
 void testConversion_ZeroValue() {
     Length result = QuantityMeasurementApp
             .demonstrateLengthConversion(0.0, Length.Unit.FEET, Length.Unit.INCHES);

     assertEquals(new Length(0.0, Length.Unit.INCHES), result);
 }

 @Test
 void testConversion_NegativeValue() {
     Length result = QuantityMeasurementApp
             .demonstrateLengthConversion(-1.0, Length.Unit.FEET, Length.Unit.INCHES);

     assertEquals(new Length(-12.0, Length.Unit.INCHES), result);
 }

 @Test
 void testConversion_SameUnit() {
     Length result = QuantityMeasurementApp
             .demonstrateLengthConversion(5.0, Length.Unit.FEET, Length.Unit.FEET);

     assertEquals(new Length(5.0, Length.Unit.FEET), result);
 }
 
 /* =========================================================
 UC5: Round Trip Test
 ========================================================= */
 
 @Test
 void testConversion_RoundTrip_PreservesValue() {
     Length original = new Length(5.0, Length.Unit.FEET);

     Length converted = QuantityMeasurementApp
             .demonstrateLengthConversion(original, Length.Unit.INCHES);

     Length back = QuantityMeasurementApp
             .demonstrateLengthConversion(converted, Length.Unit.FEET);

     assertEquals(original, back);
 }
 
 /* =========================================================
 UC5: Exception Tests
 ========================================================= */
 
 @Test
 void testConversion_InvalidUnit_Throws() {
     assertThrows(IllegalArgumentException.class,
             () -> QuantityMeasurementApp
                     .demonstrateLengthConversion(1.0, null, Length.Unit.FEET));
 }

 @Test
 void testConversion_NaN_Throws() {
     assertThrows(IllegalArgumentException.class,
             () -> QuantityMeasurementApp
                     .demonstrateLengthConversion(Double.NaN, Length.Unit.FEET, Length.Unit.INCHES));
 }

 @Test
 void testConversion_Infinite_Throws() {
     assertThrows(IllegalArgumentException.class,
             () -> QuantityMeasurementApp
                     .demonstrateLengthConversion(Double.POSITIVE_INFINITY, Length.Unit.FEET, Length.Unit.INCHES));
 }
 
 /* =========================================================
 UC6: ADDITION TESTS
 ========================================================= */

@Test
void testAddition_SameUnit_FeetPlusFeet() {
  Length result = QuantityMeasurementApp.demonstrateLengthAddition(
          new Length(1.0, Length.Unit.FEET),
          new Length(2.0, Length.Unit.FEET));

  assertEquals(new Length(3.0, Length.Unit.FEET), result);
}

@Test
void testAddition_SameUnit_InchPlusInch() {
  Length result = QuantityMeasurementApp.demonstrateLengthAddition(
          new Length(6.0, Length.Unit.INCHES),
          new Length(6.0, Length.Unit.INCHES));

  assertEquals(new Length(12.0, Length.Unit.INCHES), result);
}

@Test
void testAddition_CrossUnit_FeetPlusInches() {
  Length result = QuantityMeasurementApp.demonstrateLengthAddition(
          new Length(1.0, Length.Unit.FEET),
          new Length(12.0, Length.Unit.INCHES));

  assertEquals(new Length(2.0, Length.Unit.FEET), result);
}

@Test
void testAddition_CrossUnit_InchPlusFeet() {
  Length result = QuantityMeasurementApp.demonstrateLengthAddition(
          new Length(12.0, Length.Unit.INCHES),
          new Length(1.0, Length.Unit.FEET));

  assertEquals(new Length(24.0, Length.Unit.INCHES), result);
}

@Test
void testAddition_CrossUnit_YardPlusFeet() {
  Length result = QuantityMeasurementApp.demonstrateLengthAddition(
          new Length(1.0, Length.Unit.YARDS),
          new Length(3.0, Length.Unit.FEET));

  assertEquals(new Length(2.0, Length.Unit.YARDS), result);
}

@Test
void testAddition_CrossUnit_CentimeterPlusInch() {
  Length result = QuantityMeasurementApp.demonstrateLengthAddition(
          new Length(2.54, Length.Unit.CENTIMETERS),
          new Length(1.0, Length.Unit.INCHES));

  assertEquals(new Length(5.08, Length.Unit.CENTIMETERS), result);
}

/* =========================================================
 UC6: MATHEMATICAL PROPERTIES
 ========================================================= */

@Test
void testAddition_Commutativity() {
  Length l1 = new Length(1.0, Length.Unit.FEET);
  Length l2 = new Length(12.0, Length.Unit.INCHES);

  Length result1 = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);
  Length result2 = QuantityMeasurementApp.demonstrateLengthAddition(l2, l1);

  assertTrue(result1.equals(result2));
}

@Test
void testAddition_WithZero() {
  Length result = QuantityMeasurementApp.demonstrateLengthAddition(
          new Length(5.0, Length.Unit.FEET),
          new Length(0.0, Length.Unit.INCHES));

  assertEquals(new Length(5.0, Length.Unit.FEET), result);
}

@Test
void testAddition_NegativeValues() {
  Length result = QuantityMeasurementApp.demonstrateLengthAddition(
          new Length(5.0, Length.Unit.FEET),
          new Length(-2.0, Length.Unit.FEET));

  assertEquals(new Length(3.0, Length.Unit.FEET), result);
}

/* =========================================================
 UC6: EDGE CASES
 ========================================================= */

@Test
void testAddition_NullSecondOperand() {
  assertThrows(IllegalArgumentException.class,
          () -> QuantityMeasurementApp.demonstrateLengthAddition(
                  new Length(1.0, Length.Unit.FEET), null));
}

@Test
void testAddition_LargeValues() {
  Length result = QuantityMeasurementApp.demonstrateLengthAddition(
          new Length(1e6, Length.Unit.FEET),
          new Length(1e6, Length.Unit.FEET));

  assertEquals(new Length(2e6, Length.Unit.FEET), result);
}

/* =========================================================
UC7: ADDITION WITH EXPLICIT TARGET UNIT
========================================================= */

@Test
void testAddition_ExplicitTargetUnit_Feet() {
 Length result = QuantityMeasurementApp.demonstrateLengthAddition(
         new Length(1.0, Length.Unit.FEET),
         new Length(12.0, Length.Unit.INCHES),
         Length.Unit.FEET);

 assertEquals(new Length(2.0, Length.Unit.FEET), result);
}

@Test
void testAddition_ExplicitTargetUnit_Inches() {
 Length result = QuantityMeasurementApp.demonstrateLengthAddition(
         new Length(1.0, Length.Unit.FEET),
         new Length(12.0, Length.Unit.INCHES),
         Length.Unit.INCHES);

 assertEquals(new Length(24.0, Length.Unit.INCHES), result);
}

@Test
void testAddition_ExplicitTargetUnit_Yards() {
 Length result = QuantityMeasurementApp.demonstrateLengthAddition(
         new Length(1.0, Length.Unit.FEET),
         new Length(12.0, Length.Unit.INCHES),
         Length.Unit.YARDS);

 // 2 feet = 0.666... yards
 Length expected = new Length(0.67, Length.Unit.YARDS);
 assertTrue(result.equals(expected));
}

@Test
void testAddition_ExplicitTargetUnit_Centimeters() {
 Length result = QuantityMeasurementApp.demonstrateLengthAddition(
         new Length(1.0, Length.Unit.INCHES),
         new Length(1.0, Length.Unit.INCHES),
         Length.Unit.CENTIMETERS);

 Length expected = new Length(5.08, Length.Unit.CENTIMETERS);
 assertTrue(result.equals(expected));
}

@Test
void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
 Length result = QuantityMeasurementApp.demonstrateLengthAddition(
         new Length(2.0, Length.Unit.YARDS),
         new Length(3.0, Length.Unit.FEET),
         Length.Unit.YARDS);

 assertEquals(new Length(3.0, Length.Unit.YARDS), result);
}

@Test
void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
 Length result = QuantityMeasurementApp.demonstrateLengthAddition(
         new Length(2.0, Length.Unit.YARDS),
         new Length(3.0, Length.Unit.FEET),
         Length.Unit.FEET);

 assertEquals(new Length(9.0, Length.Unit.FEET), result);
}

@Test
void testAddition_ExplicitTargetUnit_Commutativity() {
 Length result1 = QuantityMeasurementApp.demonstrateLengthAddition(
         new Length(1.0, Length.Unit.FEET),
         new Length(12.0, Length.Unit.INCHES),
         Length.Unit.YARDS);

 Length result2 = QuantityMeasurementApp.demonstrateLengthAddition(
         new Length(12.0, Length.Unit.INCHES),
         new Length(1.0, Length.Unit.FEET),
         Length.Unit.YARDS);

 assertEquals(result1, result2);
}

@Test
void testAddition_ExplicitTargetUnit_WithZero() {
 Length result = QuantityMeasurementApp.demonstrateLengthAddition(
         new Length(5.0, Length.Unit.FEET),
         new Length(0.0, Length.Unit.INCHES),
         Length.Unit.YARDS);

 Length expected = new Length(1.67, Length.Unit.YARDS);
 assertTrue(result.equals(expected));
}

@Test
void testAddition_ExplicitTargetUnit_NegativeValues() {
 Length result = QuantityMeasurementApp.demonstrateLengthAddition(
         new Length(5.0, Length.Unit.FEET),
         new Length(-2.0, Length.Unit.FEET),
         Length.Unit.INCHES);

 assertEquals(new Length(36.0, Length.Unit.INCHES), result);
}

@Test
void testAddition_ExplicitTargetUnit_NullTargetUnit() {
 assertThrows(IllegalArgumentException.class,
         () -> QuantityMeasurementApp.demonstrateLengthAddition(
                 new Length(1.0, Length.Unit.FEET),
                 new Length(12.0, Length.Unit.INCHES),
                 null));
}

@Test
void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
 Length result = QuantityMeasurementApp.demonstrateLengthAddition(
         new Length(1000.0, Length.Unit.FEET),
         new Length(500.0, Length.Unit.FEET),
         Length.Unit.INCHES);

 assertEquals(new Length(18000.0, Length.Unit.INCHES), result);
}

@Test
void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
 Length result = QuantityMeasurementApp.demonstrateLengthAddition(
         new Length(12.0, Length.Unit.INCHES),
         new Length(12.0, Length.Unit.INCHES),
         Length.Unit.YARDS);

 Length expected = new Length(0.67, Length.Unit.YARDS);
 assertTrue(result.equals(expected));
}
 
}
