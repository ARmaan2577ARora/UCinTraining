package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0,LengthUnit.Feet);
        QuantityLength q2 = new QuantityLength(12.0,LengthUnit.Inch);

        System.out.println("Are both values equal?" + (q1.equals(q2)?"Yes":"No"));
    }

}
