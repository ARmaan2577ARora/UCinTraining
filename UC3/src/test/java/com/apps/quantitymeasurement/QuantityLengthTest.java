package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityLengthTest {

    @Test
    public void testEquality_FeetToFeet_SameValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.Feet);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.Feet);

        assertEquals(q1, q2);
    }

    @Test
    public void testEquality_InchToInch_SameValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.Inch);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.Inch);

        assertEquals(q1, q2);
    }

    @Test
    public void testEquality_InchToFeet_EquivalentValue() {
        QuantityLength q1 = new QuantityLength(12.0, LengthUnit.Inch);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.Feet);

        assertEquals(q1, q2);
    }

    @Test
    public void testEquality_FeetToFeet_DifferentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.Feet);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.Feet);

        assertNotEquals(q1, q2);
    }

    @Test
    public void testEquality_InchToInch_DifferentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.Inch);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.Inch);

        assertNotEquals(q1, q2);
    }

    @Test
    public void testEquality_SameReference() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.Feet);

        assertEquals(q1, q1);
    }

    @Test
    public void testEquality_NullComparison() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.Feet);

        assertNotEquals(null,q1);
    }

    @Test
    public void testEquality_FeetAndInchEquivalent() {
        QuantityLength q1 = new QuantityLength(2.0, LengthUnit.Feet);
        QuantityLength q2 = new QuantityLength(24.0, LengthUnit.Inch);

        assertEquals(q1, q2);
    }
}