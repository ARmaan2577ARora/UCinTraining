package com.app.quantitymeasurement;

import com.app.quantitymeasurement.controller.QuantityMeasurementController;
import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class QuantityMeasurementIntegrationTest {
    private QuantityMeasurementApp app;
    private QuantityMeasurementController controller;
    private IQuantityMeasurementRepository repository;

    @Before
    public void setUp() {
        // Set test environment if needed
        System.setProperty("app.env", "test");
        app = QuantityMeasurementApp.getInstance();
        controller = app.getController();
        repository = app.getRepository();
        repository.deleteAll();
    }

    @After
    public void tearDown() {
        repository.releaseResources();
    }

    @Test
    public void testComparisonIntegration() {
        QuantityDTO q1 = new QuantityDTO(1.0, QuantityDTO.LengthUnitDTO.FEET);
        QuantityDTO q2 = new QuantityDTO(12.0, QuantityDTO.LengthUnitDTO.INCHES);
        
        boolean result = controller.performComparison(q1, q2);
        Assert.assertTrue(result);
        
        List<QuantityMeasurementEntity> history = repository.getAllMeasurements();
        Assert.assertFalse(history.isEmpty());
        
        boolean found = false;
        for (QuantityMeasurementEntity e : history) {
            if ("COMPARE".equals(e.getOperation())) {
                found = true;
                break;
            }
        }
        Assert.assertTrue("COMPARE operation not found in history", found);
    }

    @Test
    public void testAdditionIntegration() {
        QuantityDTO q1 = new QuantityDTO(2.0, QuantityDTO.LengthUnitDTO.FEET);
        QuantityDTO q2 = new QuantityDTO(12.0, QuantityDTO.LengthUnitDTO.INCHES);
        
        QuantityDTO result = controller.performAddition(q1, q2, q1); // Target FEET
        Assert.assertEquals(3.0, result.getValue(), 0.01);
        
        List<QuantityMeasurementEntity> history = repository.getAllMeasurements();
        boolean found = false;
        for (QuantityMeasurementEntity e : history) {
            if ("ADD".equals(e.getOperation()) && Math.abs(e.getResultValue() - 3.0) < 0.01) {
                found = true;
                break;
            }
        }
        Assert.assertTrue("ADD operation with result 3.0 not found in history", found);
    }
}
