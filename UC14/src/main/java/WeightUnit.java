public enum WeightUnit implements IMeasurable {
    KILOGRAM(1000.0, "Kilogram"),
    GRAM(1.0, "Gram"),
    TONNE(1000000.0, "Tonne");

    private final double conversionFactor;
    private final String name;

    WeightUnit(double conversionFactor, String name) {
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
