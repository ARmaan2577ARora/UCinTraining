package org.apps.quantitymeasurement;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public class QuantityUC13<U extends IMeasurableUC13> {

    private final double value;
    private final U unit;

    public QuantityUC13(double value, U unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // ================= ENUM =================
    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        public double compute(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }

    // ================= VALIDATION =================
    private void validateArithmeticOperands(QuantityUC13<U> other, U targetUnit, boolean checkTarget) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Different measurement categories");
        }

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        if (checkTarget && targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
    }

    // ================= CORE HELPER =================
    private double performBaseArithmetic(QuantityUC13<U> other, ArithmeticOperation op) {
        double base1 = unit.toBaseUnit(value);
        double base2 = other.unit.toBaseUnit(other.value);
        return op.compute(base1, base2);
    }

    // ================= ADD =================
    public QuantityUC13<U> add(QuantityUC13<U> other) {
        return add(other, unit);
    }

    public QuantityUC13<U> add(QuantityUC13<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double result = targetUnit.fromBaseUnit(baseResult);

        return new QuantityUC13<>(round(result), targetUnit);
    }

    // ================= SUBTRACT =================
    public QuantityUC13<U> subtract(QuantityUC13<U> other) {
        return subtract(other, unit);
    }

    public QuantityUC13<U> subtract(QuantityUC13<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double result = targetUnit.fromBaseUnit(baseResult);

        return new QuantityUC13<>(round(result), targetUnit);
    }

    // ================= DIVIDE =================
    public double divide(QuantityUC13<U> other) {
        validateArithmeticOperands(other, null, false);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    // ================= ROUND =================
    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    // ================= toString =================
    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }

    // ================= equals =================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuantityUC13<?> other)) return false;

        double base1 = unit.toBaseUnit(value);
        double base2 = other.unit.toBaseUnit(other.value);

        return Math.abs(base1 - base2) < 0.01;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.toBaseUnit(value));
    }
}