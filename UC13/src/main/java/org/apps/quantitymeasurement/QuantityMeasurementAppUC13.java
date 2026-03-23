package org.apps.quantitymeasurement;

public class QuantityMeasurementAppUC13 {

    public static void main(String[] args) {

        QuantityUC13<LengthUnitUC13> q1 = new QuantityUC13<>(10, LengthUnitUC13.FEET);
        QuantityUC13<LengthUnitUC13> q2 = new QuantityUC13<>(6, LengthUnitUC13.INCHES);

        System.out.println("Addition: " + q1.add(q2));
        System.out.println("Subtraction: " + q1.subtract(q2));
        System.out.println("Division: " + q1.divide(new QuantityUC13<>(2, LengthUnitUC13.FEET)));

        QuantityUC13<VolumeUnitUC13> v1 = new QuantityUC13<>(5, VolumeUnitUC13.LITRE);
        QuantityUC13<VolumeUnitUC13> v2 = new QuantityUC13<>(2, VolumeUnitUC13.LITRE);

        System.out.println("Volume Subtraction: " + v1.subtract(v2));
        System.out.println("Volume Division: " + v1.divide(v2));
    }
}