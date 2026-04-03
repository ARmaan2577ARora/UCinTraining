package com.app.quantitymeasurement.unit;

public interface IMeasurable {
    double getConversionFactor();
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    String getUnitName();
    String getMeasurementType();
    IMeasurable getUnitInstance(String unitName);

    default boolean supportsArithmetic() {
        return true;
    }

    default void validateOperationSupport(String operation) {
    }

    double fromBase(double resultBase);

    double toBase(double value);
}
