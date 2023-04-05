package com.app.lot_2.domain.check.controller.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class CreateCheckDto {
    private UUID checkId = UUID.randomUUID();

    @Positive
    private BigDecimal sum;

    private String cardNumber;

    private List<PositionDto> positionList;
}
