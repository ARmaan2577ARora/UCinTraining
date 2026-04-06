package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityInputDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
@Tag(name = "Quantity Measurements", description = "REST API for quantity measurement operations")
public class QuantityMeasurementController {

    private final IQuantityMeasurementService quantityMeasurementService;

    @Autowired
    public QuantityMeasurementController(IQuantityMeasurementService quantityMeasurementService) {
        this.quantityMeasurementService = quantityMeasurementService;
    }

    @PostMapping("/compare")
    @Operation(summary = "Compare two quantities")
    public ResponseEntity<QuantityMeasurementDTO> compareQuantities(@Valid @RequestBody QuantityInputDTO input) {
        return ResponseEntity.ok(quantityMeasurementService.compare(input.getFirstQuantity(), input.getSecondQuantity()));
    }

    @PostMapping("/convert")
    @Operation(summary = "Convert a quantity to another unit")
    public ResponseEntity<QuantityMeasurementDTO> convertQuantity(@Valid @RequestBody QuantityInputDTO input) {
        return ResponseEntity.ok(quantityMeasurementService.convert(input.getFirstQuantity(), input.getTargetUnit()));
    }

    @PostMapping("/add")
    @Operation(summary = "Add two quantities")
    public ResponseEntity<QuantityMeasurementDTO> addQuantities(@Valid @RequestBody QuantityInputDTO input) {
        return ResponseEntity.ok(quantityMeasurementService.add(input.getFirstQuantity(), input.getSecondQuantity(), input.getTargetUnit()));
    }

    @PostMapping("/subtract")
    @Operation(summary = "Subtract two quantities")
    public ResponseEntity<QuantityMeasurementDTO> subtractQuantities(@Valid @RequestBody QuantityInputDTO input) {
        return ResponseEntity.ok(quantityMeasurementService.subtract(input.getFirstQuantity(), input.getSecondQuantity(), input.getTargetUnit()));
    }

    @PostMapping("/divide")
    @Operation(summary = "Divide two quantities")
    public ResponseEntity<QuantityMeasurementDTO> divideQuantities(@Valid @RequestBody QuantityInputDTO input) {
        return ResponseEntity.ok(quantityMeasurementService.divide(input.getFirstQuantity(), input.getSecondQuantity()));
    }

    @GetMapping("/history")
    @Operation(summary = "Get all operation history")
    public ResponseEntity<List<QuantityMeasurementDTO>> getAllHistory() {
        return ResponseEntity.ok(quantityMeasurementService.getAllHistory());
    }

    @GetMapping("/history/operation/{operation}")
    @Operation(summary = "Get operation history by type")
    public ResponseEntity<List<QuantityMeasurementDTO>> getOperationHistory(@PathVariable String operation) {
        return ResponseEntity.ok(quantityMeasurementService.getOperationHistory(operation));
    }

    @GetMapping("/history/type/{type}")
    @Operation(summary = "Get operation history by measurement type")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistoryByMeasurementType(@PathVariable String type) {
        return ResponseEntity.ok(quantityMeasurementService.getHistoryByMeasurementType(type));
    }

    @GetMapping("/history/errored")
    @Operation(summary = "Get errored operation history")
    public ResponseEntity<List<QuantityMeasurementDTO>> getErroredHistory() {
        return ResponseEntity.ok(quantityMeasurementService.getErroredHistory());
    }

    @GetMapping("/count/{operation}")
    @Operation(summary = "Get total successful operation count by type")
    public ResponseEntity<Long> getOperationCount(@PathVariable String operation) {
        return ResponseEntity.ok(quantityMeasurementService.getOperationCount(operation));
    }
}

