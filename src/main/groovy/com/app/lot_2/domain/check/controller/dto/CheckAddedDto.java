package com.app.lot_2.domain.check.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CheckAddedDto {
    private UUID checkId;
}
