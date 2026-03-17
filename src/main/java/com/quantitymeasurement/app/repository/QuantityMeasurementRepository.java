package com.quantitymeasurement.app.repository;

import com.quantitymeasurement.app.entity.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {
}