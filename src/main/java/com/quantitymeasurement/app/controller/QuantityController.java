package com.quantitymeasurement.app.controller;

import com.quantitymeasurement.app.dto.ApiResponse;
import com.quantitymeasurement.app.dto.QuantityRequestDto;
import com.quantitymeasurement.app.dto.TwoQuantityRequestDto;
import com.quantitymeasurement.app.entity.IMeasurable;
import com.quantitymeasurement.app.entity.Quantity;
import com.quantitymeasurement.app.entity.QuantityMeasurementEntity;
import com.quantitymeasurement.app.entity.units.*;
import com.quantitymeasurement.app.service.QuantityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quantities")
public class QuantityController {

    private final QuantityService quantityService;

    public QuantityController(QuantityService quantityService) {
        this.quantityService = quantityService;
    }

    /* =========================================================
       UNIT RESOLVER
       ========================================================= */

    private IMeasurable resolveUnit(String unit) {

        try { return LengthUnit.valueOf(unit); } catch (Exception ignored) {}
        try { return WeightUnit.valueOf(unit); } catch (Exception ignored) {}
        try { return VolumeUnit.valueOf(unit); } catch (Exception ignored) {}
        try { return TemperatureUnit.valueOf(unit); } catch (Exception ignored) {}

        throw new IllegalArgumentException("Unknown unit: " + unit);
    }

    @GetMapping
    public ResponseEntity<?> welcome(){
        return ResponseEntity.status(200).body("Welcome to the quantity measurement you are authorized");
    }

    /* =========================================================
       EQUALITY
       ========================================================= */

    @PostMapping("/equality")
    public ResponseEntity<ApiResponse<QuantityMeasurementEntity>> checkEquality(@RequestBody TwoQuantityRequestDto request, Authentication authentication) {

        QuantityRequestDto q1 = request.getQ1();
        QuantityRequestDto q2 = request.getQ2();

        Quantity<IMeasurable> left =
                new Quantity<>(q1.getValue(), resolveUnit(q1.getUnit()));

        Quantity<IMeasurable> right =
                new Quantity<>(q2.getValue(), resolveUnit(q2.getUnit()));

        return ResponseEntity.status(200).body(new ApiResponse<>(true, "Operation Successful", quantityService.compare((Long) authentication.getPrincipal(),left, right)));
    }

    /* =========================================================
       CONVERSION
       ========================================================= */

    @PostMapping("/convert")
    public ResponseEntity<ApiResponse<QuantityMeasurementEntity>> convert(Authentication authentication,
            @RequestBody QuantityRequestDto req,
            @RequestParam String targetUnit) {

        Quantity<IMeasurable> quantity =
                new Quantity<>(req.getValue(), resolveUnit(req.getUnit()));

        return ResponseEntity.status(200).body(new ApiResponse<>(true, "Operation Successful", quantityService.convert((Long) authentication.getPrincipal(),quantity, resolveUnit(targetUnit))));
    }

    /* =========================================================
       ADDITION
       ========================================================= */

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<QuantityMeasurementEntity>> add(Authentication authentication,
            @RequestBody TwoQuantityRequestDto request,
            @RequestParam(required = false) String targetUnit) {

        QuantityRequestDto q1 = request.getQ1();
        QuantityRequestDto q2 = request.getQ2();

        Quantity<IMeasurable> left =
                new Quantity<>(q1.getValue(), resolveUnit(q1.getUnit()));

        Quantity<IMeasurable> right =
                new Quantity<>(q2.getValue(), resolveUnit(q2.getUnit()));

        return ResponseEntity.status(200).body(new ApiResponse<>(true, "Operation Successful", quantityService.add((Long) authentication.getPrincipal(),left, right, resolveUnit(targetUnit))));
    }

    /* =========================================================
       SUBTRACTION
       ========================================================= */

    @PostMapping("/subtract")
    public ResponseEntity<ApiResponse<QuantityMeasurementEntity>> subtract(Authentication authentication,
            @RequestBody TwoQuantityRequestDto request,
            @RequestParam(required = false) String targetUnit) {

        QuantityRequestDto q1 = request.getQ1();
        QuantityRequestDto q2 = request.getQ2();

        Quantity<IMeasurable> left =
                new Quantity<>(q1.getValue(), resolveUnit(q1.getUnit()));

        Quantity<IMeasurable> right =
                new Quantity<>(q2.getValue(), resolveUnit(q2.getUnit()));

        return ResponseEntity.status(200).body(new ApiResponse<>(true, "Operation Successful", quantityService.subtract((Long) authentication.getPrincipal(),left, right, resolveUnit(targetUnit))));
    }

    /* =========================================================
       DIVISION
       ========================================================= */

    @PostMapping("/divide")
    public ResponseEntity<ApiResponse<QuantityMeasurementEntity>> divide(Authentication authentication,@RequestBody TwoQuantityRequestDto request) {

        QuantityRequestDto q1 = request.getQ1();
        QuantityRequestDto q2 = request.getQ2();

        Quantity<IMeasurable> left =
                new Quantity<>(q1.getValue(), resolveUnit(q1.getUnit()));

        Quantity<IMeasurable> right =
                new Quantity<>(q2.getValue(), resolveUnit(q2.getUnit()));

        return ResponseEntity.status(200).body(new ApiResponse<>(true, "Operation Successful", quantityService.divide((Long) authentication.getPrincipal(), left, right)));
    }

    @GetMapping("/history")
    public ResponseEntity<ApiResponse<List<QuantityMeasurementEntity>>> getHistory(Authentication authentication){
        return ResponseEntity.status(200).body(new ApiResponse<>(true, "History fetched successfully.", quantityService.getAllMeasurements((Long) authentication.getPrincipal())));
    }

    @DeleteMapping("/history")
    public ResponseEntity<ApiResponse<List<?>>> deleteHistory(Authentication authentication){
        int recordsDeleted = quantityService.deleteAllMeasurements((Long) authentication.getPrincipal());
        return ResponseEntity.status(200).body(new ApiResponse<>(true, recordsDeleted+ " record's deleted successfully."));
    }
}