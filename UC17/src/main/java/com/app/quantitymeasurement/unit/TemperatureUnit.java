package com.app.quantitymeasurement.unit;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {
    CELSIUS(0),
    FAHRENHEIT(1),
    KELVIN(2);

    private final Function<Double, Double> conversionToCelsius;

    TemperatureUnit(int type) {
        if (type == 1) this.conversionToCelsius = f -> (f - 32) * 5 / 9;
        else if (type == 2) this.conversionToCelsius = k -> k - 273.15;
        else this.conversionToCelsius = c -> c;
    }

    @Override
    public double toBase(double value) {
        return conversionToCelsius.apply(value);
    }

    @Override
    public double fromBase(double baseValue) {
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
        return "TemperatureUnit";
    }
}

