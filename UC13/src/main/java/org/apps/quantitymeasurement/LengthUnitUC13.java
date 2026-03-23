package org.apps.quantitymeasurement;

public enum LengthUnitUC13 implements IMeasurableUC13 {
    FEET(1.0),
    INCHES(1.0 / 12.0);

    private final double toFeet;

    LengthUnitUC13(double toFeet) {
        this.toFeet = toFeet;
    }

    public double toBaseUnit(double value) {
        return value * toFeet;
    }

    public double fromBaseUnit(double baseValue) {
        return baseValue / toFeet;
    }
}