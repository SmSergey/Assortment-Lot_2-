package com.app.lot_2.web.advice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonErrorResponse {
    private int status;
    private String message;
}

