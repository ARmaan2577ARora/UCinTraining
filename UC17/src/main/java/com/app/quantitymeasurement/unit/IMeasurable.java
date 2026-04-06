package com.app.quantitymeasurement.unit;

public interface IMeasurable {
    double toBase(double value);
    double fromBase(double baseValue);
    String getUnitName();
    String getMeasurementType();
    void validateOperationSupport(String operation);
    
    default boolean supportsArithmetic() {
        return true;
    }
}

