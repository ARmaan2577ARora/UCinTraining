import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {
    CELSIUS("Celsius", (celsius) -> celsius, (celsius) -> celsius),
    FAHRENHEIT("Fahrenheit", (fahrenheit) -> (fahrenheit - 32) * 5 / 9, (celsius) -> (celsius * 9 / 5) + 32),
    KELVIN("Kelvin", (kelvin) -> kelvin - 273.15, (celsius) -> celsius + 273.15);

    private final String name;
    private final Function<Double, Double> toBase;
    private final Function<Double, Double> fromBase;

    TemperatureUnit(String name, Function<Double, Double> toBase, Function<Double, Double> fromBase) {
        this.name = name;
        this.toBase = toBase;
        this.fromBase = fromBase;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return toBase.apply(value);
    }

    public double fromBaseUnit(double valueInBase) {
        return fromBase.apply(valueInBase);
    }

    @Override
    public double getConversionFactor() {
        // Not applicable for non-linear conversions, but interface requires it
        return 1.0;
    }

    @Override
    public boolean supportsArithmetic() {
        return false;
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException("Temperature does not support " + operation);
    }

    @Override
    public String toString() {
        return name;
    }
}
