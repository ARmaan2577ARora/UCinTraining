package com.apps.quantitymeasurement;

import java.util.Objects;

public class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    private static final double EPSILON = 1e-4;

    public QuantityWeight(double value, WeightUnit unit) {
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

    public WeightUnit getUnit() {
        return this.unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuantityWeight that = (QuantityWeight) o;
        return Math.abs(this.convertToBaseUnit() - that.convertToBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(convertToBaseUnit());
    }

    public QuantityWeight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double baseValue = convertToBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        return new QuantityWeight(round(converted), targetUnit);
    }

    public QuantityWeight add(QuantityWeight that) {
        return add(that, this.unit);
    }

    public QuantityWeight add(QuantityWeight that, WeightUnit targetUnit) {
        if (that == null) throw new IllegalArgumentException("Weight to add cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double sumInBase = this.convertToBaseUnit() + that.convertToBaseUnit();
        double result = targetUnit.convertFromBaseUnit(sumInBase);
        return new QuantityWeight(round(result), targetUnit);
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
