package com.app.quantitymeasurement.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class QuantityDTO {

    public interface IMeasurableUnit {
        String getUnitName();
        String getMeasurementType();
    }

    public enum LengthUnitDTO implements IMeasurableUnit {
        FEET, INCHES, YARDS, CENTIMETERS, METERS;
        @Override public String getUnitName() { return this.name(); }
        @Override public String getMeasurementType() { return "LengthUnit"; }
    }

    public enum VolumeUnitDTO implements IMeasurableUnit {
        LITRE, MILLILITRE, GALLON;
        @Override public String getUnitName() { return this.name(); }
        @Override public String getMeasurementType() { return "VolumeUnit"; }
    }

    public enum WeightUnitDTO implements IMeasurableUnit {
        GRAM, KILOGRAM, POUND, TONNE;
        @Override public String getUnitName() { return this.name(); }
        @Override public String getMeasurementType() { return "WeightUnit"; }
    }

    public enum TemperatureUnitDTO implements IMeasurableUnit {
        CELSIUS, FAHRENHEIT, KELVIN;
        @Override public String getUnitName() { return this.name(); }
        @Override public String getMeasurementType() { return "TemperatureUnit"; }
    }

    public QuantityDTO() {}

    public QuantityDTO(Double value, String unit, String measurementType) {
        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    @NotNull(message = "Value is required")
    private Double value;

    @NotEmpty(message = "Unit is required")
    private String unit;

    @NotEmpty(message = "Measurement type is required")
    @Pattern(regexp = "LengthUnit|VolumeUnit|WeightUnit|TemperatureUnit", 
             message = "Invalid measurement type")
    private String measurementType;

    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public String getMeasurementType() { return measurementType; }
    public void setMeasurementType(String measurementType) { this.measurementType = measurementType; }
}


