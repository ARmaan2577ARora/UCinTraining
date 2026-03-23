package org.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTestUC13 {

    @Test
    void testSubtraction() {
        QuantityUC13<LengthUnitUC13> q1 = new QuantityUC13<>(10, LengthUnitUC13.FEET);
        QuantityUC13<LengthUnitUC13> q2 = new QuantityUC13<>(6, LengthUnitUC13.INCHES);

        assertEquals(new QuantityUC13<>(9.5, LengthUnitUC13.FEET), q1.subtract(q2));
    }

    @Test
    void testDivision() {
        QuantityUC13<LengthUnitUC13> q1 = new QuantityUC13<>(10, LengthUnitUC13.FEET);
        QuantityUC13<LengthUnitUC13> q2 = new QuantityUC13<>(2, LengthUnitUC13.FEET);

        assertEquals(5.0, q1.divide(q2));
    }
}