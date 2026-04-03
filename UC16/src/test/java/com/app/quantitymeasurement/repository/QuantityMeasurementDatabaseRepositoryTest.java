package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import org.junit.*;
import java.util.List;

public class QuantityMeasurementDatabaseRepositoryTest {
    private QuantityMeasurementDatabaseRepository repository;

    @BeforeClass
    public static void setUpDatabase() {
        // Set test database environment
        System.setProperty("app.env", "test");
    }

    @Before
    public void setup() {
        repository = QuantityMeasurementDatabaseRepository.getInstance();
        repository.deleteAll(); // Clean database before each test
    }

    @After
    public void tearDown() {
        repository.releaseResources();
    }

    @Test
    public void testSaveEntity() {
        QuantityMeasurementEntity entity = createTestEntity(1.0, "FEET", "LENGTH");
        String id = repository.save(entity);
        Assert.assertNotNull(id);
        Assert.assertEquals(1, repository.getTotalCount());
    }

    @Test
    public void testGetAllMeasurements() {
        repository.save(createTestEntity(1.0, "FEET", "LENGTH"));
        repository.save(createTestEntity(2.0, "INCHES", "LENGTH"));
        
        List<QuantityMeasurementEntity> measurements = repository.getAllMeasurements();
        Assert.assertEquals(2, measurements.size());
    }

    @Test
    public void testGetMeasurementsByOperation() {
        QuantityMeasurementEntity e1 = createTestEntity(1.0, "FEET", "LENGTH");
        e1.setOperation("COMPARE");
        repository.save(e1);

        QuantityMeasurementEntity e2 = createTestEntity(2.0, "FEET", "LENGTH");
        e2.setOperation("ADD");
        repository.save(e2);

        List<QuantityMeasurementEntity> compareOps = repository.getMeasurementsByOperation("COMPARE");
        Assert.assertEquals(1, compareOps.size());
        Assert.assertEquals("COMPARE", compareOps.get(0).getOperation());
    }

    @Test
    public void testGetMeasurementsByType() {
        repository.save(createTestEntity(1.0, "FEET", "LENGTH"));
        repository.save(createTestEntity(1.0, "KG", "WEIGHT"));

        List<QuantityMeasurementEntity> lengthMeasurements = repository.getMeasurementsByType("LENGTH");
        Assert.assertEquals(1, lengthMeasurements.size());
        Assert.assertEquals("LENGTH", lengthMeasurements.get(0).getThisMeasurementType());
    }

    @Test
    public void testGetTotalCount() {
        repository.save(createTestEntity(1.0, "FEET", "LENGTH"));
        Assert.assertEquals(1, repository.getTotalCount());
    }

    @Test
    public void testDeleteAll() {
        repository.save(createTestEntity(1.0, "FEET", "LENGTH"));
        repository.deleteAll();
        Assert.assertEquals(0, repository.getTotalCount());
    }

    private QuantityMeasurementEntity createTestEntity(double value, String unit, String type) {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setThisValue(value);
        entity.setThisUnit(unit);
        entity.setThisMeasurementType(type);
        entity.setOperation("TEST");
        entity.setResultString("TestResult");
        return entity;
    }
}
