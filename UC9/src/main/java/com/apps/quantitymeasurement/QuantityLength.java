package com.apps.quantitymeasurement;

import java.util.Objects;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 1e-4;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (Double.isNaN(value) || Double.isInfinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return this.value;
    }

    public LengthUnit getUnit() {
        return this.unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuantityLength that = (QuantityLength) o;
        return Math.abs(this.convertToBaseUnit() - that.convertToBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(convertToBaseUnit());
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double baseValue = convertToBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        return new QuantityLength(round(converted), targetUnit);
    }

    public QuantityLength add(QuantityLength that) {
        return add(that, this.unit);
    }

    public QuantityLength add(QuantityLength that, LengthUnit targetUnit) {
        if (that == null) throw new IllegalArgumentException("Length to add cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double sumInBase = this.convertToBaseUnit() + that.convertToBaseUnit();
        double result = targetUnit.convertFromBaseUnit(sumInBase);
        return new QuantityLength(round(result), targetUnit);
    }

    private double convertToBaseUnit() {
        return this.unit.convertToBaseUnit(this.value);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
