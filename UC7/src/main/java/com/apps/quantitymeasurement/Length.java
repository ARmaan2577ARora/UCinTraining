package com.apps.quantitymeasurement;

public class Length{
    public final double value;
    private final LengthUnit unit;
    private static final double EPSILON=1e-6;
    public enum LengthUnit{
        INCHES(1.0),
        FEET(12.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);
        private final double conversionFactor;
        LengthUnit(double conversionFactor){
            this.conversionFactor=conversionFactor;
        }
        public double getConversionFactor(){
            return conversionFactor;
        }
        public double toBase(double value){
            return value*conversionFactor;
        }
        public double fromBase(double baseValue){
            return baseValue/conversionFactor;
        }
    }
    public Length(double value,LengthUnit unit){
        if(unit==null) throw new IllegalArgumentException("Unit cannot be null");
        if(!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
        this.value=value;
        this.unit=unit;
    }
    private double convertToBaseUnit(){
        return unit.toBase(value);
    }
    private boolean compare(Length thatLength){
        if(thatLength==null) return false;
        return Math.abs(this.convertToBaseUnit()-thatLength.convertToBaseUnit())<EPSILON;
    }
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(!(o instanceof Length)) return false;
        Length other=(Length)o;
        return this.compare(other);
    }
    @Override
    public int hashCode(){
        return Double.hashCode(convertToBaseUnit());
    }
    public Length convertTo(LengthUnit targetUnit){
        if (targetUnit==null){
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double base=this.convertToBaseUnit();
        double converted=targetUnit.fromBase(base);
        return new Length(converted,targetUnit);
    }
    public Length add(Length thatLength){
        return addAndConvert(thatLength,this.unit);
    }
    public Length add(Length length,LengthUnit targetUnit){
        if(length==null){
            throw new IllegalArgumentException("Length cannot be null");
        }
        if(targetUnit==null){
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        return addAndConvert(length,targetUnit);
    }
    private Length addAndConvert(Length length,LengthUnit targetUnit){
        double sumBase=this.convertToBaseUnit()+length.convertToBaseUnit();
        double result=targetUnit.fromBase(sumBase);
        return new Length(result,targetUnit);
    }
    private double convertFromBaseToTargetUnit(double baseValue,LengthUnit targetUnit){
        return targetUnit.fromBase(baseValue);
    }
    @Override
    public String toString(){
        return value+" "+unit;
    }

}