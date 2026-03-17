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

    public static double demonstrateLengthConversion(double value, Length.LengthUnit from, Length.LengthUnit to){
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        if (from == null || to == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        double base = value * from.getConversionFactor();
        return base / to.getConversionFactor();
    }

    public static Length demonstrateLengthConversion(Length l1, Length.LengthUnit target){
        if (l1 == null || target == null) {
            throw new IllegalArgumentException("Invalid input");
        }
        return l1.convertTo(target);
    }

    public static void main(String[] args){
        System.out.println(demonstrateLengthConversion(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES));

        Length l = new Length(36.0, Length.LengthUnit.INCHES);
        System.out.println(demonstrateLengthConversion(l, Length.LengthUnit.YARDS));
    }
}