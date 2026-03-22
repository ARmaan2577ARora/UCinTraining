package org.apps.quantitymeasurement;

public enum LengthUnitUC12 implements IMeasurableUC12 {

    FEET(1.0, "Feet"),
    INCH(1.0 / 12.0, "Inch");

    private final double factor;
    private final String name;

    LengthUnitUC12(double factor, String name) {
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
