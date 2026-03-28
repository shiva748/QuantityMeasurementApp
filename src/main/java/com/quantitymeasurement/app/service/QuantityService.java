package com.quantitymeasurement.app.service;

import com.quantitymeasurement.app.entity.IMeasurable;
import com.quantitymeasurement.app.entity.Quantity;
import com.quantitymeasurement.app.entity.QuantityMeasurementEntity;

import java.util.List;

public interface QuantityService {

    <U extends IMeasurable> QuantityMeasurementEntity add(Long userId,Quantity<U> q1, Quantity<U> q2);

    <U extends IMeasurable> QuantityMeasurementEntity add(Long userId,Quantity<U> q1, Quantity<U> q2, U targetUnit);

    <U extends IMeasurable> QuantityMeasurementEntity subtract(Long userId,Quantity<U> q1, Quantity<U> q2);

    <U extends IMeasurable> QuantityMeasurementEntity subtract(Long userId,Quantity<U> q1, Quantity<U> q2, U targetUnit);

    <U extends IMeasurable> QuantityMeasurementEntity divide(Long userId,Quantity<U> q1, Quantity<U> q2);

    <U extends IMeasurable> QuantityMeasurementEntity convert(Long userId,Quantity<U> q, U targetUnit);

    <U extends IMeasurable> QuantityMeasurementEntity compare(Long userId,Quantity<U> q1, Quantity<U> q2);

    List<QuantityMeasurementEntity> getAllMeasurements(Long userId);

    Void deleteAllMeasurements(Long userId);
}