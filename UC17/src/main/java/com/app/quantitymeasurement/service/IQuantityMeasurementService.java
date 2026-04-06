package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;
import java.util.List;

public interface IQuantityMeasurementService {
    QuantityMeasurementDTO compare(QuantityDTO q1, QuantityDTO q2);
    QuantityMeasurementDTO convert(QuantityDTO q1, String targetUnit);
    QuantityMeasurementDTO add(QuantityDTO q1, QuantityDTO q2, String targetUnit);
    QuantityMeasurementDTO subtract(QuantityDTO q1, QuantityDTO q2, String targetUnit);
    QuantityMeasurementDTO divide(QuantityDTO q1, QuantityDTO q2);
    
    // History and Stats API
    List<QuantityMeasurementDTO> getAllHistory();
    List<QuantityMeasurementDTO> getOperationHistory(String operation);
    List<QuantityMeasurementDTO> getHistoryByMeasurementType(String measurementType);
    List<QuantityMeasurementDTO> getErroredHistory();
    long getOperationCount(String operation);
}

