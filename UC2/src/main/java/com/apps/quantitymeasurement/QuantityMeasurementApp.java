package com.apps.quantitymeasurement;

public class QuantityMeasurementApp
{
    public static  class  Feet{
        private final double feet;
        public Feet(double feet) {
            this.feet = feet;
        }
        public double getFeet() { return this.feet; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Feet feet1 = (Feet) o;
            return Double.compare(feet1.feet, feet) == 0;
        }
    }

    public  static  class  Inches{
        private final double inches;
        public Inches(double inches) {
            this.inches = inches;
        }
        public double getInches() { return this.inches; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Inches inches11 = (Inches) o;
            return Double.compare(inches11.inches, inches) == 0;
        }
    }

    public static void demonstrateFeetEquality(){
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);
        System.out.println(f1.equals(f2));
    }

    public static  void demonstrateInchesEquality(){
        Inches i1 = new Inches(1.0);
        Inches i2 = new Inches(1.0);
        System.out.println(i1.equals(i2));
    }

    public  static  void main(String[] args){
        demonstrateFeetEquality();
        demonstrateInchesEquality();
    }
}

