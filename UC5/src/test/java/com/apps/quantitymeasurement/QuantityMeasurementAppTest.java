package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void f(){
        assertEquals(9,9);
    }

    @Test
    public void testFeetEquality(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(
                1.0, Length.LengthUnit.FEET,
                1.0, Length.LengthUnit.FEET
        ));
    }

    @Test
    public void testInchesEquality(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(
                1.0, Length.LengthUnit.INCHES,
                1.0, Length.LengthUnit.INCHES
        ));
    }

    @Test
    public void testFeetInchesComparison(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(
                1.0, Length.LengthUnit.FEET,
                12.0, Length.LengthUnit.INCHES
        ));
    }

    @Test
    public void testFeetInequality(){
        assertFalse(QuantityMeasurementApp.demonstrateLengthComparison(
                1.0, Length.LengthUnit.FEET,
                2.0, Length.LengthUnit.FEET
        ));
    }

    @Test
    public void testInchesInequality(){
        assertFalse(QuantityMeasurementApp.demonstrateLengthComparison(
                1.0, Length.LengthUnit.INCHES,
                12.0, Length.LengthUnit.INCHES
        ));
    }

    @Test
    public void testCrossUnitInequality(){
        assertFalse(QuantityMeasurementApp.demonstrateLengthComparison(
                1.0, Length.LengthUnit.FEET,
                10.0, Length.LengthUnit.INCHES
        ));
    }

    @Test
    public void testMultipleFeetComparison(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(
                36.0, Length.LengthUnit.INCHES,
                3.0, Length.LengthUnit.FEET
        ));
    }

    @Test
    public void yardEquals36Inches(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(
                1.0, Length.LengthUnit.YARDS,
                36.0, Length.LengthUnit.INCHES
        ));
    }

    @Test
    public void centimeterEquals39Point3701Inches(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(
                100.0, Length.LengthUnit.CENTIMETERS,
                39.3701, Length.LengthUnit.INCHES
        ));
    }

    @Test
    public void threeFeetEqualsOneYard(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(
                3.0, Length.LengthUnit.FEET,
                1.0, Length.LengthUnit.YARDS
        ));
    }

    @Test
    public void thirtyPoint48CmEqualsOneFoot(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(
                30.48, Length.LengthUnit.CENTIMETERS,
                1.0, Length.LengthUnit.FEET
        ));
    }

    @Test
    public void yardNotEqualToInches(){
        assertFalse(QuantityMeasurementApp.demonstrateLengthComparison(
                1.0, Length.LengthUnit.YARDS,
                12.0, Length.LengthUnit.INCHES
        ));
    }

    @Test
    public void referenceEqualitySameObject(){
        Length l = new Length(12.0, Length.LengthUnit.FEET);
        assertTrue(l.equals(l));
    }

    @Test
    public void equalsReturnsFalseForNull(){
        Length l = new Length(12.0, Length.LengthUnit.FEET);
        assertFalse(l.equals(null));
    }

    @Test
    public void reflexiveSymmetricAndTransitiveProperty(){
        Length l1 = new Length(36.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(3.0, Length.LengthUnit.FEET);
        Length l3 = new Length(1.0, Length.LengthUnit.YARDS);

        // Reflexive
        assertTrue(l1.equals(l1));

        // Symmetric
        assertTrue(l1.equals(l2));
        assertTrue(l2.equals(l1));

        // Transitive
        assertTrue(l1.equals(l3));
        assertTrue(l2.equals(l3));
    }

    @Test
    public void differentValuesSameUnitNotEqual(){
        assertFalse(QuantityMeasurementApp.demonstrateLengthComparison(
                1.0, Length.LengthUnit.INCHES,
                2.0, Length.LengthUnit.INCHES
        ));
    }

    @Test
    public void crossUnitEqualityDemonstrateMethod(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(
                1.0, Length.LengthUnit.YARDS,
                3.0, Length.LengthUnit.FEET
        ));
    }
}