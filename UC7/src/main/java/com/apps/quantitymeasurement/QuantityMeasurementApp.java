package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {
    public static boolean demonstrateLengthEquality(Length l1, Length l2){
        if(l1==null || l2==null)throw new IllegalArgumentException("Lengths cannot be null");
        return l1.equals(l2);
    }
    public static boolean demonstrateLengthComparison(Length l1,Length l2){
        return demonstrateLengthEquality(l1,l2);
    }
    public static Length demonstrateLengthConversion(double value,Length.LengthUnit fromUnit,Length.LengthUnit toUnit){
        return new Length(value,fromUnit).convertTo(toUnit);
    }
    public static Length demonstrateLengthConversion(Length length,Length.LengthUnit toUnit){
        if(length==null) throw new IllegalArgumentException("Length cannot be null");
        return length.convertTo(toUnit);
    }
    public static Length demonstrateLengthAddition(Length l1,Length l2){
        if(l1==null || l2==null) throw new IllegalArgumentException("Lengths cannot be null");
        return l1.add(l2);
    }
    public static Length demonstrateLengthAddition(Length l1,Length l2,Length.LengthUnit targetUnit){
        if(l1==null || l2==null) throw new IllegalArgumentException("Lengths cannot be null");
        if(targetUnit==null) throw new IllegalArgumentException("Target unit cannot be null");
        return l1.add(l2,targetUnit);
    }
    public static void main(String[] args){
        Length l1=new Length(1.0,Length.LengthUnit.FEET);
        Length l2=new Length(12.0,Length.LengthUnit.INCHES);
        System.out.println("Equal: "+demonstrateLengthEquality(l1, l2));
        System.out.println("Convert to Inches: "+demonstrateLengthConversion(l1,Length.LengthUnit.INCHES));
        System.out.println("Addition (default unit): "+demonstrateLengthAddition(l1, l2));
        System.out.println("Addition in Feet: "+demonstrateLengthAddition(l1,l2,Length.LengthUnit.FEET));
        System.out.println("Addition in Inches: "+demonstrateLengthAddition(l1,l2,Length.LengthUnit.INCHES));
        System.out.println("Addition in Yards: "+demonstrateLengthAddition(l1,l2,Length.LengthUnit.YARDS));
        Length l3=new Length(2.54,Length.LengthUnit.CENTIMETERS);
        Length l4=new Length(1.0,Length.LengthUnit.INCHES);
        System.out.println("CM + Inches in CM: "+demonstrateLengthAddition(l3,l4,Length.LengthUnit.CENTIMETERS));
    }

}