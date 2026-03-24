public class QuantityMeasurementApp {
    public static void main(String[] args) {
        System.out.println("--- Quantity Measurement App UC14 ---");

        // Temperature Equality Comparisons
        Quantity<TemperatureUnit> zeroCelsius = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> thirtyTwoFahrenheit = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        System.out.println("0.0 CELSIUS == 32.0 FAHRENHEIT: " + zeroCelsius.equals(thirtyTwoFahrenheit));

        Quantity<TemperatureUnit> boilingCelsius = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> boilingFahrenheit = new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT);
        System.out.println("100.0 CELSIUS == 212.0 FAHRENHEIT: " + boilingCelsius.equals(boilingFahrenheit));

        // Temperature Conversions
        Quantity<TemperatureUnit> convertedF = zeroCelsius.convertTo(TemperatureUnit.FAHRENHEIT);
        System.out.println("0.0 CELSIUS to FAHRENHEIT: " + convertedF);

        Quantity<TemperatureUnit> convertedC = boilingFahrenheit.convertTo(TemperatureUnit.CELSIUS);
        System.out.println("212.0 FAHRENHEIT to CELSIUS: " + convertedC);

        // Unsupported Operations
        try {
            System.out.println("Attempting to add temperatures...");
            zeroCelsius.add(boilingCelsius);
        } catch (UnsupportedOperationException e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());
        }

        try {
            System.out.println("Attempting to divide temperatures...");
            boilingCelsius.divide(zeroCelsius);
        } catch (UnsupportedOperationException e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());
        }

        // Cross-Category Prevention
        Quantity<LengthUnit> oneFeet = new Quantity<>(1.0, LengthUnit.FEET);
        System.out.println("0.0 CELSIUS == 1.0 FEET: " + zeroCelsius.equals(oneFeet));
    }
}
