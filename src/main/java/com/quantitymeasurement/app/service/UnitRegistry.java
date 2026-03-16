
package com.quantitymeasurement.app.service;

import com.quantitymeasurement.app.entity.units.LengthUnit;
import com.quantitymeasurement.app.entity.units.WeightUnit;
import com.quantitymeasurement.app.entity.units.VolumeUnit;
import com.quantitymeasurement.app.entity.units.TemperatureUnit;

import java.util.Optional;

public class UnitRegistry {

    public Optional<LengthUnit> findLength(String name) {
        try { return Optional.of(LengthUnit.valueOf(name)); } catch(Exception e){ return Optional.empty(); }
    }

    public Optional<WeightUnit> findWeight(String name) {
        try { return Optional.of(WeightUnit.valueOf(name)); } catch(Exception e){ return Optional.empty(); }
    }

    public Optional<VolumeUnit> findVolume(String name) {
        try { return Optional.of(VolumeUnit.valueOf(name)); } catch(Exception e){ return Optional.empty(); }
    }

    public Optional<TemperatureUnit> findTemperature(String name) {
        try { return Optional.of(TemperatureUnit.valueOf(name)); } catch(Exception e){ return Optional.empty(); }
    }
}
