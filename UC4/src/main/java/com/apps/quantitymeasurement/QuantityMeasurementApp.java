package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        if (l1 == l2) return true;
        if (l1 == null || l2 == null) return false;
        return l1.equals(l2);
    }

    public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1,double value2, Length.LengthUnit unit2) {

        Length l1 = new Length(value1, unit1);
        Length l2 = new Length(value2, unit2);

        return l1.equals(l2);
    }

    public static void main(String[] args) {
        System.out.println(demonstrateLengthComparison(1.0,Length.LengthUnit.FEET,
                12.0,Length.LengthUnit.INCHES));
        System.out.println(demonstrateLengthComparison(1.0,Length.LengthUnit.YARDS,
                36.0,Length.LengthUnit.INCHES));
        System.out.println(demonstrateLengthComparison(100.0,Length.LengthUnit.CENTIMETERS,
                39.3701,Length.LengthUnit.INCHES));
        System.out.println(demonstrateLengthComparison(3.0,Length.LengthUnit.FEET,
                1.0,Length.LengthUnit.YARDS));
        System.out.println(demonstrateLengthComparison(30.48,Length.LengthUnit.CENTIMETERS,
                1.0,Length.LengthUnit.FEET));
    }

}
