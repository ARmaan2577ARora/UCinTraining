package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    @Test
    public void testFeetEquality(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(
                        new Length(1.0,Length.LengthUnit.FEET),
                new Length(1.0,Length.LengthUnit.FEET)
        ));
    }

    @Test
    public void testInchesEquality(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(
                new Length(10.0, Length.LengthUnit.INCHES),
                new Length(10.0, Length.LengthUnit.INCHES)
        ));
    }

    @Test
    public void testFeetInchesEquality(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(12.0, Length.LengthUnit.INCHES)
        ));
    }

    @Test
    public void testFeetInequality(){
        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(2.0, Length.LengthUnit.FEET)
        ));
    }

    @Test
    public void testInchesInequality(){
        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(
                new Length(10.0, Length.LengthUnit.INCHES),
                new Length(12.0, Length.LengthUnit.INCHES)
        ));
    }

    @Test
    public void testCrossUnitEquality(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(
                new Length(3.0, Length.LengthUnit.FEET),
                new Length(1.0, Length.LengthUnit.YARDS)
        ));
    }

    @Test
    public void testInchesCrossUnitInequality(){
        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(
                new Length(10.0, Length.LengthUnit.INCHES),
                new Length(1.0, Length.LengthUnit.FEET)
        ));
    }

    @Test
    public void testMultipleFeetComparison(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(
                new Length(36.0, Length.LengthUnit.INCHES),
                new Length(3.0, Length.LengthUnit.FEET)
        ));
    }

    @Test
    public void yardEquals36Inches(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(
                new Length(1.0, Length.LengthUnit.YARDS),
                new Length(36.0, Length.LengthUnit.INCHES)
        ));
    }

    @Test
    public void centimeterEquals39Point3701Inches(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(
                new Length(100.0, Length.LengthUnit.CENTIMETERS),
                new Length(39.3701, Length.LengthUnit.INCHES)
        ));
    }

    @Test
    public void threeFeetEqualsOneYard(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(
                new Length(3.0, Length.LengthUnit.FEET),
                new Length(1.0, Length.LengthUnit.YARDS)
        ));
    }

    @Test
    public void thirtyPoint48CmEqualsOneFoot(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(
                new Length(30.48, Length.LengthUnit.CENTIMETERS),
                new Length(1.0, Length.LengthUnit.FEET)
        ));
    }

    @Test
    public void yardNotEqualToInches(){
        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(
                new Length(1.0, Length.LengthUnit.YARDS),
                new Length(12.0, Length.LengthUnit.INCHES)
        ));
    }

    @Test
    public void referenceEqualitySameObject(){
        Length l = new Length(12.0, Length.LengthUnit.FEET);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l, l));
    }

    @Test
    public void equalsReturnsFalseForNull(){
        Length l = new Length(12.0, Length.LengthUnit.FEET);
        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(l, null));
    }

    @Test
    public void reflexiveSymmetricAndTransitiveSymmetric(){
        Length l1 = new Length(36.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(3.0, Length.LengthUnit.FEET);
        Length l3 = new Length(1.0, Length.LengthUnit.YARDS);

        // reflexive
        assertTrue(l1.equals(l1));

        // symmetric
        assertTrue(l1.equals(l2));
        assertTrue(l2.equals(l1));

        // transitive
        assertTrue(l1.equals(l2));
        assertTrue(l2.equals(l3));
        assertTrue(l1.equals(l3));
    }

    @Test
    public void differentValuesSameUnitNotEqual(){
        assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(
                new Length(1.0, Length.LengthUnit.INCHES),
                new Length(2.0, Length.LengthUnit.INCHES)
        ));
    }

    @Test
    public void crossUnitEqualityDemonstrateMethod(){
        assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(
                1.0, Length.LengthUnit.YARDS,
                3.0, Length.LengthUnit.FEET
        ));
    }

    @Test
    public void convertFeetToInches(){
        Length result = QuantityMeasurementApp.demonstrateLengthConversion(
                new Length(1.0, Length.LengthUnit.FEET),
                Length.LengthUnit.INCHES
        );
        Length expected = new Length(12.0, Length.LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(result, expected));
    }

    @Test
    public void convertYardsToInchesUsingOverloadedMethod(){
        Length result = QuantityMeasurementApp.demonstrateLengthConversion(
                new Length(1.0, Length.LengthUnit.YARDS),
                Length.LengthUnit.INCHES
        );
        Length expected = new Length(36.0, Length.LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(result, expected));
    }
    @Test
    public void addFeetAndInches(){
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        Length sum = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);
        Length expected = new Length(2.0, Length.LengthUnit.FEET);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(sum, expected));
    }
}