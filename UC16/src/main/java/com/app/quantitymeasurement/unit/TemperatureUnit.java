package com.app.quantitymeasurement.unit;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {
    CELSIUS(0),
    FAHRENHEIT(1),
    KELVIN(2);

    private final Function<Double, Double> conversionValue;

    TemperatureUnit(int type) {
        if (type == 1) this.conversionValue = f -> (f - 32) * 5 / 9;
        else if (type == 2) this.conversionValue = k -> k - 273.15;
        else this.conversionValue = c -> c;
    }

    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return conversionValue.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        if (this == CELSIUS) return baseValue;
        if (this == FAHRENHEIT) return (baseValue * 9 / 5) + 32;
        if (this == KELVIN) return baseValue + 273.15;
        throw new IllegalStateException("Unsupported unit");
    }

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public boolean supportsArithmetic() {
        return false;
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException(name() + " does not support " + operation + " operations.");
    }

    @Override
    public String getMeasurementType() {
        return "TEMPERATURE";
    }

    @Override
    public IMeasurable getUnitInstance(String unitName) {
        return TemperatureUnit.valueOf(unitName);
    }

    @Override
    public double fromBase(double resultBase) {
        return convertFromBaseUnit(resultBase);
    }

    @Override
    public double toBase(double value) {
        return convertToBaseUnit(value);
    }

    @Override
    public String toString() {
        return name();
    }
}
