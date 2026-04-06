package com.app.quantitymeasurement.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class QuantityInputDTO {

    @NotNull(message = "First quantity is required")
    @Valid
    private QuantityDTO firstQuantity;

    @NotNull(message = "Second quantity is required")
    @Valid
    private QuantityDTO secondQuantity;

    private String targetUnit;

    public QuantityInputDTO() {}

    public QuantityInputDTO(QuantityDTO firstQuantity, QuantityDTO secondQuantity, String targetUnit) {
        this.firstQuantity = firstQuantity;
        this.secondQuantity = secondQuantity;
        this.targetUnit = targetUnit;
    }

    public QuantityDTO getFirstQuantity() { return firstQuantity; }
    public void setFirstQuantity(QuantityDTO firstQuantity) { this.firstQuantity = firstQuantity; }

    public QuantityDTO getSecondQuantity() { return secondQuantity; }
    public void setSecondQuantity(QuantityDTO secondQuantity) { this.secondQuantity = secondQuantity; }

    public String getTargetUnit() { return targetUnit; }
    public void setTargetUnit(String targetUnit) { this.targetUnit = targetUnit; }
}

