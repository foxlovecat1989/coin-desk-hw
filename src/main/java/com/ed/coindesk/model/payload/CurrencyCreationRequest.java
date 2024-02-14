package com.ed.coindesk.model.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CurrencyCreationRequest {
    private final String code;
    private final String name;
}
