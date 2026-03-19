package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(QuantityLength l1, QuantityLength l2) {
        if (l1 == l2) return true;
        if (l1 == null || l2 == null) return false;
        return l1.equals(l2);
    }

    public static boolean demonstrateLengthComparison(double value1, LengthUnit unit1, double value2, LengthUnit unit2) {

        QuantityLength l1 = new QuantityLength(value1, unit1);
        QuantityLength l2 = new QuantityLength(value2, unit2);

        return l1.equals(l2);
    }

    public static QuantityLength demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        QuantityLength length = new QuantityLength(value, fromUnit);
        return length.convertTo(toUnit);
    }

    public static QuantityLength demonstrateLengthConversion(QuantityLength length, LengthUnit toUnit) {
        if (length == null) throw new IllegalArgumentException("Length cannot be null");
        return length.convertTo(toUnit);
    }

    public static QuantityLength demonstrateLengthAddition(QuantityLength l1, QuantityLength l2) {
        if (l1 == null || l2 == null) throw new IllegalArgumentException("Lengths cannot be null");
        return l1.add(l2);
    }

    public static QuantityLength demonstrateLengthAddition(QuantityLength l1, QuantityLength l2, LengthUnit targetUnit) {
        if (l1 == null || l2 == null) throw new IllegalArgumentException("Lengths cannot be null");
        return l1.add(l2, targetUnit);
    }

    public static boolean demonstrateWeightEquality(QuantityWeight w1, QuantityWeight w2) {
        if (w1 == null || w2 == null) throw new IllegalArgumentException("Weights cannot be null");
        return w1.equals(w2);
    }

    public static boolean demonstrateWeightComparison(double value1, WeightUnit unit1,
                                                      double value2, WeightUnit unit2) {
        QuantityWeight w1 = new QuantityWeight(value1, unit1);
        QuantityWeight w2 = new QuantityWeight(value2, unit2);

        return w1.equals(w2);
    }

    public static QuantityWeight demonstrateWeightConversion(double value,
                                                     WeightUnit fromUnit,
                                                     WeightUnit toUnit) {
        QuantityWeight weight = new QuantityWeight(value, fromUnit);
        return weight.convertTo(toUnit);
    }

    public static QuantityWeight demonstrateWeightConversion(QuantityWeight weight, WeightUnit toUnit) {
        if (weight == null) throw new IllegalArgumentException("Weight cannot be null");
        return weight.convertTo(toUnit);
    }

    public static QuantityWeight demonstrateWeightAddition(QuantityWeight w1, QuantityWeight w2) {
        if (w1 == null || w2 == null) throw new IllegalArgumentException("Weights cannot be null");
        return w1.add(w2);
    }

    public static QuantityWeight demonstrateWeightAddition(QuantityWeight w1,
                                                   QuantityWeight w2,
                                                   WeightUnit targetUnit) {
        if (w1 == null || w2 == null)
            throw new IllegalArgumentException("Weights cannot be null");

        return w1.add(w2, targetUnit);
    }

    public static void main(String[] args) {

        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        System.out.println("Weight Equality (1kg, 1000g): " + demonstrateWeightEquality(w1, w2));
        System.out.println("Weight Conversion (1kg to Gram): " + demonstrateWeightConversion(w1, WeightUnit.GRAM));
        System.out.println("Weight Addition (1kg + 1000g): " + demonstrateWeightAddition(w1, w2));
        System.out.println("Weight Addition (1kg + 1000g in Gram): " + demonstrateWeightAddition(w1, w2, WeightUnit.GRAM));

        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);
        System.out.println("Length Equality (1ft, 12in): " + demonstrateLengthEquality(l1, l2));
    }
}