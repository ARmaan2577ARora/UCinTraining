package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity<LengthUnit> lengths1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> lengths2 = new Quantity<>(12.0, LengthUnit.INCHES);
        demonstrateEquality(lengths1, lengths2);

        Quantity<LengthUnit> lengthToConvert = new Quantity<>(1.0, LengthUnit.FEET);
        demonstrateConversion(lengthToConvert, LengthUnit.INCHES);

        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);
        demonstrateAddition(l1, l2, LengthUnit.FEET);

        Quantity<WeightUnit> weights1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weights2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        demonstrateEquality(weights1, weights2);

        Quantity<WeightUnit> weightToConvert = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        demonstrateConversion(weightToConvert, WeightUnit.GRAM);

        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        demonstrateAddition(w1, w2, WeightUnit.KILOGRAM);
    }

    public static <U extends IMeasurable> void demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        boolean result = q1.equals(q2);
        System.out.println("Equality: " + q1 + " == " + q2 + " ? " + result);
    }

    public static <U extends IMeasurable> void demonstrateConversion(Quantity<U> quantity, U targetUnit) {
        Quantity<U> result = quantity.convertTo(targetUnit);
        System.out.println("Conversion: " + quantity + " to " + targetUnit + " = " + result);
    }

    public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        Quantity<U> result = q1.add(q2, targetUnit);
        System.out.println("Addition: " + q1 + " + " + q2 + " = " + result + " (" + targetUnit + ")");
    }
}
