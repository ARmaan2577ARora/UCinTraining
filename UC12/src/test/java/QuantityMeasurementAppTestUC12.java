package org.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTestUC12 {

    @Test
    void testSubtraction_LitreMinusMillilitre() {
        QuantityUC12<VolumeUnitUC12> q1 =
                new QuantityUC12<>(5.0, VolumeUnitUC12.LITRE);

        QuantityUC12<VolumeUnitUC12> q2 =
                new QuantityUC12<>(500.0, VolumeUnitUC12.MILLILITRE);

        assertEquals(4.5, q1.subtract(q2).getValue());
    }

    @Test
    void testSubtraction_Negative() {
        QuantityUC12<LengthUnitUC12> q1 =
                new QuantityUC12<>(5.0, LengthUnitUC12.FEET);

        QuantityUC12<LengthUnitUC12> q2 =
                new QuantityUC12<>(10.0, LengthUnitUC12.FEET);

        assertEquals(-5.0, q1.subtract(q2).getValue());
    }

    @Test
    void testDivision_Litre() {
        QuantityUC12<VolumeUnitUC12> q1 =
                new QuantityUC12<>(10.0, VolumeUnitUC12.LITRE);

        QuantityUC12<VolumeUnitUC12> q2 =
                new QuantityUC12<>(5.0, VolumeUnitUC12.LITRE);

        assertEquals(2.0, q1.divide(q2));
    }

    @Test
    void testDivision_CrossUnit() {
        QuantityUC12<LengthUnitUC12> q1 =
                new QuantityUC12<>(24.0, LengthUnitUC12.INCH);

        QuantityUC12<LengthUnitUC12> q2 =
                new QuantityUC12<>(2.0, LengthUnitUC12.FEET);

        assertEquals(1.0, q1.divide(q2), 0.01);
    }

    @Test
    void testDivision_ByZero() {
        QuantityUC12<LengthUnitUC12> q1 =
                new QuantityUC12<>(10.0, LengthUnitUC12.FEET);

        QuantityUC12<LengthUnitUC12> q2 =
                new QuantityUC12<>(0.0, LengthUnitUC12.FEET);

        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

    @Test
    void testCrossCategory_Subtraction() {
        QuantityUC12<LengthUnitUC12> q1 =
                new QuantityUC12<>(10.0, LengthUnitUC12.FEET);

        QuantityUC12<WeightUnitUC12> q2 =
                new QuantityUC12<>(5.0, WeightUnitUC12.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> q1.subtract((QuantityUC12) q2));
    }
}
