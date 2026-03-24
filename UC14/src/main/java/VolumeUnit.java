public enum VolumeUnit implements IMeasurable {
    LITRE(1000.0, "Litre"),
    MILLILITRE(1.0, "Millilitre"),
    GALLON(3785.41, "Gallon");

    private final double conversionFactor;
    private final String name;

    VolumeUnit(double conversionFactor, String name) {
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
