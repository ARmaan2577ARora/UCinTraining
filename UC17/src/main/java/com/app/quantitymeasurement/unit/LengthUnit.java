package com.app.quantitymeasurement.unit;

public enum LengthUnit implements IMeasurable {
    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    METERS(39.3701),
    CENTIMETERS(0.393701);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double toBase(double value) {
        return value * conversionFactor;
    }

    @Override
    public double fromBase(double baseValue) {
        return baseValue / conversionFactor;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }

    @Override
    public String getMeasurementType() {
        return "LengthUnit";
    }

    @Override
    public void validateOperationSupport(String operation) {
        // All length operations supported
    }
}

