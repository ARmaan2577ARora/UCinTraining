package org.apps.quantitymeasurement;

public interface IMeasurableUC12 {
    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();
}
