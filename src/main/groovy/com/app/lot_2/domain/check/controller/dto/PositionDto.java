package com.app.lot_2.domain.check.controller.dto;


import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PositionDto {

    @Positive
    private long sum;
}
