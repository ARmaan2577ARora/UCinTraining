package com.apps.quantitymeasurement;

public class Length
{
    private double value;
    private LengthUnit unit;

    public enum LengthUnit{
        FEET(12.0), INCHES(1.0), YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor){
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor(){
            return conversionFactor;
        }
    }

    public Length(double value , LengthUnit unit){
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.unit = unit;
        this.value = value;
    }

    private double convertToBaseUnit(){
        return this.value * this.unit.getConversionFactor();
    }

    public boolean compare(Length that){
        if(that == null) return false;
        return Math.abs(this.convertToBaseUnit() - that.convertToBaseUnit()) < 1e-5;
    }

    @Override
    public boolean equals(Object that){
        if(this == that) return true;
        if(!(that instanceof Length)) return false;
        return compare((Length)that);
    }

    public Length convertTo(LengthUnit targetUnit){
        if(targetUnit == null){
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue = this.convertToBaseUnit();
        double convertedValue = baseValue / targetUnit.getConversionFactor();
        return new Length(convertedValue , targetUnit);
    }

    @Override
    public int hashCode(){
        return Double.hashCode(convertToBaseUnit());
    }

    @Override
    public String toString(){
        return "value=" + this.value + ", unit=" + this.unit;
    }
}