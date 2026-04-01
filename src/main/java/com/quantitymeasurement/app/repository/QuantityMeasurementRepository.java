package com.quantitymeasurement.app.repository;

import com.quantitymeasurement.app.entity.QuantityMeasurementEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {
    List<QuantityMeasurementEntity> findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM QuantityMeasurementEntity q WHERE q.userId = :userId")
    int deleteByUserId(@Param("userId") Long userId);
}