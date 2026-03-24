import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    // Temperature Equality Tests
    @Test
    public void testTemperatureEquality_CelsiusToCelsius_SameValue() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testTemperatureEquality_FahrenheitToFahrenheit_SameValue() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> q2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testTemperatureEquality_CelsiusToFahrenheit_0Celsius32Fahrenheit() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testTemperatureEquality_CelsiusToFahrenheit_100Celsius212Fahrenheit() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testTemperatureEquality_CelsiusToFahrenheit_Negative40Equal() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(-40.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testTemperatureEquality_CelsiusToKelvin_0Celsius273_15Kelvin() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(273.15, TemperatureUnit.KELVIN);
        assertTrue(q1.equals(q2));
    }

    // Temperature Conversion Tests
    @Test
    public void testTemperatureConversion_CelsiusToFahrenheit() {
        Quantity<TemperatureUnit> q = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> converted = q.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(122.0, converted.getValue(), 0.001);
    }

    @Test
    public void testTemperatureConversion_FahrenheitToCelsius() {
        Quantity<TemperatureUnit> q = new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> converted = q.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(100.0, converted.getValue(), 0.001);
    }

    // Unsupported Operations Tests
    @Test(expected = UnsupportedOperationException.class)
    public void testTemperatureUnsupportedOperation_Add() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        q1.add(q2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testTemperatureUnsupportedOperation_Subtract() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        q1.subtract(q2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testTemperatureUnsupportedOperation_Divide() {
        Quantity<TemperatureUnit> q1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> q2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        q1.divide(q2);
    }

    // Cross-Category Prevention Tests
    @Test
    public void testTemperatureVsLengthIncompatibility() {
        Quantity<TemperatureUnit> temp = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<LengthUnit> length = new Quantity<>(100.0, LengthUnit.FEET);
        assertFalse(temp.equals(length));
    }

    // Existing Functionality Tests (Backward Compatibility)
    @Test
    public void testLengthEquality_FeetToInch() {
        Quantity<LengthUnit> oneFeet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> twelveInch = new Quantity<>(12.0, LengthUnit.INCH);
        assertTrue(oneFeet.equals(twelveInch));
    }

    @Test
    public void testLengthAddition_FeetAndInch() {
        Quantity<LengthUnit> oneFeet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> twelveInch = new Quantity<>(12.0, LengthUnit.INCH);
        Quantity<LengthUnit> sum = oneFeet.add(twelveInch);
        assertEquals(2.0, sum.getValue(), 0.001);
        assertEquals(LengthUnit.FEET, sum.getUnit());
    }
}
