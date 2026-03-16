package com.quantitymeasurement.app.service;

import com.quantitymeasurement.app.entity.IMeasurable;
import com.quantitymeasurement.app.entity.Quantity;
import com.quantitymeasurement.app.entity.QuantityMeasurementEntity;
import com.quantitymeasurement.app.repository.IQuantityMeasurementRepository;

import java.util.List;

public class QuantityServiceImpl implements QuantityService {

    private final IQuantityMeasurementRepository repository;

    public QuantityServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    /* =========================================================
       ADDITION
       ========================================================= */

    @Override
    public <U extends IMeasurable> Quantity<U> add(Quantity<U> q1, Quantity<U> q2) {

        Quantity<U> result = q1.add(q2);

        saveResult(result);

        return result;
    }

    @Override
    public <U extends IMeasurable> Quantity<U> add(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit) {

        Quantity<U> result = q1.add(q2, targetUnit);

        saveResult(result);

        return result;
    }

    /* =========================================================
       SUBTRACTION
       ========================================================= */

    @Override
    public <U extends IMeasurable> Quantity<U> subtract(Quantity<U> q1, Quantity<U> q2) {

        Quantity<U> result = q1.subtract(q2);

        saveResult(result);

        return result;
    }

    @Override
    public <U extends IMeasurable> Quantity<U> subtract(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit) {

        Quantity<U> result = q1.subtract(q2, targetUnit);

        saveResult(result);

        return result;
    }

    /* =========================================================
       DIVISION
       ========================================================= */

    @Override
    public <U extends IMeasurable> double divide(Quantity<U> q1, Quantity<U> q2) {

        double result = q1.divide(q2);

        return result;
    }

    /* =========================================================
       CONVERSION
       ========================================================= */

    @Override
    public <U extends IMeasurable> Quantity<U> convert(
            Quantity<U> quantity,
            U targetUnit) {

        Quantity<U> result = quantity.convertTo(targetUnit);

        saveResult(result);

        return result;
    }

    /* =========================================================
       HISTORY ACCESS
       ========================================================= */

    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return repository.findAll();
    }

    /* =========================================================
       PRIVATE HELPER
       ========================================================= */

    private void saveResult(Quantity<?> quantity) {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        quantity.getValue(),
                        quantity.getUnit().getUnitName()
                );

        repository.save(entity);
    }
}