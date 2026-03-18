package com.apps.quantitymeasurement;

public class Length
{
    private double value;
    private LengthUnit unit;

    public enum LengthUnit{
        FEET(12.0),INCHES(1.0),YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor)
        {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor()
        {
            return conversionFactor;
        }
    }

    public double getValue()
    {
        return value;
    }
    public LengthUnit getUnit()
    {
        return unit;
    }

    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit(){
        return this.value*this.unit.getConversionFactor();
    }

    private boolean compare(Length thatLength){
        if (thatLength == null) return false;
        return Math.abs(this.convertToBaseUnit() - thatLength.convertToBaseUnit()) < 0.01;
    }

    public Length convertTo(LengthUnit targetUnit){
        return new Length(this.convertToBaseUnit()/targetUnit.getConversionFactor(), targetUnit);
    }

    public Length add(Length thatLength){
        return new Length((this.convertToBaseUnit()+thatLength.convertToBaseUnit())/this.unit.getConversionFactor(), this.unit);
    }

    private double convertFromBaseToTargetUnit(double lengthInInches, LengthUnit targetUnit){
        return lengthInInches/targetUnit.getConversionFactor();
    }

    @Override
    public String toString(){
        return "Length{value=" + this.value + ", unit=" + this.unit + "}";
    }

    @Override
    public boolean equals(Object thatLength)
    {
        if (thatLength == this) return true;
        if (thatLength == null) return false;
        if(!(thatLength instanceof Length)) return false;
        return this.compare((Length)thatLength);
    }

    @Override
    public int hashCode(){
        return (int) Math.round(convertToBaseUnit()*1000);
    }
}
