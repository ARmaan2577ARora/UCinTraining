public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
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
        if (obj == null || getClass() != obj.getClass()) return false;
        Quantity<?> that = (Quantity<?>) obj;

        // Prevent cross-category comparison by checking unit type
        if (!this.unit.getClass().equals(that.unit.getClass())) {
            return false;
        }

        double thisValueInBase = this.unit.convertToBaseUnit(this.value);
        double thatValueInBase = ((IMeasurable) that.unit).convertToBaseUnit(that.value);

        return Math.abs(thisValueInBase - thatValueInBase) < 0.001;
    }

    public Quantity<U> add(Quantity<U> other) {
        return performArithmetic(other, ArithmeticOperation.ADD);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return performArithmetic(other, ArithmeticOperation.SUBTRACT);
    }

    public Quantity<U> divide(Quantity<U> other) {
        return performArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    private Quantity<U> performArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        // Validate operation support upfront
        this.unit.validateOperationSupport(operation.name());

        double thisValueInBase = this.unit.convertToBaseUnit(this.value);
        double otherValueInBase = other.unit.convertToBaseUnit(other.value);
        double resultInBase = operation.apply(thisValueInBase, otherValueInBase);
        
        // Handle conversion back to original unit
        double resultInOriginalUnit;
        if (this.unit instanceof TemperatureUnit) {
            resultInOriginalUnit = ((TemperatureUnit) this.unit).fromBaseUnit(resultInBase);
        } else {
            resultInOriginalUnit = resultInBase / this.unit.getConversionFactor();
        }
        
        return new Quantity<>(resultInOriginalUnit, this.unit);
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double valueInBase = this.unit.convertToBaseUnit(this.value);
        
        double valueInTargetUnit;
        if (targetUnit instanceof TemperatureUnit) {
            valueInTargetUnit = ((TemperatureUnit) targetUnit).fromBaseUnit(valueInBase);
        } else {
            valueInTargetUnit = valueInBase / targetUnit.getConversionFactor();
        }
        
        return new Quantity<>(valueInTargetUnit, targetUnit);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}
