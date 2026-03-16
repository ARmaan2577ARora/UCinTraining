package com.quantitymeasurement;

import java.util.Scanner;

class QuantityMeasurementEquality{
    public static class Feet{
        private final double value;
        public Feet(double value){
            this.value = value;
        }
        public double getValue() {
            return value;
        }
        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Feet feet = (Feet) obj;
            return Double.compare(feet.value, value) == 0;
        }
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double input1 = sc.nextInt();
        double input2 = sc.nextInt();
        Feet f1 = new Feet(input1);
        Feet f2 = new Feet(input2);

        boolean result = f1.equals(f2);
        System.out.println(result);
        sc.close();
    }
}