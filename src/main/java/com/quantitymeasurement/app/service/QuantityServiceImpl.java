package com.quantitymeasurement.app.service;

import com.quantitymeasurement.app.entity.IMeasurable;
import com.quantitymeasurement.app.entity.Quantity;
import com.quantitymeasurement.app.entity.QuantityMeasurementEntity;
import com.quantitymeasurement.app.repository.QuantityMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityServiceImpl implements QuantityService {

    private QuantityMeasurementRepository repository;

    @Autowired
    public QuantityServiceImpl(QuantityMeasurementRepository quantityMeasurementRepository) {
        this.repository = quantityMeasurementRepository;
    }
    /* =========================================================
       ADDITION
       ========================================================= */

    @Override
    public <U extends IMeasurable> Quantity<U> add(Quantity<U> q1, Quantity<U> q2) {

        try {

            Quantity<U> result = q1.add(q2);

            repository.save(
                    new QuantityMeasurementEntity(
                            (Quantity<IMeasurable>) q1,
                            (Quantity<IMeasurable>) q2,
                            "ADD",
                            (Quantity<IMeasurable>) result
                    )
            );

            return result;

        } catch (Exception e) {

            logError(q1, q2, "ADD", e);

            throw e;
        }
    }

    @Override
    public <U extends IMeasurable> Quantity<U> add(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit) {

        try {

            Quantity<U> result = q1.add(q2, targetUnit);

            repository.save(
                    new QuantityMeasurementEntity(
                            (Quantity<IMeasurable>) q1,
                            (Quantity<IMeasurable>) q2,
                            "ADD",
                            (Quantity<IMeasurable>) result
                    )
            );

            return result;

        } catch (Exception e) {

            logError(q1, q2, "ADD", e);

            throw e;
        }
    }

    /* =========================================================
       SUBTRACTION
       ========================================================= */

    @Override
    public <U extends IMeasurable> Quantity<U> subtract(Quantity<U> q1, Quantity<U> q2) {

        try {

            Quantity<U> result = q1.subtract(q2);

            repository.save(
                    new QuantityMeasurementEntity(
                            (Quantity<IMeasurable>) q1,
                            (Quantity<IMeasurable>) q2,
                            "SUBTRACT",
                            (Quantity<IMeasurable>) result
                    )
            );

            return result;

        } catch (Exception e) {

            logError(q1, q2, "SUBTRACT", e);

            throw e;
        }
    }

    @Override
    public <U extends IMeasurable> Quantity<U> subtract(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit) {

        try {

            Quantity<U> result = q1.subtract(q2, targetUnit);

            repository.save(
                    new QuantityMeasurementEntity(
                            (Quantity<IMeasurable>) q1,
                            (Quantity<IMeasurable>) q2,
                            "SUBTRACT",
                            (Quantity<IMeasurable>) result
                    )
            );

            return result;

        } catch (Exception e) {

            logError(q1, q2, "SUBTRACT", e);

            throw e;
        }
    }

    /* =========================================================
       DIVISION
       ========================================================= */

    @Override
    public <U extends IMeasurable> double divide(Quantity<U> q1, Quantity<U> q2) {

        try {

            double result = q1.divide(q2);

            repository.save(
                    new QuantityMeasurementEntity(
                            (Quantity<IMeasurable>) q1,
                            (Quantity<IMeasurable>) q2,
                            "DIVIDE",
                            String.valueOf(result)
                    )
            );

            return result;

        } catch (Exception e) {

            logError(q1, q2, "DIVIDE", e);

            throw e;
        }
    }

    /* =========================================================
       CONVERSION
       ========================================================= */

    @Override
    public <U extends IMeasurable> Quantity<U> convert(
            Quantity<U> quantity,
            U targetUnit) {

        try {

            Quantity<U> result = quantity.convertTo(targetUnit);

            repository.save(
                    new QuantityMeasurementEntity(
                            (Quantity<IMeasurable>) quantity,
                            (Quantity<IMeasurable>) result,
                            "CONVERT",
                            result.toString()
                    )
            );

            return result;

        } catch (Exception e) {

            repository.save(
                    new QuantityMeasurementEntity(
                            (Quantity<IMeasurable>) quantity,
                            (Quantity<IMeasurable>) quantity,
                            "CONVERT",
                            e.getMessage(),
                            true
                    )
            );

            throw e;
        }
    }

    /* =========================================================
       COMPARISON
       ========================================================= */

    public boolean compare(Quantity<IMeasurable> q1, Quantity<IMeasurable> q2) {

        boolean result = q1.equals(q2);

        repository.save(
                new QuantityMeasurementEntity(
                        q1,
                        q2,
                        "COMPARE",
                        result ? "Equal" : "Not Equal"
                )
        );

        return result;
    }

    /* =========================================================
       HISTORY
       ========================================================= */

    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return repository.findAll();
    }

    /* =========================================================
       ERROR LOGGER
       ========================================================= */

    private <U extends IMeasurable> void logError(
            Quantity<U> q1,
            Quantity<U> q2,
            String operation,
            Exception e) {

        repository.save(
                new QuantityMeasurementEntity(
                        (Quantity<IMeasurable>) q1,
                        (Quantity<IMeasurable>) q2,
                        operation,
                        e.getMessage(),
                        true
                )
        );
    }
}