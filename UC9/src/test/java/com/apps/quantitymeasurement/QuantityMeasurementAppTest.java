package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // -------------------- LENGTH TESTS --------------------

    @Test
    public void threeFeetEqualsOneYard() {
        QuantityLength l1 = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(1.0, LengthUnit.YARDS);

        assertEquals(l1, l2);
    }

    @Test
    public void thirtyPoint48CmEqualsOneFoot() {
        QuantityLength l1 = new QuantityLength(30.48, LengthUnit.CENTIMETERS);
        QuantityLength l2 = new QuantityLength(1.0, LengthUnit.FEET);

        assertEquals(l1, l2);
    }

    @Test
    public void yardNotEqualToInches() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength l2 = new QuantityLength(24.0, LengthUnit.INCHES);

        assertNotEquals(l1, l2);
    }

    @Test
    public void referenceEqualitySameObject() {
        QuantityLength l = new QuantityLength(1.0, LengthUnit.FEET);

        assertEquals(l, l);
    }

    @Test
    public void equalsReturnsFalseForNull() {
        QuantityLength l = new QuantityLength(1.0, LengthUnit.FEET);

        assertNotEquals(l, null);
    }

    @Test
    public void reflexiveSymmetricAndTransitiveProperty() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength b = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength c = new QuantityLength(36.0, LengthUnit.INCHES);

        // Reflexive
        assertEquals(a, a);

        // Symmetric
        assertEquals(a, b);
        assertEquals(b, a);

        // Transitive
        assertEquals(b, c);
        assertEquals(a, c);
    }

    @Test
    public void differentValuesSameUnitNotEqual() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(2.0, LengthUnit.FEET);

        assertNotEquals(l1, l2);
    }

    @Test
    public void crossUnitEqualityDemonstrateMethod() {
        assertTrue(
                QuantityMeasurementApp.demonstrateLengthEquality(
                        new QuantityLength(1.0, LengthUnit.YARDS),
                        new QuantityLength(3.0, LengthUnit.FEET)
                )
        );
    }

    @Test
    public void convertFeetToInches() {
        QuantityLength result = QuantityMeasurementApp
                .demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);

        assertEquals(new QuantityLength(12.0, LengthUnit.INCHES), result);
    }

    @Test
    public void convertYardsToInchesUsingOverloadedMethod() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength result = QuantityMeasurementApp
                .demonstrateLengthConversion(yard, LengthUnit.INCHES);

        assertEquals(new QuantityLength(36.0, LengthUnit.INCHES), result);
    }

    @Test
    public void addFeetAndInches() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = QuantityMeasurementApp
                .demonstrateLengthAddition(l1, l2);

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    public void addFeetAndInchesWithTargetUnitInches() {
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = QuantityMeasurementApp
                .demonstrateLengthAddition(l1, l2, LengthUnit.INCHES);

        assertEquals(new QuantityLength(24.0, LengthUnit.INCHES), result);
    }

    // -------------------- WEIGHT TESTS (UC9) --------------------

    @Test
    public void testEquality_KilogramToGram() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_PoundToGram() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.POUND);
        QuantityWeight w2 = new QuantityWeight(453.592, WeightUnit.GRAM);

        assertEquals(w1, w2);
    }

    @Test
    public void testWeightConversion_KgToGram() {
        QuantityWeight result = QuantityMeasurementApp
                .demonstrateWeightConversion(1.0, WeightUnit.KILOGRAM, WeightUnit.GRAM);

        assertEquals(new QuantityWeight(1000.0, WeightUnit.GRAM), result);
    }

    @Test
    public void testWeightConversion_PoundToKg() {
        QuantityWeight result = QuantityMeasurementApp
                .demonstrateWeightConversion(2.20462, WeightUnit.POUND, WeightUnit.KILOGRAM);

        assertEquals(new QuantityWeight(1.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    public void testAddition_KgPlusGram() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = QuantityMeasurementApp
                .demonstrateWeightAddition(w1, w2);

        assertEquals(new QuantityWeight(2.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    public void testAddition_WithTargetUnit() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = QuantityMeasurementApp
                .demonstrateWeightAddition(w1, w2, WeightUnit.GRAM);

        assertEquals(new QuantityWeight(2000.0, WeightUnit.GRAM), result);
    }

    @Test
    public void testWeightComparisonMethod() {
        assertTrue(
                QuantityMeasurementApp.demonstrateWeightComparison(
                        1.0, WeightUnit.KILOGRAM,
                        1000.0, WeightUnit.GRAM
                )
        );
    }

    @Test
    public void testWeightNotEqual() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(2.0, WeightUnit.KILOGRAM);

        assertNotEquals(w1, w2);
    }

    @Test
    public void testWeightVsLength_Incompatible() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityLength length = new QuantityLength(1.0, LengthUnit.FEET);

        assertNotEquals(weight, length);
    }

    @Test
    public void testWeightEquality_ReflexiveSymmetricTransitive() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);
        QuantityWeight c = new QuantityWeight(2.20462, WeightUnit.POUND); // Approx

        assertEquals(a, a);

        assertEquals(a, b);
        assertEquals(b, a);

        assertEquals(b, c);
        assertEquals(a, c);
    }

    @Test
    public void testNullWeightThrowsExceptionInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityWeight(1.0, null));
    }

    @Test
    public void testInvalidValueThrowsExceptionInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM));
    }

    @Test
    public void testRoundTripConversion() {
        QuantityWeight original = new QuantityWeight(1.5, WeightUnit.KILOGRAM);
        QuantityWeight result = original.convertTo(WeightUnit.GRAM).convertTo(WeightUnit.KILOGRAM);

        assertEquals(original, result);
    }

    @Test
    public void testAddition_WithZero() {
        QuantityWeight w1 = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(0.0, WeightUnit.GRAM);

        assertEquals(w1, w1.add(w2));
    }

    @Test
    public void testAddition_NegativeValues() {
        QuantityWeight w1 = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(-2000.0, WeightUnit.GRAM); // -2kg

        assertEquals(new QuantityWeight(3.0, WeightUnit.KILOGRAM), w1.add(w2));
    }

    @Test
    public void testAddition_MixedUnitsExplicitTarget() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.POUND);
        QuantityWeight w2 = new QuantityWeight(453.592, WeightUnit.GRAM);

        QuantityWeight result = w1.add(w2, WeightUnit.POUND);
        assertEquals(2.0, result.getValue(), 0.01);
        assertEquals(WeightUnit.POUND, result.getUnit());
    }
}
