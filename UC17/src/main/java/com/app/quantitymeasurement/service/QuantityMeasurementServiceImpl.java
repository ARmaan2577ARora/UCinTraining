package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.model.*;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.app.quantitymeasurement.unit.*;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Logger;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {
    private static final Logger logger = Logger.getLogger(QuantityMeasurementServiceImpl.class.getName());

    private final QuantityMeasurementRepository repository;

    @Autowired
    public QuantityMeasurementServiceImpl(QuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public QuantityMeasurementDTO compare(QuantityDTO q1, QuantityDTO q2) {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        try {
            QuantityModel<IMeasurable> m1 = convertDtoToModel(q1);
            QuantityModel<IMeasurable> m2 = convertDtoToModel(q2);
            validateSameType(m1, m2);

            boolean result = Double.compare(m1.getUnit().toBase(m1.getValue()),
                    m2.getUnit().toBase(m2.getValue())) == 0;

            populateBaseEntity(entity, m1, m2, "compare");
            entity.setResultString(String.valueOf(result));
            entity.setResultValue(result ? 1.0 : 0.0);
            entity.setError(false);
        } catch (Exception e) {
            handleException(entity, q1, q2, "compare", e);
        }
        return QuantityMeasurementDTO.fromEntity(repository.save(entity));
    }

    @Override
    public QuantityMeasurementDTO convert(QuantityDTO q1, String targetUnitName) {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        try {
            QuantityModel<IMeasurable> m1 = convertDtoToModel(q1);
            IMeasurable targetUnit = findUnitByName(targetUnitName, m1.getUnit().getMeasurementType());
            
            double baseValue = m1.getUnit().toBase(m1.getValue());
            double resValue = targetUnit.fromBase(baseValue);

            populateBaseEntity(entity, m1, null, "convert");
            entity.setThatUnit(targetUnitName);
            entity.setResultValue(resValue);
            entity.setResultUnit(targetUnitName);
            entity.setResultMeasurementType(m1.getUnit().getMeasurementType());
            entity.setResultString(String.valueOf(resValue));
            entity.setError(false);
        } catch (Exception e) {
            handleException(entity, q1, null, "convert", e);
        }
        return QuantityMeasurementDTO.fromEntity(repository.save(entity));
    }

    @Override
    public QuantityMeasurementDTO add(QuantityDTO q1, QuantityDTO q2, String targetUnitName) {
        return performArithmetic(q1, q2, targetUnitName, "add");
    }

    @Override
    public QuantityMeasurementDTO subtract(QuantityDTO q1, QuantityDTO q2, String targetUnitName) {
        return performArithmetic(q1, q2, targetUnitName, "subtract");
    }

    @Override
    public QuantityMeasurementDTO divide(QuantityDTO q1, QuantityDTO q2) {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        try {
            QuantityModel<IMeasurable> m1 = convertDtoToModel(q1);
            QuantityModel<IMeasurable> m2 = convertDtoToModel(q2);
            validateSameType(m1, m2);

            double base1 = m1.getUnit().toBase(m1.getValue());
            double base2 = m2.getUnit().toBase(m2.getValue());

            if (base2 == 0) throw new ArithmeticException("Divide by zero");

            double result = base1 / base2;

            populateBaseEntity(entity, m1, m2, "divide");
            entity.setResultValue(result);
            entity.setResultString(String.valueOf(result));
            entity.setError(false);
        } catch (Exception e) {
            handleException(entity, q1, q2, "divide", e);
        }
        return QuantityMeasurementDTO.fromEntity(repository.save(entity));
    }

    @Override
    public List<QuantityMeasurementDTO> getAllHistory() {
        return QuantityMeasurementDTO.fromEntityList(repository.findAll());
    }

    @Override
    public List<QuantityMeasurementDTO> getOperationHistory(String operation) {
        return QuantityMeasurementDTO.fromEntityList(repository.findByOperation(operation.toLowerCase()));
    }

    @Override
    public List<QuantityMeasurementDTO> getHistoryByMeasurementType(String measurementType) {
        return QuantityMeasurementDTO.fromEntityList(repository.findByThisMeasurementType(measurementType));
    }

    @Override
    public List<QuantityMeasurementDTO> getErroredHistory() {
        return QuantityMeasurementDTO.fromEntityList(repository.findByIsErrorTrue());
    }

    @Override
    public long getOperationCount(String operation) {
        return repository.countByOperationAndIsErrorFalse(operation.toLowerCase());
    }

    private QuantityMeasurementDTO performArithmetic(QuantityDTO q1, QuantityDTO q2, String targetUnitName, String op) {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        try {
            QuantityModel<IMeasurable> m1 = convertDtoToModel(q1);
            QuantityModel<IMeasurable> m2 = convertDtoToModel(q2);
            validateSameType(m1, m2);

            IMeasurable targetUnit = findUnitByName(targetUnitName, m1.getUnit().getMeasurementType());

            m1.getUnit().validateOperationSupport(op);
            m2.getUnit().validateOperationSupport(op);

            double v1 = m1.getUnit().toBase(m1.getValue());
            double v2 = m2.getUnit().toBase(m2.getValue());

            double resBase = op.equals("add") ? v1 + v2 : v1 - v2;
            double resValue = targetUnit.fromBase(resBase);

            populateBaseEntity(entity, m1, m2, op);
            entity.setResultValue(resValue);
            entity.setResultUnit(targetUnitName);
            entity.setResultMeasurementType(m1.getUnit().getMeasurementType());
            entity.setResultString(String.valueOf(resValue));
            entity.setError(false);
        } catch (Exception e) {
            handleException(entity, q1, q2, op, e);
        }
        return QuantityMeasurementDTO.fromEntity(repository.save(entity));
    }

    private void populateBaseEntity(QuantityMeasurementEntity entity, QuantityModel<IMeasurable> m1, QuantityModel<IMeasurable> m2, String op) {
        entity.setThisValue(m1.getValue());
        entity.setThisUnit(m1.getUnit().getUnitName());
        entity.setThisMeasurementType(m1.getUnit().getMeasurementType());
        entity.setOperation(op);
        if (m2 != null) {
            entity.setThatValue(m2.getValue());
            entity.setThatUnit(m2.getUnit().getUnitName());
            entity.setThatMeasurementType(m2.getUnit().getMeasurementType());
        }
    }

    private void handleException(QuantityMeasurementEntity entity, QuantityDTO q1, QuantityDTO q2, String op, Exception e) {
        entity.setOperation(op);
        if (q1 != null) {
            entity.setThisValue(q1.getValue());
            entity.setThisUnit(q1.getUnit());
            entity.setThisMeasurementType(q1.getMeasurementType());
        }
        if (q2 != null) {
            entity.setThatValue(q2.getValue());
            entity.setThatUnit(q2.getUnit());
            entity.setThatMeasurementType(q2.getMeasurementType());
        }
        entity.setError(true);
        entity.setErrorMessage(e.getMessage());
        logger.severe("Error in " + op + ": " + e.getMessage());
    }

    private QuantityModel<IMeasurable> convertDtoToModel(QuantityDTO dto) {
        if (dto == null) return null;
        IMeasurable unit = findUnitByName(dto.getUnit(), dto.getMeasurementType());
        return new QuantityModel<>(dto.getValue(), unit);
    }

    private IMeasurable findUnitByName(String unitName, String measurementType) {
        try {
            switch (measurementType) {
                case "LengthUnit": return LengthUnit.valueOf(unitName);
                case "WeightUnit": return WeightUnit.valueOf(unitName);
                case "VolumeUnit": return VolumeUnit.valueOf(unitName);
                case "TemperatureUnit": return TemperatureUnit.valueOf(unitName);
                default: throw new QuantityMeasurementException("Invalid measurement type: " + measurementType);
            }
        } catch (IllegalArgumentException e) {
            throw new QuantityMeasurementException("Unit must be valid for the specified measurement type");
        }
    }

    private void validateSameType(QuantityModel<IMeasurable> m1, QuantityModel<IMeasurable> m2) {
        if (m1 == null || m2 == null) return;
        if (!m1.getUnit().getMeasurementType().equals(m2.getUnit().getMeasurementType())) {
            throw new QuantityMeasurementException("Incompatible measurement categories: " +
                    m1.getUnit().getMeasurementType() + " and " + m2.getUnit().getMeasurementType());
        }
    }
}


