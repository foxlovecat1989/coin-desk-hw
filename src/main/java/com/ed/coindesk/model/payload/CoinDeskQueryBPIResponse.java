package com.ed.coindesk.model.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinDeskQueryBPIResponse {
    public Time time;
    public String disclaimer;
    public String chartName;
    public BPI bpi;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Time {
        @JsonProperty("updated")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM d, yyyy HH:mm:ss z", timezone = "UTC")
        public LocalDateTime updatedUTC;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
        public ZonedDateTime updatedISO;

        @JsonProperty("updateduk")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM d, yyyy 'at' HH:mm z", timezone = "GMT")
        public LocalDateTime updatedTimeUK;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BPI {
        public Currency USD;
        public Currency GBP;
        public Currency EUR;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Currency {
            public String code;
            public String rate;
            public String symbol;
            public String description;
            @JsonProperty("rate_float")
            public Float rateFloat;
        }
    }
}

