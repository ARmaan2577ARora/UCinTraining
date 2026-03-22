package org.apps.quantitymeasurement;

public class QuantityMeasurementAppUC12 {

    public static void main(String[] args) {

        QuantityUC12<LengthUnitUC12> l1 =
                new QuantityUC12<>(10.0, LengthUnitUC12.FEET);

        QuantityUC12<LengthUnitUC12> l2 =
                new QuantityUC12<>(6.0, LengthUnitUC12.INCH);

        System.out.println("Subtraction: " + l1.subtract(l2));
        System.out.println("Division: " + l1.divide(l2));


        QuantityUC12<VolumeUnitUC12> v1 =
                new QuantityUC12<>(5.0, VolumeUnitUC12.LITRE);

        QuantityUC12<VolumeUnitUC12> v2 =
                new QuantityUC12<>(500.0, VolumeUnitUC12.MILLILITRE);

        System.out.println("Volume Subtraction: " + v1.subtract(v2));
        System.out.println("Volume Division: " + v1.divide(v2));
    }
}
