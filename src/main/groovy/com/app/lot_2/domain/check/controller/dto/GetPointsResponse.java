package com.app.lot_2.domain.check.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class GetPointsResponse {
    private BigDecimal points;
    private Date lastUpdate;
}
