package org.apps.quantitymeasurement;

public enum VolumeUnitUC12 implements IMeasurableUC12 {

    LITRE(1.0, "Litre"),
    MILLILITRE(0.001, "Millilitre"),
    GALLON(3.78541, "Gallon");

    private final double factor;
    private final String name;

    VolumeUnitUC12(double factor, String name) {
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
