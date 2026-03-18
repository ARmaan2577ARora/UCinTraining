package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    private static final double EPSILON=1e-3;
    @Test
    void testAddition_ExplicitTargetUnit_Feet(){
        Length result=new Length(1,Length.LengthUnit.FEET).add(new Length(12,Length.LengthUnit.INCHES),Length.LengthUnit.FEET);
        assertEquals(new Length(2,Length.LengthUnit.FEET),result);
    }
    @Test
    void testAddition_ExplicitTargetUnit_Inches(){
        Length result=new Length(1,Length.LengthUnit.FEET).add(new Length(12,Length.LengthUnit.INCHES),Length.LengthUnit.INCHES);
        assertEquals(new Length(24,Length.LengthUnit.INCHES),result);
    }
    @Test
    void testAddition_ExplicitTargetUnit_Yards(){
        Length result=new Length(1,Length.LengthUnit.FEET).add(new Length(12,Length.LengthUnit.INCHES),Length.LengthUnit.YARDS);
        assertEquals(0.667,result.convertTo(Length.LengthUnit.YARDS).toString().contains("YARDS")?result.convertTo(Length.LengthUnit.YARDS).value:0,EPSILON);
    }
    @Test
    void testAddition_ExplicitTargetUnit_Centimeters(){
        Length result=new Length(1,Length.LengthUnit.INCHES).add(new Length(1,Length.LengthUnit.INCHES),Length.LengthUnit.CENTIMETERS);
        assertEquals(5.08,result.convertTo(Length.LengthUnit.CENTIMETERS).value,EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand(){
        Length result=new Length(2,Length.LengthUnit.YARDS).add(new Length(3,Length.LengthUnit.FEET),Length.LengthUnit.YARDS);
        assertEquals(new Length(3,Length.LengthUnit.YARDS), result);
    }
    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand(){
        Length result=new Length(2,Length.LengthUnit.YARDS).add(new Length(3,Length.LengthUnit.FEET),Length.LengthUnit.FEET);
        assertEquals(new Length(9,Length.LengthUnit.FEET),result);
    }
    @Test
    void testAddition_ExplicitTargetUnit_Commutativity(){
        Length a=new Length(1,Length.LengthUnit.FEET);
        Length b=new Length(12,Length.LengthUnit.INCHES);
        assertEquals(a.add(b,Length.LengthUnit.YARDS),b.add(a,Length.LengthUnit.YARDS)
        );
    }
    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {
        Length result=new Length(5,Length.LengthUnit.FEET).add(new Length(0,Length.LengthUnit.INCHES),Length.LengthUnit.YARDS);
        assertEquals(1.667,result.convertTo(Length.LengthUnit.YARDS).value, EPSILON);
    }
    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues(){
        Length result=new Length(5,Length.LengthUnit.FEET).add(new Length(-2,Length.LengthUnit.FEET),Length.LengthUnit.INCHES);
        assertEquals(new Length(36,Length.LengthUnit.INCHES),result);
    }
    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit(){
        assertThrows(IllegalArgumentException.class,()->new Length(1,Length.LengthUnit.FEET).add(new Length(12,Length.LengthUnit.INCHES),null)
        );
    }
    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale(){
        Length result=new Length(1000,Length.LengthUnit.FEET).add(new Length(500,Length.LengthUnit.FEET),Length.LengthUnit.INCHES);
        assertEquals(new Length(18000,Length.LengthUnit.INCHES),result);
    }
    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale(){
        Length result=new Length(12,Length.LengthUnit.INCHES).add(new Length(12,Length.LengthUnit.INCHES),Length.LengthUnit.YARDS);
        assertEquals(0.667,result.convertTo(Length.LengthUnit.YARDS).value,EPSILON);
    }
    @Test
    void testAddition_ExplicitTargetUnit_AllUnitCombinations(){
        Length.LengthUnit[] units=Length.LengthUnit.values();
        for(Length.LengthUnit u1:units){
            for(Length.LengthUnit u2:units){
                for(Length.LengthUnit target:units){
                    Length l1=new Length(1.0,u1);
                    Length l2=new Length(2.0,u2);
                    Length result=l1.add(l2,target);
                    assertNotNull(result);
                }
            }
        }
    }
    @Test
    void testAddition_ExplicitTargetUnit_PrecisionTolerance(){
        Length result1=new Length(1,Length.LengthUnit.FEET).add(new Length(12,Length.LengthUnit.INCHES),Length.LengthUnit.YARDS);
        Length result2=new Length(24,Length.LengthUnit.INCHES).add(new Length(0,Length.LengthUnit.INCHES),Length.LengthUnit.YARDS);
        assertEquals(result1.convertTo(Length.LengthUnit.YARDS).value,result2.convertTo(Length.LengthUnit.YARDS).value,EPSILON);
    }
}