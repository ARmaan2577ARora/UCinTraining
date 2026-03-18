package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {
    public static boolean demonstrateLengthEquality(Length l1, Length l2){
        if (l1 == l2) return true;
        if (l1 == null || l2 == null) return false;
        return l1.equals(l2);
    }

    public static boolean demonstrateLengthComparison(double value1,Length.LengthUnit unit1,double value2,Length.LengthUnit unit2){
        Length l1 = new Length(value1, unit1);
        Length l2 = new Length(value2, unit2);

        return l1.equals(l2);
    }

    public static Length demonstrateLengthConversion(){
        Length l = new Length(1.0, Length.LengthUnit.FEET);
        return l.convertTo(Length.LengthUnit.INCHES);
    }

    public static Length demonstrateLengthConversion(Length l,Length.LengthUnit toUnit){
        if (l == null || toUnit == null) throw new IllegalArgumentException("Invalid Input");
        return l.convertTo(toUnit);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2){

        double base1 = l1.getValue() * l1.getUnit().getConversionFactor();
        double base2 = l2.getValue() * l2.getUnit().getConversionFactor();

        double sum = base1 + base2;
        double result = sum/l1.getUnit().getConversionFactor();

        return new Length(result,l1.getUnit());
    }

    public static void main(String[] args){
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        System.out.println(demonstrateLengthAddition(l1,l2));

        Length l3 = new Length(12.0, Length.LengthUnit.INCHES);
        Length l4 = new Length(1.0, Length.LengthUnit.FEET);
        System.out.println(demonstrateLengthAddition(l3,l4));
    }
}
