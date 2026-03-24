public enum LengthUnit implements IMeasurable {
    FEET(12.0, "Feet"),
    INCH(1.0, "Inch"),
    YARD(36.0, "Yard"),
    CENTIMETER(0.4, "Centimeter");

    private final double conversionFactor;
    private final String name;

    LengthUnit(double conversionFactor, String name) {
        this.conversionFactor = conversionFactor;
        this.name = name;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public String toString() {
        return name;
    }
}
