package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Inches;

public class QuantityMeasurementAppTest {
    @Test
    public void testFeetEquality_SameValue(){
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        assertEquals(f1,f2);
    }

    @Test
    public void testFeetEquality_DifferentValue(){
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(2.0);

        assertNotEquals(f1,f2);
    }

    @Test
    public void testFeetEquality_NullComparison(){
        Feet f1 = new Feet(1.0);

        assertNotEquals(null,f1);
    }

    @Test
    public void testFeetEquality_DifferentClass(){
        Feet f1 = new Feet(1.0);
        Inches i1 = new Inches(1.0);

        assertNotEquals(f1.getClass(),i1.getClass());
    }

    @Test
    public void testFeetEquality_SameReference(){
        Feet f1 = new Feet(1.0);
        Feet f2 = f1;

        assertEquals(f1,f2);
    }

    @Test
    public void testInchesEquality_SameValue(){
        Inches i1 = new Inches(1.0);
        Inches i2 = new Inches(1.0);

        assertEquals(i1,i2);
    }

    @Test
    public void testInchesEquality_DifferentValue(){
        Inches i1 = new Inches(1.0);
        Inches i2 = new Inches(2.0);

        assertNotEquals(i1,i2);
    }

    @Test
    public void testInchesEquality_NullComparison(){
        Inches i1 = new Inches(1.0);

        assertNotEquals(null,i1);
    }

    @Test
    public void testInchesEquality_DifferentClass(){
        Feet f1 = new Feet(1.0);
        Inches i1 = new Inches(1.0);

        assertNotEquals(i1.getClass(),f1.getClass());
    }

    @Test
    public void testInchesEquality_SameReference(){
        Inches i1 = new  Inches(1.0);
        Inches i2 = i1;
        assertEquals(i1,i2);
    }
}