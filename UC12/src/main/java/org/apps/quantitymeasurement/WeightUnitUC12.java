package org.apps.quantitymeasurement;

public enum WeightUnitUC12 implements IMeasurableUC12 {

    KILOGRAM(1.0, "Kilogram"),
    GRAM(0.001, "Gram");

    private final double factor;
    private final String name;

    WeightUnitUC12(double factor, String name) {
        this.factor = factor;
        this.name = name;
    }

    public double getConversionFactor() {
        return factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    public String getUnitName() {
        return name;
    }
}
