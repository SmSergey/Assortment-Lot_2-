package com.app.lot_2.domain.check.controller.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateCheckDto {
    @Positive
    private BigDecimal sum;

    private String cardNumber;

    private List<PositionDto> positionList;
}
