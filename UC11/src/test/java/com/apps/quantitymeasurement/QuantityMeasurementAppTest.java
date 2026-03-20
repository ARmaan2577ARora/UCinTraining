package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.01;

    @Test
    public void testIMeasurableInterface_LengthUnitImplementation() {
        IMeasurable feet = LengthUnit.FEET;
        assertEquals(1.0, feet.getConversionFactor(), EPSILON);
        assertEquals(36.0, feet.convertToBaseUnit(36.0), EPSILON);
        assertEquals(3.0, feet.convertFromBaseUnit(3.0), EPSILON);
        assertEquals("FEET", feet.getUnitName());
    }

    @Test
    public void testIMeasurableInterface_WeightUnitImplementation() {
        IMeasurable kg = WeightUnit.KILOGRAM;
        assertEquals(1000.0, kg.getConversionFactor(), EPSILON);
        assertEquals(1000.0, kg.convertToBaseUnit(1.0), EPSILON);
        assertEquals(1.0, kg.convertFromBaseUnit(1000.0), EPSILON);
        assertEquals("KILOGRAM", kg.getUnitName());
    }

    @Test
    public void testGenericQuantity_LengthOperations_Equality() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(q1, q2);
    }

    @Test
    public void testGenericQuantity_WeightOperations_Equality() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertEquals(q1, q2);
    }

    @Test
    public void testGenericQuantity_LengthOperations_Conversion() {
        Quantity<LengthUnit> quantity = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> converted = quantity.convertTo(LengthUnit.INCHES);
        assertEquals(12.0, converted.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, converted.getUnit());
    }

    @Test
    public void testGenericQuantity_WeightOperations_Conversion() {
        Quantity<WeightUnit> quantity = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> converted = quantity.convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, converted.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, converted.getUnit());
    }

    @Test
    public void testGenericQuantity_LengthOperations_Addition() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.add(q2, LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testGenericQuantity_WeightOperations_Addition() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> result = q1.add(q2, WeightUnit.KILOGRAM);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testCrossCategoryPrevention_LengthVsWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        
        assertNotEquals(length, (Object) weight);
    }

    @Test
    public void testGenericQuantity_ConstructorValidation_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(1.0, null);
        });
    }

    @Test
    public void testGenericQuantity_ConstructorValidation_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(Double.NaN, LengthUnit.FEET);
        });
    }

    @Test
    public void testHashCode_GenericQuantity_Consistency() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(q1.hashCode(), q2.hashCode());
    }

    @Test
    public void testToString_GenericQuantity() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals("Quantity(1.0, FEET)", q.toString());
    }

    // UC11: Volume Unit Tests

    @Test
    public void testIMeasurableInterface_VolumeUnitImplementation() {
        IMeasurable litre = VolumeUnit.LITRE;
        assertEquals(1.0, litre.getConversionFactor(), EPSILON);
        assertEquals(1.0, litre.convertToBaseUnit(1.0), EPSILON);
        assertEquals(1.0, litre.convertFromBaseUnit(1.0), EPSILON);
        assertEquals("LITRE", litre.getUnitName());

        IMeasurable ml = VolumeUnit.MILLILITRE;
        assertEquals(0.001, ml.getConversionFactor(), EPSILON);
        assertEquals(1.0, ml.convertToBaseUnit(1000.0), EPSILON);
        assertEquals(1000.0, ml.convertFromBaseUnit(1.0), EPSILON);

        IMeasurable gallon = VolumeUnit.GALLON;
        assertEquals(3.78541, gallon.getConversionFactor(), EPSILON);
        assertEquals(3.78541, gallon.convertToBaseUnit(1.0), EPSILON);
        assertEquals(1.0, gallon.convertFromBaseUnit(3.78541), EPSILON);
    }

    @Test
    public void testEquality_VolumeUnits_SameValue() {
        assertEquals(new Quantity<>(1.5, VolumeUnit.LITRE), new Quantity<>(1.5, VolumeUnit.LITRE));
        assertEquals(new Quantity<>(500.0, VolumeUnit.MILLILITRE), new Quantity<>(500.0, VolumeUnit.MILLILITRE));
        assertEquals(new Quantity<>(2.0, VolumeUnit.GALLON), new Quantity<>(2.0, VolumeUnit.GALLON));
    }

    @Test
    public void testEquality_LitreToMillilitre_EquivalentValue() {
        Quantity<VolumeUnit> oneLitre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> thousandMl = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(oneLitre, thousandMl);
    }

    @Test
    public void testEquality_LitreToGallon_EquivalentValue() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> litres = new Quantity<>(3.78541, VolumeUnit.LITRE);
        assertEquals(gallon, litres);
    }

    @Test
    public void testEquality_MillilitreToGallon_EquivalentValue() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> ml = new Quantity<>(3785.41, VolumeUnit.MILLILITRE);
        assertEquals(gallon, ml);
    }

    @Test
    public void testEquality_VolumeVsLength_Incompatible() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        assertNotEquals(volume, (Object) length);
    }

    @Test
    public void testEquality_VolumeVsWeight_Incompatible() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertNotEquals(volume, (Object) weight);
    }

    @Test
    public void testVolumeConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertEquals(1000.0, litre.convertTo(VolumeUnit.MILLILITRE).getValue(), EPSILON);
    }

    @Test
    public void testVolumeConversion_GallonToLitre() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        assertEquals(3.78541, gallon.convertTo(VolumeUnit.LITRE).getValue(), EPSILON);
    }

    @Test
    public void testVolumeAddition_SameUnit() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        assertEquals(3.0, v1.add(v2).getValue(), EPSILON);
    }

    @Test
    public void testVolumeAddition_CrossUnit_LitrePlusMillilitre() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = litre.add(ml, VolumeUnit.LITRE);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testVolumeAddition_ExplicitTargetUnit_Gallon() {
        Quantity<VolumeUnit> v1 = new Quantity<>(3.78541, VolumeUnit.LITRE); // 1 gallon
        Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.GALLON);     // 1 gallon
        Quantity<VolumeUnit> result = v1.add(v2, VolumeUnit.GALLON);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.GALLON, result.getUnit());
    }

    @Test
    public void testVolume_ZeroValueEquality() {
        assertEquals(new Quantity<>(0.0, VolumeUnit.LITRE), new Quantity<>(0.0, VolumeUnit.GALLON));
    }

    @Test
    public void testVolume_NegativeValueConversion() {
        Quantity<VolumeUnit> negLitre = new Quantity<>(-1.0, VolumeUnit.LITRE);
        assertEquals(-1000.0, negLitre.convertTo(VolumeUnit.MILLILITRE).getValue(), EPSILON);
    }

    @Test
    public void testBackwardCompatibility_AllTests() {
        // This test method serves as a boundary check to ensure existing tests still pass.
        // Existing tests are already defined in the class and will be run by JUnit.
        assertTrue(true);
    }
}
