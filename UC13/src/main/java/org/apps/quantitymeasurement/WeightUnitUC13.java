package org.apps.quantitymeasurement;

public enum WeightUnitUC13 implements IMeasurableUC13 {
    KILOGRAM(1.0),
    GRAM(1.0 / 1000.0);

    private final double toKg;

    WeightUnitUC13(double toKg) {
        this.toKg = toKg;
    }

    public double toBaseUnit(double value) {
        return value * toKg;
    }

    public double fromBaseUnit(double baseValue) {
        return baseValue / toKg;
    }
}