
package com.quantitymeasurement.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuantityResponseDto {
    private final String display;
    private final double numeric;
}
