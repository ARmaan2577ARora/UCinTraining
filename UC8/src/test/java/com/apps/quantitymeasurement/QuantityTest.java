package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityTest {
    @Test
    void testConvertToBaseUnit_FeetToInches() {
        assertEquals(12.0,
                LengthUnit.FEET.convertToBaseUnit(1.0),
                0.0001);
    }

    @Test
    void testConvertToBaseUnit_YardsToInches() {
        assertEquals(36.0,
                LengthUnit.YARDS.convertToBaseUnit(1.0),
                0.0001);
    }

    @Test
    void testConvertToBaseUnit_CmToInches() {
        assertEquals(1.0,
                LengthUnit.CENTIMETERS.convertToBaseUnit(2.54),
                0.0001);
    }

    @Test
    void testConvertFromBaseUnit_InchesToFeet() {
        assertEquals(1.0,
                LengthUnit.FEET.convertFromBaseUnit(12.0),
                0.0001);
    }

    @Test
    void testConvertFromBaseUnit_InchesToYards() {
        assertEquals(1.0,
                LengthUnit.YARDS.convertFromBaseUnit(36.0),
                0.0001);
    }

    @Test
    void testConvertFromBaseUnit_InchesToCm() {
        assertEquals(2.54,
                LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0),
                0.0001);
    }

    @Test
    void testEquality_FeetToInches() {
        Quantity q1 = new Quantity(1, LengthUnit.FEET);
        Quantity q2 = new Quantity(12, LengthUnit.INCHES);

        assertEquals(q1, q2);
    }

    @Test
    void testEquality_YardsToFeet() {
        Quantity q1 = new Quantity(1, LengthUnit.YARDS);
        Quantity q2 = new Quantity(3, LengthUnit.FEET);

        assertEquals(q1, q2);
    }

    @Test
    void testEquality_CmToInches() {
        Quantity q1 = new Quantity(2.54, LengthUnit.CENTIMETERS);
        Quantity q2 = new Quantity(1, LengthUnit.INCHES);

        assertEquals(q1, q2);
    }

    @Test
    void testConvertTo() {
        Quantity q = new Quantity(1, LengthUnit.FEET);

        Quantity result = q.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue());
    }

    @Test
    void testAdd_FeetAndInches() {
        Quantity q1 = new Quantity(1, LengthUnit.FEET);     // 12 in
        Quantity q2 = new Quantity(12, LengthUnit.INCHES);  // 12 in

        Quantity result = q1.add(q2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue()); // 24 in → 2 ft
    }

    @Test
    void testAdd_WithTargetUnit() {
        Quantity q1 = new Quantity(1, LengthUnit.FEET);     // 12 in
        Quantity q2 = new Quantity(12, LengthUnit.INCHES);  // 12 in

        Quantity result = q1.add(q2, LengthUnit.YARDS);

        assertEquals(0.67, result.getValue(), 0.01); // 24 in → 0.67 yd
    }
}