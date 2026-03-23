package org.apps.quantitymeasurement;

public enum VolumeUnitUC13 implements IMeasurableUC13 {
    LITRE(1.0),
    MILLILITRE(1.0 / 1000.0),
    GALLON(3.78541);

    private final double toLitre;

    VolumeUnitUC13(double toLitre) {
        this.toLitre = toLitre;
    }

    public double toBaseUnit(double value) {
        return value * toLitre;
    }

    public double fromBaseUnit(double baseValue) {
        return baseValue / toLitre;
    }
}