package com.quantitymeasurement.app;

import com.quantitymeasurement.app.controller.QuantityController;
import com.quantitymeasurement.app.dto.QuantityRequestDto;
import com.quantitymeasurement.app.service.QuantityServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println("===== Quantity Measurement Demonstration =====");

        QuantityServiceImpl service = new QuantityServiceImpl();
        QuantityController controller = new QuantityController(service);

        /* =========================================================
           LENGTH OPERATIONS
           ========================================================= */

        System.out.println("\n----- LENGTH OPERATIONS -----");

        System.out.println(controller.checkEquality(
                new QuantityRequestDto(1.0, "FEET"),
                new QuantityRequestDto(12.0, "INCH")
        ));

        System.out.println(controller.convert(
                new QuantityRequestDto(1.0, "FEET"),
                "INCH"
        ));

        System.out.println(controller.add(
                new QuantityRequestDto(1.0, "FEET"),
                new QuantityRequestDto(12.0, "INCH"),
                "FEET"
        ));


        /* =========================================================
           WEIGHT OPERATIONS
           ========================================================= */

        System.out.println("\n----- WEIGHT OPERATIONS -----");

        System.out.println(controller.checkEquality(
                new QuantityRequestDto(1.0, "KILOGRAM"),
                new QuantityRequestDto(1000.0, "GRAM")
        ));

        System.out.println(controller.convert(
                new QuantityRequestDto(1.0, "KILOGRAM"),
                "GRAM"
        ));

        System.out.println(controller.add(
                new QuantityRequestDto(1.0, "KILOGRAM"),
                new QuantityRequestDto(1000.0, "GRAM"),
                "KILOGRAM"
        ));


        /* =========================================================
           VOLUME OPERATIONS
           ========================================================= */

        System.out.println("\n----- VOLUME OPERATIONS -----");

        System.out.println(controller.checkEquality(
                new QuantityRequestDto(1.0, "LITRE"),
                new QuantityRequestDto(1000.0, "MILLILITRE")
        ));

        System.out.println(controller.convert(
                new QuantityRequestDto(1.0, "LITRE"),
                "MILLILITRE"
        ));

        System.out.println(controller.add(
                new QuantityRequestDto(1.0, "LITRE"),
                new QuantityRequestDto(1000.0, "MILLILITRE"),
                "LITRE"
        ));


        /* =========================================================
           SUBTRACTION
           ========================================================= */

        System.out.println("\n----- SUBTRACTION -----");

        System.out.println(controller.subtract(
                new QuantityRequestDto(10.0, "FEET"),
                new QuantityRequestDto(6.0, "INCH"),
                "FEET"
        ));


        /* =========================================================
           DIVISION
           ========================================================= */

        System.out.println("\n----- DIVISION -----");

        System.out.println(controller.divide(
                new QuantityRequestDto(24.0, "INCH"),
                new QuantityRequestDto(2.0, "FEET")
        ));


        /* =========================================================
           TEMPERATURE
           ========================================================= */

        System.out.println("\n----- TEMPERATURE OPERATIONS -----");

        System.out.println(controller.checkEquality(
                new QuantityRequestDto(0.0, "CELSIUS"),
                new QuantityRequestDto(32.0, "FAHRENHEIT")
        ));

        System.out.println(controller.convert(
                new QuantityRequestDto(100.0, "CELSIUS"),
                "FAHRENHEIT"
        ));

        try {

            System.out.println(controller.add(
                    new QuantityRequestDto(100.0, "CELSIUS"),
                    new QuantityRequestDto(50.0, "CELSIUS")
            ));

        } catch (UnsupportedOperationException e) {

            System.out.println("Temperature addition blocked → " + e.getMessage());
        }
    }
}