package com.ed.coindesk.model.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class BPIResponse {
    private final String updatedTime;
    private final List<Currency> currencies;

    @Data
    @AllArgsConstructor
    @Builder
    public static class Currency {
        public String code;
        public Float rate;
    }
}
