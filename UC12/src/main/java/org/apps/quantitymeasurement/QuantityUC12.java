package org.apps.quantitymeasurement;

public class QuantityUC12<U extends IMeasurableUC12> {

    private final double value;
    private final U unit;
    private static final double EPSILON = 0.01;

    public QuantityUC12(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof QuantityUC12<?>)) return false;

        QuantityUC12<?> other = (QuantityUC12<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass())) return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }
    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }

    // ---------------- CONVERSION ----------------
    public QuantityUC12<U> convertTo(U targetUnit) {
        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);
        return new QuantityUC12<>(round(converted), targetUnit);
    }

    // ---------------- ADDITION ----------------
    public QuantityUC12<U> add(QuantityUC12<U> other) {
        return add(other, this.unit);
    }

    public QuantityUC12<U> add(QuantityUC12<U> other, U targetUnit) {
        validate(other);

        double baseSum =
                unit.convertToBaseUnit(value) +
                        other.unit.convertToBaseUnit(other.value);

        double result = targetUnit.convertFromBaseUnit(baseSum);
        return new QuantityUC12<>(round(result), targetUnit);
    }

    public QuantityUC12<U> subtract(QuantityUC12<U> other) {
        return subtract(other, this.unit);
    }

    public QuantityUC12<U> subtract(QuantityUC12<U> other, U targetUnit) {
        validate(other);
        if (targetUnit == null) throw new IllegalArgumentException("Target unit null");

        double baseDiff =
                unit.convertToBaseUnit(value) -
                        other.unit.convertToBaseUnit(other.value);

        double result = targetUnit.convertFromBaseUnit(baseDiff);
        return new QuantityUC12<>(round(result), targetUnit);
    }


    public double divide(QuantityUC12<U> other) {
        validate(other);

        double baseOther = other.unit.convertToBaseUnit(other.value);
        if (baseOther == 0) throw new ArithmeticException("Division by zero");

        double baseThis = unit.convertToBaseUnit(value);

        return baseThis / baseOther;
    }

    // ---------------- VALIDATION ----------------
    private void validate(QuantityUC12<U> other) {
        if (other == null) throw new IllegalArgumentException("Null quantity");
        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different categories");
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
